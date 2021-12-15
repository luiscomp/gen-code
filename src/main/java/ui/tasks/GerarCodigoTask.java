package ui.tasks;

import java.util.ArrayList;

import geradores.NodeBackendGen;
import geradores.VueJsFrontendGen;
import geradores.VueJsPM2FrontendGen;
import javafx.concurrent.Task;
import javafx.scene.control.TextArea;
import singletons.ProjSingleton;
import ui.enums.Plataformas;
import ui.interfaces.OnProgress;
import ui.util.UtilsUI;
import util.Util;
import vo.TabelaVO;

public class GerarCodigoTask extends Task<Void> implements OnProgress {
	private ArrayList<TabelaVO> listaTabelas;
	private TextArea txtLog;
	private long start;
	
	public GerarCodigoTask(ArrayList<TabelaVO> listaTabelas, TextArea txtLog) {
		super();
		this.listaTabelas = listaTabelas;
		this.txtLog = txtLog;
	}

	@Override
	protected Void call() throws Exception {
		NodeBackendGen nodeAppGen = new NodeBackendGen(listaTabelas, this);
		VueJsFrontendGen vueAppGen = new VueJsFrontendGen(listaTabelas, this);
		VueJsPM2FrontendGen vuePm2AppGen = new VueJsPM2FrontendGen(listaTabelas, this);
		try {
			start = System.currentTimeMillis();
		
			nodeAppGen.gerarCodigo();
			
			if(ProjSingleton.getProjeto().getPlataforma().equals(Plataformas.NODE_VUE.name())) {
				vueAppGen.gerarCodigo();
			} else {
				vuePm2AppGen.gerarCodigo();
			}
			
			txtLog.appendText("\n"+Util.getHoraAtual()+": Código Gerado em : "+String.valueOf((float) (System.currentTimeMillis() - start) / 1000)+"s");
		} catch(Exception e) {
			txtLog.appendText("\nFalha ao gerar código.");
			e.printStackTrace();
			UtilsUI.mostrarExceptionDialog(e, getClass());
		}
		return null;
	}

	@Override
	public void onProgress(int progress, int total, String... args) {
		this.updateProgress(progress, total);
		txtLog.appendText(args[0]);
	}
}
