package geradores.interpretadores;

import java.util.ArrayList;

import enums.TipoChave;
import enums.TipoData;
import vo.ColunaVO;
import vo.RelacionamentoVO;
import vo.TabelaVO;

public class VueJsPM2FrontendInt {
	private TabelaVO tabela;

	public VueJsPM2FrontendInt() {
		super();
	}

	public VueJsPM2FrontendInt(TabelaVO tabela) {
		super();
		this.tabela = tabela;
	}

	public String getServerInfo() {
		return "`express/${require('express/package.json').version} ` +\n  `vue-server-renderer/${require('vue-server-renderer/package.json').version}`";
	}

	public String getRenderLog() {
		return "console.log(`whole request: ${Date.now() - s}ms`)";
	}

	public String getEntryServerPromisseThen() {
		return "isDev && console.log(`data pre-fetch: ${Date.now() - s}ms`)";
	}
	
	public String getDateInputParseDate() {
		return "`${this.dataFormatada.substring(0, 2)}/${this.dataFormatada.substring(2, 4)}/${this.dataFormatada.substring(4, this.dataFormatada.length)}`";
	}
	
	public String getDataFormatada() {
		return "`${ano}-${mes}-${dia}`";
	}
	
	public String getDateMask() {
		return "##/##/####";
	}
	
	public String getNome() {
		return tabela.getNome();
	}
	
	public String getNomeMinusculo() {
		return tabela.getNomeMinusculo();
	}
	
	public String getMetaRouterJson(ArrayList<TabelaVO> listaTabelas) {
		StringBuffer metaDados = new StringBuffer();		
		for(TabelaVO tabela: listaTabelas) {
			metaDados.append("\"/").append(tabela.getNomeMinusculo()).append("\": {\n\t");
			metaDados.append("\"title\": \"").append(tabela.getNome()).append("\",\n\t");
			metaDados.append("\"description\": \"Visualize ").append(tabela.getNome()).append("\",\n\t");
			metaDados.append("\"keywords\": \"\"\n  ");
			metaDados.append("},\n  ");
		}
		
		return metaDados.length() > 0 ? metaDados.substring(0, metaDados.lastIndexOf(",")) : "";
	}

	public String getColunasTabelaVueFront() {
		StringBuffer colunas = new StringBuffer();

		int count = 1;
		for (ColunaVO coluna : tabela.getColunas()) {
			if ((!coluna.isNulo() && coluna.getChave() != TipoChave.PRI && coluna.getChave() != TipoChave.MUL)
					&& (!coluna.isNulo() && coluna.getValorDefault() == null)) {
				if (count == 1) {
					colunas.append("{ text: '").append(coluna.getNome()).append("', value: '").append(coluna.getNome())
							.append("', sortable: false, aling: 'left', width: '100%' },\n\t\t\t\t\t");
				} else {
					colunas.append("{ text: '").append(coluna.getNome()).append("', value: '").append(coluna.getNome())
							.append("', sortable: false, aling: 'right', width: '100%' },\n\t\t\t\t\t");
				}
				count++;
			}
		}

		return colunas.length() > 0 ? colunas.substring(0, colunas.lastIndexOf("\n")) : "";
	}

	public String getColunasTabelaHtmlVueFront() {
		StringBuffer colunas = new StringBuffer();

		int count = 1;
		for (ColunaVO coluna : tabela.getColunas()) {
			if ((!coluna.isNulo() && coluna.getChave() != TipoChave.PRI && coluna.getChave() != TipoChave.MUL)
					&& (!coluna.isNulo() && coluna.getValorDefault() == null)) {
				if (count == 1) {
					colunas.append("<td class=\"text-md-left\">{{ props.item.").append(coluna.getNome())
							.append(" }}</td>\n\t\t\t\t\t\t\t\t\t");
				} else {
					colunas.append("<td class=\"text-md-right\">{{ props.item.").append(coluna.getNome())
							.append(" }}</td>\n\t\t\t\t\t\t\t\t\t");
				}
				count++;
			}
		}

		return colunas.length() > 0 ? colunas.substring(0, colunas.lastIndexOf("\n")) : "";
	}

