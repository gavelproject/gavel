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

import gavel.api.norm.Norm;
import gavel.api.sanction.SanctionDecision.Cause;

/**
 * @author igorcadelima
 *
 */
public interface SanctionDecisionBuilder {
  /**
   * Set time the sanction outcome was obtained.
   * 
   * @param time time at which the outcome was obtained
   * @return builder builder instance
   */
  SanctionDecisionBuilder time(long time);

  /**
   * Set id of the detector which reported the normative event.
   * 
   * @param detectorId id of the detector
   * @return builder builder instance
   */
  SanctionDecisionBuilder detectorId(String detectorId);

  /**
   * Set id of the evaluator which assessed the normative event.
   * 
   * @param evaluatorId id of the evaluator
   * @return builder builder instance
   */
  SanctionDecisionBuilder evaluatorId(String evaluatorId);

  /**
   * Set id of the target entity to be sanctioned.
   * 
   * @param targetId id of the target
   * @return builder builder instance
   */
  SanctionDecisionBuilder targetId(String targetId);

  /**
   * Set evaluated norm instance.
   * 
   * @param normInstance norm instance which was evaluated
   * @return builder builder instance
   */
  SanctionDecisionBuilder normInstance(Norm normInstance);

  /**
   * Set sanction instance.
   * 
   * @param sanctionInstance sanction instance
   * @return builder builder instance
   */
  SanctionDecisionBuilder sanctionInstance(Sanction sanctionInstance);

  /**
   * Set cause which led the evaluator to decide the sanction.
   * 
   * @param cause cause for the sanction decided
   * @return builder builder instance
   */
  SanctionDecisionBuilder cause(Cause cause);

  /**
   * Return a new {@link SanctionOutcome} with the specified implementation.
   * 
   * @return new {@code SanctionOutcome}
   */
  SanctionDecision build();
}
