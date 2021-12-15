package vo;

import java.math.BigInteger;
import java.util.List;

import enums.PadraoColuna;
import enums.TipoChave;
import enums.TipoData;

public class ColunaVO {
	private int ordem;
	private TipoChave chave;
	private String nome;
	private TipoData tipoData;
	private List<String> enums;
	private BigInteger tamanho;
	private Boolean nulo;
	private int base;
	private int precisao;
	private PadraoColuna padrao;
	private String valorDefault;
	private boolean imagem;
	
	@Override
	public String toString() {
		return nome;
	}
	
	public int getOrdem() { return ordem; }
	public void setOrdem(int ordem) { this.ordem = ordem; }
	
	public TipoChave getChave() { return chave;	}
	public void setChave(TipoChave chave) { this.chave = chave;	}
	public void setChave(String tipo) {
		if(!tipo.isEmpty()) {
			this.chave = TipoChave.valueOf(tipo);
		}
	}
	
	public String getNome() { return nome;}
	public void setNome(String nome) { this.nome = nome; }

	public TipoData getTipoData() { return tipoData; }
	public String getTipoDataMinusculo() { return tipoData.name().toLowerCase(); }
	public void setTipoData(TipoData tipoData) { this.tipoData = tipoData; }
	public void setTipoData(String tipoData) {
		try {
			this.tipoData = TipoData.valueOf(tipoData);
		} catch (IllegalArgumentException e) {
			this.tipoData = TipoData.TEXT;
			System.out.println("Tipo Coluna [ERROR]: "+tipoData);
		}
	}
	
	public List<String> getEnums() { return enums; }
	public void setEnums(List<String> enums) { this.enums = enums; }
	
	public BigInteger getTamanho() { return tamanho; }
	public void setTamanho(BigInteger tamanho) { this.tamanho = tamanho; }
	
	public Boolean isNulo() { return nulo; }
	public void setNulo(Boolean nulo) { this.nulo = nulo; }
	
	public int getBase() { return base; }
	public void setBase(int base) { this.base = base; }
	
	public int getPrecisao() { return precisao; }
	public void setPrecisao(int precisao) { this.precisao = precisao; }

	public PadraoColuna getPadrao() { return padrao; }
	public void setPadrao(PadraoColuna padrao) { this.padrao = padrao; }
	public void setPadrao(String padrao) { this.padrao = PadraoColuna.valueOf(padrao); }
	
	public String getValorDefault() { return valorDefault; }
	public void setValorDefault(String valorDefault) { this.valorDefault = valorDefault; }

	public boolean isImagem() { return imagem; }
	public void setImagem(boolean imagem) { this.imagem = imagem; }
}
