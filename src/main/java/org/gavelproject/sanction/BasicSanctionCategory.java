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

/**
 * @author igorcadelima
 *
 */
final class BasicSanctionCategory implements SanctionCategory {
  private SanctionPurpose purpose;
  private SanctionIssuer issuer;
  private SanctionLocus locus;
  private SanctionMode mode;
  private SanctionPolarity polarity;
  private SanctionDiscernability discernability;

  /**
   * SanctionCategory constructor.
   * 
   * @param builder builder from which data should be obtained
   */
  BasicSanctionCategory(Builder builder) {
    this.purpose = builder.purpose;
    this.issuer = builder.issuer;
    this.locus = builder.locus;
    this.mode = builder.mode;
    this.polarity = builder.polarity;
    this.discernability = builder.discernability;
  }

  @Override
  public SanctionPurpose getPurpose() {
    return purpose;
  }

  @Override
  public SanctionIssuer getIssuer() {
    return issuer;
  }

  @Override
  public SanctionLocus getLocus() {
    return locus;
  }

  @Override
  public SanctionMode getMode() {
    return mode;
  }

  @Override
  public SanctionPolarity getPolarity() {
    return polarity;
  }

  @Override
  public SanctionDiscernability getDiscernability() {
    return discernability;
  }

  @Override
  public String toString() {
    return new StringBuilder(NAME).append("discernability(")
                                  .append(discernability.lowercase())
                                  .append("),issuer(")
                                  .append(issuer.lowercase())
                                  .append("),locus(")
                                  .append(locus.lowercase())
                                  .append("),mode(")
                                  .append(mode.lowercase())
                                  .append("),polarity(")
                                  .append(polarity.lowercase())
                                  .append("),purpose(")
                                  .append(purpose.lowercase())
                                  .append(')')
                                  .toString();
  }

  static final class Builder implements SanctionCategoryBuilder {
    private SanctionPurpose purpose;
    private SanctionIssuer issuer;
    private SanctionLocus locus;
    private SanctionMode mode;
    private SanctionPolarity polarity;
    private SanctionDiscernability discernability;

    public Builder setPurpose(SanctionPurpose purpose) {
      this.purpose = purpose;
      return this;
    }

    public Builder setIssuer(SanctionIssuer issuer) {
      this.issuer = issuer;
      return this;
    }

    public Builder setLocus(SanctionLocus locus) {
      this.locus = locus;
      return this;
    }

    public Builder setMode(SanctionMode mode) {
      this.mode = mode;
      return this;
    }

    public Builder setPolarity(SanctionPolarity polarity) {
      this.polarity = polarity;
      return this;
    }

    public Builder setDiscernability(SanctionDiscernability discernability) {
      this.discernability = discernability;
      return this;
    }

    public BasicSanctionCategory build() {
      return new BasicSanctionCategory(this);
    }
  }
}
