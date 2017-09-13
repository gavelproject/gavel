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

import org.gavelproject.common.Status;

import jason.asSyntax.Atom;
import jason.asSyntax.LogicalFormula;

/**
 * @author igorcadelima
 *
 */
public interface Sanction {
  /**
   * @return id
   */
  public Atom getId();

  /**
   * @return status
   * @see Status
   */
  Status getStatus();

  /**
   * @return activation condition
   */
  LogicalFormula getCondition();

  /**
   * @return category
   */
  SanctionCategory getCategory();

  /**
   * @return content
   */
  LogicalFormula getContent();

  /**
   * Enable the sanction changing its status to {@link Status#ENABLED}.
   * 
   * @return {@code true} is status was previously {@link Status#DISABLED}.
   */
  boolean enable();

  /**
   * Disable the sanction changing its status to {@link Status#DISABLED}.
   * 
   * @return {@code true} is status was previously {@link Status#ENABLED}.
   */
  boolean disable();
}
