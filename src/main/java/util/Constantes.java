package util;

import java.io.File;

import singletons.ProjSingleton;

public class Constantes {
	// ===============================================================================================================================================================
	// * PATHS E FILES PARA GERAÇÃO DO CÓDIGO BACKEND EM NODE + EXPRESS
	// ===============================================================================================================================================================
	// PATH DE DIRETORIOS DESTINOS
	public static final String rootBackPath = 						String.join(File.separator, ProjSingleton.getPathDestino(), ProjSingleton.getProjeto().getNome()+"_Node");
	public static final String binBackPath = 						String.join(File.separator, rootBackPath, "bin");
	public static final String vsCodeBackPath = 					String.join(File.separator, rootBackPath, ".vscode");
	public static final String controlBackPath = 					String.join(File.separator, rootBackPath, "control");
	public static final String controDaoBackPath =					String.join(File.separator, rootBackPath, "control", "dao");
	public static final String controlFachadaBackPath = 			String.join(File.separator, rootBackPath, "control", "fachada");
	public static final String controlMiddlewaresBackPath = 		String.join(File.separator, rootBackPath, "control", "middlewares");
	public static final String controlUtilBackPath = 				String.join(File.separator, rootBackPath, "control", "util");
	public static final String controlValidadoresBackPath = 		String.join(File.separator, rootBackPath, "control", "validadores");
	public static final String controlValidadoresSchemasBackPath = 	String.join(File.separator, rootBackPath, "control", "validadores", "schemas");
	public static final String publicBackPath = 					String.join(File.separator, rootBackPath, "public");
	public static final String publicStylesheetsBackPath = 			String.join(File.separator, rootBackPath, "public", "stylesheets");
	public static final String routersBackPath = 					String.join(File.separator, rootBackPath, "routes");
	public static final String viewsBackPath = 						String.join(File.separator, rootBackPath, "views");
	
	// PATH DE ARQUIVOS
	// ROOT
	public static final String appFile =							String.join(File.separator, "templates", "nodeexpress", "app.vm");
	public static final String gitignoreFile = 						String.join(File.separator, "templates", "nodeexpress", "gitignore.vm");
	public static final String packagelockFile = 					String.join(File.separator, "templates", "nodeexpress", "package-lock.vm");
	public static final String packageFile = 						String.join(File.separator, "templates", "nodeexpress", "package.vm");
	public static final String readmeFile = 						String.join(File.separator, "templates", "nodeexpress", "README.vm");
	// VSCODE
	public static final String launchVsCodeFile = 					String.join(File.separator, "templates", "nodeexpress", "vscode", "launch.vm");
	// PUBLIC - STYLESHEETS
	public static final String stylePublicStylesheetFile = 			String.join(File.separator, "templates", "nodeexpress", "public", "stylesheets", "style.vm");
	// BIN
	public static final String wwwFile = 							String.join(File.separator, "templates", "nodeexpress", "bin", "www.vm");
	// VIEWS
	public static final String errorViewFile = 						String.join(File.separator, "templates", "nodeexpress", "views", "error.vm");
	public static final String indexViewFile = 						String.join(File.separator, "templates", "nodeexpress", "views", "index.vm");
	public static final String layouViewtFile = 					String.join(File.separator, "templates", "nodeexpress", "views", "layout.vm");
	public static final String sessaViewoFile = 					String.join(File.separator, "templates", "nodeexpress", "views", "sessao.vm");
	// ROUTES
	public static final String indexRouterFile = 					String.join(File.separator, "templates", "nodeexpress", "routes", "index.vm");
	public static final String routerRouterFile = 					String.join(File.separator, "templates", "nodeexpress", "routes", "router.vm");
	// VALIDATOR
	public static final String validadorValidadoresFile = 			String.join(File.separator, "templates", "nodeexpress", "control", "validadores", "Validador.vm");
	public static final String schemaValidadoresSchemasFile = 		String.join(File.separator, "templates", "nodeexpress", "control", "validadores", "schemas", "Schema.vm");
	// UTIL
	public static final String codigoRespostaUtilFile = 			String.join(File.separator, "templates", "nodeexpress", "control", "util", "codigoResposta.vm");
	public static final String constantesUtilFile = 				String.join(File.separator, "templates", "nodeexpress", "control", "util", "constantes.vm");
	public static final String utilsUtilFile = 						String.join(File.separator, "templates", "nodeexpress", "control", "util", "utils.vm");
	// MIDDLEWARES
	public static final String validarAcessoMiddlewareFile = 		String.join(File.separator, "templates", "nodeexpress", "control", "middlewares", "ValidarAcesso.vm");
	// FACHADA
	public static final String objetoFachadaFile = 					String.join(File.separator, "templates", "nodeexpress", "control", "fachada", "ObjetoFachada.vm");
	// DAO
	public static final String baseDaoFile = 						String.join(File.separator, "templates", "nodeexpress", "control", "dao", "BaseDAO.vm");
	public static final String objetoDaoFile = 						String.join(File.separator, "templates", "nodeexpress", "control", "dao", "ObjetoDAO.vm");
	
