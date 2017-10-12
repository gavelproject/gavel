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

import org.gavelproject.sanction.SanctionDiscernabilityImpl;
import org.gavelproject.sanction.SanctionIssuerImpl;
import org.gavelproject.sanction.SanctionLocusImpl;
import org.gavelproject.sanction.SanctionModeImpl;
import org.gavelproject.sanction.SanctionPolarityImpl;
import org.gavelproject.sanction.SanctionPurposeImpl;

public interface SanctionCategoryBuilder {
  /**
   * @param purpose sanction purpose
   */
  SanctionCategoryBuilder setPurpose(SanctionPurposeImpl purpose);

  /**
   * @param issuer sanction issuer
   */
  SanctionCategoryBuilder setIssuer(SanctionIssuerImpl issuer);

  /**
   * @param locus sanction locus
   */
  SanctionCategoryBuilder setLocus(SanctionLocusImpl locus);

  /**
   * @param mode sanction mode
   */
  SanctionCategoryBuilder setMode(SanctionModeImpl mode);

  /**
   * @param polarity sanction polarity
   */
  SanctionCategoryBuilder setPolarity(SanctionPolarityImpl polarity);

  /**
   * @param discernability sanction discernability
   */
  SanctionCategoryBuilder setDiscernability(SanctionDiscernabilityImpl discernability);

  /**
   * Return a new {@link SanctionCategory} with the given properties.
   * 
   * @return new {@code SanctionCategory}
   */
  SanctionCategory build();
}
