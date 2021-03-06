package gavel.api.sanction;

import gavel.api.common.LogicalFormula;
import gavel.api.common.Status;

public interface SanctionBuilder {
  /**
   * Set id for the sanction.
   * 
   * @param id sanction's id
   * @return builder builder instance
   */
  SanctionBuilder id(String id);

  /**
   * Set status of the sanction.
   * 
   * @param status sanction's status
   * @return builder builder instance
   */
  SanctionBuilder status(Status status);

  /**
   * Set activation condition for the sanction.
   * 
   * @param activation sanction's activation condition
   * @return builder builder instance
   */
  SanctionBuilder activation(LogicalFormula activation);

  /**
   * Set category for the sanction.
   * 
   * @param category sanction category
   * @return builder builder instance
   */
  SanctionBuilder category(SanctionCategory category);

  /**
   * Set content for the sanction.
   * 
   * @param content sanctions's content
   * @return builder builder instance
   */
  SanctionBuilder content(LogicalFormula content);

  /**
   * Return a new {@link Sanction} with the specified implementation.
   * 
   * @return new {@code Sanction}
   */
  Sanction build();
}
