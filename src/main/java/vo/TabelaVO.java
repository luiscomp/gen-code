package vo;

import java.util.ArrayList;

import enums.PadraoColuna;
import enums.TipoChave;
import enums.TipoData;

public class TabelaVO {
	private String nome;
	private ArrayList<ColunaVO> colunas;
	private ArrayList<RelacionamentoVO> relacionamentos;
	private ArrayList<RelacionamentoExternoVO> relacionamentosExterno;
	
	public TabelaVO() {
		super();
	}

	public TabelaVO(String nome) {
		super();
		this.nome = nome;
	}
	
	@Override
	public String toString() {
		return nome;
	}

	public String getNomeMinusculo() { return nome.toLowerCase(); }
	public String getNome() { return nome; }
	public void setNome(String nome) { this.nome = nome; }
	
	public ArrayList<ColunaVO> getColunas() { return colunas; }
	public void setColunas(ArrayList<ColunaVO> colunas) { this.colunas = colunas; }
	
	public ArrayList<RelacionamentoVO> getRelacionamentos() { return relacionamentos; }
	public void setRelacionamentos(ArrayList<RelacionamentoVO> relacionamentos) { this.relacionamentos = relacionamentos; }
	
	public ArrayList<RelacionamentoExternoVO> getRelacionamentosExterno() { return relacionamentosExterno; }
	public void setRelacionamentosExterno(ArrayList<RelacionamentoExternoVO> relacionamentosExterno) { this.relacionamentosExterno = relacionamentosExterno; }
	
	// -------------------------------------------------------------------------------------------------------------------------------------------------
	// MÉTODOS FONTEND VUE
	// -------------------------------------------------------------------------------------------------------------------------------------------------

	public String getColunasTabelaVueFront() {
		StringBuffer colunas = new StringBuffer();
		
		int count = 1;
		for(ColunaVO coluna : getColunas()) {
			if((!coluna.isNulo() && coluna.getChave() != TipoChave.PRI && coluna.getChave() != TipoChave.MUL) && (!coluna.isNulo() && coluna.getValorDefault() == null)) {
				if(count == 1) {
					colunas.append("{ text: '").append(coluna.getNome()).append("', value: '").append(coluna.getNome()).append("', sortable: false, aling: 'left', width: '100%' },\n\t\t\t\t\t");
				} else {
					colunas.append("{ text: '").append(coluna.getNome()).append("', value: '").append(coluna.getNome()).append("', sortable: false, aling: 'right', width: '100%' },\n\t\t\t\t\t");
				}
				count++;
			}
		}
		
		return colunas.length() > 0 ? colunas.substring(0, colunas.lastIndexOf("\n")) : "";
	}
	
	public String getColunasTabelaHtmlVueFront() {
		StringBuffer colunas = new StringBuffer();
		
		int count = 1;
		for(ColunaVO coluna : getColunas()) {
			if((!coluna.isNulo() && coluna.getChave() != TipoChave.PRI && coluna.getChave() != TipoChave.MUL) && (!coluna.isNulo() && coluna.getValorDefault() == null)) {
				if(count == 1) {
					colunas.append("<td class=\"text-md-left\">{{ props.item.").append(coluna.getNome()).append(" }}</td>\n\t\t\t\t\t\t\t\t\t");
				} else {
					colunas.append("<td class=\"text-md-right\">{{ props.item.").append(coluna.getNome()).append(" }}</td>\n\t\t\t\t\t\t\t\t\t");
				}
				count++;
			}
		}
		
		return colunas.length() > 0 ? colunas.substring(0, colunas.lastIndexOf("\n")) : "";
	}
	
	public String getRecuperarRelacionamentosVueFront() {
		StringBuffer relacionamentos = new StringBuffer();
		
		if(this.getRelacionamentos() != null && !this.getRelacionamentos().isEmpty()) {
			for(RelacionamentoVO relacionamento: this.getRelacionamentos()) {
				relacionamentos.append(relacionamento.getNomeTabelaMinusculo()).append(": {},\n\t\t\t\t");
			}
		}
		
		return relacionamentos.length() > 0 ? relacionamentos.substring(0, relacionamentos.lastIndexOf(",")) : "";
	}
	
	public String getRecuperarColunasDecimaisVueFront() {
		StringBuffer colunas = new StringBuffer();
		
		if(this.getColunas() != null && !this.getColunas().isEmpty()) {
			for(ColunaVO coluna: this.getColunas()) {
				if(coluna.getTipoData() == TipoData.DECIMAL) {
					colunas.append(coluna.getNome()).append(": 0.0,\n\t\t\t\t");
				}
			}
		}
		
		return colunas.length() > 0 ? colunas.substring(0, colunas.lastIndexOf("\n")) : "";
	}
	
	public String getColunasImagemVueFrontComponent() {
		StringBuffer colunas = new StringBuffer();
		
		if(this.getColunas() != null && !this.getColunas().isEmpty()) {
			for(ColunaVO coluna: this.getColunas()) {
				if(coluna.isImagem()) {
					colunas.append("this.").append(this.getNomeMinusculo()).append(".imagem = this.").append(String.join(".", this.getNomeMinusculo(), coluna.getNome())).append(" ? Constantes.URL_IMAGENS.concat(this.").append(String.join(".", this.getNomeMinusculo(), coluna.getNome())).append(") : require('../assets/photo.svg')\n\t\t\t\t\t");
				}
			}
		}
		
		return colunas.length() > 0 ? colunas.substring(0, colunas.lastIndexOf("\n")) : "";
	}
	
