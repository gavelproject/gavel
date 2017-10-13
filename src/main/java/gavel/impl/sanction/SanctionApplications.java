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

import gavel.api.common.Uuid;
import gavel.api.sanction.SanctionApplication;
import gavel.impl.common.UuidImpl;

/**
 * Static utility methods pertaining to {@link SanctionApplication} instances.
 * 
 * @author igorcadelima
 *
 */
public final class SanctionApplications {
  private SanctionApplications() {}

  public static SanctionApplication of(long time, Uuid decisionId, String executorId) {
    return SanctionApplicationImpl.of(time, decisionId, executorId);
  }

  /**
   * Return a new {@link SanctionApplication} instance whose string representation is the given
   * {@code in}.
   * 
   * @param in string to be parsed
   * @return sanction application represented by the string argument if parsing succeeds;
   *         {@code null} otherwise
   * @throws IllegalArgumentException if string does not contain a parsable sanction application
   * @throws NullPointerException if string is {@code null}
   */
  public static SanctionApplication tryParse(String norm) {
    Pattern pattern =
        Pattern.compile("sanction_application\\\\s*(" + "\\s*id\\s*\\(\\s*(\\w+)\\s*\\),"
            + "\\s*time\\s*\\(\\s*(\\w+)\\s*\\)," + "\\s*decision_id\\s*\\(\\s*(\\w+)\\s*\\),"
            + "\\s*executor_id\\s*\\(\\s*(\\w+)\\s*\\)\\)");
    Matcher matcher = pattern.matcher(norm);
    matcher.find();
    if (matcher.matches()) {
      return of(Long.valueOf(matcher.group(2)), UuidImpl.parse(matcher.group(3)), matcher.group(4));
    }
    return null;
  }
}