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

import gavel.api.common.Uuid;

/**
 * @author igorcadelima
 *
 */
public interface SanctionOutcomeBuilder {
  /**
   * Set time the sanction outcome was obtained.
   * 
   * @param time time at which the outcome was obtained
   * @return builder builder instance
   */
  SanctionOutcomeBuilder time(long time);

  /**
   * Set id of the sanction application to which the outcome relates.
   * 
   * @param applicationId id of the sanction application
   * @return builder builder instance
   */
  SanctionOutcomeBuilder applicationId(Uuid applicationId);

  /**
   * Set id of the controller which assessed the sanction outcome.
   * 
   * @param controllerId id of the controller
   * @return builder builder instance
   */
  SanctionOutcomeBuilder controllerId(String controllerId);

  /**
   * Set efficacy of the sanction application.
   * 
   * @param efficacy sanction efficacy
   * @return builder builder instance
   */
  SanctionOutcomeBuilder efficacy(SanctionEfficacy efficacy);

  /**
   * Return a new {@link SanctionOutcome} with the specified implementation.
   * 
   * @return new {@code SanctionOutcome}
   */
  SanctionOutcome build();
}
