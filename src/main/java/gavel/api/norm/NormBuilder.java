package gavel.api.norm;

import java.util.Set;

import gavel.api.common.LogicalFormula;
import gavel.api.common.Status;
import gavel.api.sanction.Sanction;

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
   * Associate a sanction with the norm.
   * 
   * @param sanction sanction to be associated
   * @return builder instance
   */
  NormBuilder sanction(Sanction sanction);

  /**
   * Associate sanctions with the norm.
   * 
   * @param sanctions sanctions to be associated
   * @return builder instance
   */
  NormBuilder sanctions(Set<Sanction> sanction);

  /**
   * Clear set of associated sanctions.
   * 
   * @return builder instance
   */
  NormBuilder clearSanctions();

  /**
   * Return a new {@link Norm} with the specified implementation.
   * 
   * @return new {@code Norm}
   */
  Norm build();
}
