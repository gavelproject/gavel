package gavel.api.norm;

import gavel.api.common.Content;
import gavel.api.common.Status;
import gavel.api.sanction.Sanction;
import jason.asSyntax.LogicalFormula;

public interface NormBuilder {
  /**
   * Set id for the norm.
   * 
   * @param id norm's id
   * @return builder instance
   */
  NormBuilder setId(String id);

  /**
   * Set status of the norm.
   * 
   * @param status norm's status
   * @return builder instance
   */
  NormBuilder setStatus(Status status);

  /**
   * Set condition for the norm.
   * 
   * @param condition norm's activation condition
   * @return builder instance
   */
  NormBuilder setCondition(LogicalFormula condition);

  /**
   * Set issuer for the norm.
   * 
   * @param issuer norm's issuer
   * @return builder instance
   */
  NormBuilder setIssuer(String issuer);

  /**
   * Set content for the norm.
   * 
   * @param content norm's content
   * @return builder instance
   */
  NormBuilder setContent(Content content);

  /**
   * Associate a sanction with the norm.
   * 
   * @param sanction sanction to be associated
   * @return builder instance
   */
  NormBuilder addSanction(Sanction sanction);

  /**
   * Return a new {@link Norm} with the specified implementation.
   * 
   * @return new {@code Norm}
   */
  Norm build();
}
