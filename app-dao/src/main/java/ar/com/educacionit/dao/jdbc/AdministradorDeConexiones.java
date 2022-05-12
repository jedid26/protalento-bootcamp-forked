package ar.com.educacionit.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

import ar.com.educacionit.dao.exceptions.GenericException;

public class AdministradorDeConexiones {

	public static Connection obtenerConexion() throws GenericException {
		
		String url = System.getenv("SPRING_DATABASE_URL"); //"jdbc:postgresql://ec2-3-225-79-57.compute-1.amazonaws.com:5432/d24uecr8r2ocj3";
		String user = System.getenv("SPRING_DATASOURCE_USERNAME"); //"pgpsajdnasvqrk";
		String password = System.getenv("SPRING_DATASOURCE_PASSWORD"); //"92030ba1bf51c596d40e440c2711ba3f346993f2e6d2274f4a23aa0d9d5b5c92";
		String diverName  = System.getenv("SPRING_DATASOURCE_DRIVER"); //"org.postgresql.Driver";
		
		try {
			//com.mysql.cj.jdbc.Driver.class.newInstance();
			Class.forName(diverName);
			Connection connection = DriverManager.getConnection(url, user, password);
			return connection;
		} catch (Exception  e) {
			throw new GenericException("Error oteniendo conexion: " + e.getMessage(),e);
		} 
	}
	
	public static void main(String[] args) {
		
		try (Connection con = AdministradorDeConexiones.obtenerConexion();) {			
			System.out.println("Conexion obtenida");
		}catch (Exception e) {			
			e.printStackTrace();
		}
	}
}
