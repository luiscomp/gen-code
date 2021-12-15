package dao;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;

import enums.PadraoColuna;
import enums.TipoData;
import singletons.ProjSingleton;
import ui.interfaces.OnProgress;
import vo.ColunaVO;
import vo.RelacionamentoExternoVO;
import vo.RelacionamentoVO;
import vo.TabelaVO;

public class TabelaDAO extends BaseDAO {

	private static final String SELECT_TABELAS = "SELECT TABLE_NAME AS 'nome' FROM INFORMATION_SCHEMA.TABLES where TABLE_SCHEMA = ? ";
	private static final String SELECT_COLUNAS = "SELECT " + 
			"    ORDINAL_POSITION AS 'ordem', " + 
			"    COLUMN_KEY AS 'chave', " + 
			"    COLUMN_NAME AS 'nome', " + 
			"    UPPER(DATA_TYPE) AS 'tipo', " + 
			"    COLUMN_TYPE AS 'tipoColuna', " +
			"    CHARACTER_MAXIMUM_LENGTH AS 'tamanho', " + 
			"    IS_NULLABLE AS 'nulo', " + 
			"    NUMERIC_PRECISION AS 'base', " + 
			"    NUMERIC_SCALE AS 'precisao', " + 
			"    UPPER(EXTRA) AS 'padrao', " +
			"    COLUMN_DEFAULT AS 'valorDefault' " +
			"FROM " + 
			"    INFORMATION_SCHEMA.COLUMNS " + 
			"WHERE " + 
			"    TABLE_SCHEMA = ? " + 
			"        AND TABLE_NAME = ? ";
	
	private static final String SELECT_RELACIONAMENTOS = "SELECT " + 
			"    i.TABLE_NAME AS 'tabela', " + 
			"    k.REFERENCED_TABLE_NAME AS 'tabelaReferencia', " + 
			"    k.COLUMN_NAME  AS 'idReferencia' " + 
			"FROM " + 
			"    information_schema.TABLE_CONSTRAINTS i " + 
			"        LEFT JOIN " + 
			"    information_schema.KEY_COLUMN_USAGE k ON i.CONSTRAINT_NAME = k.CONSTRAINT_NAME AND k.REFERENCED_TABLE_SCHEMA = i.CONSTRAINT_SCHEMA " + 
			"WHERE " + 
			"    i.CONSTRAINT_TYPE = 'FOREIGN KEY' " + 
			"        AND i.TABLE_SCHEMA = ? " + 
			"        AND i.TABLE_NAME = ? ";
	
	private static final String SELECT_RELACIONAMENTOS_EXTERNOS = "SELECT " + 
			"	REFERENCED_TABLE_NAME AS 'tabela', " + 
			"	TABLE_NAME AS 'tabelaExterna', " + 
			"  	COLUMN_NAME AS 'colunaReferencia', " + 
			"  	REFERENCED_COLUMN_NAME AS 'colunaReferenciada' " + 
			"FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE " + 
			"WHERE " + 
			"    TABLE_SCHEMA = ? " + 
			"			AND REFERENCED_TABLE_NAME = ? ";

	public ArrayList<TabelaVO> listarBanco(OnProgress callback) throws Exception {
		ArrayList<TabelaVO> listaTabelas = listarTabelas();
		
		callback.onProgress(0, listaTabelas.size());
		int count = 1;
		for(TabelaVO tabela : listaTabelas) {
			tabela.setColunas(recuperarColunas(tabela));
			tabela.setRelacionamentos(recuperarRelacionamentos(tabela));
			tabela.setRelacionamentosExterno(recuperarRelacionamentosExternos(tabela));
			
			callback.onProgress(count++, listaTabelas.size());
		}
		
		return listaTabelas;
	}

