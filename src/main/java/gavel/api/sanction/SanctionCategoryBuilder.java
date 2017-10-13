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
package gavel.api.sanction;

import gavel.impl.sanction.SanctionDiscernabilityImpl;
import gavel.impl.sanction.SanctionIssuerImpl;
import gavel.impl.sanction.SanctionLocusImpl;
import gavel.impl.sanction.SanctionModeImpl;
import gavel.impl.sanction.SanctionPolarityImpl;
import gavel.impl.sanction.SanctionPurposeImpl;

public interface SanctionCategoryBuilder {
  /**
   * @param purpose sanction purpose
   */
  SanctionCategoryBuilder purpose(SanctionPurposeImpl purpose);

  /**
   * @param issuer sanction issuer
   */
  SanctionCategoryBuilder issuer(SanctionIssuerImpl issuer);

  /**
   * @param locus sanction locus
   */
  SanctionCategoryBuilder locus(SanctionLocusImpl locus);

  /**
   * @param mode sanction mode
   */
  SanctionCategoryBuilder mode(SanctionModeImpl mode);

  /**
   * @param polarity sanction polarity
   */
  SanctionCategoryBuilder polarity(SanctionPolarityImpl polarity);

  /**
   * @param discernability sanction discernability
   */
  SanctionCategoryBuilder discernability(SanctionDiscernabilityImpl discernability);

  /**
   * Return a new {@link SanctionCategory} with the given properties.
   * 
   * @return new {@code SanctionCategory}
   */
  SanctionCategory build();
}
