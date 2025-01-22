package com.dataprovider;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
//import org.testng.Assert;
import com.testng.Assert;

import com.utilities.UtilityMethods;

public class XMLManager
{
  SAXBuilder builder = new SAXBuilder();
  File xFile;
  Document xmldoc;
  Element rootNode;
  private Logger log = Logger.getLogger("XMLManager");

  public String readXmlContent(String path, String xmlFilePath)
  {
    String value = null;
    try {
      xFile = new File(xmlFilePath);
      xmldoc = builder.build(xFile);

      rootNode = xmldoc.getRootElement();

      String[] array = path.split("/");
      int last_element = array.length;

      Element s = rootNode;
      for (int i = 1; i < last_element; i++)
        s = s.getChild(array[i]);
      value = s.getText();
    }
    catch (JDOMException|IOException e) {
      log.error("Exception while reading XML content from file - "+xmlFilePath+e.getMessage()+UtilityMethods.getStackTrace());
      Assert.fail("Exception while reading XML content from file - "+xmlFilePath+e.getMessage()+UtilityMethods.getStackTrace());
    }
    return value;
  }

  public void addXmlContent(String keyPath, String value, String xmlFilePath)
  {
    try {
      xFile = new File(xmlFilePath);
      xmldoc = builder.build(xFile);
      rootNode = xmldoc.getRootElement();

      String[] array = keyPath.split("/");
      int last_element = array.length;

      Element new_node = new Element(array[(last_element - 1)]).setText(value);

      Element s = rootNode;
      for (int i = 1; i < last_element - 1; i++) {
        s = s.getChild(array[i]);
      }
      s.addContent(new_node);
      updateFile(xmldoc, xmlFilePath);
    }
    catch (JDOMException|IOException e) {
    	log.error("Exception while writing XML content from file - "+xmlFilePath+e.getMessage()+UtilityMethods.getStackTrace());
    	Assert.fail("Exception while writing XML content from file - "+xmlFilePath+e.getMessage()+UtilityMethods.getStackTrace());
    }
  }

  public void removeXmlContent(String keyPath, String xmlFilePath) {
    try {
      xFile = new File(xmlFilePath);
      xmldoc = builder.build(xFile);
      rootNode = xmldoc.getRootElement();
      String[] array = keyPath.split("/");
      int last_element = array.length;
      Element s = rootNode;
      for (int i = 1; i < last_element - 1; i++) {
        s = s.getChild(array[i]);
      }
      s.removeChild(array[(last_element - 1)]);
      updateFile(xmldoc, xmlFilePath);
    }
    catch (JDOMException|IOException e) {
    	log.error("Exception while removing XML content from file - "+xmlFilePath+e.getMessage()+UtilityMethods.getStackTrace());
    	Assert.fail("Exception while removing XML content from file - "+xmlFilePath+e.getMessage()+UtilityMethods.getStackTrace());
    }
  }

  public void updateXmlContent(String keyPath, String newvalue, String xmlFilePath) {
    try {
      xFile = new File(xmlFilePath);
      xmldoc = builder.build(xFile);
      rootNode = xmldoc.getRootElement();

      String[] array = keyPath.split("/");
      int last_element = array.length;

      Element s = rootNode;
      for (int i = 1; i < last_element; i++) {
        s = s.getChild(array[i]);
      }
      s.setText(newvalue);
      updateFile(xmldoc, xmlFilePath);
    }
    catch (JDOMException|IOException e) {
    	log.error("XML  file - "+xmlFilePath+" not found or no permissions to update the file"+e.getMessage()+UtilityMethods.getStackTrace());
    	Assert.fail("XML  file - "+xmlFilePath+" not found or no permissions to update the file"+e.getMessage()+UtilityMethods.getStackTrace());
    }
  }

  public void updateFile(Document xmlDoc, String xmlFilePath) throws IOException {
    try {
      xFile = new File(xmlFilePath);
      XMLOutputter xmlOutput = new XMLOutputter();
      xmlOutput.setFormat(Format.getPrettyFormat());
      xmlOutput.output(xmlDoc, new FileWriter(xmlFilePath));
      System.out.println("File" + xmlFilePath + " updated!");
    }
    catch (IOException e) {
    	log.error("XML  file - "+xmlFilePath+" not found or no permissions to update the file"+e.getMessage()+UtilityMethods.getStackTrace());
    	Assert.fail("XML  file - "+xmlFilePath+" not found or no permissions to update the file"+e.getMessage()+UtilityMethods.getStackTrace());
    }
  }
}