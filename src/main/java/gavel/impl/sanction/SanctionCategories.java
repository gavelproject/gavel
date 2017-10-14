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

import gavel.api.sanction.SanctionCategory;
import gavel.api.sanction.SanctionCategoryBuilder;
import gavel.impl.common.Enums;

/**
 * Static utility methods pertaining to {@link SanctionCategory} instances.
 * 
 * @author igorcadelima
 *
 */
public final class SanctionCategories {
  static final String NAME = "category";

  private SanctionCategories() {}

  /**
   * Return a new {@link SanctionCategory} object initialised to the value represented by the
   * specified {@code Element}.
   * 
   * @param el element to be parsed
   * @return {@link SanctionCategory} object represented by {@code el}
   * @throws NullPointerException if element is {@code null}
   */
  public static SanctionCategory of(Element el) {
    NodeList dimensions = el.getChildNodes();
    SanctionCategoryBuilder builder = builder();

    for (int i = 0; i < dimensions.getLength(); i++) {
      Node dimensionNode = dimensions.item(i);
      String dimension = dimensionNode.getTextContent();

      switch (dimensionNode.getNodeName()) {
        case "purpose":
          builder.purpose(Enums.lookup(DefaultSanctionPurpose.class, dimension));
          break;
        case "issuer":
          builder.issuer(Enums.lookup(DefaultSanctionIssuer.class, dimension));
          break;
        case "locus":
          builder.locus(Enums.lookup(DefaultSanctionLocus.class, dimension));
          break;
        case "mode":
          builder.mode(Enums.lookup(DefaultSanctionMode.class, dimension));
          break;
        case "polarity":
          builder.polarity(Enums.lookup(DefaultSanctionPolarity.class, dimension));
          break;
        case "discernability":
          builder.discernability(Enums.lookup(DefaultSanctionDiscernability.class, dimension));
          break;
      }
    }
    return builder.build();
  }

  /**
   * Return a new {@link SanctionCategory} instance whose string representation is the given
   * {@code in}.
   * 
   * @param in string to be parsed
   * @return sanction category represented by the string argument if parsing succeeds; {@code null}
   *         otherwise
   * @throws IllegalArgumentException if string does not contain a parsable sanction category
   * @throws NullPointerException if string is {@code null}
   */
  public static SanctionCategory tryParse(String in) {
    Pattern pattern =
        Pattern.compile("sanction_category\\\\s*(" + "\\s*purpose\\s*\\(\\s*(\\w+)\\s*\\),"
            + "\\s*issuer\\s*\\(\\s*(\\w+)\\s*\\)," + "\\s*locus\\s*\\(\\s*(\\w+)\\s*\\),"
            + "\\s*mode\\s*\\(\\s*(\\w+)\\s*\\)," + "\\s*polarity\\s*\\(\\s*(\\w+)\\s*\\),"
            + "\\s*discernability\\s*\\(\\s*(\\w+)\\s*\\)\\)");
    Matcher matcher = pattern.matcher(in);
    matcher.find();
    if (matcher.matches()) {
      return builder().purpose(Enums.lookup(DefaultSanctionPurpose.class, matcher.group(1)))
                      .issuer(Enums.lookup(DefaultSanctionIssuer.class, matcher.group(2)))
                      .locus(Enums.lookup(DefaultSanctionLocus.class, matcher.group(3)))
                      .mode(Enums.lookup(DefaultSanctionMode.class, matcher.group(4)))
                      .polarity(Enums.lookup(DefaultSanctionPolarity.class, matcher.group(5)))
                      .discernability(
                          Enums.lookup(DefaultSanctionDiscernability.class, matcher.group(6)))
                      .build();
    }
    return null;
  }

  /** Return a builder for {@link SanctionCategory}. */
  public static SanctionCategoryBuilder builder() {
    return new DefaultSanctionCategory.Builder();
  }
}
