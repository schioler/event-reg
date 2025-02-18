package dk.schioler.event.base.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.lang3.BooleanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import dk.schioler.event.base.entity.AbstractEntityName;
import dk.schioler.event.base.entity.AbstractEntityParentChild;
import dk.schioler.event.base.entity.EventTemplate;
import dk.schioler.event.base.entity.EventType;
import dk.schioler.event.base.entity.UNIT;
import dk.schioler.event.base.entity.XMLRootElement;

@Component
public class EventTypeXMLHelper implements EventTypeXMLElements {

	private Logger logger = LoggerFactory.getLogger(getClass());

	public EventTypeXMLHelper() {

	}

	public List<AbstractEntityParentChild> buildEventTypes(String srcFileName) {
		return buildEventTypes(new File(srcFileName));
	}

	public List<AbstractEntityParentChild> buildEventTypes(File srcFile) {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(srcFile);
			return buildEventTypesFromXML(fis);
		} catch (Exception e) {
			throw new EventXMLException(e.getMessage(), e);
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					throw new EventXMLException(e.getMessage(), e);
				}
			}
		}
	}

	public List<AbstractEntityParentChild> buildEventTypesFromXML(InputStream is) {
		logger.trace("buildEventTypesfromXML:" + is);
		XMLRootElement rootNode = null;
		try {

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(is);

//			logger.debug("dc=" + doc);
			String rootName = doc.getDocumentElement().getNodeName();
			logger.debug("Root Element :" + rootName);
//			logger.debug("------");

			if (ELEMENT_ROOT.equals(rootName)) {

				rootNode = new XMLRootElement();

				if (doc.hasChildNodes()) {
					traverseNodes(doc.getDocumentElement(), rootNode);
				}
			}
			
			// all the EventTypes that has been discovered
			return rootNode.getChildren();
		} catch (Exception e) {
			logger.error("parse caught ", e);
		}
		logger.debug("return null");
		return null;
	}

	private void traverseNodes(Node xmlCurNode, AbstractEntityName curObject) {
		NodeList xmlChildNodes = xmlCurNode.getChildNodes();
		for (int idx = 0; idx < xmlChildNodes.getLength(); idx++) {
			Node xmlChildNode = xmlChildNodes.item(idx);

			if (Node.ELEMENT_NODE == xmlChildNode.getNodeType()) {

				String xmlNodeName = xmlChildNode.getNodeName();
//				logger.trace("looking at node=" + xmlNodeName);

				if (ELEMENT_EVENT_TYPE.equalsIgnoreCase(xmlNodeName)) {
					logger.trace("found: event-type");
					EventType eType = buildEventTypeInstance(xmlChildNode);
					XMLRootElement rootE = (XMLRootElement) curObject;
					rootE.addChild(eType);
					traverseNodes(xmlChildNode, eType);
				} else if (ELEMENT_EVENT_TEMPLATE.equalsIgnoreCase(xmlNodeName)) {
				   	logger.trace("found: event-template");
					EventTemplate dTmppl  = buildEventTemplateInstance(xmlChildNode);
					EventType e = (EventType) curObject;

					e.addChild(dTmppl);
//					curObject.addChild(childTreeNode);

//					traverseNodes(xmlChildNode, childTreeNode);
				//				
				} else {
					throw new EventXMLException("un-known element in received xml/accountPlan: " + xmlNodeName);
				}

			}
		}
	}

	private EventType buildEventTypeInstance(Node xmlChildNode) {

		if (xmlChildNode.hasAttributes()) {

			String name = null;
			String shortName = null;
			String description = null;

			// get attributes names and values
			NamedNodeMap xmlNodeMap = xmlChildNode.getAttributes();
			for (int i = 0; i < xmlNodeMap.getLength(); i++) {
				Node xmlNode = xmlNodeMap.item(i);
				logger.trace("looking at attr: name=" + xmlNode.getNodeName());
				if (ATTR_NAME.equals(xmlNode.getNodeName())) {
					name = xmlNode.getNodeValue();
					logger.trace("found: attr:name=" + name);
				} else if (ATTR_SHORT_NAME.equals(xmlNode.getNodeName())) {
					shortName = xmlNode.getNodeValue();
					logger.trace("found: attr:short=" + shortName);
				} else if (ATTR_DESCRIPTION.equals(xmlNode.getNodeName())) {
					description = xmlNode.getNodeValue();
					logger.trace("found: attr:description=" + description);
				}
			}
			EventType et = new EventType();
			et.setName(name);
			et.setDescription(shortName);
			et.setDescription(description);
			
			return et;
		} else {
			return null;
		}

	}

	private EventTemplate buildEventTemplateInstance(Node xmlChildNode) {

		if (xmlChildNode.hasAttributes()) {
			String name = null;
			String shortName = null;
			String description = null;
			String unit = null;
			String dose = null;
			String isFavorite = null;
			
			// get attributes names and values
			NamedNodeMap xmlNodeMap = xmlChildNode.getAttributes();
			for (int i = 0; i < xmlNodeMap.getLength(); i++) {
				Node xmlNode = xmlNodeMap.item(i);
				logger.trace("looking at attr: name=" + xmlNode.getNodeName());
				if (ATTR_NAME.equals(xmlNode.getNodeName())) {
					name = xmlNode.getNodeValue();
					logger.trace("found: attr:name=" + name);
				} else if (ATTR_DOSE.equals(xmlNode.getNodeName())) {
					dose = xmlNode.getNodeValue();
					logger.trace("found: attr:dose=" + dose);
				} else if (ATTR_UNIT.equals(xmlNode.getNodeName())) {
					unit = xmlNode.getNodeValue();
					logger.trace("found: attr:unit=" + unit);
				} else if (ATTR_SHORT_NAME.equals(xmlNode.getNodeName())) {
					shortName = xmlNode.getNodeValue();
					logger.trace("found: attr:short=" + shortName);
				} else if (ATTR_DESCRIPTION.equals(xmlNode.getNodeName())) {
					description = xmlNode.getNodeValue();
					logger.trace("found: attr:description=" + description);
				} else if (ATTR_IS_FAVORITE.equals(xmlNode.getNodeName())) {
					isFavorite = xmlNode.getNodeValue();
					logger.trace("found: attr:is-favorite=" + description);
				}
			}
			EventTemplate et = new EventTemplate();
			et.setName(name);
			et.setDescription(shortName);
			et.setDescription(description);
			et.setDose(new BigDecimal(dose));
			et.setUnit(UNIT.getUnit(unit));
			et.setFavorite(BooleanUtils.toBoolean(isFavorite));			
			
			return et;
		} else {
			return null;
		}
	}
	
	
	// *************************************************************************************
	// *************************************************************************************
