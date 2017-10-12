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
public interface SanctionDecision {
  /**
   * Efficacy values that can be used:
   * <li>{@link #INDETERMINATE}</li>
   * <li>{@link #EFFECTIVE}</li>
   * <li>{@link #INEFFECTIVE}</li>
   */
  enum Efficacy {
    INDETERMINATE, EFFECTIVE, INEFFECTIVE;

    @Override
    public String toString() {
      return name().toLowerCase();
    }
  }

  /**
   * Cause values that can be used:
   * <li>{@link #COMPLIANCE}</li>
   * <li>{@link #VIOLATION}</li>
   */
  enum Cause {
    COMPLIANCE, VIOLATION;

    @Override
    public String toString() {
      return name().toLowerCase();
    }
  }

  Uuid getId();

  long getTime();

  String getSanctioner();

  String getSanctionee();

  String getNormId();

  String getSanctionId();

  Cause getCause();

  Efficacy getEfficacy();

  void setEfficacy(Efficacy e);

  boolean isApplied();

  void setApplied(boolean applied);
}