	public String getCamposCadastroImagemVueFront() {
		StringBuffer campos = new StringBuffer();
		
		if(this.getColunas() != null && !this.getColunas().isEmpty()) {
			for(ColunaVO coluna: this.getColunas()) {
				if(coluna.isImagem()) {
					campos.append("<v-layout row wrap>\n\t\t\t\t\t\t\t");
					campos.append("<v-flex xs12 sm6 md12>\n\t\t\t\t\t\t\t\t");
					campos.append("<ImageBrowser v-model=\"").append(String.join(".", this.getNomeMinusculo(), "imagem")).append("\" :aspectRatio=\"16/9\"></ImageBrowser>\n\t\t\t\t\t\t\t");
					campos.append("</v-flex>\n\t\t\t\t\t\t");
					campos.append("</v-layout>\n\t\t\t\t\t\t");
				}
			}
		}
		
		return campos.length() > 0 ? campos.substring(0, campos.lastIndexOf("\n")) : "";
	}
	
	public String getCamposCadastroFrontVue() {
		StringBuffer campos = new StringBuffer();
		
		if(this.getColunas() != null && !this.getColunas().isEmpty()) {
			for(ColunaVO coluna: this.getColunas()) {
				if(coluna.getChave() != TipoChave.PRI && coluna.getChave() != TipoChave.MUL && !coluna.isImagem()) {
					campos.append("<v-layout row wrap>\n\t\t\t\t\t\t\t");
					campos.append("<v-flex d-flex xs12 sm12 md12>\n\t\t\t\t\t\t\t\t");
					
					switch(coluna.getTipoData()) {
						case INT:
						case BIGINT:
							if(coluna.isNulo()) {
								campos.append("<v-text-field v-model.number=\"").append(String.join(".", this.getNomeMinusculo(), coluna.getNome())).append("\" label=\"").append(coluna.getNome()).append("\"></v-text-field>\n\t\t\t\t\t\t\t");
							} else {
								campos.append("<v-text-field v-model.number=\"").append(String.join(".", this.getNomeMinusculo(), coluna.getNome())).append("\" label=\"").append(coluna.getNome()).append("\" required :rules=\"validacao.").append(coluna.getNome()).append("\"></v-text-field>\n\t\t\t\t\t\t\t");
							}
						break;
						case TEXT:
						case MEDIUMTEXT:
						case LONGTEXT:
						case VARCHAR:
							if(coluna.isNulo()) {
								campos.append("<v-text-field v-model=\"").append(String.join(".", this.getNomeMinusculo(), coluna.getNome())).append("\" type=\"text\" label=\"").append(coluna.getNome()).append("\"></v-text-field>\n\t\t\t\t\t\t\t");
							} else {
								campos.append("<v-text-field v-model=\"").append(String.join(".", this.getNomeMinusculo(), coluna.getNome())).append("\" type=\"text\" label=\"").append(coluna.getNome()).append("\" required :rules=\"validacao.").append(coluna.getNome()).append("\"></v-text-field>\n\t\t\t\t\t\t\t");
							}
						break;
						case DATE:
							if (coluna.isNulo()) {
								campos.append("<DateInput v-model=\"").append(String.join(".", this.getNomeMinusculo(), coluna.getNome())).append("\" label=\"").append(coluna.getNome()).append("\"></DateInput>\n\t\t\t\t\t\t\t");
							} else {
								campos.append("<DateInput v-model=\"").append(String.join(".", this.getNomeMinusculo(), coluna.getNome())).append("\" label=\"").append(coluna.getNome()).append("\" required :rules=\"validacao.").append(coluna.getNome()).append("\"></DateInput>\n\t\t\t\t\t\t\t");
							}
						break;
						case TIME: // TODO - PRECISA FAZER CÓDIGO COM COMPONENTE TIME
							if(coluna.isNulo()) {
								campos.append("<v-text-field v-model=\"").append(String.join(".", this.getNomeMinusculo(), coluna.getNome())).append("\" type=\"text\" label=\"").append(coluna.getNome()).append("\"></v-text-field>\n\t\t\t\t\t\t\t");
							} else {
								campos.append("<v-text-field v-model=\"").append(String.join(".", this.getNomeMinusculo(), coluna.getNome())).append("\" type=\"text\" label=\"").append(coluna.getNome()).append("\" required :rules=\"validacao.").append(coluna.getNome()).append("\"></v-text-field>\n\t\t\t\t\t\t\t");
							}
						break;
						case DATETIME:
						case TIMESTAMP: // TODO - PRECISA FAZER CÓDIGO COM COMPONENTE TIMESTAMP
							if (coluna.isNulo()) {
								campos.append("<DateInput v-model=\"").append(String.join(".", this.getNomeMinusculo(), coluna.getNome())).append("\" label=\"").append(coluna.getNome()).append("\"></DateInput>\n\t\t\t\t\t\t\t");
							} else {
								campos.append("<DateInput v-model=\"").append(String.join(".", this.getNomeMinusculo(), coluna.getNome())).append("\" label=\"").append(coluna.getNome()).append("\" required :rules=\"validacao.").append(coluna.getNome()).append("\"></DateInput>\n\t\t\t\t\t\t\t");
							}
						break;
						case DECIMAL:
						case DOUBLE:
						case FLOAT:
							if(coluna.isNulo()) {
								campos.append("<DecimalInput v-model.number=\"").append(String.join(".", this.getNomeMinusculo(), coluna.getNome())).append("\" label=\"").append(coluna.getNome()).append("\" :precision=\"2\" />\n\t\t\t\t\t\t\t");
							} else {
								campos.append("<DecimalInput v-model.number=\"").append(String.join(".", this.getNomeMinusculo(), coluna.getNome())).append("\" label=\"").append(coluna.getNome()).append("\" :precision=\"2\" />\n\t\t\t\t\t\t\t");
							}
						break;
						case ENUM: // TODO - PRECISA FAZER O CÓDIGO COM COMPONENTE COMBOBOX PARA ENUM
							if(coluna.isNulo()) {
								campos.append("<v-text-field v-model=\"").append(String.join(".", this.getNomeMinusculo(), coluna.getNome())).append("\" type=\"text\" label=\"").append(coluna.getNome()).append("\"></v-text-field>\n\t\t\t\t\t\t\t");
							} else {
								campos.append("<v-text-field v-model=\"").append(String.join(".", this.getNomeMinusculo(), coluna.getNome())).append("\" type=\"text\" label=\"").append(coluna.getNome()).append("\" required :rules=\"validacao.").append(coluna.getNome()).append("\"></v-text-field>\n\t\t\t\t\t\t\t");
							}
						break;
						case TINYINT: // TODO - PRECISA FAZER O CÓDIGO COM COMPONENTE COMBOBOX PARA BOOLEAN
							if(coluna.isNulo()) {
								campos.append("<v-text-field v-model=\"").append(String.join(".", this.getNomeMinusculo(), coluna.getNome())).append("\" type=\"text\" label=\"").append(coluna.getNome()).append("\"></v-text-field>\n\t\t\t\t\t\t\t");
							} else {
								campos.append("<v-text-field v-model=\"").append(String.join(".", this.getNomeMinusculo(), coluna.getNome())).append("\" type=\"text\" label=\"").append(coluna.getNome()).append("\" required :rules=\"validacao.").append(coluna.getNome()).append("\"></v-text-field>\n\t\t\t\t\t\t\t");
							}
						break;
						default:
							if(coluna.isNulo()) {
								campos.append("<v-text-field v-model=\"").append(String.join(".", this.getNomeMinusculo(), coluna.getNome())).append("\" type=\"text\" label=\"").append(coluna.getNome()).append("\"></v-text-field>\n\t\t\t\t\t\t\t");
							} else {
								campos.append("<v-text-field v-model=\"").append(String.join(".", this.getNomeMinusculo(), coluna.getNome())).append("\" type=\"text\" label=\"").append(coluna.getNome()).append("\" required :rules=\"validacao.").append(coluna.getNome()).append("\"></v-text-field>\n\t\t\t\t\t\t\t");
							}
						break;
					}
					
					campos.append("</v-flex>\n\t\t\t\t\t\t");
					campos.append("</v-layout>\n\t\t\t\t\t\t");
				}
			}
		}
		
		return campos.length() > 0 ? campos.substring(0, campos.lastIndexOf("\n")) : "";
	}
	
