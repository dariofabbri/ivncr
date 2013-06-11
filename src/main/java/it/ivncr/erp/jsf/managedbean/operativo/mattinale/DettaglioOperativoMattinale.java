package it.ivncr.erp.jsf.managedbean.operativo.mattinale;

import it.ivncr.erp.service.ServiceFactory;
import it.ivncr.erp.service.lut.LUTService;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.TreeNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean
@ViewScoped
public class DettaglioOperativoMattinale implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(DettaglioOperativoMattinale.class);

	private static final long serialVersionUID = 1L;

	private TreeNode reparti;

	@PostConstruct
	public void init() {

		LUTService lutService = ServiceFactory.createService("LUT");

		logger.debug("Initialization performed.");
	}

	public TreeNode getReparti() {
		return reparti;
	}

	public void setReparti(TreeNode reparti) {
		this.reparti = reparti;
	}
}