//	public Document createXMLDocumentFromAccountNodeTree(AccountTreeNode accountTreeRootNode) {
//		try {
//			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//			DocumentBuilder builder = factory.newDocumentBuilder();
//
//			Document document = builder.newDocument();
//
//			Element rootElement = document.createElement(ELEMENT_ROOT);
//			document.appendChild(rootElement);
//
//			buildXML(accountTreeRootNode, document, rootElement);
//
//			return document;
//		} catch (Exception e) {
//			throw new EconomyXMLException(e.getMessage(), e);
//		} finally {
//
//		}
//	}

//	private void buildXML(AccountTreeNode curAccountTreeNode, Document document, Element curElement) {
//		Element childElement = null;
//		List<AccountTreeNode> children = curAccountTreeNode.getChildren();
//		if (children.size() > 0) {
//			for (AccountTreeNode childNode : children) {
//				Account childAccount = childNode.getEntity();
//				String name = childAccount.getName();
//				if (ELEMENT_ACCOUNT_PLAN.equals(name)) {
//					childElement = document.createElement(ELEMENT_ACCOUNT_PLAN);
//				} else if (ELEMENT_DEBIT_ACCOUNTS.equals(name)) {
//					childElement = document.createElement(ELEMENT_DEBIT_ACCOUNTS);
//				} else if (ELEMENT_CREDIT_ACCOUNTS.equals(name)) {
//					childElement = document.createElement(ELEMENT_CREDIT_ACCOUNTS);
//				} else {
//					childElement = document.createElement(ELEMENT_ACCOUNT);
//					childElement.setAttribute(ATTR_NAME, childAccount.getName());
//					childElement.setAttribute(ATTR_DESCRIPTION, childAccount.getDescription());
//					childElement.setAttribute(ATTR_NUMBER, childAccount.getNumber());
//					childElement.setAttribute(ATTR_EX_NAME, childAccount.getExternalName());
//					childElement.setAttribute(ATTR_EX_NUMBER, childAccount.getExternalNumber());
//					childElement.setAttribute(ATTR_UK_NAME, childAccount.getUkName());
////					childElement.setAttribute(ATTR_IS_CATEGORY, Boolean.toString(childAccount.isCategory()));
//
//					List<Pattern> patterns = childAccount.getPatterns();
//					for (Pattern pattern : patterns) {
//						Element patternElement = document.createElement(ELEMENT_PATTERN);
//						patternElement.setTextContent(pattern.getPattern());
//						childElement.appendChild(patternElement);
//					}
//				}
//				curElement.appendChild(childElement);
//
//				buildXML(childNode, document, childElement);
//			}
//		}
//
//	}

}
