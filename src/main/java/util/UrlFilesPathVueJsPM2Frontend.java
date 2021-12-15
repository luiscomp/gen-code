package util;

import java.io.File;

import singletons.ProjSingleton;

public class UrlFilesPathVueJsPM2Frontend {
	// PATH DE DIRETORIOS DESTINOS
	public static final String rootFrontPath = 						String.join(File.separator, ProjSingleton.getPathDestino(), ProjSingleton.getProjeto().getNome()+"_Vue");
	public static final String assetsFrontPath =					String.join(File.separator, rootFrontPath, "assets");
	public static final String assetsImagensFrontPath =				String.join(File.separator, rootFrontPath, "assets", "imagens");
	public static final String buildFrontPath =						String.join(File.separator, rootFrontPath, "build");
	public static final String componentsFrontPath =				String.join(File.separator, rootFrontPath, "components");
	public static final String componentsCadastrosFrontPath =		String.join(File.separator, rootFrontPath, "components", "cadastros");
	public static final String componentsFiltrosFrontPath =			String.join(File.separator, rootFrontPath, "components", "filtros");
	public static final String componentsModalsFrontPath =			String.join(File.separator, rootFrontPath, "components", "modals");
	public static final String componentsTemplatesFrontPath =		String.join(File.separator, rootFrontPath, "components", "templates");
	public static final String mixinsFrontPath =					String.join(File.separator, rootFrontPath, "mixins");
	public static final String pagesFrontPath =						String.join(File.separator, rootFrontPath, "pages");
	public static final String routerFrontPath =					String.join(File.separator, rootFrontPath, "router");
	public static final String srcFrontPath =						String.join(File.separator, rootFrontPath, "src");
	public static final String srcUtilsFrontPath =					String.join(File.separator, rootFrontPath, "src", "utils");
	public static final String storeFrontPath =						String.join(File.separator, rootFrontPath, "store");
	public static final String storeModuleFrontPath =				String.join(File.separator, rootFrontPath, "store", "modules");
	public static final String staticFrontPath =					String.join(File.separator, rootFrontPath, "static");
	
	// PATH DE ARQUIVOS
	// ROOT
	public static final String babelrcFile =						String.join(File.separator, "templates", "vuejspm2", "babelrc.vm");
	public static final String editorconfigFile =					String.join(File.separator, "templates", "vuejspm2", "editorconfig.vm");
	public static final String eslintgnoreFile =					String.join(File.separator, "templates", "vuejspm2", "eslintignore.vm");
	public static final String eslintrcFile =						String.join(File.separator, "templates", "vuejspm2", "eslintrc.vm");
	public static final String gitignoreFile =						String.join(File.separator, "templates", "vuejspm2", "gitignore.vm");
	
	public static final String yarnFile =							String.join(File.separator, "templates", "vuejspm2", "yarn.vm");
	public static final String serverFile =							String.join(File.separator, "templates", "vuejspm2", "server.vm");
	public static final String readmeFile =							String.join(File.separator, "templates", "vuejspm2", "README.vm");
	public static final String packageFile =						String.join(File.separator, "templates", "vuejspm2", "package.vm");
	public static final String packageLockFile =					String.join(File.separator, "templates", "vuejspm2", "package-lock.vm");
	
	// ASSETS
	public static final String appJsFile =							String.join(File.separator, "templates", "vuejspm2", "assets", "appJs.vm");
	public static final String appVueFile =							String.join(File.separator, "templates", "vuejspm2", "assets", "AppVue.vm");
	public static final String entryClientFile =					String.join(File.separator, "templates", "vuejspm2", "assets", "entry-client.vm");
	public static final String entryServerFile =					String.join(File.separator, "templates", "vuejspm2", "assets", "entry-server.vm");
	public static final String indexTemplateFile =					String.join(File.separator, "templates", "vuejspm2", "assets", "index.template.vm");
	
	// ASSETS/IMAGENS
	public static final String avatarFile =							String.join(File.separator, "templates", "vuejspm2", "assets", "imagens", "avatar.vm");
	public static final String bannerFile =							String.join(File.separator, "templates", "vuejspm2", "assets", "imagens", "banner.vm");
	public static final String logoFile =							String.join(File.separator, "templates", "vuejspm2", "assets", "imagens", "logo.vm");
	public static final String photoFile =							String.join(File.separator, "templates", "vuejspm2", "assets", "imagens", "photo.vm");
	
