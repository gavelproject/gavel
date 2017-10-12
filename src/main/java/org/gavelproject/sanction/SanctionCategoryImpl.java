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
package org.gavelproject.sanction;

import static org.gavelproject.sanction.SanctionCategories.NAME;

import gavel.api.sanction.SanctionCategory;
import gavel.api.sanction.SanctionCategoryBuilder;
import lombok.Builder;
import lombok.Value;

/**
 * @author igorcadelima
 *
 */
@Builder(builderClassName = "Builder")
@Value
class SanctionCategoryImpl implements SanctionCategory {
  SanctionPurposeImpl purpose;
  SanctionIssuerImpl issuer;
  SanctionLocusImpl locus;
  SanctionModeImpl mode;
  SanctionPolarityImpl polarity;
  SanctionDiscernabilityImpl discernability;

  @Override
  public String toString() {
    return new StringBuilder(NAME).append("discernability(")
                                  .append(discernability)
                                  .append("),issuer(")
                                  .append(issuer)
                                  .append("),locus(")
                                  .append(locus)
                                  .append("),mode(")
                                  .append(mode)
                                  .append("),polarity(")
                                  .append(polarity)
                                  .append("),purpose(")
                                  .append(purpose)
                                  .append(')')
                                  .toString();
  }

  public static final class Builder implements SanctionCategoryBuilder {

  }
}
