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
package gavel.api.repo;

import java.util.Set;

import gavel.api.norm.Norm;
import gavel.api.nslink.NsLink;
import gavel.api.sanction.Sanction;

/**
 * @author igorcadelima
 *
 */
public interface DeJure {
  /** Returns all the norms from the repository. */
  Set<Norm> getNorms();

  /**
   * Adds a norm to the repository.
   * 
   * @param norm norm to be added
   * @return {@code true} if the norm was successfully added; {@code false} otherwise
   */
  boolean addNorm(Norm norm);

  /**
   * Removes a norm with the given id from the repository.
   * 
   * @param id id of the norm to be removed
   * @return {@code true} if the norm was successfully removed; {@code false} otherwise
   */
  boolean removeNorm(String id);

  /**
   * Enables a norm from the repository with the given id.
   * 
   * @param id id of the norm to be enabled
   * @return {@code true} if the norm was successfully enabled; {@code false} otherwise
   */
  boolean enableNorm(String id);

  /**
   * Disables a norm from the repository with the given id.
   * 
   * @param id id of the norm to be disabled
   * @return {@code true} if the norm was successfully disabled; {@code false} otherwise
   */
  boolean disableNorm(String id);

  /** Returns all the sanctions from the repository. */
  Set<Sanction> getSanctions();

  /**
   * Adds a sanction to the repository.
   * 
   * @param sanction sanction to be added
   * @return {@code true} if the sanction was successfully added; {@code false} otherwise
   */
  boolean addSanction(Sanction sanction);

  /**
   * Removes a sanction with the given id from the repository.
   * 
   * @param id id of the sanction to be removed
   * @return {@code true} if the sanction was successfully removed; {@code false} otherwise
   */
  boolean removeSanction(String id);

  /**
   * Enables a sanction from the repository with the given id.
   * 
   * @param id id of the sanction to be enabled
   * @return {@code true} if the sanction was successfully enabled; {@code false} otherwise
   */
  boolean enableSanction(String id);

  /**
   * Disables a sanction from the repository with the given id.
   * 
   * @param id id of the sanction to be disabled
   * @return {@code true} if the sanction was successfully disabled; {@code false} otherwise
   */
  boolean disableSanction(String id);

  /** Returns all the norm-sanction links from the repository. */
  Set<NsLink> getNsLinks();

  /**
   * Adds a norm-sanction link to the repository.
   * 
   * @param nsLink norm-sanction link to be added
   * @return {@code true} if the norm-sanction link was successfully added; {@code false} otherwise
   */
  boolean addNsLink(NsLink nsLink);

  /**
   * Removes link between norm and sanction.
   * 
   * @param normId id of the norm
   * @param sanctionId id of the sanction
   * @return {@code true} if the norm-sanction link was successfully removed; {@code false}
   *         otherwise
   */
  boolean removeNsLink(String normId, String sanctionId);

  /**
   * Enables existing link between norm and sanction.
   * 
   * @param normId id of the norm
   * @param sanctionId id of the sanction
   * @return {@code true} if the norm-sanction link was successfully enabled; {@code false}
   *         otherwise
   */
  boolean enableNsLink(String normId, String sanctionId);

  /**
   * Disables existing link between norm and sanction.
   * 
   * @param normId id of the norm
   * @param sanctionId id of the sanction
   * @return {@code true} if the norm-sanction link was successfully enabled; {@code false}
   *         otherwise
   */
  boolean disableNsLink(String normId, String sanctionId);
}
