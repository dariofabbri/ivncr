package it.ivncr.erp.jsf.managedbean.session;

import java.io.InputStream;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.primefaces.component.menuitem.MenuItem;
import org.primefaces.component.separator.Separator;
import org.primefaces.component.submenu.Submenu;
import org.primefaces.model.DefaultMenuModel;
import org.primefaces.model.MenuModel;
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

		MenuModel model = new DefaultMenuModel();
		
		NodeList nl = doc.getDocumentElement().getChildNodes();
		for(int i = 0; i < nl.getLength(); ++i) {
			
			Node node = nl.item(i);
			
			if(node.getNodeType() != Node.ELEMENT_NODE)
				continue;
			
			Element element = (Element)node;
			
			if(element.getNodeName().equals("menuitem")) {
				
				MenuItem item = buildMenuItem(element);
				model.addMenuItem(item);
				
			} else if(element.getNodeName().equals("separator")) {

				Separator separator = buildSeparator(element);
				model.addSeparator(separator);
				
			} else if(element.getNodeName().equals("submenu")) {

				Submenu submenu = buildSubmenu(element);
				model.addSubmenu(submenu);
				
			}
		}
		
		return model;
	}
	
	
	private Submenu buildSubmenu(Element element) {
		
		Submenu result = new Submenu();
		
		String label = element.getAttribute("label");
		if(!StringUtils.isEmpty(label))
			result.setLabel(label);
		
		String icon = element.getAttribute("icon");
		if(!StringUtils.isEmpty(icon))
			result.setIcon(icon);
		
		
		NodeList nl = element.getChildNodes();
		for(int i = 0; i < nl.getLength(); ++i) {
			
			Node node = nl.item(i);
			
			if(node.getNodeType() != Node.ELEMENT_NODE)
				continue;
			
			Element child = (Element)node;
			
			if(child.getNodeName().equals("menuitem")) {
				
				MenuItem item = buildMenuItem(child);
				result.getChildren().add(item);
				
			} else if(child.getNodeName().equals("separator")) {

				Separator separator = buildSeparator(child);
				result.getChildren().add(separator);
				
			} else if(child.getNodeName().equals("submenu")) {

				Submenu submenu = buildSubmenu(child);
				result.getChildren().add(submenu);
				
			}
		}
		
		return result;
	}

	
	private Separator buildSeparator(Element element) {

		Separator separator = new Separator();
		return separator;
	}

	
	private MenuItem buildMenuItem(Element element) {
		
		MenuItem item = new MenuItem();

		// Only a subset of possible attributes is supported.
		//
		
		String value = element.getAttribute("value");
		if(!StringUtils.isEmpty(value))
			item.setValue(value);
		
		String url = element.getAttribute("url");
		if(!StringUtils.isEmpty(url))
			item.setUrl(url);
		
		String icon = element.getAttribute("icon");
		if(!StringUtils.isEmpty(icon))
			item.setIcon(icon);
		
		String permission = element.getAttribute("icon");
		if(!StringUtils.isEmpty(permission)) {
			boolean permitted = SecurityUtils.getSubject().isPermitted(permission);
			item.setDisabled(!permitted);
		}
		
		return item;
	}
}