	public String getCamposCadastroRelacionamentoVueFront(ArrayList<TabelaVO> tabelas) {
		StringBuffer campos = new StringBuffer();
		
		if(this.getRelacionamentos() != null && !this.getRelacionamentos().isEmpty()) {
			for(RelacionamentoVO relacionamento: this.getRelacionamentos()) {
				campos.append("<v-layout row wrap>\n\t\t\t\t\t\t\t");
				campos.append("<v-flex d-flex xs12 sm12 md12>\n\t\t\t\t\t\t\t\t");
				
				for(TabelaVO tabela: tabelas) {
					if(tabela.getNome().equals(relacionamento.getNomeTabela())) {
						for(ColunaVO coluna: tabela.getColunas()) {
							if(!coluna.isNulo() && coluna.getChave() != TipoChave.PRI && coluna.getChave() != TipoChave.MUL && coluna.getTipoData() == TipoData.VARCHAR && !coluna.isImagem()) {
								campos.append("<v-text-field v-model=\""+this.getNomeMinusculo()+"."+relacionamento.getNomeTabelaMinusculo()+"."+coluna.getNome()+"\" append-icon=\"search\" label=\""+relacionamento.getNomeTabela()+"\" readonly required :rules=\"validacao."+relacionamento.getNomeTabelaMinusculo()+"\" @focus=\"modal"+relacionamento.getNomeTabela()+".exibir = true\"></v-text-field>\n\t\t\t\t\t\t\t");
								break;
							}
						}
						break;
					}
				}
				
				campos.append("</v-flex>\n\t\t\t\t\t\t");
				campos.append("</v-layout>\n\t\t\t\t\t\t");
			}
		}
		
		return campos.length() > 0 ? campos.substring(0, campos.lastIndexOf("\n")) : "";
	}
	
