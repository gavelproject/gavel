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
package gavel.impl.sanction;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import gavel.api.sanction.Sanction;
import gavel.api.sanction.SanctionBuilder;
import gavel.impl.common.DefaultStatus;
import gavel.impl.common.Enums;
import gavel.impl.common.LogicalFormulas;

/**
 * Static utility methods pertaining to {@link Sanction} instances.
 * 
 * @author igorcadelima
 *
 */
public final class Sanctions {
  private Sanctions() {}

  /** Returns the name of the structure used to represent a sanction. */
  public static String getStructureName() {
    return "sanction";
  }

  /**
   * Return a copy of the given sanction.
   * 
   * @param sanction sanction to be copied
   * @return copy of {@code sanction}
   */
  public static Sanction newInstance(Sanction sanction) {
    return builder().id(sanction.getId())
                    .status(sanction.getStatus())
                    .condition(sanction.getCondition())
                    .category(sanction.getCategory())
                    .content(sanction.getContent())
                    .build();
  }

  /**
   * Return a new {@link Sanction} instance initialised to the value represented by the specified
   * {@code Element}.
   * 
   * @param el element to be parsed
   * @return {@link Sanction} instance represented by {@code el}
   * @throws NullPointerException if element is {@code null}
   */
  public static Sanction parse(Element el) {
    SanctionBuilder builder = builder();
    builder.id(el.getAttribute("id"))
           .status(
               Enums.lookup(DefaultStatus.class, el.getAttribute("status"), DefaultStatus.ENABLED));

    NodeList props = el.getChildNodes();
    for (int i = 0; i < props.getLength(); i++) {
      Node prop = props.item(i);

      switch (prop.getNodeName()) {
        case "condition":
          builder.condition(LogicalFormulas.tryParse(prop.getTextContent()));
          break;
        case "category":
          builder.category(SanctionCategories.of((Element) prop));
          break;
        case "content":
          builder.content(LogicalFormulas.tryParse(prop.getTextContent()));
        default: // Ignore
      }
    }
    return builder.build();
  }

  /**
   * Return a new {@link Sanction} instance whose string representation is the given {@code in}.
   * 
   * @param in string to be parsed
   * @return sanction represented by the string argument if parsing succeeds; {@code null} otherwise
   * @throws IllegalArgumentException if string does not contain a parsable sanction
   * @throws NullPointerException if string is {@code null}
   */
  public static Sanction tryParse(String in) {
    Pattern pattern = Pattern.compile("sanction\\\\s*(" + "\\s*id\\s*\\(\\s*(\\w+)\\s*\\),"
        + "\\s*status\\s*\\(\\s*(\\w+)\\s*\\)," + "\\s*activation\\s*\\(\\s*(\\w+)\\s*\\),"
        + "\\s*category\\s*\\(\\s*(\\w+)\\s*\\)," + "\\s*content\\s*\\(\\s*(\\w+)\\s*\\)\\)");
    Matcher matcher = pattern.matcher(in);
    matcher.find();
    if (matcher.matches()) {
      return builder().id(matcher.group(1))
                      .status(Enums.lookup(DefaultStatus.class, matcher.group(2)))
                      .condition(LogicalFormulas.tryParse(matcher.group(3)))
                      .category(SanctionCategories.tryParse(matcher.group(4)))
                      .content(LogicalFormulas.tryParse(matcher.group(5)))
                      .build();
    }
    return null;
  }

  /** Return a builder for {@link Sanction}. */
  public static SanctionBuilder builder() {
    return DefaultSanction.builder();
  }
}
