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

import gavel.api.sanction.SanctionDecision;
import gavel.api.sanction.SanctionDecision.Cause;
import gavel.api.sanction.SanctionDecisionBuilder;
import gavel.impl.common.Enums;
import gavel.impl.norm.Norms;

/**
 * Static utility methods pertaining to {@link SanctionDecision} instances.
 * 
 * @author igorcadelima
 *
 */
public final class SanctionDecisions {
  static final String NAME = "sanction_decision";

  private SanctionDecisions() {}

  /** Returns the name of the structure used to represent a sanction decision. */
  public static String getStructureName() {
    return "sanction_decision";
  }

  /**
   * Return a new sanction decision initialized to the value represented by the specified
   * {@code String}.
   * 
   * @param decision string to be parsed
   * @return sanction decision represented by the string argument
   * @throws IllegalArgumentException if string does not contain a parsable sanction decision
   * @throws NullPointerException if string is {@code null}
   */
  public static SanctionDecision parse(String decision) {
    Pattern pattern = Pattern.compile("sanction_decision\\s*\\(" + "\\s*id\\s*\\(\\s*(\\w+)\\s*\\),"
        + "\\s*time\\s*\\(\\s*(\\w+)\\s*\\)," + "\\s*detector\\s*\\(\\s*(\\w+)\\s*\\),"
        + "\\s*evaluator\\s*\\(\\s*(.*?)\\s*\\)," + "\\s*target\\s*\\(\\s*(\\w+)\\s*\\),"
        + "\\s*norm\\s*\\(\\s*(.*?)\\s*\\)," + "\\s*sanction\\s*\\(\\s*(.*?)\\s*\\),"
        + "\\s*cause\\s*\\(\\s*(\\w+)\\s*\\)\\)");
    Matcher matcher = pattern.matcher(decision);
    matcher.find();
    if (matcher.matches()) {
      return builder().time(Long.parseLong(matcher.group(2)))
                      .detectorId(matcher.group(3))
                      .evaluatorId(matcher.group(4))
                      .targetId(matcher.group(5))
                      .normInstance(Norms.parse(matcher.group(6)))
                      .sanctionInstance(Sanctions.tryParse(matcher.group(7)))
                      .cause(Enums.lookup(Cause.class, matcher.group(8)))
                      .build();
    }
    throw new IllegalArgumentException("String does not contain a parsable sanction decision");
  }

  /** Return a builder for {@link SanctionDecision}. */
  public static SanctionDecisionBuilder builder() {
    return DefaultSanctionDecision.builder();
  }
}
