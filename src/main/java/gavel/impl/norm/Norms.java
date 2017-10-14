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
package gavel.impl.norm;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import gavel.api.norm.Norm;
import gavel.api.norm.NormBuilder;
import gavel.impl.common.DefaultStatus;
import gavel.impl.common.Enums;
import gavel.impl.common.LogicalFormulas;

/**
 * Static utility methods pertaining to {@link Norm} instances.
 * 
 * @author igorcadelima
 *
 */
public final class Norms {
  private Norms() {}

  /** Returns the name of the structure used to represent a norm. */
  public static String getStructureName() {
    return "norm";
  }

  /**
   * Return a copy of the given norm.
   * 
   * @param norm norm to be copied
   * @return copy of {@code norm}
   */
  public static Norm newInstance(Norm norm) {
    return builder().id(norm.getId())
                    .status(norm.getStatus())
                    .issuer(norm.getIssuer())
                    .condition(norm.getCondition())
                    .content(norm.getContent())
                    .build();
  }

  /**
   * Return a new norm initialised to the value represented by the specified {@code String}.
   * 
   * @param norm string to be parsed
   * @return norm represented by the string argument
   * @throws IllegalArgumentException if string does not contain a parsable norm
   * @throws NullPointerException if string is {@code null}
   */
  public static Norm parse(String norm) {
    Pattern pattern = Pattern.compile("norm\\\\s*(" + "\\s*id\\s*\\(\\s*(\\w+)\\s*\\),"
        + "\\s*status\\s*\\(\\s*(\\w+)\\s*\\)," + "\\s*condition\\s*\\(\\s*(\\w+)\\s*\\),"
        + "\\s*issuer\\s*\\(\\s*(\\w+)\\s*\\)," + "\\s*content\\s*\\(\\s*(\\w+)\\s*\\)\\)");
    Matcher matcher = pattern.matcher(norm);
    matcher.find();
    if (matcher.matches()) {
      return builder().id(matcher.group(1))
                      .status(Enums.lookup(DefaultStatus.class, matcher.group(2)))
                      .condition(LogicalFormulas.tryParse(matcher.group(3)))
                      .issuer(matcher.group(4))
                      .content(LogicalFormulas.tryParse(matcher.group(5)))
                      .build();
    }
    throw new IllegalArgumentException("String does not contain a parsable norm");
  }

  /** Return a builder for {@link Norm}. */
  public static NormBuilder builder() {
    return DefaultNorm.builder();
  }
}