	public String getModalCadastroRelacionamentoVueFront() {
		StringBuffer relacionamentos = new StringBuffer();
		
		if(this.getRelacionamentos() != null && !this.getRelacionamentos().isEmpty()) {
			for(RelacionamentoVO relacionamento: this.getRelacionamentos()) {
				relacionamentos.append("<").append(relacionamento.getNomeTabelaMinusculo()).append("-modal v-model=\"modal").append(relacionamento.getNomeTabela()).append(".exibir\" @selecionar=\"v => ").append(String.join(".", this.getNomeMinusculo(), relacionamento.getNomeTabelaMinusculo())).append(" = v[0]\"/>\n\t\t\t");
			}
		}
		
		return relacionamentos.length() > 0 ? relacionamentos.substring(0, relacionamentos.lastIndexOf("\n")) : "";
	}
	
	public String getImportsCadastroVueFront() {
		StringBuffer imports = new StringBuffer();
		
		if(this.getColunas() != null && !this.getColunas().isEmpty()) {
			for(ColunaVO coluna: this.getColunas()) {
				if(coluna.isImagem()) {
					imports.append("import ImageBrowser from '../templates/ImageBrowser'\n");
					break;
				}
			}
			
			for(ColunaVO coluna: this.getColunas()) {
				if(coluna.getTipoData() == TipoData.DECIMAL || coluna.getTipoData() == TipoData.DOUBLE || coluna.getTipoData() == TipoData.FLOAT) {
					imports.append("import DecimalInput from '../templates/DecimalInput'\n");
					break;
				}
			}
			
			for (ColunaVO coluna : this.getColunas()) {
				if (coluna.getTipoData() == TipoData.DATE || coluna.getTipoData() == TipoData.DATETIME || coluna.getTipoData() == TipoData.TIMESTAMP) {
					imports.append("import DateInput from '../templates/DateInput'\n");
					break;
				}
			}
		}
		
		if(this.getRelacionamentos() != null && !this.getRelacionamentos().isEmpty()) {
			for(RelacionamentoVO relacionamento: this.getRelacionamentos()) {
				imports.append("import ").append(relacionamento.getNomeTabela()).append("Modal from '../modals/").append(relacionamento.getNomeTabela()).append("Modal'\n");
			}
		}
		
		return imports.length() > 0 ? imports.substring(0, imports.lastIndexOf("\n")) : "";
	}
	
	public String getAddComponentCadastroVueFront() {
		StringBuffer imports = new StringBuffer();
		
		if(this.getColunas() != null && !this.getColunas().isEmpty()) {
			for(ColunaVO coluna: this.getColunas()) {
				if(coluna.isImagem()) {
					imports.append("ImageBrowser,\n\t\t");
					break;
				}
			}
			
			for(ColunaVO coluna: this.getColunas()) {
				if(coluna.getTipoData() == TipoData.DECIMAL || coluna.getTipoData() == TipoData.DOUBLE || coluna.getTipoData() == TipoData.FLOAT) {
					imports.append("DecimalInput,\n\t\t");
					break;
				}
			}

			for (ColunaVO coluna : this.getColunas()) {
				if (coluna.getTipoData() == TipoData.DATE || coluna.getTipoData() == TipoData.DATETIME || coluna.getTipoData() == TipoData.TIMESTAMP) {
					imports.append("DateInput,\n\t\t");
					break;
				}
			}
		}
		
		if(this.getRelacionamentos() != null && !this.getRelacionamentos().isEmpty()) {
			for(RelacionamentoVO relacionamento: this.getRelacionamentos()) {
				imports.append(relacionamento.getNomeTabela()).append("Modal,\n\t\t");
			}
		}
		
		return imports.length() > 0 ? imports.substring(0, imports.lastIndexOf(",")) : "";
	}
	
	public String getModalDataCadastroVueFront() {
		StringBuffer modalRelacionamento = new StringBuffer();
		
		if(this.getRelacionamentos() != null && !this.getRelacionamentos().isEmpty()) {
			for(RelacionamentoVO relacionamento: this.getRelacionamentos()) {
				modalRelacionamento.append("modal").append(relacionamento.getNomeTabela()).append(": {\n\t\t\t\t");
				modalRelacionamento.append("exibir: false\n\t\t\t");
				modalRelacionamento.append("},\n\t\t\t");
			}
		}
		
		return modalRelacionamento.length() > 0 ? modalRelacionamento.substring(0, modalRelacionamento.lastIndexOf("\n")) : "";
	}
	
	public String getValidadorCadastroVueFront() {
		StringBuffer colunas = new StringBuffer("validacao: {\n\t\t\t\t");
		
		if(this.getColunas() != null && !this.getColunas().isEmpty()) {
			for(ColunaVO coluna: this.getColunas()) {
				if((!coluna.isNulo() && coluna.getChave() != TipoChave.PRI && coluna.getChave() != TipoChave.MUL) && (!coluna.isNulo() && coluna.getValorDefault() == null)) {
					if(coluna.getTipoData() == TipoData.DATE || coluna.getTipoData() == TipoData.DATETIME || coluna.getTipoData() == TipoData.TIMESTAMP) {
						colunas.append(coluna.getNome()).append(": [\n\t\t\t\t\t");
						colunas.append("v => !!v || 'Informe uma data válida'\n\t\t\t\t");
						colunas.append("],\n\t\t\t\t");
					} else {
						colunas.append(coluna.getNome()).append(": [\n\t\t\t\t\t");
						colunas.append("v => !!v || 'Informe um ").append(coluna.getNome()).append("'\n\t\t\t\t");
						colunas.append("],\n\t\t\t\t");
					}
				}
			}
			colunas = new StringBuffer(colunas.length() > 0 ? colunas.substring(0, colunas.lastIndexOf("\t")) : "");
		}
		
		if(this.getRelacionamentos() != null && !this.getRelacionamentos().isEmpty()) {
			colunas.append("\t");
			for(RelacionamentoVO relacionamento: this.getRelacionamentos()) {
				colunas.append(relacionamento.getNomeTabelaMinusculo()).append(": [\n\t\t\t\t\t");
				colunas.append("v => !!v || 'Informe um ").append(relacionamento.getNomeTabelaMinusculo()).append("'\n\t\t\t\t");
				colunas.append("],\n\t\t\t\t");
			}
			colunas = new StringBuffer(colunas.length() > 0 ? colunas.substring(0, colunas.lastIndexOf("\t")) : "");
		}
		
		colunas = new StringBuffer(colunas.length() > 0 ? colunas.substring(0, colunas.lastIndexOf(",")) : "");
		colunas.append("\n\t\t\t}");
		
		return colunas.toString();
	}
	