	public String getRecuperarRelacionamentosVueFront() {
		StringBuffer relacionamentos = new StringBuffer();

		if (tabela.getRelacionamentos() != null && !tabela.getRelacionamentos().isEmpty()) {
			for (RelacionamentoVO relacionamento : tabela.getRelacionamentos()) {
				relacionamentos.append(relacionamento.getNomeTabelaMinusculo()).append(": {},\n\t\t\t\t");
			}
		}

		return relacionamentos.length() > 0 ? relacionamentos.substring(0, relacionamentos.lastIndexOf(",")) : "";
	}

	public String getRecuperarColunasBooleansVueFront() {
		StringBuffer colunas = new StringBuffer();

		if (tabela.getColunas() != null && !tabela.getColunas().isEmpty()) {
			for (ColunaVO coluna : tabela.getColunas()) {
				if (coluna.getTipoData() == TipoData.TINYINT) {
					colunas.append(coluna.getNome()).append(": false,\n\t\t\t\t");
				}
			}
		}

		return colunas.length() > 0 ? colunas.substring(0, colunas.lastIndexOf("\n")) : "";
	}

	public String getRecuperarColunasDecimaisVueFront() {
		StringBuffer colunas = new StringBuffer();

		if (tabela.getColunas() != null && !tabela.getColunas().isEmpty()) {
			for (ColunaVO coluna : tabela.getColunas()) {
				if (coluna.getTipoData() == TipoData.DECIMAL) {
					colunas.append(coluna.getNome()).append(": 0.0,\n\t\t\t\t");
				}
			}
		}

		return colunas.length() > 0 ? colunas.substring(0, colunas.lastIndexOf("\n")) : "";
	}

	public String getColunasImagemVueFrontComponent() {
		StringBuffer colunas = new StringBuffer();

		if (tabela.getColunas() != null && !tabela.getColunas().isEmpty()) {
			for (ColunaVO coluna : tabela.getColunas()) {
				if (coluna.isImagem()) {
					colunas.append("this.").append(tabela.getNomeMinusculo()).append(".imagem = this.")
							.append(String.join(".", tabela.getNomeMinusculo(), coluna.getNome()))
							.append(" ? Constantes.URL_IMAGENS.concat(this.")
							.append(String.join(".", tabela.getNomeMinusculo(), coluna.getNome()))
							.append(") : require('../assets/imagens/photo.svg')\n\t\t\t\t\t");
				}
			}
		}

		return colunas.length() > 0 ? colunas.substring(0, colunas.lastIndexOf("\n")) : "";
	}

	public String getCamposCadastroImagemVueFront() {
		StringBuffer campos = new StringBuffer();

		if (tabela.getColunas() != null && !tabela.getColunas().isEmpty()) {
			for (ColunaVO coluna : tabela.getColunas()) {
				if (coluna.isImagem()) {
					campos.append("<v-layout row wrap>\n\t\t\t\t\t\t\t");
					campos.append("<v-flex xs12 sm6 md12>\n\t\t\t\t\t\t\t\t");
					campos.append("<ImageBrowser v-model=\"")
							.append(String.join(".", tabela.getNomeMinusculo(), "imagem"))
							.append("\" :aspectRatio=\"16/9\"></ImageBrowser>\n\t\t\t\t\t\t\t");
					campos.append("</v-flex>\n\t\t\t\t\t\t");
					campos.append("</v-layout>\n\t\t\t\t\t\t");
				}
			}
		}

		return campos.length() > 0 ? campos.substring(0, campos.lastIndexOf("\n")) : "";
	}

