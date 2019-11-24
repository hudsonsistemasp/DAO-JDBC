package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DB {

	//Criar os métodos statics para conectar e desconectar no banco
	private static Connection conn = null;
	
	//Configuração da conexão, apontado tudo para o arquivo db.properties e guardando dentro de um objeto Properties
	private static Properties loadConfigurations() {
		try (FileInputStream fis = new FileInputStream("db.properties")){
			
			Properties pro = new Properties();
			pro.load(fis);
			return pro;
		} 
		catch (IOException e) {
			throw new DbException(e.getMessage());
		}	
	}
	
	//Abrindo a conexão com o MySQL
	private static Connection getConnection() {
		if(conn == null) {
			Properties pro = loadConfigurations();
			String url = pro.getProperty("dburl");//Vem do arquivo db.properties. Colocar a propriedade do jeito que está lá
			try {
				conn = DriverManager.getConnection(url, pro);
				System.out.println("Connection Opened");
				return conn;
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
		return null;
	}
	
	//Fechando conexão com o banco
	private static Connection closeConnection() {
		if(conn != null) {
			try {
				conn.close();
				System.out.println("Connection Closed!");
			}catch(SQLException e){
				throw new DbException(e.getMessage());
			}
		}
		return null;
	}
	
	
}
