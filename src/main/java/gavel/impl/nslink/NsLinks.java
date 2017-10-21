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
package gavel.impl.nslink;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import gavel.api.common.Status;
import gavel.api.nslink.NsLink;
import gavel.impl.common.DefaultStatus;
import gavel.impl.common.Enums;

/**
 * Static utility methods pertaining to {@link NsLink} instances.
 * 
 * @author igorcadelima
 *
 */
public final class NsLinks {
  private NsLinks() {}

  /** Returns the name of the structure used to represent a norm-sanction link. */
  public static String getStructureName() {
    return "nslink";
  }

  public static NsLink of(String normId, String sanctionId) {
    return new DefaultNsLink(normId, sanctionId);
  }

  public static NsLink of(Status status, String normId, String sanctionId) {
    return new DefaultNsLink(status, normId, sanctionId);
  }

  /**
   * Return a new {@link NsLink} instance whose string representation is the given {@code in}.
   * 
   * @param in string to be parsed
   * @return norm-sanction link represented by the string argument if parsing succeeds; {@code null}
   *         otherwise
   * @throws IllegalArgumentException if string does not contain a parsable norm-sanction link
   * @throws NullPointerException if string is {@code null}
   */
  public static NsLink tryParse(String in) {
    Pattern pattern = Pattern.compile("nslink\\\\s*(" + "\\s*status\\s*\\(\\s*(\\w+)\\s*\\),"
        + "\\s*norm_id\\s*\\(\\s*(\\w+)\\s*\\)," + "\\s*sanction_id\\s*\\(\\s*(\\w+)\\s*\\)\\)");
    Matcher matcher = pattern.matcher(in);
    matcher.find();
    if (matcher.matches()) {
      return of(Enums.lookup(DefaultStatus.class, matcher.group(1)), matcher.group(2),
          matcher.group(3));
    }
    return null;
  }
}