	public String getFiltrosVueFront() {
		StringBuffer filtros = new StringBuffer();
		
		if(this.getColunas() != null && !this.getColunas().isEmpty()) {
			for(ColunaVO coluna: this.getColunas()) {
				if(!coluna.isNulo() && coluna.getChave() != TipoChave.PRI && coluna.getChave() != TipoChave.MUL) {
					filtros.append("<v-flex xs12 sm12 md6 pl-1 pr-1>\n\t\t\t\t");
					filtros.append("<v-text-field v-model=\"").append(String.join(".", this.getNomeMinusculo(), coluna.getNome())).append("\" label=\"").append(coluna.getNome()).append("\" type=\"text\" @keyup.enter=\"pesquisar\"></v-text-field>\n\t\t\t");
					filtros.append("</v-flex>\n\t\t\t");
				}
			}
		}
		
		return filtros.length() > 0 ? filtros.substring(0, filtros.lastIndexOf("\n")) : "";
	}
	
	public String getBuscaHtmlModalVueFront() {
		StringBuffer busca = new StringBuffer();
		
		if(this.getColunas() != null && !this.getColunas().isEmpty()) {
			for(ColunaVO coluna: this.getColunas()) {
				if(!coluna.isNulo() && coluna.getChave() != TipoChave.PRI && coluna.getChave() != TipoChave.MUL && coluna.getTipoData() == TipoData.VARCHAR) {
					busca.append("<v-flex xs12 sm12 md12>\n\t\t\t\t\t\t\t");
					busca.append("<v-text-field v-model=\"busca\" type=\"text\" label=\"").append(coluna.getNome()).append("\"></v-text-field>\n\t\t\t\t\t\t");
					busca.append("</v-flex>\n\t\t\t\t\t\t");
					break;
				}
			}
		}

		return busca.length() > 0 ? busca.substring(0, busca.lastIndexOf("\n")) : "";
	}
	
	public String getColunaHtmlModalVueFront() {
		StringBuffer colunaVisivel = new StringBuffer();
		
		if(this.getColunas() != null && !this.getColunas().isEmpty()) {
			for(ColunaVO coluna: this.getColunas()) {
				if(!coluna.isNulo() && coluna.getChave() != TipoChave.PRI && coluna.getChave() != TipoChave.MUL && coluna.getTipoData() == TipoData.VARCHAR) {
					colunaVisivel.append("<td>{{ props.item.").append(coluna.getNome()).append(" }}</td>");
					break;
				}
			}
		}
		
		return colunaVisivel.toString();
	}
	
	public String getColunasModalVueFront() {
		StringBuffer colunas = new StringBuffer();
		
		for(ColunaVO coluna : getColunas()) {
			if(!coluna.isNulo() && coluna.getChave() != TipoChave.PRI && coluna.getChave() != TipoChave.MUL && coluna.getTipoData() == TipoData.VARCHAR) {
				colunas.append("{ text: '").append(coluna.getNome()).append("', value: '").append(coluna.getNome()).append("', sortable: false, aling: 'left', width: '100%' }");
				break;
			}
		}
		
		return colunas.toString();
	}

	// -------------------------------------------------------------------------------------------------------------------------------------------------
	// MÉTODOS BACKEND NODE + EXPRESS
	// -------------------------------------------------------------------------------------------------------------------------------------------------
	
	public String getColunasObrigatorias() {
		StringBuffer colunas = new StringBuffer();
		
		for(ColunaVO coluna : getColunas()) {
			if(coluna.getChave() != null && coluna.getChave() == TipoChave.MUL && !coluna.isNulo()) {
				for(RelacionamentoVO relacionamento: getRelacionamentos()) {
					if(relacionamento.getColunaRelacionada().equals(coluna.getNome())) {
						colunas.append("'").append(relacionamento.getNomeTabelaMinusculo()).append("', ");
						break;
					}
				}
			} else if((!coluna.isNulo() && coluna.getPadrao() != PadraoColuna.AUTO_INCREMENT) && (!coluna.isNulo() && coluna.getValorDefault() == null) && !coluna.isImagem()) {
				colunas.append("'").append(coluna.getNome()).append("', ");
			}
		}
		
		return colunas.length() > 0 ? colunas.substring(0, colunas.lastIndexOf(",")) : "";
	}
	
