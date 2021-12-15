package geradores;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.velocity.VelocityContext;

import dao.FabricaDAO;
import dao.TabelaDAO;
import singletons.ProjSingleton;
import ui.interfaces.OnProgress;
import ui.model.ProjetoModel;
import util.Constantes;
import util.LoggerApp;
import util.Util;
import vo.TabelaVO;

public class VueJsFrontendGen {
	
	private VelocityContext ctx;
	private ArrayList<TabelaVO> listaTabelas;
	private OnProgress callback;
	
	public VueJsFrontendGen(ArrayList<TabelaVO> listaTabelas, OnProgress callback) {
		super();
		this.listaTabelas = listaTabelas;
		this.callback = callback;
	}
	
	public void gerarCodigo() throws IOException {
		int passoAtual = 0;
		int totalPassos = 15;
		callback.onProgress(passoAtual++, totalPassos, String.join(": ", Util.getHoraAtual(),"Iniciando ... [VUEJS]\n"));
		
		gerarPastas();
		callback.onProgress(passoAtual++, totalPassos, String.join(": ", Util.getHoraAtual(),"Paths ... [OK]\n"));

		gerarArquivosRoot();
		callback.onProgress(passoAtual++, totalPassos, String.join(": ", Util.getHoraAtual(),"Arquivos Root ... [OK]\n"));
		
		gerarArquivosBuild();
		callback.onProgress(passoAtual++, totalPassos, String.join(": ", Util.getHoraAtual(),"Arquivos Build ... [OK]\n"));
		
		gerarArquivosConfig();
		callback.onProgress(passoAtual++, totalPassos, String.join(": ", Util.getHoraAtual(),"Arquivos Config ... [OK]\n"));
		
		gerarArquivosSrc();
		callback.onProgress(passoAtual++, totalPassos, String.join(": ", Util.getHoraAtual(),"Arquivos Src ... [OK]\n"));
		
		gerarArquivosSrcAssets();
		callback.onProgress(passoAtual++, totalPassos, String.join(": ", Util.getHoraAtual(),"Arquivos Assets ... [OK]\n"));
		
		gerarArquivosSrcComponents();
		callback.onProgress(passoAtual++, totalPassos, String.join(": ", Util.getHoraAtual(),"Arquivos Componentes ... [OK]\n"));
		
		gerarArquivosSrcComponentsCadastros();
		callback.onProgress(passoAtual++, totalPassos, String.join(": ", Util.getHoraAtual(),"Arquivos Cadastros ... [OK]\n"));
		
		gerarArquivosSrcComponentsFiltros();
		callback.onProgress(passoAtual++, totalPassos, String.join(": ", Util.getHoraAtual(),"Arquivos Filtros ... [OK]\n"));
		
		gerarArquivosSrcComponentsModals();
		callback.onProgress(passoAtual++, totalPassos, String.join(": ", Util.getHoraAtual(),"Arquivos Modals ... [OK]\n"));
		
		gerarArquivosSrcComponentsTemplates();
		callback.onProgress(passoAtual++, totalPassos, String.join(": ", Util.getHoraAtual(),"Arquivos Templates ... [OK]\n"));
		
		gerarArquivosSrcRouter();
		callback.onProgress(passoAtual++, totalPassos, String.join(": ", Util.getHoraAtual(),"Arquivos Router ... [OK]\n"));
		
		gerarArquivosSrcStores();
		callback.onProgress(passoAtual++, totalPassos, String.join(": ", Util.getHoraAtual(),"Arquivos Stores ... [OK]\n"));
		
		gerarArquivosSrcStoresModules();
		callback.onProgress(passoAtual++, totalPassos, String.join(": ", Util.getHoraAtual(),"Arquivos Modules ... [OK]\n"));
		
		gerarArquivosSrcUtils();
		callback.onProgress(passoAtual++, totalPassos, String.join(": ", Util.getHoraAtual(),"Arquivos Utils ... [OK]\n"));

		gerarArquivoBat();
		callback.onProgress(passoAtual++, totalPassos, String.join(": ", Util.getHoraAtual(),"Código gerado com sucesso... [VUEJS]"));
	}

