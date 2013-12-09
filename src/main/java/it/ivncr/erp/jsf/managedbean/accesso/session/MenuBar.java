package it.ivncr.erp.jsf.managedbean.accesso.session;

import java.io.InputStream;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultSeparator;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuItem;
import org.primefaces.model.menu.MenuModel;
import org.primefaces.model.menu.Separator;
import org.primefaces.model.menu.Submenu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@ManagedBean
@SessionScoped
public class MenuBar implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(MenuBar.class);

	private static final long serialVersionUID = 1L;

	private MenuModel menuModel;

	public MenuModel getMenuModel() {
		return menuModel;
	}

	public void setMenuModel(MenuModel menuModel) {
		this.menuModel = menuModel;
	}

	public MenuBar() {

		// Parse XML from resources.
		//
		Document doc = null;
		try {
			InputStream is = this.getClass().getResourceAsStream("/menu.xml");
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			doc = db.parse(is);
		} catch(Exception e) {

			logger.error("Exception caught while parsing XML control file for menu definition.", e);
			throw new RuntimeException("Exception caught while parsing XML control file for menu definition.", e);
		}

		menuModel = buildMenuModel(doc);
		logger.debug("Menu model successfully built.");
	}

	private MenuModel buildMenuModel(Document doc) {

		MenuModel model = new SerializableDefaultMenuModel();

		NodeList nl = doc.getDocumentElement().getChildNodes();
		for(int i = 0; i < nl.getLength(); ++i) {

			Node node = nl.item(i);

			if(node.getNodeType() != Node.ELEMENT_NODE)
				continue;

			Element element = (Element)node;

			if(element.getNodeName().equals("menuitem")) {

				MenuItem item = buildMenuItem(element);
				model.addElement(item);

			} else if(element.getNodeName().equals("separator")) {

				Separator separator = buildSeparator(element);
				model.addElement(separator);

			} else if(element.getNodeName().equals("submenu")) {

				Submenu submenu = buildSubmenu(element);
				model.addElement(submenu);

			}
		}

		return model;
	}


	@SuppressWarnings("unchecked")
	private Submenu buildSubmenu(Element element) {

		String label = element.getAttribute("label");
		String icon = element.getAttribute("icon");
		Submenu result = new DefaultSubMenu(label, icon);

		NodeList nl = element.getChildNodes();
		for(int i = 0; i < nl.getLength(); ++i) {

			Node node = nl.item(i);

			if(node.getNodeType() != Node.ELEMENT_NODE)
				continue;

			Element child = (Element)node;

			if(child.getNodeName().equals("menuitem")) {

				MenuItem item = buildMenuItem(child);
				result.getElements().add(item);

			} else if(child.getNodeName().equals("separator")) {

				Separator separator = buildSeparator(child);
				result.getElements().add(separator);

			} else if(child.getNodeName().equals("submenu")) {

				Submenu submenu = buildSubmenu(child);
				result.getElements().add(submenu);

			}
		}

		return result;
	}


	private Separator buildSeparator(Element element) {

		Separator separator = new DefaultSeparator();
		return separator;
	}


	private MenuItem buildMenuItem(Element element) {

		// Only a subset of possible attributes is supported.
		//
		DefaultMenuItem item = new DefaultMenuItem();
		item.setAjax(false);

		String value = element.getAttribute("value");
		if(!StringUtils.isEmpty(value)) {
			item.setValue(value);
		}

		String url = element.getAttribute("url");
		if(!StringUtils.isEmpty(url)) {
			item.setCommand(url);
		}

		String icon = element.getAttribute("icon");
		if(!StringUtils.isEmpty(icon)) {
			item.setIcon(icon);
		}

		String permission = element.getAttribute("permission");
		if(!StringUtils.isEmpty(permission)) {
			boolean permitted = SecurityUtils.getSubject().isPermitted(permission);
			item.setDisabled(!permitted);
		}

		return item;
	}
}