	public ArrayList<TabelaVO> listarTabelas() throws Exception {
		try {
			super.prepararDAO(SELECT_TABELAS);
			
			st.setString(1, ProjSingleton.getProjeto().getSchemaDB());
			
			ResultSet rs = super.listar();
			ArrayList<TabelaVO> lista = new ArrayList<TabelaVO>();
			TabelaVO item;
			while(rs.next()) {
				item = new TabelaVO();
				item.setNome(rs.getString("nome"));
				
				lista.add(item);
			}
			rs.close();
			rs = null;
			
			return lista;
		} finally {
			super.fecharConexao();
		}
	}
	
	private ArrayList<ColunaVO> recuperarColunas(TabelaVO tabela) throws Exception {
		try {
			super.prepararDAO(SELECT_COLUNAS);
			
			int indice = 1;
			st.setString(indice++, ProjSingleton.getProjeto().getSchemaDB());
			st.setString(indice++, tabela.getNome());
			
			ResultSet rs = super.listar();
			ArrayList<ColunaVO> lista = new ArrayList<ColunaVO>();
			ColunaVO item;
			while(rs.next()) {
				item = new ColunaVO();
				item.setOrdem(rs.getInt("ordem"));
				item.setChave(rs.getString("chave"));
				item.setNome(rs.getString("nome"));
				item.setTipoData(rs.getString("tipo"));
				
				if(item.getTipoData() == TipoData.ENUM) {
					String tipoColuna = rs.getString("tipoColuna"); 
					String[] enums = tipoColuna.substring(tipoColuna.lastIndexOf("(") + 1, tipoColuna.lastIndexOf(")")).split(",");
					
					item.setEnums(Arrays.asList(enums));
				}
				
				item.setTamanho(BigInteger.valueOf(rs.getLong("tamanho")));
				item.setNulo(rs.getBoolean("nulo"));
				item.setBase(rs.getInt("base"));
				item.setPrecisao(rs.getInt("precisao"));
				item.setPadrao(!rs.getString("padrao").isEmpty() ? rs.getString("padrao").replace(" ", "_") : PadraoColuna.NULL.name());
				item.setValorDefault(rs.getString("valorDefault"));
				
				lista.add(item);
			}
			rs.close();
			rs = null;
			
			return lista;
		} finally {
			super.fecharConexao();
		}
	}
	
	private ArrayList<RelacionamentoVO> recuperarRelacionamentos(TabelaVO tabela) throws Exception {
		try {
			super.prepararDAO(SELECT_RELACIONAMENTOS);
			
			int indice = 1;
			st.setString(indice++, ProjSingleton.getProjeto().getSchemaDB());
			st.setString(indice++, tabela.getNome());
			
			ResultSet rs = super.listar();
			ArrayList<RelacionamentoVO> lista = new ArrayList<>();
			RelacionamentoVO item;
			while(rs.next()) {
				item = new RelacionamentoVO();
				item.setNomeTabela(rs.getString("tabelaReferencia"));
				item.setColunaRelacionada(rs.getString("idReferencia"));
				
				lista.add(item);
			}
			rs.close();
			rs = null;
			
			return lista;
		} finally {
			super.fecharConexao();
		}
	}
	
	private ArrayList<RelacionamentoExternoVO> recuperarRelacionamentosExternos(TabelaVO tabela) throws Exception {
		try {
			super.prepararDAO(SELECT_RELACIONAMENTOS_EXTERNOS);
			
			int indice = 1;
			st.setString(indice++, ProjSingleton.getProjeto().getSchemaDB());
			st.setString(indice++, tabela.getNome());
			
			ResultSet rs = super.listar();
			ArrayList<RelacionamentoExternoVO> lista = new ArrayList<>();
			RelacionamentoExternoVO item;
			while(rs.next()) {
				item = new RelacionamentoExternoVO();
				item.setNomeTabela(rs.getString("tabelaExterna"));
				item.setColunaReferencia(rs.getString("colunaReferencia"));
				item.setColunaReferenciada(rs.getString("colunaReferenciada"));
				
				lista.add(item);
			}
			rs.close();
			rs = null;
			
			return lista;
		} finally {
			super.fecharConexao();
		}
	}
}