	private void gerarArquivoBat() {
		ctx = new VelocityContext();
		ctx.put("pathProjetoBack", Constantes.rootBackPath);
		ctx.put("pathProjetoFront", Constantes.rootFrontPath);
		
		try {
			String rootProjectPath = System.getProperty("user.dir");
			Util.copiarArquivo("buildNode.bat", "buildNode.vm", rootProjectPath, ctx, rootProjectPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void gerarPastas() {
		try {
			Util.criarDiretorio(Constantes.rootFrontPath);
			Util.criarDiretorio(Constantes.buildFrontPath);
			Util.criarDiretorio(Constantes.configFrontPath);
			Util.criarDiretorio(Constantes.srcFrontPath);
			Util.criarDiretorio(Constantes.srcAssetsFrontPath);
			Util.criarDiretorio(Constantes.srcComponentsFrontPath);
			Util.criarDiretorio(Constantes.srcComponentsCadastrosFrontPath);
			Util.criarDiretorio(Constantes.srcComponentsFiltrosFrontPath);
			Util.criarDiretorio(Constantes.srcComponentsModalsFrontPath);
			Util.criarDiretorio(Constantes.srcComponentsTemplatesFrontPath);
			Util.criarDiretorio(Constantes.srcRouterFrontPath);
			Util.criarDiretorio(Constantes.srcStoresFrontPath);
			Util.criarDiretorio(Constantes.srcStoresModulesFrontPath);
			Util.criarDiretorio(Constantes.srcUtilsFrontPath);
			Util.criarDiretorio(Constantes.staticFrontPath);
		} catch (IOException e) {
			LoggerApp.logError("Erro em VueJsFrontendGen.gerarPastas()", e);
		}
	}
	
	public void gerarArquivosRoot() throws IOException {
		ctx = new VelocityContext();
		ctx.put("nomeProjeto", ProjSingleton.getProjeto().getNome().toLowerCase());

		Util.copiarArquivo("README.md", Constantes.readmeVueFile, Constantes.rootFrontPath, ctx);
		Util.copiarArquivo(".postcssrc.js", Constantes.postcssrcVueFile, Constantes.rootFrontPath, ctx);
		Util.copiarArquivo("package.json", Constantes.packageVueFile, Constantes.rootFrontPath, ctx);
		Util.copiarArquivo("package-lock.json", Constantes.packageLockVueFile, Constantes.rootFrontPath, ctx);
		Util.copiarArquivo("index.html", Constantes.indexRootVueFile, Constantes.rootFrontPath, ctx);
		Util.copiarArquivo(".gitignore", Constantes.gitignoreVueFile, Constantes.rootFrontPath, ctx);
		Util.copiarArquivo(".eslintrc.js", Constantes.eslintrcVueFile, Constantes.rootFrontPath, ctx);
		Util.copiarArquivo(".eslintignore", Constantes.eslintignoreVueFile, Constantes.rootFrontPath, ctx);
		Util.copiarArquivo(".editorconfig", Constantes.editorconfigVueFile, Constantes.rootFrontPath, ctx);
		Util.copiarArquivo(".babelrc", Constantes.babelrcVueFile, Constantes.rootFrontPath, ctx);

		Util.copiarArquivo(".gitkeep", Constantes.gitkeepVueFile, Constantes.staticFrontPath, ctx);
	}

	public void gerarArquivosBuild() throws IOException {
		ctx = new VelocityContext();
		
		Util.copiarArquivo("build.js", Constantes.buildVueFile, Constantes.buildFrontPath, ctx);
		Util.copiarArquivo("check-versions.js", Constantes.checkVersionsVueFile, Constantes.buildFrontPath, ctx);
		Util.copiarArquivo("logo.png", Constantes.logoVueFile, Constantes.buildFrontPath, ctx);
		Util.copiarArquivo("utils.js", Constantes.utilsVueFile, Constantes.buildFrontPath, ctx);
		Util.copiarArquivo("vue-loader.conf.js", Constantes.vueLoaderConfVueFile, Constantes.buildFrontPath, ctx);
		Util.copiarArquivo("webpack.base.conf.js", Constantes.webpackBaseConfVueFile, Constantes.buildFrontPath, ctx);
		Util.copiarArquivo("webpack.dev.conf.js", Constantes.webpackDevConfVueFile, Constantes.buildFrontPath, ctx);
		Util.copiarArquivo("webpack.prod.conf.js", Constantes.webpackProdConfVueFile, Constantes.buildFrontPath, ctx);
	}
	
	public void gerarArquivosConfig() throws IOException {
		ctx = new VelocityContext();
		
		Util.copiarArquivo("dev.env.js", Constantes.devEnvVueFile, Constantes.configFrontPath, ctx);
		Util.copiarArquivo("index.js", Constantes.indexConfigVueFile, Constantes.configFrontPath, ctx);
		Util.copiarArquivo("prod.env.js", Constantes.prodEnvVueFile, Constantes.configFrontPath, ctx);
	}
	
	public void gerarArquivosSrc() throws IOException {
		ctx = new VelocityContext();
		ctx.put("modulos", listaTabelas);

		Util.copiarArquivo("App.vue", Constantes.appSrcVueFile, Constantes.srcFrontPath, ctx);
		Util.copiarArquivo("main.js", Constantes.mainSrcVueFile, Constantes.srcFrontPath, ctx);
	}
	
	public void gerarArquivosSrcAssets() throws IOException {
		ctx = new VelocityContext();
		
		Util.copiarArquivo("avatar.svg", Constantes.avatarSrcAssetsVueFile, Constantes.srcAssetsFrontPath, ctx);
		Util.copiarArquivo("banner.svg", Constantes.bannerSrcAssetsVueFile, Constantes.srcAssetsFrontPath, ctx);
		Util.copiarArquivo("logo.svg", Constantes.logoSrcAssetsVueFile, Constantes.srcAssetsFrontPath, ctx);
		Util.copiarArquivo("photo.svg", Constantes.photoSrcAssetsVueFile, Constantes.srcAssetsFrontPath, ctx);
	}
	
	public void gerarArquivosSrcComponents() throws IOException {
		ctx = new VelocityContext();
		
		for(TabelaVO tabela : listaTabelas) {
			ctx.put("tabela", tabela);
			Util.copiarArquivo(String.join(".", tabela.getNome(), "vue"), Constantes.componenteComponentsVueFile, Constantes.srcComponentsFrontPath, ctx);
		}
	}
	
	public void gerarArquivosSrcComponentsCadastros() throws IOException {
		ctx = new VelocityContext();
		
		for(TabelaVO tabela : listaTabelas) {
			ctx.put("tabela", tabela);
			Util.copiarArquivo(String.join(".", tabela.getNome()+"Cadastro", "vue"), Constantes.cadastroCadastrosVueFile, Constantes.srcComponentsCadastrosFrontPath, ctx);
		}
	}
	
	public void gerarArquivosSrcComponentsFiltros() throws IOException {
		ctx = new VelocityContext();
		
		for(TabelaVO tabela : listaTabelas) {
			ctx.put("tabela", tabela);
			Util.copiarArquivo(String.join(".", tabela.getNome()+"Filtro", "vue"), Constantes.filtroFiltrosVueFile, Constantes.srcComponentsFiltrosFrontPath, ctx);
		}
	}
	
	public void gerarArquivosSrcComponentsModals() throws IOException {
		ctx = new VelocityContext();
		
		for(TabelaVO tabela : listaTabelas) {
			ctx.put("tabela", tabela);
			Util.copiarArquivo(String.join(".", tabela.getNome()+"Modal", "vue"), Constantes.modalModalsVueFile, Constantes.srcComponentsModalsFrontPath, ctx);
		}
	}
	
	public void gerarArquivosSrcComponentsTemplates() throws IOException {
		ctx = new VelocityContext();
		
		Util.copiarArquivo("Cabecalho.vue", Constantes.cabecalhoTemplatesVueFile, Constantes.srcComponentsTemplatesFrontPath, ctx);
		Util.copiarArquivo("DecimalInput.vue", Constantes.decimalInputTemplatesVueFile, Constantes.srcComponentsTemplatesFrontPath, ctx);
		Util.copiarArquivo("ImageBrowser.vue", Constantes.imageBrowserTemplatesVueFile, Constantes.srcComponentsTemplatesFrontPath, ctx);
		Util.copiarArquivo("MessageDialog.vue", Constantes.messageDialogTemplatesVueFile, Constantes.srcComponentsTemplatesFrontPath, ctx);
		Util.copiarArquivo("Paginacao.vue", Constantes.paginacaoTemplatesVueFile, Constantes.srcComponentsTemplatesFrontPath, ctx);
	}
	
	public void gerarArquivosSrcRouter() throws IOException {
		ctx = new VelocityContext();
		ctx.put("modulos", listaTabelas);
		
		Util.copiarArquivo("index.js", Constantes.indexRouterVueFile, Constantes.srcRouterFrontPath, ctx);
	}
	
	public void gerarArquivosSrcStores() throws IOException {
		ctx = new VelocityContext();
		ctx.put("modulos", listaTabelas);
		
		Util.copiarArquivo("index.js", Constantes.indexStoresVueFile, Constantes.srcStoresFrontPath, ctx);
	}
	
	public void gerarArquivosSrcStoresModules() throws IOException {
		ctx = new VelocityContext();
		
		for(TabelaVO tabela: listaTabelas) {
			Util.copiarArquivo(String.join(".", tabela.getNome(), "js"), Constantes.moduleStoresModulesVueFile, Constantes.srcStoresModulesFrontPath, ctx);
		}
	}
	
	public void gerarArquivosSrcUtils() throws IOException {
		ctx = new VelocityContext();
		ctx.put("nomeProjeto", ProjSingleton.getProjeto().getNome().toLowerCase());
		ctx.put("urlBaseArquivos", ProjSingleton.getProjeto().getUrlBaseArquivos());
		ctx.put("modulos", listaTabelas);

		Util.copiarArquivo("Constantes.js", Constantes.constantesUtilsVueFile, Constantes.srcUtilsFrontPath, ctx);
		Util.copiarArquivo("ResponseCodes.js", Constantes.responseCodesUtilsVueFile, Constantes.srcUtilsFrontPath, ctx);
		Util.copiarArquivo("UrlRequests.js", Constantes.urlRequestsUtilsVueFile, Constantes.srcUtilsFrontPath, ctx);
	}

	public static void main(String[] args) {
		System.out.println("Iniciado");
		
		ProjetoModel projeto = new ProjetoModel();
		projeto.setNome("Admed");
		projeto.setHostDB("localhost");
		projeto.setServidorDB("jdbc:mysql://localhost:3306/admed");
		projeto.setUsuarioDB("root");
		projeto.setSenhaDB("local!@#dev");
		projeto.setPortaDB("3306");
		projeto.setSchemaDB("admed");
		projeto.setUrlBaseArquivos("http://localhost/admed/");
		
		ProjSingleton.getInstance();
//		ProjSingleton.setPathDestino("C:\\Users\\Pichau\\Desktop\\GenVue");
		ProjSingleton.setPathDestino("C:\\Users\\Luis Eduardo.DESKTOP-L5FUQPS\\Desktop\\GenVue");
		ProjSingleton.setProjeto(projeto);
		
		FabricaDAO.setarParametrosConexao(ProjSingleton.getProjeto().getServidorDB(), ProjSingleton.getProjeto().getUsuarioDB(), ProjSingleton.getProjeto().getSenhaDB());
		
		try {
			if(FabricaDAO.criarConexaoSimples() != null) {
				TabelaDAO dao = new TabelaDAO();
				
				ArrayList<TabelaVO> listaTabelas = dao.listarBanco(new OnProgress() {
					@Override
					public void onProgress(int progress, int total, String... args) {
						System.out.println("progresso: "+progress);
					}
				});
				
				VueJsFrontendGen vueGerador = new VueJsFrontendGen(listaTabelas, new OnProgress() {
					@Override
					public void onProgress(int progress, int total, String... args) {
						System.out.println("progresso: "+progress);
					}
				});
				
				vueGerador.gerarCodigo();
			} else {
				System.out.println("Falha ao criar conexão com Banco.");
			}
		} catch (Exception e) {
			System.out.println("Falha ao criar conexão com Banco Crash.");
		}
		
		System.out.println("Finalizado");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