	// BUILD
	public static final String setupDevServerFile =					String.join(File.separator, "templates", "vuejspm2", "build", "setup-dev-server.vm");
	public static final String vueLoaderConfigFile =				String.join(File.separator, "templates", "vuejspm2", "build", "vue-loader.config.vm");
	public static final String webpackBaseConfigFile =				String.join(File.separator, "templates", "vuejspm2", "build", "webpack.base.config.vm");
	public static final String webpackClientConfigFile =			String.join(File.separator, "templates", "vuejspm2", "build", "webpack.client.config.vm");
	public static final String webpackServerConfigFile =			String.join(File.separator, "templates", "vuejspm2", "build", "webpack.server.config.vm");
	
	// COMPONENTS
	public static final String indexComponentsFile =				String.join(File.separator, "templates", "vuejspm2", "components", "_index.vm");
	public static final String vuetifyComponentFile =				String.join(File.separator, "templates", "vuejspm2", "components", "Vuetify.vm");
	
	// COMPONENTS/FILTROS
	public static final String cadastroFile =						String.join(File.separator, "templates", "vuejspm2", "components", "cadastros", "Cadastro.vm");
	
	// COMPONENTS/FILTROS
	public static final String filtroFile =							String.join(File.separator, "templates", "vuejspm2", "components", "filtros", "Filtro.vm");
	
	// COMPONENTS/MODALS
	public static final String modalFile =							String.join(File.separator, "templates", "vuejspm2", "components", "modals", "Modal.vm");
	
	// COMPONENTS/TEMPLATES
	public static final String cabecalhoTemplatesFile =				String.join(File.separator, "templates", "vuejspm2", "components", "templates", "Cabecalho.vm");
	public static final String dateInputTemplatesFile =				String.join(File.separator, "templates", "vuejspm2", "components", "templates", "DateInput.vm");
	public static final String decimalInputTemplateFile =			String.join(File.separator, "templates", "vuejspm2", "components", "templates", "DecimalInput.vm");
	public static final String imageBrowserTemplateFile =			String.join(File.separator, "templates", "vuejspm2", "components", "templates", "ImageBrowser.vm");
	public static final String messageDialogTemplateFile =			String.join(File.separator, "templates", "vuejspm2", "components", "templates", "MessageDialog.vm");
	public static final String paginacaoTemplateFile =				String.join(File.separator, "templates", "vuejspm2", "components", "templates", "Paginacao.vm");
	
	// MIXINS
	public static final String metaMixisFile =						String.join(File.separator, "templates", "vuejspm2", "mixins", "meta.vm");
	
	// PAGES
	public static final String homeViewFile =						String.join(File.separator, "templates", "vuejspm2", "pages", "HomeView.vm");
	public static final String pageViewFile =						String.join(File.separator, "templates", "vuejspm2", "pages", "PageView.vm");
	
	// ROUTER
	public static final String trezentrosUmFile =					String.join(File.separator, "templates", "vuejspm2", "router", "301.vm");
	public static final String indexRouterFile =					String.join(File.separator, "templates", "vuejspm2", "router", "index.vm");
	public static final String metaRouterFile =						String.join(File.separator, "templates", "vuejspm2", "router", "meta.vm");
	
	// SRC/UTILS
	public static final String contantesFile =						String.join(File.separator, "templates", "vuejspm2", "src", "utils", "Constantes.vm");
	public static final String responsesCodesFile =					String.join(File.separator, "templates", "vuejspm2", "src", "utils", "ResponseCodes.vm");
	public static final String urlRequestsFile =					String.join(File.separator, "templates", "vuejspm2", "src", "utils", "UrlRequests.vm");
	
	// STORE
	public static final String indexStoreFile =						String.join(File.separator, "templates", "vuejspm2", "store", "index.vm");
	
	// STORE/MODULES
	public static final String moduleStoreFile =					String.join(File.separator, "templates", "vuejspm2", "store", "modules", "module.vm");
	
	// STATIC
	public static final String favicon32x32File =					String.join(File.separator, "templates", "vuejspm2", "static", "favicon-32x32.vm");
	public static final String faviconFile =						String.join(File.separator, "templates", "vuejspm2", "static", "favicon.vm");
	public static final String robotsFile =							String.join(File.separator, "templates", "vuejspm2", "static", "robots.vm");
	public static final String sitemapFile =						String.join(File.separator, "templates", "vuejspm2", "static", "sitemap.vm");
	public static final String vFile =								String.join(File.separator, "templates", "vuejspm2", "static", "v.vm");
}