	// ===============================================================================================================================================================
	// * PATHS E FILES PARA GERAÇÃO DO CÓDIGO FRONTEND EM VUEJS
	// ===============================================================================================================================================================
	// PATH DE DIRETORIOS DESTINOS
	public static final String rootFrontPath = 						String.join(File.separator, ProjSingleton.getPathDestino(), ProjSingleton.getProjeto().getNome()+"_Vue");
	public static final String buildFrontPath =						String.join(File.separator, rootFrontPath, "build");
	public static final String configFrontPath =					String.join(File.separator, rootFrontPath, "config");
	public static final String srcFrontPath =						String.join(File.separator, rootFrontPath, "src");
	public static final String srcAssetsFrontPath =					String.join(File.separator, rootFrontPath, "src", "assets");
	public static final String srcComponentsFrontPath =				String.join(File.separator, rootFrontPath, "src", "components");
	public static final String srcComponentsCadastrosFrontPath =	String.join(File.separator, rootFrontPath, "src", "components", "cadastros");
	public static final String srcComponentsFiltrosFrontPath =		String.join(File.separator, rootFrontPath, "src", "components", "filtros");
	public static final String srcComponentsModalsFrontPath =		String.join(File.separator, rootFrontPath, "src", "components", "modals");
	public static final String srcComponentsTemplatesFrontPath =	String.join(File.separator, rootFrontPath, "src", "components", "templates");
	public static final String srcRouterFrontPath =					String.join(File.separator, rootFrontPath, "src", "router");
	public static final String srcStoresFrontPath =					String.join(File.separator, rootFrontPath, "src", "stores");
	public static final String srcStoresModulesFrontPath =			String.join(File.separator, rootFrontPath, "src", "stores", "modules");
	public static final String srcUtilsFrontPath =					String.join(File.separator, rootFrontPath, "src", "utils");
	public static final String staticFrontPath =					String.join(File.separator, rootFrontPath, "static");
	
