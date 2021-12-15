package geradores;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.velocity.VelocityContext;

import singletons.ProjSingleton;
import ui.interfaces.OnProgress;
import util.Constantes;
import util.LoggerApp;
import util.Util;
import vo.TabelaVO;

public class NodeBackendGen {
	
	private VelocityContext ctx;
	private ArrayList<TabelaVO> listaTabelas;
	private OnProgress callback;
	
	public NodeBackendGen(ArrayList<TabelaVO> listaTabelas, OnProgress callback) {
		super();
		this.listaTabelas = listaTabelas;
		this.callback = callback;
	}

	public void gerarCodigo() throws IOException {
		int passoAtual = 0;
		int totalPassos = 12;
		callback.onProgress(passoAtual++, totalPassos, String.join(": ", Util.getHoraAtual(),"Iniciando ... [NODE + EXPRESS]\n"));

		gerarPastas();
		callback.onProgress(passoAtual++, totalPassos, String.join(": ", Util.getHoraAtual(),"Paths ... [OK]\n"));

		gerarArquivosRoot();
		callback.onProgress(passoAtual++, totalPassos, String.join(": ", Util.getHoraAtual(),"Arquivos Root ... [OK]\n"));

		gerarArquivosVsCode();
		callback.onProgress(passoAtual++, totalPassos, String.join(": ", Util.getHoraAtual(),"Arquivos VSCode ... [OK]\n"));

		gerarArquivosPublic();
		callback.onProgress(passoAtual++, totalPassos, String.join(": ", Util.getHoraAtual(),"Arquivos Public ... [OK]\n"));

		gerarArquivosView();
		callback.onProgress(passoAtual++, totalPassos, String.join(": ", Util.getHoraAtual(),"Arquivos View ... [OK]\n"));

		gerarArquivosRotas();
		callback.onProgress(passoAtual++, totalPassos, String.join(": ", Util.getHoraAtual(),"Arquivos Rotas ... [OK]\n"));

		gerarArquivosControlValidadores();
		callback.onProgress(passoAtual++, totalPassos, String.join(": ", Util.getHoraAtual(),"Arquivos Validadores ... [OK]\n"));

		gerarArquivosControlUtil();
		callback.onProgress(passoAtual++, totalPassos, String.join(": ", Util.getHoraAtual(),"Arquivos Util ... [OK]\n"));

		gerarArquivosMiddlewares();
		callback.onProgress(passoAtual++, totalPassos, String.join(": ", Util.getHoraAtual(),"Arquivos Middlewares ... [OK]\n"));

		gerarArquivosFachada();
		callback.onProgress(passoAtual++, totalPassos, String.join(": ", Util.getHoraAtual(),"Arquivos Fachada ... [OK]\n"));

		gerarArquivosDAO();
		callback.onProgress(passoAtual++, totalPassos, String.join(": ", Util.getHoraAtual(),"Arquivos DAO ... [OK]\n"));
		
//		gerarArquivoBat();
		callback.onProgress(passoAtual++, totalPassos, String.join(": ", Util.getHoraAtual(),"Código gerado com sucesso... [NODE + EXPRESS]"));
	}

//	private void gerarArquivoBat() {
//		ctx = new VelocityContext();
//		ctx.put("pathProjeto", Constantes.rootBackPath);
//		
//		try {
//			String rootProjectPath = System.getProperty("user.dir");
//			Util.copiarArquivo("buildNode.bat", "buildNode.vm", rootProjectPath, ctx, rootProjectPath);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

	public void gerarPastas() {
		try {
			Util.criarDiretorio(Constantes.rootBackPath);
			Util.criarDiretorio(Constantes.binBackPath);
			Util.criarDiretorio(Constantes.vsCodeBackPath);
			Util.criarDiretorio(Constantes.controlBackPath);
			Util.criarDiretorio(Constantes.controDaoBackPath);
			Util.criarDiretorio(Constantes.controlFachadaBackPath);
			Util.criarDiretorio(Constantes.controlMiddlewaresBackPath);
			Util.criarDiretorio(Constantes.controlUtilBackPath);
			Util.criarDiretorio(Constantes.controlValidadoresBackPath);
			Util.criarDiretorio(Constantes.controlValidadoresSchemasBackPath);
			Util.criarDiretorio(Constantes.publicBackPath);
			Util.criarDiretorio(Constantes.publicStylesheetsBackPath);
			Util.criarDiretorio(Constantes.routersBackPath);
			Util.criarDiretorio(Constantes.viewsBackPath);
		} catch (IOException e) {
			LoggerApp.logError("Erro em NodeBackendGen.gerarPastas()", e);
		}	
	}
	
