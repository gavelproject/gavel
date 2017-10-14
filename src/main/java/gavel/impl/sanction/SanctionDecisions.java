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

import gavel.api.sanction.SanctionDecision;
import gavel.api.sanction.SanctionDecision.Cause;
import gavel.impl.common.Enums;
import gavel.impl.norm.Norms;
import jason.asSyntax.ASSyntax;
import jason.asSyntax.Literal;
import jason.asSyntax.NumberTerm;

/**
 * Static utility methods pertaining to {@link SanctionDecision} instances.
 * 
 * @author igorcadelima
 *
 */
public final class SanctionDecisions {
  static final String NAME = "sanction_decision";

  private SanctionDecisions() {}

  /**
   * Return a new sanction decision initialised to the value represented by the specified
   * {@code String}.
   * 
   * @param decision string to be parsed
   * @return sanction decision represented by the string argument
   * @throws IllegalArgumentException if string does not contain a parsable sanction decision
   * @throws NullPointerException if string is {@code null}
   */
  public static SanctionDecision parse(String decision) {
    try {
      Literal l = ASSyntax.parseLiteral(decision);

      if (!l.getFunctor()
            .equals(NAME)) {
        throw new IllegalArgumentException();
      }

      return DefaultSanctionDecision.builder()
                                 .time((long) ((NumberTerm) l.getTerm(1)).solve())
                                 .detectorId(l.getTerm(2)
                                              .toString())
                                 .evaluatorId(l.getTerm(3)
                                               .toString())
                                 .targetId(l.getTerm(4)
                                            .toString())
                                 .normInstance(Norms.parse(l.getTerm(5)
                                                            .toString()))
                                 .sanctionInstance(Sanctions.tryParse(l.getTerm(6)
                                                                       .toString()))
                                 .cause(Enums.lookup(Cause.class, l.getTerm(7)
                                                                   .toString()))
                                 .build();

    } catch (Exception e) {
      throw new IllegalArgumentException("String does not contain a parsable sanction decision");
    }
  }
}
