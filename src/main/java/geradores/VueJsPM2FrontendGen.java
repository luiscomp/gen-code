package geradores;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.velocity.VelocityContext;

import dao.FabricaDAO;
import dao.TabelaDAO;
import geradores.interpretadores.VueJsPM2FrontendInt;
import singletons.ProjSingleton;
import ui.interfaces.OnProgress;
import ui.model.ProjetoModel;
import util.Constantes;
import util.LoggerApp;
import util.UrlFilesPathVueJsPM2Frontend;
import util.Util;
import vo.TabelaVO;

public class VueJsPM2FrontendGen {
	
	private VelocityContext ctx;
	private ArrayList<TabelaVO> listaTabelas;
	private OnProgress callback;

	public VueJsPM2FrontendGen(ArrayList<TabelaVO> listaTabelas, OnProgress callback) {
		super();
		this.listaTabelas = listaTabelas;
		this.callback = callback;
	}
	
	public void gerarCodigo() throws IOException {
		int passoAtual = 0;
		int totalPassos = 17;
		callback.onProgress(passoAtual++, totalPassos, String.join(": ", Util.getHoraAtual(),"\nIniciando ... [VUEJS_PM2]\n"));
		
		gerarPastas();
		callback.onProgress(passoAtual++, totalPassos, String.join(": ", Util.getHoraAtual(),"Paths ... [OK]\n"));
		
		gerarArquivosRoot();
		callback.onProgress(passoAtual++, totalPassos, String.join(": ", Util.getHoraAtual(),"Arquivos Root ... [OK]\n"));
		
		gerarArquivosAssets();
		callback.onProgress(passoAtual++, totalPassos, String.join(": ", Util.getHoraAtual(),"Arquivos Assets ... [OK]\n"));
		
		gerarArquivosAssetsImagens();
		callback.onProgress(passoAtual++, totalPassos, String.join(": ", Util.getHoraAtual(),"Arquivos Imagens ... [OK]\n"));
		
		gerarArquivosBuild();
		callback.onProgress(passoAtual++, totalPassos, String.join(": ", Util.getHoraAtual(),"Arquivos Build ... [OK]\n"));
		
		gerarArquivosComponents();
		callback.onProgress(passoAtual++, totalPassos, String.join(": ", Util.getHoraAtual(),"Arquivos Components ... [OK]\n"));
		
		gerarArquivosComponentsCadastros();
		callback.onProgress(passoAtual++, totalPassos, String.join(": ", Util.getHoraAtual(),"Arquivos Cadastros ... [OK]\n"));
		
		gerarArquivosComponentsFiltros();
		callback.onProgress(passoAtual++, totalPassos, String.join(": ", Util.getHoraAtual(),"Arquivos Filtros ... [OK]\n"));
		
		gerarArquivosComponentsModals();
		callback.onProgress(passoAtual++, totalPassos, String.join(": ", Util.getHoraAtual(),"Arquivos Modals ... [OK]\n"));
		
		gerarArquivosComponentsTemplates();
		callback.onProgress(passoAtual++, totalPassos, String.join(": ", Util.getHoraAtual(),"Arquivos Templates ... [OK]\n"));
		
		gerarArquivosMixins();
		callback.onProgress(passoAtual++, totalPassos, String.join(": ", Util.getHoraAtual(),"Arquivos Mixins ... [OK]\n"));
		
		gerarArquivosPages();
		callback.onProgress(passoAtual++, totalPassos, String.join(": ", Util.getHoraAtual(),"Arquivos Pages ... [OK]\n"));
		
		gerarArquivosRouter();
		callback.onProgress(passoAtual++, totalPassos, String.join(": ", Util.getHoraAtual(),"Arquivos Router ... [OK]\n"));
		
		gerarArquivosSrcUtils();
		callback.onProgress(passoAtual++, totalPassos, String.join(": ", Util.getHoraAtual(),"Arquivos Utils ... [OK]\n"));
		
		gerarArquivosStore();
		callback.onProgress(passoAtual++, totalPassos, String.join(": ", Util.getHoraAtual(),"Arquivos Store ... [OK]\n"));
		
		gerarArquivosStoreModules();
		callback.onProgress(passoAtual++, totalPassos, String.join(": ", Util.getHoraAtual(),"Arquivos Modules ... [OK]\n"));
		
		gerarArquivosStatic();
		callback.onProgress(passoAtual++, totalPassos, String.join(": ", Util.getHoraAtual(),"Arquivos Static ... [OK]\n"));
		
		gerarArquivoBat();
		callback.onProgress(passoAtual++, totalPassos, String.join(": ", Util.getHoraAtual(),"Código gerado com sucesso... [VUEJS_PM2]"));
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
			Util.criarDiretorio(UrlFilesPathVueJsPM2Frontend.rootFrontPath);
			Util.criarDiretorio(UrlFilesPathVueJsPM2Frontend.assetsFrontPath);
			Util.criarDiretorio(UrlFilesPathVueJsPM2Frontend.assetsImagensFrontPath);
			Util.criarDiretorio(UrlFilesPathVueJsPM2Frontend.buildFrontPath);
			Util.criarDiretorio(UrlFilesPathVueJsPM2Frontend.componentsFrontPath);
			Util.criarDiretorio(UrlFilesPathVueJsPM2Frontend.componentsCadastrosFrontPath);
			Util.criarDiretorio(UrlFilesPathVueJsPM2Frontend.componentsFiltrosFrontPath);
			Util.criarDiretorio(UrlFilesPathVueJsPM2Frontend.componentsModalsFrontPath);
			Util.criarDiretorio(UrlFilesPathVueJsPM2Frontend.componentsTemplatesFrontPath);
			Util.criarDiretorio(UrlFilesPathVueJsPM2Frontend.mixinsFrontPath);
			Util.criarDiretorio(UrlFilesPathVueJsPM2Frontend.pagesFrontPath);
			Util.criarDiretorio(UrlFilesPathVueJsPM2Frontend.routerFrontPath);
			Util.criarDiretorio(UrlFilesPathVueJsPM2Frontend.srcFrontPath);
			Util.criarDiretorio(UrlFilesPathVueJsPM2Frontend.srcUtilsFrontPath);
			Util.criarDiretorio(UrlFilesPathVueJsPM2Frontend.storeFrontPath);
			Util.criarDiretorio(UrlFilesPathVueJsPM2Frontend.storeModuleFrontPath);
			Util.criarDiretorio(UrlFilesPathVueJsPM2Frontend.staticFrontPath);
		} catch (IOException e) {
			LoggerApp.logError("Erro em VueJsPM2FrontendGen.gerarPastas()", e);
		}
	}
	
	public void gerarArquivosRoot() throws IOException {
		ctx = new VelocityContext();
		ctx.put("nomeProjeto", ProjSingleton.getProjeto().getNome().toLowerCase());
		ctx.put("interpretador", new VueJsPM2FrontendInt());
		
		Util.copiarArquivo(".babelrc", UrlFilesPathVueJsPM2Frontend.babelrcFile, UrlFilesPathVueJsPM2Frontend.rootFrontPath, ctx);
		Util.copiarArquivo(".editorconfig", UrlFilesPathVueJsPM2Frontend.editorconfigFile, UrlFilesPathVueJsPM2Frontend.rootFrontPath, ctx);
		Util.copiarArquivo(".eslintignore", UrlFilesPathVueJsPM2Frontend.eslintgnoreFile, UrlFilesPathVueJsPM2Frontend.rootFrontPath, ctx);
		Util.copiarArquivo(".eslintrc.js", UrlFilesPathVueJsPM2Frontend.eslintrcFile, UrlFilesPathVueJsPM2Frontend.rootFrontPath, ctx);
		Util.copiarArquivo(".gitignore", UrlFilesPathVueJsPM2Frontend.gitignoreFile, UrlFilesPathVueJsPM2Frontend.rootFrontPath, ctx);
		Util.copiarArquivo("yarn.lock", UrlFilesPathVueJsPM2Frontend.yarnFile, UrlFilesPathVueJsPM2Frontend.rootFrontPath, ctx);
		Util.copiarArquivo("server.js", UrlFilesPathVueJsPM2Frontend.serverFile, UrlFilesPathVueJsPM2Frontend.rootFrontPath, ctx);
		Util.copiarArquivo("README.md", UrlFilesPathVueJsPM2Frontend.readmeFile, UrlFilesPathVueJsPM2Frontend.rootFrontPath, ctx);
		Util.copiarArquivo("package.json", UrlFilesPathVueJsPM2Frontend.packageFile, UrlFilesPathVueJsPM2Frontend.rootFrontPath, ctx);
		Util.copiarArquivo("package-lock.json", UrlFilesPathVueJsPM2Frontend.packageLockFile, UrlFilesPathVueJsPM2Frontend.rootFrontPath, ctx);
	}

	public void gerarArquivosAssets() throws IOException {
		ctx = new VelocityContext();
		ctx.put("modulos", listaTabelas);
		ctx.put("interpretador", new VueJsPM2FrontendInt());
		
		Util.copiarArquivo("app.js", UrlFilesPathVueJsPM2Frontend.appJsFile, UrlFilesPathVueJsPM2Frontend.assetsFrontPath, ctx);
		Util.copiarArquivo("App.vue", UrlFilesPathVueJsPM2Frontend.appVueFile, UrlFilesPathVueJsPM2Frontend.assetsFrontPath, ctx);
		Util.copiarArquivo("entry-client.js", UrlFilesPathVueJsPM2Frontend.entryClientFile, UrlFilesPathVueJsPM2Frontend.assetsFrontPath, ctx);
		Util.copiarArquivo("entry-server.js", UrlFilesPathVueJsPM2Frontend.entryServerFile, UrlFilesPathVueJsPM2Frontend.assetsFrontPath, ctx);
		Util.copiarArquivo("index.template.html", UrlFilesPathVueJsPM2Frontend.indexTemplateFile, UrlFilesPathVueJsPM2Frontend.assetsFrontPath, ctx);
	}
	
	public void gerarArquivosAssetsImagens() throws IOException {
		ctx = new VelocityContext();
		ctx.put("nomeProjeto", ProjSingleton.getProjeto().getNome().toLowerCase());
		
		Util.copiarArquivo("avatar.svg", UrlFilesPathVueJsPM2Frontend.avatarFile, UrlFilesPathVueJsPM2Frontend.assetsImagensFrontPath, ctx);
		Util.copiarArquivo("banner.svg", UrlFilesPathVueJsPM2Frontend.bannerFile, UrlFilesPathVueJsPM2Frontend.assetsImagensFrontPath, ctx);
		Util.copiarArquivo("logo.svg", UrlFilesPathVueJsPM2Frontend.logoFile, UrlFilesPathVueJsPM2Frontend.assetsImagensFrontPath, ctx);
		Util.copiarArquivo("photo.svg", UrlFilesPathVueJsPM2Frontend.photoFile, UrlFilesPathVueJsPM2Frontend.assetsImagensFrontPath, ctx);
	}
	
	public void gerarArquivosBuild() throws IOException {
		ctx = new VelocityContext();
		
		Util.copiarArquivo("setup-dev-server.js", UrlFilesPathVueJsPM2Frontend.setupDevServerFile, UrlFilesPathVueJsPM2Frontend.buildFrontPath, ctx);
		Util.copiarArquivo("vue-loader.config.js", UrlFilesPathVueJsPM2Frontend.vueLoaderConfigFile, UrlFilesPathVueJsPM2Frontend.buildFrontPath, ctx);
		Util.copiarArquivo("webpack.base.config.js", UrlFilesPathVueJsPM2Frontend.webpackBaseConfigFile, UrlFilesPathVueJsPM2Frontend.buildFrontPath, ctx);
		Util.copiarArquivo("webpack.client.config.js", UrlFilesPathVueJsPM2Frontend.webpackClientConfigFile, UrlFilesPathVueJsPM2Frontend.buildFrontPath, ctx);
		Util.copiarArquivo("webpack.server.config.js", UrlFilesPathVueJsPM2Frontend.webpackServerConfigFile, UrlFilesPathVueJsPM2Frontend.buildFrontPath, ctx);
	}
	
	public void gerarArquivosComponents() throws IOException {
		ctx = new VelocityContext();

		Util.copiarArquivo("_index.js", UrlFilesPathVueJsPM2Frontend.indexComponentsFile, UrlFilesPathVueJsPM2Frontend.componentsFrontPath, ctx);
		Util.copiarArquivo("Vuetify.vue", UrlFilesPathVueJsPM2Frontend.vuetifyComponentFile, UrlFilesPathVueJsPM2Frontend.componentsFrontPath, ctx);
	}
	
	public void gerarArquivosComponentsCadastros() throws IOException {
		ctx = new VelocityContext();
		ctx.put("modulos", listaTabelas);
		
		for(TabelaVO tabela : listaTabelas) {
			ctx.put("tabela", new VueJsPM2FrontendInt(tabela));
			Util.copiarArquivo(String.join(".", tabela.getNome()+"Cadastro", "vue"), UrlFilesPathVueJsPM2Frontend.cadastroFile, UrlFilesPathVueJsPM2Frontend.componentsCadastrosFrontPath, ctx);
		}
	}
	
	public void gerarArquivosComponentsFiltros() throws IOException {
		ctx = new VelocityContext();
		
		for(TabelaVO tabela : listaTabelas) {
			ctx.put("tabela", new VueJsPM2FrontendInt(tabela));
			Util.copiarArquivo(String.join(".", tabela.getNome()+"Filtro", "vue"), UrlFilesPathVueJsPM2Frontend.filtroFile, UrlFilesPathVueJsPM2Frontend.componentsFiltrosFrontPath, ctx);
		}
	}
	
	public void gerarArquivosComponentsModals() throws IOException {
		ctx = new VelocityContext();
		
		for(TabelaVO tabela : listaTabelas) {
			ctx.put("tabela", new VueJsPM2FrontendInt(tabela));
			Util.copiarArquivo(String.join(".", tabela.getNome()+"Modal", "vue"), UrlFilesPathVueJsPM2Frontend.modalFile, UrlFilesPathVueJsPM2Frontend.componentsModalsFrontPath, ctx);
		}
	}
	
	public void gerarArquivosComponentsTemplates() throws IOException {
		ctx = new VelocityContext();
		ctx.put("interpretador", new VueJsPM2FrontendInt());

		Util.copiarArquivo("Cabecalho.vue", UrlFilesPathVueJsPM2Frontend.cabecalhoTemplatesFile, UrlFilesPathVueJsPM2Frontend.componentsTemplatesFrontPath, ctx);
		Util.copiarArquivo("DateInput.vue", UrlFilesPathVueJsPM2Frontend.dateInputTemplatesFile, UrlFilesPathVueJsPM2Frontend.componentsTemplatesFrontPath, ctx);
		Util.copiarArquivo("DecimalInput.vue", UrlFilesPathVueJsPM2Frontend.decimalInputTemplateFile, UrlFilesPathVueJsPM2Frontend.componentsTemplatesFrontPath, ctx);
		Util.copiarArquivo("ImageBrowser.vue", UrlFilesPathVueJsPM2Frontend.imageBrowserTemplateFile, UrlFilesPathVueJsPM2Frontend.componentsTemplatesFrontPath, ctx);
		Util.copiarArquivo("MessageDialog.vue", UrlFilesPathVueJsPM2Frontend.messageDialogTemplateFile, UrlFilesPathVueJsPM2Frontend.componentsTemplatesFrontPath, ctx);
		Util.copiarArquivo("Paginacao.vue", UrlFilesPathVueJsPM2Frontend.paginacaoTemplateFile, UrlFilesPathVueJsPM2Frontend.componentsTemplatesFrontPath, ctx);
	}
	
	public void gerarArquivosMixins() throws IOException {
		ctx = new VelocityContext();
		
		Util.copiarArquivo("meta.js", UrlFilesPathVueJsPM2Frontend.metaMixisFile, UrlFilesPathVueJsPM2Frontend.mixinsFrontPath, ctx);
	}
	
	public void gerarArquivosPages() throws IOException {
		ctx = new VelocityContext();
		
		Util.copiarArquivo("HomeView.vue", UrlFilesPathVueJsPM2Frontend.homeViewFile, UrlFilesPathVueJsPM2Frontend.pagesFrontPath, ctx);
		for(TabelaVO tabela : listaTabelas) {
			ctx.put("tabela", new VueJsPM2FrontendInt(tabela));
			Util.copiarArquivo(String.join(".", tabela.getNome()+"View", "vue"), UrlFilesPathVueJsPM2Frontend.pageViewFile, UrlFilesPathVueJsPM2Frontend.pagesFrontPath, ctx);
		}
	}
	
	public void gerarArquivosRouter() throws IOException {
		ctx = new VelocityContext();
		ctx.put("nomeProjeto", ProjSingleton.getProjeto().getNome().toLowerCase());
		ctx.put("modulos", listaTabelas);
		ctx.put("interpretador", new VueJsPM2FrontendInt());
		
		Util.copiarArquivo("301.json", UrlFilesPathVueJsPM2Frontend.trezentrosUmFile, UrlFilesPathVueJsPM2Frontend.routerFrontPath, ctx);
		Util.copiarArquivo("index.js", UrlFilesPathVueJsPM2Frontend.indexRouterFile, UrlFilesPathVueJsPM2Frontend.routerFrontPath, ctx);
		Util.copiarArquivo("meta.json", UrlFilesPathVueJsPM2Frontend.metaRouterFile, UrlFilesPathVueJsPM2Frontend.routerFrontPath, ctx);
	}
	
	public void gerarArquivosSrcUtils() throws IOException {
		ctx = new VelocityContext();
		ctx.put("nomeProjeto", ProjSingleton.getProjeto().getNome().toLowerCase());
		ctx.put("modulos", listaTabelas);

		Util.copiarArquivo("Constantes.js", UrlFilesPathVueJsPM2Frontend.contantesFile, UrlFilesPathVueJsPM2Frontend.srcUtilsFrontPath, ctx);
		Util.copiarArquivo("ResponseCodes.js", UrlFilesPathVueJsPM2Frontend.responsesCodesFile, UrlFilesPathVueJsPM2Frontend.srcUtilsFrontPath, ctx);
		Util.copiarArquivo("UrlRequests.js", UrlFilesPathVueJsPM2Frontend.urlRequestsFile, UrlFilesPathVueJsPM2Frontend.srcUtilsFrontPath, ctx);
	}
	
	public void gerarArquivosStore() throws IOException {
		ctx = new VelocityContext();
		ctx.put("modulos", listaTabelas);
		
		Util.copiarArquivo("index.js", UrlFilesPathVueJsPM2Frontend.indexStoreFile, UrlFilesPathVueJsPM2Frontend.storeFrontPath, ctx);
	}
	
	public void gerarArquivosStoreModules() throws IOException {
		ctx = new VelocityContext();
		
		for(TabelaVO tabela: listaTabelas) {
			Util.copiarArquivo(String.join(".", tabela.getNome(), "js"), UrlFilesPathVueJsPM2Frontend.moduleStoreFile, UrlFilesPathVueJsPM2Frontend.storeModuleFrontPath, ctx);
		}
	}
	
	public void gerarArquivosStatic() throws IOException {
		ctx = new VelocityContext();

		Util.copiarArquivo("favicon-32x32.png", UrlFilesPathVueJsPM2Frontend.favicon32x32File, UrlFilesPathVueJsPM2Frontend.staticFrontPath, ctx);
		Util.copiarArquivo("favicon.ico", UrlFilesPathVueJsPM2Frontend.faviconFile, UrlFilesPathVueJsPM2Frontend.staticFrontPath, ctx);
		Util.copiarArquivo("robots.txt", UrlFilesPathVueJsPM2Frontend.robotsFile, UrlFilesPathVueJsPM2Frontend.staticFrontPath, ctx);
		Util.copiarArquivo("sitemap.xml", UrlFilesPathVueJsPM2Frontend.sitemapFile, UrlFilesPathVueJsPM2Frontend.staticFrontPath, ctx);
		Util.copiarArquivo("v.png", UrlFilesPathVueJsPM2Frontend.vFile, UrlFilesPathVueJsPM2Frontend.staticFrontPath, ctx);
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
		ProjSingleton.setPathDestino("C:\\Users\\Pichau\\Desktop\\GenVue");
//		ProjSingleton.setPathDestino("C:\\Users\\Luis Eduardo.DESKTOP-L5FUQPS\\Desktop\\GenVue");
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
				
				VueJsPM2FrontendGen vueGerador = new VueJsPM2FrontendGen(listaTabelas, new OnProgress() {
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
			System.out.println("Falha ao gerar o código.");
			e.printStackTrace();
		}
		
		System.out.println("Finalizado");
	}
}
