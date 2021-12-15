package vo;

public class RelacionamentoExternoVO {
	private String nomeTabela;
	private String colunaReferencia;
	private String colunaReferenciada;
	
	public String getNomeTabela() { return nomeTabela; }
	public String getNomeTabelaMinusculo() { return this.nomeTabela.toLowerCase(); }
	public void setNomeTabela(String nomeTabela) { this.nomeTabela = nomeTabela; }
	
	public String getCounaReferencia() { return colunaReferencia; }
	public void setColunaReferencia(String colunaReferencia) { this.colunaReferencia = colunaReferencia; }
	
	public String getColunaReferenciada() { return colunaReferenciada; }
	public void setColunaReferenciada(String colunaReferenciada) { this.colunaReferenciada = colunaReferenciada; }
}
