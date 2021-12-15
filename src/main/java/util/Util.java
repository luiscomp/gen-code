package util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

public class Util {
	private static final SimpleDateFormat formatoHHmmss = new SimpleDateFormat("HH:mm:ss");
	
	public static String formatarTimestampHHmmss(Timestamp timestamp) {
		return formatoHHmmss.format((java.util.Date) timestamp);
	}
	
	public static String getHoraAtual() {
		return Util.formatarTimestampHHmmss(new Timestamp(Calendar.getInstance().getTimeInMillis()));
	}
	
	public static Boolean criarDiretorio(String path) throws IOException {
		File file = new File(path);

		if (!file.exists()) {
			return file.mkdirs();
		}
		
		return false;
	}
	
	public static void criarArquivo(String conteudo, String path, String fileName) throws IOException {
		BufferedWriter bw = null;
		FileWriter fw = null;
		
		fw = new FileWriter(String.join(File.separator, path, fileName));
		bw = new BufferedWriter(fw);
		bw.write(conteudo);
		
		if(bw != null)
			bw.close();

		if(fw != null)
			fw.close();
	}
	
	public static void copiarArquivo(String fileName, String filePath, String destPath, VelocityContext paramsCtx, String... rootTemplate) throws IOException {	
		Template template;
		StringWriter sw;
		
		VelocityEngine v = new VelocityEngine();
		if(rootTemplate.length > 0) {
			v.setProperty(RuntimeConstants.FILE_RESOURCE_LOADER_PATH, rootTemplate[0]);
			v.init();
		} else {
			v.init(getPropriedadesLogVelocity());
		}
		
		template = v.getTemplate(filePath, StandardCharsets.UTF_8.name());
		
		sw = new StringWriter();
		template.merge(paramsCtx, sw);
		
		
		Util.criarArquivo(sw.toString(), destPath, fileName);
		
		sw.close();
	}
	
	public static Properties getPropriedadesLogVelocity() {
		Properties p = new Properties();

		p.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		p.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
		p.put("runtime.log.logsystem.class", "org.apache.velocity.runtime.log.SimpleLog4JLogSystem");
		p.put("runtime.log.logsystem.log4j.category", "velocity");
		p.put("runtime.log.logsystem.log4j.logger", "velocity");
		
		return p;
	}
	
	public static String getPaginacao() {
		StringBuffer paginacao = new StringBuffer();
		
		paginacao.append("if(!isNaN(pagina) && pagina >= 0) {\n\t\t\t")
		.append("sql = sql.concat(` LIMIT ${pagina * MAX_LENGHT_PAGE}, ${MAX_LENGHT_PAGE}`);\n\t\t")
		.append("}\n\t\t");
		
		return paginacao.toString();
	}
}
