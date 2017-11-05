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
package gavel.base.sanction;

import static gavel.impl.sanction.SanctionCategories.getStructureName;

import gavel.api.sanction.SanctionCategory;
import gavel.impl.sanction.DefaultSanctionDiscernability;
import gavel.impl.sanction.DefaultSanctionIssuer;
import gavel.impl.sanction.DefaultSanctionLocus;
import gavel.impl.sanction.DefaultSanctionMode;
import gavel.impl.sanction.DefaultSanctionPolarity;
import gavel.impl.sanction.DefaultSanctionPurpose;
import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @author igorcadelima
 *
 */
@Data
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class AbstractSanctionCategory implements SanctionCategory {
  private final DefaultSanctionPurpose purpose;
  private final DefaultSanctionIssuer issuer;
  private final DefaultSanctionLocus locus;
  private final DefaultSanctionMode mode;
  private final DefaultSanctionPolarity polarity;
  private final DefaultSanctionDiscernability discernability;

  @Override
  public String toString() {
    return new StringBuilder(getStructureName()).append("(purpose(")
                                                .append(purpose)
                                                .append("),issuer(")
                                                .append(issuer)
                                                .append("),locus(")
                                                .append(locus)
                                                .append("),mode(")
                                                .append(mode)
                                                .append("),polarity(")
                                                .append(polarity)
                                                .append("),discernability(")
                                                .append(discernability)
                                                .append("))")
                                                .toString();
  }
}
