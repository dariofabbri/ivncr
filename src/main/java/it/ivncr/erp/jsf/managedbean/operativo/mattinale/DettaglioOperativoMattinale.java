package it.ivncr.erp.jsf.managedbean.operativo.mattinale;

import it.ivncr.erp.jsf.managedbean.accesso.session.LoginInfo;
import it.ivncr.erp.model.personale.Reparto;
import it.ivncr.erp.service.ServiceFactory;
import it.ivncr.erp.service.lut.LUTService;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean
@ViewScoped
public class DettaglioOperativoMattinale implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(DettaglioOperativoMattinale.class);

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{loginInfo}")
    private LoginInfo loginInfo;

	private TreeNode reparti;
	private TreeNode selectedReparto;

	private Date dataMattinale;

	private List<Object> addetti;


	@PostConstruct
	public void init() {

		LUTService lutService = ServiceFactory.createService("LUT");
		List<Reparto> list = lutService.listItems("Reparto", "descrizione", "azienda.id", loginInfo.getCodiceAzienda());

		reparti = new DefaultTreeNode("root", null);
		reparti.setExpanded(true);
		reparti.setSelectable(false);

		TreeNode azienda = new DefaultTreeNode("azienda", loginInfo.getAzienda(), reparti);
		azienda.setExpanded(true);
		azienda.setSelectable(false);

		for(Reparto reparto : list) {
			new DefaultTreeNode("reparto", reparto, azienda);
		}

		logger.debug("Initialization performed.");
	}

	public void onRepartoSelect() {

		System.out.println(">>>>>>>>>>>>>>>>");
	}

	public LoginInfo getLoginInfo() {
		return loginInfo;
	}

	public void setLoginInfo(LoginInfo loginInfo) {
		this.loginInfo = loginInfo;
	}

	public TreeNode getReparti() {
		return reparti;
	}

	public void setReparti(TreeNode reparti) {
		this.reparti = reparti;
	}

	public TreeNode getSelectedReparto() {
		return selectedReparto;
	}

	public void setSelectedReparto(TreeNode selectedReparto) {
		this.selectedReparto = selectedReparto;
	}

	public Date getDataMattinale() {
		return dataMattinale;
	}

	public void setDataMattinale(Date dataMattinale) {
		this.dataMattinale = dataMattinale;
	}

	public List<Object> getAddetti() {
		return addetti;
	}

	public void setAddetti(List<Object> addetti) {
		this.addetti = addetti;
	}
}