	public String getPropriedadesColunas() {
		StringBuffer propriedades = new StringBuffer();
		
		for(ColunaVO coluna : getColunas()) {
			if(coluna.getChave() != null && coluna.getChave() == TipoChave.MUL) {
				for(RelacionamentoVO relacionamento: getRelacionamentos()) {
					if(relacionamento.getColunaRelacionada().equals(coluna.getNome())) {
						propriedades.append(relacionamento.getNomeTabelaMinusculo());
					}
				}
				
				if(coluna.isNulo()) {
					propriedades.append(": { type: [ 'null', 'object' ]");
				} else {
					propriedades.append(": { type: 'object'");
				}
			} else {
				propriedades.append(coluna.getNome());
				
				
				switch(coluna.getTipoData()) {
					case INT:
					case BIGINT:
						if(coluna.isNulo()) {
							propriedades.append(": { type: [ 'null', 'integer' ]");
						} else {
							propriedades.append(": { type: 'integer'");
						}
						break;
					case TEXT:
					case MEDIUMTEXT:
					case LONGTEXT:
					case VARCHAR:
						if(coluna.isNulo()) {
							propriedades.append(": { type: [ 'null', 'string' ]");
						} else {
							propriedades.append(": { type: 'string'");
						}
						break;
					case DATE:
						if(coluna.isNulo()) {
							propriedades.append(": { type: [ 'null', 'string' ], format: 'date'");
						} else {
							propriedades.append(": { type: 'string', format: 'date'");
						}
						break;
					case TIME:
						if(coluna.isNulo()) {
							propriedades.append(": { type: [ 'null', 'string' ], pattern: '^(?:2[0-3]|[01][0-9]):[0-5][0-9]:[0-5][0-9]$'");
						} else {
							propriedades.append(": { type: 'string', pattern: '^(?:2[0-3]|[01][0-9]):[0-5][0-9]:[0-5][0-9]$'");
						}
						break;
					case DATETIME:
					case TIMESTAMP:
						if(coluna.isNulo()) {
							propriedades.append(": { type: [ 'null', 'string' ], pattern: '[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1]) (2[0-3]|[01][0-9]):[0-5][0-9]:[0-5][0-9]$'");
						} else {
							propriedades.append(": { type: 'string', pattern: '[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1]) (2[0-3]|[01][0-9]):[0-5][0-9]:[0-5][0-9]$'");
						}
						break;
					case DECIMAL:
					case DOUBLE:
					case FLOAT:
						if(coluna.isNulo()) {
							propriedades.append(": { type: [ 'null', 'number' ]");
						} else {
							propriedades.append(": { type: 'number'");
						}
						break;
					case ENUM:
						if(coluna.isNulo()) {
							propriedades.append(": { type: ").append("[ 'null', 'string' ], enum: [");
							for(String valor : coluna.getEnums()) {
								propriedades.append(valor).append(", ");
							}
							propriedades = new StringBuffer(propriedades.substring(0, propriedades.lastIndexOf(",")));
							propriedades.append("]");
						} else {
							propriedades.append(": { type: ").append("'string', enum: [");
							for(String valor : coluna.getEnums()) {
								propriedades.append(valor).append(", ");
							}
							propriedades = new StringBuffer(propriedades.substring(0, propriedades.lastIndexOf(",")));
							propriedades.append("]");
						}
						break;
					case TINYINT:
						if(coluna.isNulo()) {
							propriedades.append(": { type: [ 'null', 'number', 'boolean' ]");
						} else {
							propriedades.append(": { type: [ 'number', 'boolean' ]");
						}
						break;
					default:
						propriedades.append(coluna.getNome()).append(": { type: ").append("'").append(coluna.getTipoDataMinusculo()).append("' },\n\t\t");
						break;
				}
			}
			
			if(coluna.getValorDefault() == null) {
				propriedades.append(", default: null");
			}
			propriedades.append(" },\n\t\t");
		}
		
		return propriedades.length() > 0 ? propriedades.substring(0, propriedades.lastIndexOf(",")) : "";
	}
	
	public String getCamposImagemInsertUpdate() {
		StringBuffer campos = new StringBuffer();
		
		if(this.getColunas() != null && !this.getColunas().isEmpty()) {
			for(ColunaVO coluna: this.getColunas()) {
				if(coluna.isImagem()) {
					campos.append("if(").append(String.join(".", this.getNomeMinusculo(), "imagem")).append(" !== undefined && Utils.isBase64(").append(String.join(".", this.getNomeMinusculo(), "imagem")).append(")) {\n\t\t\t");
					campos.append(String.join(".", this.getNomeMinusculo(), coluna.getNome())).append(" = Utils.gerarNomeArquivo('IMG', ").append(String.join(".", this.getNomeMinusculo(), "imagem")).append(");\n\t\t\t");
					campos.append("Utils.salvarArquivo(").append(String.join(".", this.getNomeMinusculo(), "imagem")).append(", ").append(String.join(".", this.getNomeMinusculo(), coluna.getNome())).append(", Constantes.URL_IMAGENS)\n\t\t");
					campos.append("}\n");
				}
			}
		}
		
		return campos.length() > 0 ? campos.substring(0, campos.lastIndexOf("\n")) : "";
	}
	
