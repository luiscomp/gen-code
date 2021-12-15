package vo;

public class RelacionamentoVO {
	private String nomeTabela;
	private String colunaRelacionada;

	public String getNomeTabela() { return nomeTabela; }
	public String getNomeTabelaMinusculo() { return this.nomeTabela.toLowerCase(); }
	public void setNomeTabela(String nomeTabela) { this.nomeTabela = nomeTabela; }
	
	public String getColunaRelacionada() { return colunaRelacionada; }
	public void setColunaRelacionada(String colunaRelacionada) { this.colunaRelacionada = colunaRelacionada; }
}
