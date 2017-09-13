package org.gavelproject.sanction;

import org.gavelproject.common.Status;

import jason.asSyntax.LogicalFormula;

public interface SanctionBuilder {
  /**
   * Set id for the sanction.
   * 
   * @param id sanction's id
   * @return builder builder instance
   */
  SanctionBuilder setId(String id);

  /**
   * Set status of the sanction.
   * 
   * @param status sanction's status
   * @return builder builder instance
   */
  SanctionBuilder setStatus(Status status);

  /**
   * Set condition for the sanction.
   * 
   * @param condition sanction's activation condition
   * @return builder builder instance
   */
  SanctionBuilder setCondition(LogicalFormula condition);

  /**
   * Set category for the sanction.
   * 
   * @param category sanction category
   * @return builder builder instance
   */
  SanctionBuilder setCategory(SanctionCategory category);

  /**
   * Set content for the sanction.
   * 
   * @param content sanctions's content
   * @return builder builder instance
   */
  SanctionBuilder setContent(LogicalFormula content);

  /**
   * Return a new {@link Sanction} with the specified implementation.
   * 
   * @return new {@code Sanction}
   */
  Sanction build();
}
