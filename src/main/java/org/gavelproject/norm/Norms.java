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
package org.gavelproject.norm;

import org.gavelproject.common.Enums;
import org.gavelproject.common.Status;
import org.gavelproject.norm.BasicNorm.Builder;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import jason.asSyntax.ASSyntax;
import jason.asSyntax.Atom;
import jason.asSyntax.Literal;
import jason.asSyntax.LogicalFormula;
import jason.asSyntax.parser.ParseException;

/**
 * Static utility methods pertaining to {@link Norm} instances.
 * 
 * @author igorcadelima
 *
 */
public final class Norms {
  static final String NAME = "norm";

  private Norms() {}

  /**
   * Return a copy of the given norm.
   * 
   * @param norm norm to be copied
   * @return copy of {@code norm}
   */
  public static Norm newInstance(Norm norm) {
    return new BasicNorm.Builder().id(norm.getId())
                                  .status(norm.getStatus())
                                  .issuer(norm.getIssuer())
                                  .condition(norm.getCondition())
                                  .content(norm.getContent())
                                  .build();
  }

  /**
   * Return a new norm initialised to the value represented by the specified {@code String}.
   * 
   * @param norm string to be parsed
   * @return norm represented by the string argument
   * @throws IllegalArgumentException if string does not contain a parsable norm
   * @throws NullPointerException if string is {@code null}
   */
  public static Norm parse(String norm) {
    try {
      Literal l = ASSyntax.parseLiteral(norm);
      Status status = Enums.lookup(Status.class, l.getTerm(1)
                                                  .toString());
      NormContent content = NormContents.of((Literal) l.getTerm(4));
      return new Builder().id((Atom) l.getTerm(0))
                          .status(status)
                          .condition((LogicalFormula) l.getTerm(2))
                          .issuer((Atom) l.getTerm(3))
                          .content(content)
                          .build();
    } catch (ParseException e) {
      throw new IllegalArgumentException("String does not contain a parsable norm");
    }
  }

  public static Norm of(Element el) {
    Builder builder = new Builder();
    builder.id(ASSyntax.createAtom(el.getAttribute("id")))
           .status(Enums.lookup(Status.class, el.getAttribute("status"), Status.ENABLED));

    NodeList properties = el.getChildNodes();
    for (int i = 0; i < properties.getLength(); i++) {
      Node prop = properties.item(i);
      String propContent = prop.getTextContent();

      switch (prop.getNodeName()) {
        case "condition":
          try {
            builder.condition(ASSyntax.parseFormula(propContent));
          } catch (ParseException e) {
            throw new IllegalArgumentException("Element does not contain a parsable norm");
          }
          break;
        case "issuer":
          builder.issuer(ASSyntax.createAtom(propContent));
          break;
        case "content":
          builder.content(NormContents.parse(propContent));
          break;
        default: // Ignore
      }
    }
    return builder.build();
  }
}