	public void gerarArquivosRoot() throws IOException {
		ctx = new VelocityContext();
		ctx.put("nomeProjeto", ProjSingleton.getProjeto().getNome().toLowerCase());
		ctx.put("rotas", listaTabelas);

		Util.copiarArquivo("www", Constantes.wwwFile, Constantes.binBackPath, ctx);
		Util.copiarArquivo(".gitignore", Constantes.gitignoreFile, Constantes.rootBackPath, ctx);
		Util.copiarArquivo("app.js", Constantes.appFile, Constantes.rootBackPath, ctx);
		Util.copiarArquivo("package-lock.json", Constantes.packagelockFile, Constantes.rootBackPath, ctx);
		Util.copiarArquivo("package.json", Constantes.packageFile, Constantes.rootBackPath, ctx);
		Util.copiarArquivo("README.md", Constantes.readmeFile, Constantes.rootBackPath, ctx);
	}
	
	public void gerarArquivosVsCode() throws IOException {
		ctx = new VelocityContext();

		Util.copiarArquivo("launch.json", Constantes.launchVsCodeFile, Constantes.vsCodeBackPath, ctx);
	}
	
	public void gerarArquivosPublic() throws IOException {
		ctx = new VelocityContext();
		
		Util.copiarArquivo("style.css", Constantes.stylePublicStylesheetFile, Constantes.publicStylesheetsBackPath, ctx);
	}
	
	private void gerarArquivosView() throws IOException {
		ctx = new VelocityContext();

		Util.copiarArquivo("error.pug", Constantes.errorViewFile, Constantes.viewsBackPath, ctx);
		Util.copiarArquivo("index.pug", Constantes.indexViewFile, Constantes.viewsBackPath, ctx);
		Util.copiarArquivo("layout.pug", Constantes.layouViewtFile, Constantes.viewsBackPath, ctx);
		Util.copiarArquivo("sessao.pug", Constantes.sessaViewoFile, Constantes.viewsBackPath, ctx);
	}
	
	private void gerarArquivosRotas() throws IOException {
		ctx = new VelocityContext();
		ctx.put("nomeProjeto", ProjSingleton.getProjeto().getNome());
		
		Util.copiarArquivo("index.js", Constantes.indexRouterFile, Constantes.routersBackPath, ctx);
		
		for (TabelaVO tabela : listaTabelas) {
			ctx.put("tabela", tabela);
			Util.copiarArquivo(String.join(".", tabela.getNome(), "js"), Constantes.routerRouterFile, Constantes.routersBackPath, ctx);
		}
	}
	
	private void gerarArquivosControlValidadores() throws IOException {
		ctx = new VelocityContext();
		
		Util.copiarArquivo("Validador.js", Constantes.validadorValidadoresFile, Constantes.controlValidadoresBackPath, ctx);
		
		for(TabelaVO tabela : listaTabelas) {
			ctx.put("tabela", tabela);
			Util.copiarArquivo(String.join(".", tabela.getNome(), "js"), Constantes.schemaValidadoresSchemasFile, Constantes.controlValidadoresSchemasBackPath, ctx);
		}
	}
	
	private void gerarArquivosControlUtil() throws IOException {
		ctx = new VelocityContext();
		ctx.put("urlBaseArquivos", ProjSingleton.getProjeto().getUrlBaseArquivos());
		
		Util.copiarArquivo("codigoResposta.js", Constantes.codigoRespostaUtilFile, Constantes.controlUtilBackPath, ctx);
		Util.copiarArquivo("constantes.js", Constantes.constantesUtilFile, Constantes.controlUtilBackPath, ctx);
		Util.copiarArquivo("utils.js", Constantes.utilsUtilFile, Constantes.controlUtilBackPath, ctx);
	}
	
	private void gerarArquivosMiddlewares() throws IOException {
		ctx = new VelocityContext();
		ctx.put("urlValidarSessao", ProjSingleton.getProjeto().getUrlValidarAcesso());
		
		Util.copiarArquivo("ValidarAcesso.js", Constantes.validarAcessoMiddlewareFile, Constantes.controlMiddlewaresBackPath, ctx);
	}
	
	private void gerarArquivosFachada() throws IOException {
		ctx = new VelocityContext();
		
		for(TabelaVO tabela : listaTabelas) {
			ctx.put("tabela", tabela);
			Util.copiarArquivo(String.join(".", tabela.getNome()+"Fachada", "js"), Constantes.objetoFachadaFile, Constantes.controlFachadaBackPath, ctx);
		}
	}
	
	private void gerarArquivosDAO() throws IOException {
		ctx = new VelocityContext();
		ctx.put("hostDB", ProjSingleton.getProjeto().getHostDB());
		ctx.put("portaDB", ProjSingleton.getProjeto().getPortaDB());
		ctx.put("usuarioDB", ProjSingleton.getProjeto().getUsuarioDB());
		ctx.put("senhaDB", ProjSingleton.getProjeto().getSenhaDB());
		ctx.put("schemaDB", ProjSingleton.getProjeto().getSchemaDB());
		
		Util.copiarArquivo("BaseDAO.js", Constantes.baseDaoFile, Constantes.controDaoBackPath, ctx);
		
		for(TabelaVO tabela : listaTabelas) {
			ctx.put("tabela", tabela);
			ctx.put("util", new Util());
			Util.copiarArquivo(String.join(".", tabela.getNome()+"DAO", "js"), Constantes.objetoDaoFile, Constantes.controDaoBackPath, ctx);
		}
	}
}
