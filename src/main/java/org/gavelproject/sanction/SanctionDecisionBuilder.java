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

import org.gavelproject.sanction.SanctionDecision.Cause;
import org.gavelproject.sanction.SanctionDecision.Efficacy;

final class SanctionDecisionBuilder {
  private long time;
  private String sanctioner;
  private String sanctionee;
  private String norm;
  private String sanction;
  private Cause cause;
  private Efficacy efficacy = Efficacy.INDETERMINATE;
  private boolean applied;

  SanctionDecisionBuilder time(long time) {
    this.time = time;
    return this;
  }

  SanctionDecisionBuilder sanctioner(String sanctioner) {
    this.sanctioner = sanctioner;
    return this;
  }

  SanctionDecisionBuilder sanctionee(String sanctionee) {
    this.sanctionee = sanctionee;
    return this;
  }

  SanctionDecisionBuilder norm(String norm) {
    this.norm = norm;
    return this;
  }

  SanctionDecisionBuilder sanction(String sanction) {
    this.sanction = sanction;
    return this;
  }

  SanctionDecisionBuilder cause(Cause cause) {
    this.cause = cause;
    return this;
  }

  SanctionDecisionBuilder efficacy(Efficacy efficacy) {
    this.efficacy = efficacy;
    return this;
  }

  SanctionDecisionBuilder applied(boolean applied) {
    this.applied = applied;
    return this;
  }

  /**
   * Return a new {@link SanctionDecision} with the specified implementation.
   * 
   * @return new {@link SanctionDecision} instance
   */
  BasicSanctionDecision build() {
    return new BasicSanctionDecision(this);
  }

  long time() {
    return time;
  }

  String sanctioner() {
    return sanctioner;
  }

  String sanctionee() {
    return sanctionee;
  }

  String norm() {
    return norm;
  }

  String sanction() {
    return sanction;
  }

  Cause cause() {
    return cause;
  }

  Efficacy efficacy() {
    return efficacy;
  }

  boolean applied() {
    return applied;
  }
}