	public String getErroObrigatoriedadeColunas() {
		StringBuffer erros = new StringBuffer();
		
		for(ColunaVO coluna : getColunas()) {
			if(!coluna.isNulo() && coluna.getValorDefault() == null && !coluna.isImagem()) {
				if(coluna.getChave() != null && coluna.getChave() == TipoChave.MUL) {
					for(RelacionamentoVO relacionamento: getRelacionamentos()) {
						if(relacionamento.getColunaRelacionada().equals(coluna.getNome())) {
							erros.append(relacionamento.getNomeTabelaMinusculo()).append(": \"O campo '").append(relacionamento.getNomeTabelaMinusculo()).append("' é obrigatório.\",\n\t\t\t");
						}
					}
				} else {
					erros.append(coluna.getNome()).append(": \"O campo '").append(coluna.getNome()).append("' é obrigatório.\",\n\t\t\t");
				}
			}
		}
		
		return erros.length() > 0 ? erros.substring(0, erros.lastIndexOf(",")) : "";
	}
	
	public String getColunasSQL() {
		StringBuffer colunas = new StringBuffer();
		
		for(ColunaVO coluna : getColunas()) {
			colunas.append("${NOME_TABELA}.").append(coluna.getNome()).append(", ");
		}
		
		return colunas.length() > 0 ? colunas.substring(0, colunas.lastIndexOf(",")) : "";
	}
	
	public String getInsertSQL() {
		StringBuffer insert = new StringBuffer("INSERT INTO ${NOME_TABELA} (");
		
		for(ColunaVO coluna : getColunas()) {
			if(coluna.getChave() != TipoChave.PRI || (coluna.getChave() == TipoChave.PRI && coluna.getPadrao() != PadraoColuna.AUTO_INCREMENT)) {
				insert.append(coluna.getNome()).append(", ");
			}
		}
		
		insert = new StringBuffer(insert.substring(0, insert.lastIndexOf(",")));
		insert.append(") VALUES (");
		
		for(ColunaVO coluna : getColunas()) {
			if(coluna.getChave() != TipoChave.PRI || (coluna.getChave() == TipoChave.PRI && coluna.getPadrao() != PadraoColuna.AUTO_INCREMENT)) {
				insert.append("?, ");
			}
		}
		
		insert = new StringBuffer(insert.substring(0, insert.lastIndexOf(",")));
		insert.append(")");
		
		return insert.toString();
	}
	
	public String getParametrosInsert() {
		StringBuffer campos = new StringBuffer();
		
		for(ColunaVO coluna : getColunas()) {
			if(coluna.getChave() != TipoChave.PRI || (coluna.getChave() == TipoChave.PRI && coluna.getPadrao() != PadraoColuna.AUTO_INCREMENT)) {
				if(coluna.getChave() != null && coluna.getChave() == TipoChave.MUL) {
					for(RelacionamentoVO relacionamento: getRelacionamentos()) {
						if(coluna.getNome().equals(relacionamento.getColunaRelacionada())) {
							campos.append(getNomeMinusculo()).append(".").append(relacionamento.getNomeTabelaMinusculo()).append(".id,\n\t\t\t\t");
							break;
						}
					}
				} else {
					campos.append(getNomeMinusculo()).append(".").append(coluna.getNome()).append(",\n\t\t\t\t");
				}
			}
		}
		
		campos = new StringBuffer(campos.substring(0, campos.lastIndexOf(",")));
		
		return campos.toString();
	}
	
	public String getUpdateSQL() {
		StringBuffer update = new StringBuffer("UPDATE ${NOME_TABELA} SET ");
		
		for(ColunaVO coluna : getColunas()) {
			if(coluna.getChave() != TipoChave.PRI || (coluna.getChave() == TipoChave.PRI && coluna.getPadrao() != PadraoColuna.AUTO_INCREMENT)) {
				update.append(coluna.getNome()).append(" = ?, ");
			}
		}
		
		update = new StringBuffer(update.substring(0, update.lastIndexOf(",")));
		update.append(" WHERE id = ?");
		
		return update.toString();
	}
	
	public String getParametrosUpdate() {
		StringBuffer campos = new StringBuffer();
		
		int posRel = 0;
		for(ColunaVO coluna : getColunas()) {
			if(coluna.getChave() != TipoChave.PRI || (coluna.getChave() == TipoChave.PRI && coluna.getPadrao() != PadraoColuna.AUTO_INCREMENT)) {
				if(coluna.getChave() != null && coluna.getChave() == TipoChave.MUL) {
					campos.append(getNomeMinusculo()).append(".").append(getRelacionamentos().get(posRel++).getNomeTabelaMinusculo()).append(".id,\n\t\t\t\t");
				} else {
					campos.append(getNomeMinusculo()).append(".").append(coluna.getNome()).append(",\n\t\t\t\t");
				}
			}
		}
		
		for(ColunaVO coluna : getColunas()) {
			if(coluna.getChave() == TipoChave.PRI) {
				campos.append(getNomeMinusculo()).append(".").append(coluna.getNome()).append(",\n\t\t\t\t");
				break;
			}
		}
		
		campos = new StringBuffer(campos.substring(0, campos.lastIndexOf(",")));
		
		return campos.toString();
	}
	
