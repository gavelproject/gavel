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

import gavel.api.sanction.SanctionOutcome;
import gavel.impl.common.Enums;
import gavel.impl.common.DefaultUuid;

/**
 * Static utility methods pertaining to {@link SanctionOutcome} instances.
 * 
 * @author igorcadelima
 *
 */
public final class SanctionOutcomes {
  private SanctionOutcomes() {}

  /**
   * Return a new {@link SanctionOutcome} instance whose string representation is the given
   * {@code in}.
   * 
   * @param in string to be parsed
   * @return sanction outcome represented by the string argument if parsing succeeds; {@code null}
   *         otherwise
   * @throws IllegalArgumentException if string does not contain a parsable sanction outcome
   * @throws NullPointerException if string is {@code null}
   */
  public static SanctionOutcome tryParse(String norm) {
    Pattern pattern = Pattern.compile("sanction_outcome\\\\s*(" + "\\s*id\\s*\\(\\s*(\\w+)\\s*\\),"
        + "\\s*time\\s*\\(\\s*(\\w+)\\s*\\)," + "\\s*application_id\\s*\\(\\s*(\\w+)\\s*\\),"
        + "\\s*controller_id\\s*\\(\\s*(\\w+)\\s*\\)," + "\\s*efficacy\\s*\\(\\s*(\\w+)\\s*\\)\\)");
    Matcher matcher = pattern.matcher(norm);
    matcher.find();
    if (matcher.matches()) {
      return DefaultSanctionOutcome.builder()
                                .time(Long.valueOf(matcher.group(2)))
                                .applicationId(DefaultUuid.parse(matcher.group(3)))
                                .controllerId(matcher.group(4))
                                .efficacy(
                                    Enums.lookup(DiscreteSanctionEfficacy.class, matcher.group(5)))
                                .build();
    }
    return null;
  }
}
