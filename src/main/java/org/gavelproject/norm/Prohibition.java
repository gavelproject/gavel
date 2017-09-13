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

import jason.asSyntax.Literal;

/**
 * @author igorcadelima
 *
 */
final class Prohibition extends AbstractRegulationContent {
  private static final String NAME = "prohibition";

  /**
   * @param literal literal content
   * @throws IllegalArgumentException if functor is not 'prohibition'
   */
  Prohibition(Literal literal) throws IllegalArgumentException {
    super(literal);
    if (!literal.getFunctor()
                .equals(NAME)) {
      throw new IllegalArgumentException("Functor should be 'prohibition'");
    }
  }

  @Override
  public String toString() {
    return new StringBuilder(NAME).append(target)
                                  .append(maintenanceCondition)
                                  .append(aim)
                                  .append(deadline)
                                  .toString();
  }
}
