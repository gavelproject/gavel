/*******************************************************************************
 * MIT License
 *
 * Copyright (c) Igor Conrado Alves de Lima <igorcadelima@gmail.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *******************************************************************************/
package gavel.impl.repo;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

/**
 * @author igorcadelima
 *
 */
final class Documents {
  /**
   * Try to parse, validate using schema passed as argument, normalize, and return {@link Document}.
   * 
   * @param filePath path to file to be validated
   * @param schemaPath path to schema which should be used for validation
   * @return Document resulting document after parsing and validation
   * @throws ParserConfigurationException
   * @throws SAXException
   * @throws IOException
   */
  static Document parseDocument(String filePath, String schemaPath)
      throws ParserConfigurationException, SAXException, IOException {
    Document doc = parseDocument(filePath);
    validate(doc, schemaPath);
    return doc;
  }

  /**
   * Try to parse, normalize, and return {@link Document}.
   * 
   * @param filePath path to file to be validated
   * @return Document resulting document after parsing
   * @throws ParserConfigurationException
   * @throws SAXException
   * @throws IOException
   */
  static Document parseDocument(String filePath)
      throws ParserConfigurationException, SAXException, IOException {
    DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
    documentBuilderFactory.setNamespaceAware(true);

    DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
    File file = new File(filePath);
    Document doc = documentBuilder.parse(file);
    doc.normalize();
    return doc;
  }

  /**
   * Validate a XML file against a schema.
   * 
   * @param node node to be validated
   * @param schemaPath path to schema which should be used for validation
   * @throws SAXException
   * @throws IOException
   */
  static void validate(Node node, String schemaPath) throws SAXException, IOException {
    Source xmlSource = new DOMSource(node);
    Schema schema = getSchema(schemaPath);
    Validator validator = schema.newValidator();
    validator.validate(xmlSource);
  }

  /**
   * Create and return {@link Schema} representation of schema if existing in the path passed as
   * argument.
   * 
   * @return {@link Schema} representation of schema passed as argument
   * @throws SAXException
   */
  static Schema getSchema(String schemaPath) throws SAXException {
    SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
    URL schemaResource = Documents.class.getResource(schemaPath);
    return schemaFactory.newSchema(schemaResource);
  }

  /**
   * Convert a {@link Node} into a {@link Document} instance.
   * 
   * @param node node to be transformed
   * @return {@link Node} as a {@link Document}
   */
  static Document nodeToDocument(Node node) {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    factory.setNamespaceAware(true);
    DocumentBuilder builder = null;
    try {
      builder = factory.newDocumentBuilder();
    } catch (ParserConfigurationException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    Document newDocument = builder.newDocument();
    Node importedNode = newDocument.importNode(node, true);
    newDocument.appendChild(importedNode);
    return newDocument;
  }
}
