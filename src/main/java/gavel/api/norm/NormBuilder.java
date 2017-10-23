package gavel.api.norm;

import gavel.api.common.LogicalFormula;
import gavel.api.common.Status;

public interface NormBuilder {
  /**
   * Set id for the norm.
   * 
   * @param id norm's id
   * @return builder instance
   */
  NormBuilder id(String id);

  /**
   * Set status of the norm.
   * 
   * @param status norm's status
   * @return builder instance
   */
  NormBuilder status(Status status);

  /**
   * Set activation condition for the norm.
   * 
   * @param condition norm's activation condition
   * @return builder instance
   */
  NormBuilder activation(LogicalFormula condition);

  /**
   * Set issuer of the norm.
   * 
   * @param issuer norm's issuer
   * @return builder instance
   */
  NormBuilder issuer(String issuer);

  /**
   * Set target of the norm.
   * 
   * @param target norm's target
   * @return builder instance
   */
  NormBuilder target(String target);

  /**
   * Set deactivation condition for the norm.
   * 
   * @param condition norm's deactivation condition
   * @return builder instance
   */
  NormBuilder deactivation(LogicalFormula condition);

  /**
   * Set deadline condition for the norm.
   * 
   * @param condition norm's deadline condition
   * @return builder instance
   */
  NormBuilder deadline(LogicalFormula condition);

  /**
   * Set content for the norm.
   * 
   * @param content norm's content
   * @return builder instance
   */
  NormBuilder content(LogicalFormula content);

  /**
   * Return a new {@link Norm} with the specified implementation.
   * 
   * @return new {@code Norm}
   */
  Norm build();
}
