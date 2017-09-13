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
package org.gavelproject.norm;

import java.util.Set;

import org.gavelproject.common.Status;
import org.gavelproject.sanction.Sanction;

import jason.asSyntax.LogicalFormula;

/**
 * @author igorcadelima
 *
 */
public interface Norm {
  /**
   * @return norm's id
   */
  String getId();

  /**
   * @return status
   * @see Status
   */
  Status getStatus();

  /**
   * @return norm's activation condition
   */
  LogicalFormula getCondition();

  /**
   * @return norm issuer's name
   */
  String getIssuer();

  /**
   * @return norm's content
   */
  NormContent getContent();

  /**
   * @return sanctions linked to the norm
   */
  Set<Sanction> getSanctions();

  /**
   * @param sanction sanction which can be applied should the norm be complied or violated
   * @return {@code true} if the sanction was successfully added
   */
  boolean addSanction(Sanction sanction);

  /**
   * @param sanctionId id of the sanction to be dissociated from the norm
   * @return {@code true} if the sanction was successfully dissociated
   */
  boolean removeSanction(String sanctionId);

  /**
   * Enable the norm changing its status to {@link Status#ENABLED}.
   * 
   * @return {@code true} is status was previously {@link Status#DISABLED}.
   */
  boolean enable();

  /**
   * Disable the norm changing its status to {@link Status#DISABLED}.
   * 
   * @return {@code true} is status was previously {@link Status#ENABLED}.
   */
  boolean disable();
}
