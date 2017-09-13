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
import org.gavelproject.common.LowercaseEnum;

import jason.asSyntax.ASSyntax;
import jason.asSyntax.Literal;
import jason.asSyntax.parser.ParseException;

/**
 * Static utility methods pertaining to {@link NormContent} instances.
 * 
 * @author igorcadelima
 *
 */
public final class NormContents {
  private NormContents() {}

  private enum Mode implements LowercaseEnum {
    OBLIGATION {
      @Override
      NormContent newContent(Literal literal) {
        return new Obligation(literal);
      }
    },

    PERMISSION {
      @Override
      NormContent newContent(Literal literal) {
        return new Permission(literal);
      }
    },

    PROHIBITION {
      @Override
      NormContent newContent(Literal literal) {
        return new Prohibition(literal);
      }
    };

    /**
     * Factory method that returns a new {@link NormContent} instance whose literal representation
     * is the given {@code literal}.
     * 
     * @param literal literal representation of the content
     * @return new content
     */
    abstract NormContent newContent(Literal literal);
  }

  /**
   * Return a new {@link NormContent} instance whose literal representation is the given
   * {@code literal}.
   * 
   * This method considers not only number of terms, but also the functor.
   * 
   * @param literal literal representation of the content
   * @return new content
   */
  public static NormContent of(Literal literal) {
    return Enums.lookup(Mode.class, literal.getFunctor())
                .newContent(literal);
  }

  /**
   * Return a new {@link NormContent} instance whose string representation is the given {@code in}.
   * 
   * @param in string to be parsed
   * @return content represented by the string argument
   * @throws IllegalArgumentException if string does not contain a parsable norm
   * @throws NullPointerException if string is {@code null}
   */
  public static NormContent parse(String in) {
    try {
      Literal literal = ASSyntax.parseLiteral(in);
      return NormContents.of(literal);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return null;
  }
}