	// PATH DE ARQUIVOS
	// ROOT
	public static final String readmeVueFile =						String.join(File.separator, "templates", "vuejs", "README.vm");
	public static final String postcssrcVueFile =					String.join(File.separator, "templates", "vuejs", "postcssrc.vm");
	public static final String packageVueFile =						String.join(File.separator, "templates", "vuejs", "package.vm");
	public static final String packageLockVueFile =					String.join(File.separator, "templates", "vuejs", "package-lock.vm");
	public static final String indexRootVueFile =					String.join(File.separator, "templates", "vuejs", "index.vm");
	public static final String gitignoreVueFile =					String.join(File.separator, "templates", "vuejs", "gitignore.vm");
	public static final String eslintrcVueFile =					String.join(File.separator, "templates", "vuejs", "eslintrc.vm");
	public static final String eslintignoreVueFile =				String.join(File.separator, "templates", "vuejs", "eslintignore.vm");
	public static final String editorconfigVueFile =				String.join(File.separator, "templates", "vuejs", "editorconfig.vm");
	public static final String babelrcVueFile =						String.join(File.separator, "templates", "vuejs", "babelrc.vm");
	// STATIC
	public static final String gitkeepVueFile =						String.join(File.separator, "templates", "vuejs", "static", "gitkeep.vm");
	// BUILD
	public static final String buildVueFile =						String.join(File.separator, "templates", "vuejs", "build", "build.vm");
	public static final String checkVersionsVueFile =				String.join(File.separator, "templates", "vuejs", "build", "check-versions.vm");
	public static final String logoVueFile =						String.join(File.separator, "templates", "vuejs", "build", "logo.vm");
	public static final String utilsVueFile =						String.join(File.separator, "templates", "vuejs", "build", "utils.vm");
	public static final String vueLoaderConfVueFile =				String.join(File.separator, "templates", "vuejs", "build", "vue-loader.conf.vm");
	public static final String webpackBaseConfVueFile =				String.join(File.separator, "templates", "vuejs", "build", "webpack.base.conf.vm");
	public static final String webpackDevConfVueFile =				String.join(File.separator, "templates", "vuejs", "build", "webpack.dev.conf.vm");
	public static final String webpackProdConfVueFile =				String.join(File.separator, "templates", "vuejs", "build", "webpack.prod.conf.vm");
	//CONFIG
	public static final String devEnvVueFile =						String.join(File.separator, "templates", "vuejs", "config", "dev.env.vm");
	public static final String indexConfigVueFile =					String.join(File.separator, "templates", "vuejs", "config", "index.vm");
	public static final String prodEnvVueFile =						String.join(File.separator, "templates", "vuejs", "config", "prod.env.vm");
	// SRC
	public static final String appSrcVueFile =						String.join(File.separator, "templates", "vuejs", "src", "App.vm");
	public static final String mainSrcVueFile =						String.join(File.separator, "templates", "vuejs", "src", "main.vm");
	// ASSETS
	public static final String avatarSrcAssetsVueFile =				String.join(File.separator, "templates", "vuejs", "src", "assets", "avatar.vm");
	public static final String bannerSrcAssetsVueFile =				String.join(File.separator, "templates", "vuejs", "src", "assets", "banner.vm");
	public static final String logoSrcAssetsVueFile =				String.join(File.separator, "templates", "vuejs", "src", "assets", "logo.vm");
	public static final String photoSrcAssetsVueFile =				String.join(File.separator, "templates", "vuejs", "src", "assets", "photo.vm");
	// COMPONENTS
	public static final String componenteComponentsVueFile =		String.join(File.separator, "templates", "vuejs", "src", "components", "Componente.vm");
	// CADASTROS
	public static final String cadastroCadastrosVueFile =			String.join(File.separator, "templates", "vuejs", "src", "components", "cadastros", "Cadastro.vm");
	// FILTROS
	public static final String filtroFiltrosVueFile =				String.join(File.separator, "templates", "vuejs", "src", "components", "filtros", "Filtro.vm");
	// MODAL
	public static final String modalModalsVueFile =					String.join(File.separator, "templates", "vuejs", "src", "components", "modals", "Modal.vm");
	// TEMPLATES
	public static final String cabecalhoTemplatesVueFile =			String.join(File.separator, "templates", "vuejs", "src", "components", "templates", "Cabecalho.vm");
	public static final String decimalInputTemplatesVueFile =		String.join(File.separator, "templates", "vuejs", "src", "components", "templates", "DecimalInput.vm");
	public static final String imageBrowserTemplatesVueFile =		String.join(File.separator, "templates", "vuejs", "src", "components", "templates", "ImageBrowser.vm");
	public static final String messageDialogTemplatesVueFile =		String.join(File.separator, "templates", "vuejs", "src", "components", "templates", "MessageDialog.vm");
	public static final String paginacaoTemplatesVueFile =			String.join(File.separator, "templates", "vuejs", "src", "components", "templates", "Paginacao.vm");
	// ROUTER
	public static final String indexRouterVueFile =					String.join(File.separator, "templates", "vuejs", "src", "router", "index.vm");
	// STORES
	public static final String indexStoresVueFile =					String.join(File.separator, "templates", "vuejs", "src", "stores", "index.vm");
	// MODULES
	public static final String moduleStoresModulesVueFile =			String.join(File.separator, "templates", "vuejs", "src", "stores", "modules", "module.vm");
	// UTILS
	public static final String constantesUtilsVueFile =				String.join(File.separator, "templates", "vuejs", "src", "utils", "Constantes.vm");
	public static final String responseCodesUtilsVueFile =			String.join(File.separator, "templates", "vuejs", "src", "utils", "ResponseCodes.vm");
	public static final String urlRequestsUtilsVueFile =			String.join(File.separator, "templates", "vuejs", "src", "utils", "UrlRequests.vm");
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