	public String getFiltrosColunasLista(TabelaVO tabela) {
		StringBuffer filtros = new StringBuffer();
		
		for(ColunaVO coluna : this.getColunas()) {
			if(!coluna.isNulo()) {
				switch(coluna.getTipoData()) {
					case TEXT:
					case VARCHAR:
						filtros.append("if(").append(String.join(".", tabela.getNomeMinusculo(), coluna.getNome())).append(" !== undefined  && ").append(String.join(".", tabela.getNomeMinusculo(), coluna.getNome(), "trim()", "lenght")).append(" !== 0 ) {\n\t\t\t")
						.append("sql = BaseDAO.incluirClausulaNoWhereAND(sql, '").append(coluna.getNome()).append(" LIKE ? ');\n\t\t\t")
						.append("parametros.push(`%${").append(String.join(".", tabela.getNomeMinusculo(), coluna.getNome(), "trim()")).append("}%`);\n\t\t")
						.append("}\n\t\t");
						break;
					default:
						filtros.append("if(").append(String.join(".", tabela.getNomeMinusculo(), coluna.getNome())).append(" !== undefined) {\n\t\t\t")
						.append("sql = BaseDAO.incluirClausulaNoWhereAND(sql, '").append(coluna.getNome()).append(" = ? ');\n\t\t\t")
						.append("parametros.push(").append(String.join(".", tabela.getNomeMinusculo(), coluna.getNome())).append(");\n\t\t")
						.append("}\n\t\t");
						break;
				}
			}
		}
		
		return filtros.length() > 0 ? filtros.toString() : "";
	}
	
	public String getRecuperarRelacionamentos(TabelaVO tabela) {
		StringBuffer relacionamentosARecuperar = new StringBuffer();
		
		if(tabela.getRelacionamentos() != null && !tabela.getRelacionamentos().isEmpty()) {
			relacionamentosARecuperar.append("\n\t\t").append("if(").append(tabela.getNomeMinusculo()).append(" !== undefined) {\n\t\t\t")
			.append("var resultado;");
			
			for(RelacionamentoVO relacionamento : tabela.getRelacionamentos()) {
				relacionamentosARecuperar.append("\n\t\t\t//----------------------------------------").append("\n\t\t\t")
				.append("resultado = await ").append(relacionamento.getNomeTabela()).append(String.join(".", "Fachada", "recuperar(")).append(String.join(".", tabela.getNomeMinusculo(), relacionamento.getColunaRelacionada())).append(");\n\t\t\t")
				.append(String.join(".", tabela.getNomeMinusculo(), relacionamento.getNomeTabelaMinusculo())).append(" = resultado.resultado;\n\t\t\t")
				.append("delete ").append(String.join(".", tabela.getNomeMinusculo(), relacionamento.getColunaRelacionada())).append(";");
			}
			
			relacionamentosARecuperar.append("\n\t\t\t//----------------------------------------").append("\n\t\t}");
		}
		
		return relacionamentosARecuperar.toString();
	}
	
	public String getRecuperarRelacionamentosExterno(TabelaVO tabela) {
		StringBuffer relacionamentosARecuperar = new StringBuffer();
		
		if(tabela.getRelacionamentosExterno() != null && !tabela.getRelacionamentosExterno().isEmpty()) {
			relacionamentosARecuperar.append("\n\t\t").append("if(listasRel) {\n\t\t\t")
			.append("var resultado;");
			
			for(RelacionamentoExternoVO relacionamento : tabela.getRelacionamentosExterno()) {
				relacionamentosARecuperar.append("\n\t\t\t//----------------------------------------").append("\n\t\t\t")
				.append("var ").append(relacionamento.getNomeTabela()).append("Fachada = require(\"./").append(relacionamento.getNomeTabela()).append("Fachada\");\n\t\t\t")
				.append("var ").append(relacionamento.getNomeTabelaMinusculo()).append(" = { ").append(relacionamento.getCounaReferencia()).append(": ").append(String.join(".", tabela.getNomeMinusculo(), relacionamento.getColunaReferenciada())).append(" };\n\t\t\t")
				.append("resultado = await ").append(relacionamento.getNomeTabela()).append(String.join(".", "Fachada", "listar(")).append(relacionamento.getNomeTabelaMinusculo()).append(");\n\t\t\t")
				.append(String.join(".", tabela.getNomeMinusculo(), "lista"+relacionamento.getNomeTabela())).append(" = resultado.lista;");
			}
			
			relacionamentosARecuperar.append("\n\t\t\t//----------------------------------------").append("\n\t\t}");
		}
		
		return relacionamentosARecuperar.toString();
	}
	
	public String getRecuperarImportRelacionamentos(TabelaVO tabela) {
		StringBuffer importRelacionamento = new StringBuffer();
		
		for(RelacionamentoVO relacionamento : tabela.getRelacionamentos()) {
			importRelacionamento.append("var ").append(relacionamento.getNomeTabela()).append("Fachada = require(\"./").append(relacionamento.getNomeTabela()).append("Fachada\");\n");
		}
		
		return importRelacionamento.length() != 0 ? importRelacionamento.toString().substring(0, importRelacionamento.toString().lastIndexOf("\n")) : "";
	}
	
	public String getRecuperarImportRelacionamentosExternos(TabelaVO tabela) {
		StringBuffer importRelacionamento = new StringBuffer();
		
		for(RelacionamentoExternoVO relacionamento : tabela.getRelacionamentosExterno()) {
			importRelacionamento.append("var ").append(relacionamento.getNomeTabela()).append("Fachada = require(\"./").append(relacionamento.getNomeTabela()).append("Fachada\");\n");
		}
		
		return importRelacionamento.length() != 0 ? importRelacionamento.toString().substring(0, importRelacionamento.toString().lastIndexOf("\n")) : "";
	}
}
