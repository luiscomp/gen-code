package dao;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;



public class FabricaDAO {

	private static String urlMYSQL = "jdbc:mysql://10.10.1.121/menueletronico";

	private static String nomeUsuarioMySQL = "root";

	private static String senhaUsuarioMySQL = "luiz";

	private static InitialContext ctx;

	private static Context envCtx;

	private static DataSource ds;

	private static boolean datasourceDown = false;

	private static boolean isDeamon = false;

	public static boolean isDeamon() {
		return isDeamon;
	}

	public static void setDeamon(boolean isDeamon) {
		FabricaDAO.isDeamon = isDeamon;
	}

	public static Connection criarConexao() throws Exception {
		if (!isDeamon && !datasourceDown) {
			if (ctx == null) {
				try {
					ctx = new InitialContext();
					envCtx = (Context) ctx.lookup("java:comp/env");
					ds = (DataSource) envCtx.lookup("jdbc/GenNodeDB");
				} catch (NamingException e) {
					e.printStackTrace();
					datasourceDown = true;
				}
			}
		}

		if (isDeamon()) {
			return criarConexaoSimples();
		}

		return ds.getConnection();
	}

	public static Connection criarConexaoSimples() throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			return DriverManager.getConnection(urlMYSQL, nomeUsuarioMySQL, senhaUsuarioMySQL);
		} catch (Exception e) {
			throw e;
		}
	}

	public static void setarParametrosConexao(String pUrlMysql, String pNomeUsuario, String pSenhaUsuario) {
		urlMYSQL = pUrlMysql;
		nomeUsuarioMySQL = pNomeUsuario;
		senhaUsuarioMySQL = pSenhaUsuario;
		isDeamon = true;
	}

}