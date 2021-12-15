package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BaseDAO {

	protected Connection conexao;

	protected PreparedStatement st;

	private int linhasAfetadas;

	protected void iniciarTransacao() throws Exception {
		if (conexao == null) {
			conexao = FabricaDAO.criarConexao();
		}
		conexao.setAutoCommit(false);
	}
	
	protected boolean verificarSeTransacaoAberta() throws SQLException {
		if(conexao != null) {
			return conexao.getAutoCommit();
		}
		return false;
	}

	protected void finalizarTransacao() throws SQLException {
		conexao.commit();
		conexao.setAutoCommit(true);
	}
	
	protected void desfazerTransacao() throws SQLException {
		conexao.rollback();
		conexao.setAutoCommit(true);
		fecharConexao();
	}
	
	protected void prepararDAO(StringBuffer instrucaoSQL) throws Exception {
		prepararDAO(instrucaoSQL.toString());
	}

	protected void prepararDAO(String instrucaoSQL) throws Exception {
		if (conexao == null) {
			conexao = FabricaDAO.criarConexao();
		}
		st = conexao.prepareStatement(instrucaoSQL, Statement.RETURN_GENERATED_KEYS);
	}

	protected int atualizar() throws SQLException {
		linhasAfetadas = st.executeUpdate();
		return linhasAfetadas;
	}
	
	protected void inserirComandoNoLote(String query) throws SQLException{
		st.addBatch(query);
	}
	
	protected void setarArrayNoLote(ArrayList<String> array  ) throws SQLException {
		int indice = 1;
		for (String string : array) {
			st.setString(indice++, string);
		}
	}
	
	protected void atualizarLote() throws SQLException {
		st.executeBatch();
	}

	protected int atualizarERetornarID() throws SQLException {
		linhasAfetadas = st.executeUpdate();
		ResultSet rs = st.getGeneratedKeys();
		
		int valorChave = 0;
		
		while (rs.next()) {
			valorChave = rs.getInt(1);
		}
		
		rs.close();
		rs = null;
		
		return valorChave;
	}
	
	protected ResultSet listar() throws SQLException {
		return st.executeQuery();
	}

	protected void fecharConexao() throws SQLException {
		if(conexao != null && conexao.getAutoCommit()) {
			try {
				if (st != null) {
					st.close();
				}
				if (conexao != null && !conexao.isClosed()) {
					conexao.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				st = null;
				conexao = null;
			}	
		} else if(conexao != null && !conexao.getAutoCommit()) {
			try {
				if (st != null) {
					st.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				st = null;
			}	
		}
	}
}