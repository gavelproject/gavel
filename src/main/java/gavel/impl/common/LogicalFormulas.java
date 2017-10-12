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
package gavel.impl.common;

import gavel.api.common.LogicalFormula;
import jason.asSyntax.ASSyntax;
import jason.asSyntax.parser.ParseException;

/**
 * Static utility methods pertaining to {@link LogicalFormula} instances.
 * 
 * @author igorcadelima
 *
 */
final class LogicalFormulas {
  private LogicalFormulas() {}

  /**
   * Return a new {@link LogicalFormula} instance whose string representation is the given
   * {@code in}.
   * 
   * @param in string to be parsed
   * @return logical formula represented by the string argument
   * @throws IllegalArgumentException if string does not contain a parsable logical formula
   * @throws NullPointerException if string is {@code null}
   */
  static LogicalFormula tryParse(String in) {
    try {
      return new LogicalFormula() {
        private final jason.asSyntax.LogicalFormula formula = ASSyntax.parseFormula(in);

        @Override
        public String toString() {
          return formula.toString();
        }
      };
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return null;
  }
}
