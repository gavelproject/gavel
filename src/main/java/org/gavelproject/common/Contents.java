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
package org.gavelproject.common;

import static jason.asSyntax.ASSyntax.parseFormula;

import jason.asSyntax.parser.ParseException;

/**
 * Static utility methods pertaining to {@link Content} instances.
 * 
 * @author igorcadelima
 *
 */
public final class Contents {
  private Contents() {}

  /**
   * Return a new {@link Content} instance whose string representation is the given
   * {@code in}.
   * 
   * @param in string to be parsed
   * @return content represented by the string argument
   * @throws IllegalArgumentException if string does not contain a parsable content
   * @throws NullPointerException if string is {@code null}
   */
  public static Content tryParse(String in) {
    String cleanedIn = in.trim().replaceAll("(^content\\s+\\()|(\\)$)", ""); 
    try {
      return new Content() {
        private final jason.asSyntax.LogicalFormula formula = parseFormula(cleanedIn);

        @Override
        public String toString() {
          return "content(" + formula.toString() + ')';
        }
      };
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return null;
  }
}
