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

import static org.gavelproject.sanction.SanctionDecisions.NAME;

import gavel.api.common.Uuid;
import gavel.api.sanction.SanctionDecision;
import gavel.impl.common.UuidImpl;

/**
 * This class provides a basic implementation of the {@link SanctionDecision} interface.
 * 
 * @author igorcadelima
 *
 */
final class SanctionDecisionImpl implements SanctionDecision {
  private final Uuid id = UuidImpl.newInstance();
  private final long time;
  private final String sanctioner;
  private final String sanctionee;
  private final String normId;
  private final String sanctionId;
  private final Cause cause;
  private Efficacy efficacy = Efficacy.INDETERMINATE;
  private boolean applied;

  /**
   * Constructs a {@link SanctionDecisionImpl} with the properties specified in {@code builder}.
   */
  SanctionDecisionImpl(SanctionDecisionBuilder builder) {
    time = builder.time();
    sanctioner = builder.sanctioner();
    sanctionee = builder.sanctionee();
    normId = builder.normId();
    sanctionId = builder.sanctionId();
    cause = builder.cause();
    efficacy = builder.efficacy();
    applied = builder.applied();
  }

  /** Return new {@link SanctionDecisionBuilder} instance. */
  static SanctionDecisionBuilder builder() {
    return new SanctionDecisionBuilder();
  }

  @Override
  public Uuid getId() {
    return id;
  }

  @Override
  public long getTime() {
    return time;
  }

  @Override
  public String getSanctioner() {
    return sanctioner;
  }

  @Override
  public String getSanctionee() {
    return sanctionee;
  }

  @Override
  public String getNormId() {
    return normId;
  }

  @Override
  public String getSanctionId() {
    return sanctionId;
  }

  @Override
  public Cause getCause() {
    return cause;
  }

  @Override
  public Efficacy getEfficacy() {
    return efficacy;
  }

  @Override
  public void setEfficacy(Efficacy efficacy) {
    this.efficacy = efficacy;
  }

  @Override
  public boolean isApplied() {
    return applied;
  }

  @Override
  public void setApplied(boolean applied) {
    this.applied = applied;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (applied ? 1231 : 1237);
    result = prime * result + ((cause == null) ? 0 : cause.hashCode());
    result = prime * result + ((efficacy == null) ? 0 : efficacy.hashCode());
    result = prime * result + ((normId == null) ? 0 : normId.hashCode());
    result = prime * result + ((sanctionId == null) ? 0 : sanctionId.hashCode());
    result = prime * result + ((sanctionee == null) ? 0 : sanctionee.hashCode());
    result = prime * result + ((sanctioner == null) ? 0 : sanctioner.hashCode());
    result = prime * result + (int) (time ^ (time >>> 32));
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    SanctionDecisionImpl other = (SanctionDecisionImpl) obj;
    if (applied != other.applied)
      return false;
    if (cause != other.cause)
      return false;
    if (efficacy != other.efficacy)
      return false;
    if (normId == null) {
      if (other.normId != null)
        return false;
    } else if (!normId.equals(other.normId))
      return false;
    if (sanctionId == null) {
      if (other.sanctionId != null)
        return false;
    } else if (!sanctionId.equals(other.sanctionId))
      return false;
    if (sanctionee == null) {
      if (other.sanctionee != null)
        return false;
    } else if (!sanctionee.equals(other.sanctionee))
      return false;
    if (sanctioner == null) {
      if (other.sanctioner != null)
        return false;
    } else if (!sanctioner.equals(other.sanctioner))
      return false;
    if (time != other.time)
      return false;
    return true;
  }

  @Override
  public String toString() {
    return new StringBuilder(NAME + '(').append("id(" + id + "),")
                                        .append("time(" + id + "),")
                                        .append("sanctioner(" + sanctioner + "),")
                                        .append("sanctionee(" + sanctionee + "),")
                                        .append("norm_id(" + normId + "),")
                                        .append("sanction_id(" + sanctionId + "),")
                                        .append("cause(" + cause + "),")
                                        .append("efficacy(" + efficacy + "),")
                                        .append("applied(" + applied + ")")
                                        .toString();
  }
}
