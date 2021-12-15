package ui.tasks;

import java.util.ArrayList;

import dao.TabelaDAO;
import javafx.concurrent.Task;
import ui.interfaces.OnProgress;
import ui.util.UtilsUI;
import vo.TabelaVO;

public class RecuperarInfoBancoTask extends Task<ArrayList<TabelaVO>> implements OnProgress {
	@Override
	protected ArrayList<TabelaVO> call() throws Exception {
		TabelaDAO dao = new TabelaDAO();
		try {
			return dao.listarBanco(this);
		} catch(Exception e) {
			e.printStackTrace();
			UtilsUI.mostrarExceptionDialog(e, getClass());
		}
		return null;
	}

	@Override
	public void onProgress(int progress, int total, String... args) {
		this.updateProgress(progress, total);
	}
}
