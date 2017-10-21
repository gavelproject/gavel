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

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import gavel.api.repo.CapabilityBoard;
import gavel.impl.capability.DefaultCapability;

/**
 * @author igorcadelima
 *
 */
public final class CapabilityBoards {
  private static final String SCHEMA_PATH = "/xsd/capability-assignment-spec.xsd";

  private CapabilityBoards() {}

  public static CapabilityBoard of() {
    return new InMemCapabilityBoard();
  }

  public static CapabilityBoard fromFile(String file) {
    try {
      Document specDoc = Documents.parseDocument(file, SCHEMA_PATH);
      CapabilityBoard board = of();
      loadAssignments(specDoc, board);
      return board;
    } catch (ParserConfigurationException | SAXException | IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  private static void loadAssignments(Document specDoc, CapabilityBoard board) {
    for (DefaultCapability c : DefaultCapability.values()) {
      Node capabilityEl = specDoc.getElementsByTagName(c.toString())
                                 .item(0);

      NodeList agents = capabilityEl.getChildNodes();
      for (int i = 0; i < agents.getLength(); i++) {
        Node agent = agents.item(i);
        if (agent.getNodeName()
                 .equals(c.toString())) {
          board.registerAg(agent.getTextContent(), c);
        }
      }
    }
  }
}