	public String getCamposCadastroFrontVue() {
		StringBuffer campos = new StringBuffer();

		if (tabela.getColunas() != null && !tabela.getColunas().isEmpty()) {
			for (ColunaVO coluna : tabela.getColunas()) {
				if (coluna.getChave() != TipoChave.PRI && coluna.getChave() != TipoChave.MUL && !coluna.isImagem()) {
					campos.append("<v-layout row wrap>\n\t\t\t\t\t\t\t");
					campos.append("<v-flex d-flex xs12 sm12 md12>\n\t\t\t\t\t\t\t\t");

					switch (coluna.getTipoData()) {
					case INT:
					case BIGINT:
						if (coluna.isNulo()) {
							campos.append("<v-text-field v-model.number=\"")
									.append(String.join(".", tabela.getNomeMinusculo(), coluna.getNome()))
									.append("\" label=\"").append(coluna.getNome())
									.append("\"></v-text-field>\n\t\t\t\t\t\t\t");
						} else {
							campos.append("<v-text-field v-model.number=\"")
									.append(String.join(".", tabela.getNomeMinusculo(), coluna.getNome()))
									.append("\" label=\"").append(coluna.getNome())
									.append("\" required :rules=\"validacao.").append(coluna.getNome())
									.append("\"></v-text-field>\n\t\t\t\t\t\t\t");
						}
						break;
					case TEXT:
					case MEDIUMTEXT:
					case LONGTEXT:
					case VARCHAR:
						if (coluna.isNulo()) {
							campos.append("<v-text-field v-model=\"")
									.append(String.join(".", tabela.getNomeMinusculo(), coluna.getNome()))
									.append("\" type=\"text\" label=\"").append(coluna.getNome())
									.append("\"></v-text-field>\n\t\t\t\t\t\t\t");
						} else {
							campos.append("<v-text-field v-model=\"")
									.append(String.join(".", tabela.getNomeMinusculo(), coluna.getNome()))
									.append("\" type=\"text\" label=\"").append(coluna.getNome())
									.append("\" required :rules=\"validacao.").append(coluna.getNome())
									.append("\"></v-text-field>\n\t\t\t\t\t\t\t");
						}
						break;
					case DATE:
						if (coluna.isNulo()) {
							campos.append("<DateInput v-model=\"").append(String.join(".", tabela.getNomeMinusculo(), coluna.getNome())).append("\" label=\"").append(coluna.getNome()).append("\"></DateInput>\n\t\t\t\t\t\t\t");
						} else {
							campos.append("<DateInput v-model=\"").append(String.join(".", tabela.getNomeMinusculo(), coluna.getNome())).append("\" label=\"").append(coluna.getNome()).append("\" required :rules=\"validacao.").append(coluna.getNome()).append("\"></DateInput>\n\t\t\t\t\t\t\t");
						}
						break;
					case TIME: // TODO - PRECISA FAZER CÓDIGO COM COMPONENTE TIME
						if (coluna.isNulo()) {
							campos.append("<v-text-field v-model=\"")
									.append(String.join(".", tabela.getNomeMinusculo(), coluna.getNome()))
									.append("\" type=\"text\" label=\"").append(coluna.getNome())
									.append("\"></v-text-field>\n\t\t\t\t\t\t\t");
						} else {
							campos.append("<v-text-field v-model=\"")
									.append(String.join(".", tabela.getNomeMinusculo(), coluna.getNome()))
									.append("\" type=\"text\" label=\"").append(coluna.getNome())
									.append("\" required :rules=\"validacao.").append(coluna.getNome())
									.append("\"></v-text-field>\n\t\t\t\t\t\t\t");
						}
						break;
					case DATETIME:
					case TIMESTAMP: // TODO - PRECISA FAZER CÓDIGO COM COMPONENTE TIMESTAMP
						if (coluna.isNulo()) {
							campos.append("<DateInput v-model=\"").append(String.join(".", tabela.getNomeMinusculo(), coluna.getNome())).append("\" label=\"").append(coluna.getNome()).append("\"></DateInput>\n\t\t\t\t\t\t\t");
						} else {
							campos.append("<DateInput v-model=\"").append(String.join(".", tabela.getNomeMinusculo(), coluna.getNome())).append("\" label=\"").append(coluna.getNome()).append("\" required :rules=\"validacao.").append(coluna.getNome()).append("\"></DateInput>\n\t\t\t\t\t\t\t");
						}
						break;
					case DECIMAL:
					case DOUBLE:
					case FLOAT:
						if (coluna.isNulo()) {
							campos.append("<DecimalInput v-model.number=\"")
									.append(String.join(".", tabela.getNomeMinusculo(), coluna.getNome()))
									.append("\" label=\"").append(coluna.getNome())
									.append("\" :precision=\"2\" />\n\t\t\t\t\t\t\t");
						} else {
							campos.append("<DecimalInput v-model.number=\"")
									.append(String.join(".", tabela.getNomeMinusculo(), coluna.getNome()))
									.append("\" label=\"").append(coluna.getNome())
									.append("\" :precision=\"2\" />\n\t\t\t\t\t\t\t");
						}
						break;
					case ENUM: // TODO - PRECISA FAZER O CÓDIGO COM COMPONENTE COMBOBOX PARA ENUM
						if (coluna.isNulo()) {
							campos.append("<v-text-field v-model=\"")
									.append(String.join(".", tabela.getNomeMinusculo(), coluna.getNome()))
									.append("\" type=\"text\" label=\"").append(coluna.getNome())
									.append("\"></v-text-field>\n\t\t\t\t\t\t\t");
						} else {
							campos.append("<v-text-field v-model=\"")
									.append(String.join(".", tabela.getNomeMinusculo(), coluna.getNome()))
									.append("\" type=\"text\" label=\"").append(coluna.getNome())
									.append("\" required :rules=\"validacao.").append(coluna.getNome())
									.append("\"></v-text-field>\n\t\t\t\t\t\t\t");
						}
						break;
					case TINYINT: // TODO - PRECISA FAZER O CÓDIGO COM COMPONENTE COMBOBOX PARA BOOLEAN
						campos.append("<v-switch v-model=\"").append(String.join(".", tabela.getNomeMinusculo(), coluna.getNome())).append("\" label=\"").append(coluna.getNome()).append("\"></v-switch>\n\t\t\t\t\t\t\t");
						break;
					default:
						if (coluna.isNulo()) {
							campos.append("<v-text-field v-model=\"")
									.append(String.join(".", tabela.getNomeMinusculo(), coluna.getNome()))
									.append("\" type=\"text\" label=\"").append(coluna.getNome())
									.append("\"></v-text-field>\n\t\t\t\t\t\t\t");
						} else {
							campos.append("<v-text-field v-model=\"")
									.append(String.join(".", tabela.getNomeMinusculo(), coluna.getNome()))
									.append("\" type=\"text\" label=\"").append(coluna.getNome())
									.append("\" required :rules=\"validacao.").append(coluna.getNome())
									.append("\"></v-text-field>\n\t\t\t\t\t\t\t");
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
		
		if(tabela.getRelacionamentos() != null && !tabela.getRelacionamentos().isEmpty()) {
			for(RelacionamentoVO relacionamento: tabela.getRelacionamentos()) {
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

		if (tabela.getRelacionamentos() != null && !tabela.getRelacionamentos().isEmpty()) {
			for (RelacionamentoVO relacionamento : tabela.getRelacionamentos()) {
				relacionamentos.append("<").append(relacionamento.getNomeTabelaMinusculo())
						.append("-modal v-model=\"modal").append(relacionamento.getNomeTabela())
						.append(".exibir\" @selecionar=\"v => ")
						.append(String.join(".", tabela.getNomeMinusculo(), relacionamento.getNomeTabelaMinusculo()))
						.append(" = v[0]\"/>\n\t\t\t");
			}
		}

		return relacionamentos.length() > 0 ? relacionamentos.substring(0, relacionamentos.lastIndexOf("\n")) : "";
	}

	public String getImportsCadastroVueFront() {
		StringBuffer imports = new StringBuffer();

		if (tabela.getColunas() != null && !tabela.getColunas().isEmpty()) {
			for (ColunaVO coluna : tabela.getColunas()) {
				if (coluna.isImagem()) {
					imports.append("import ImageBrowser from '../templates/ImageBrowser'\n");
					break;
				}
			}

			for (ColunaVO coluna : tabela.getColunas()) {
				if (coluna.getTipoData() == TipoData.DECIMAL || coluna.getTipoData() == TipoData.DOUBLE || coluna.getTipoData() == TipoData.FLOAT) {
					imports.append("import DecimalInput from '../templates/DecimalInput'\n");
					break;
				}
			}
			
			for (ColunaVO coluna : tabela.getColunas()) {
				if (coluna.getTipoData() == TipoData.DATE || coluna.getTipoData() == TipoData.DATETIME || coluna.getTipoData() == TipoData.TIMESTAMP) {
					imports.append("import DateInput from '../templates/DateInput'\n");
					break;
				}
			}
		}

		if (tabela.getRelacionamentos() != null && !tabela.getRelacionamentos().isEmpty()) {
			for (RelacionamentoVO relacionamento : tabela.getRelacionamentos()) {
				imports.append("import ").append(relacionamento.getNomeTabela()).append("Modal from '../modals/")
						.append(relacionamento.getNomeTabela()).append("Modal'\n");
			}
		}

		return imports.length() > 0 ? imports.substring(0, imports.lastIndexOf("\n")) : "";
	}

	public String getAddComponentCadastroVueFront() {
		StringBuffer imports = new StringBuffer();

		if (tabela.getColunas() != null && !tabela.getColunas().isEmpty()) {
			for (ColunaVO coluna : tabela.getColunas()) {
				if (coluna.isImagem()) {
					imports.append("ImageBrowser,\n\t\t");
					break;
				}
			}

			for (ColunaVO coluna : tabela.getColunas()) {
				if (coluna.getTipoData() == TipoData.DECIMAL || coluna.getTipoData() == TipoData.DOUBLE
						|| coluna.getTipoData() == TipoData.FLOAT) {
					imports.append("DecimalInput,\n\t\t");
					break;
				}
			}

			for (ColunaVO coluna : tabela.getColunas()) {
				if (coluna.getTipoData() == TipoData.DATE || coluna.getTipoData() == TipoData.DATETIME || coluna.getTipoData() == TipoData.TIMESTAMP) {
					imports.append("DateInput,\n\t\t");
					break;
				}
			}
		}

		if (tabela.getRelacionamentos() != null && !tabela.getRelacionamentos().isEmpty()) {
			for (RelacionamentoVO relacionamento : tabela.getRelacionamentos()) {
				imports.append(relacionamento.getNomeTabela()).append("Modal,\n\t\t");
			}
		}

		return imports.length() > 0 ? imports.substring(0, imports.lastIndexOf(",")) : "";
	}

	public String getModalDataCadastroVueFront() {
		StringBuffer modalRelacionamento = new StringBuffer();

		if (tabela.getRelacionamentos() != null && !tabela.getRelacionamentos().isEmpty()) {
			for (RelacionamentoVO relacionamento : tabela.getRelacionamentos()) {
				modalRelacionamento.append("modal").append(relacionamento.getNomeTabela()).append(": {\n\t\t\t\t");
				modalRelacionamento.append("exibir: false\n\t\t\t");
				modalRelacionamento.append("},\n\t\t\t");
			}
		}

		return modalRelacionamento.length() > 0
				? modalRelacionamento.substring(0, modalRelacionamento.lastIndexOf("\n"))
				: "";
	}

	public String getValidadorCadastroVueFront() {
		StringBuffer colunas = new StringBuffer("validacao: {\n\t\t\t\t");

		if (tabela.getColunas() != null && !tabela.getColunas().isEmpty()) {
			for (ColunaVO coluna : tabela.getColunas()) {
				if ((!coluna.isNulo() && coluna.getChave() != TipoChave.PRI && coluna.getChave() != TipoChave.MUL) && (!coluna.isNulo() && coluna.getValorDefault() == null) && coluna.getTipoData() != TipoData.TINYINT) {
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

		if (tabela.getRelacionamentos() != null && !tabela.getRelacionamentos().isEmpty()) {
			colunas.append("\t");
			for (RelacionamentoVO relacionamento : tabela.getRelacionamentos()) {
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

		if (tabela.getColunas() != null && !tabela.getColunas().isEmpty()) {
			for (ColunaVO coluna : tabela.getColunas()) {
				if (!coluna.isNulo() && coluna.getChave() != TipoChave.PRI && coluna.getChave() != TipoChave.MUL) {
					filtros.append("<v-flex xs12 sm12 md6 pl-1 pr-1>\n\t\t\t\t");
					filtros.append("<v-text-field v-model=\"")
							.append(String.join(".", tabela.getNomeMinusculo(), coluna.getNome())).append("\" label=\"")
							.append(coluna.getNome())
							.append("\" type=\"text\" @keyup.enter=\"pesquisar\"></v-text-field>\n\t\t\t");
					filtros.append("</v-flex>\n\t\t\t");
				}
			}
		}

		return filtros.length() > 0 ? filtros.substring(0, filtros.lastIndexOf("\n")) : "";
	}

	public String getBuscaHtmlModalVueFront() {
		StringBuffer busca = new StringBuffer();

		if (tabela.getColunas() != null && !tabela.getColunas().isEmpty()) {
			for (ColunaVO coluna : tabela.getColunas()) {
				if (!coluna.isNulo() && coluna.getChave() != TipoChave.PRI && coluna.getChave() != TipoChave.MUL
						&& coluna.getTipoData() == TipoData.VARCHAR) {
					busca.append("<v-flex xs12 sm12 md12>\n\t\t\t\t\t\t\t");
					busca.append("<v-text-field v-model=\"busca\" type=\"text\" label=\"").append(coluna.getNome())
							.append("\"></v-text-field>\n\t\t\t\t\t\t");
					busca.append("</v-flex>\n\t\t\t\t\t\t");
					break;
				}
			}
		}

		return busca.length() > 0 ? busca.substring(0, busca.lastIndexOf("\n")) : "";
	}

	public String getColunaHtmlModalVueFront() {
		StringBuffer colunaVisivel = new StringBuffer();

		if (tabela.getColunas() != null && !tabela.getColunas().isEmpty()) {
			for (ColunaVO coluna : tabela.getColunas()) {
				if (!coluna.isNulo() && coluna.getChave() != TipoChave.PRI && coluna.getChave() != TipoChave.MUL
						&& coluna.getTipoData() == TipoData.VARCHAR) {
					colunaVisivel.append("<td>{{ props.item.").append(coluna.getNome()).append(" }}</td>");
					break;
				}
			}
		}

		return colunaVisivel.toString();
	}

	public String getColunasModalVueFront() {
		StringBuffer colunas = new StringBuffer();

		for (ColunaVO coluna : tabela.getColunas()) {
			if (!coluna.isNulo() && coluna.getChave() != TipoChave.PRI && coluna.getChave() != TipoChave.MUL && coluna.getTipoData() == TipoData.VARCHAR) {
				colunas.append("{ text: '").append(coluna.getNome()).append("', value: '").append(coluna.getNome())
						.append("', sortable: false, aling: 'left', width: '100%' }");
				break;
			}
		}

		return colunas.toString();
	}
}
