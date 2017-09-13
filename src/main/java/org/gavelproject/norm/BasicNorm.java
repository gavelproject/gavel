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

import static org.gavelproject.norm.Norms.NAME;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.gavelproject.common.Status;
import org.gavelproject.sanction.Sanction;

import jason.asSyntax.LogicalFormula;

final class BasicNorm implements Norm {
  private String id;
  private Status status = Status.ENABLED;
  private LogicalFormula condition;
  private String issuer;
  private NormContent content;
  private Map<String, Sanction> sanctions;

  /** Constructs an {@link AbstractNorm} with the properties specified in {@code builder}. */
  private BasicNorm(Builder builder) {
    if ((builder.id != null) && (builder.condition != null) && (builder.issuer != null)
        && (builder.content != null)) {
      id = builder.id;
      status = builder.status;
      condition = builder.condition;
      issuer = builder.issuer;
      content = builder.content;
      sanctions = builder.sanctions;
    } else {
      throw new NullPointerException(
          "The following properties should not be null: id, condition, issuer, content.");
    }
  }

  static final class Builder {
    private String id;
    private Status status = Status.ENABLED;
    private LogicalFormula condition;
    private String issuer;
    private NormContent content;
    private Map<String, Sanction> sanctions = new HashMap<>();

    /**
     * Set id for the norm.
     * 
     * @param id norm's id
     * @return builder instance
     */
    Builder id(String id) {
      this.id = id;
      return this;
    }

    /**
     * Set status of the norm.
     * 
     * @param status norm's status
     * @return builder instance
     */
    Builder status(Status status) {
      this.status = status;
      return this;
    }

    /**
     * Set condition for the norm.
     * 
     * @param condition norm's activation condition
     * @return builder instance
     */
    Builder condition(LogicalFormula condition) {
      this.condition = condition;
      return this;
    }

    /**
     * Set issuer for the norm.
     * 
     * @param issuer norm's issuer
     * @return builder instance
     */
    Builder issuer(String issuer) {
      this.issuer = issuer;
      return this;
    }

    /**
     * Set content for the norm.
     * 
     * @param content norm's content
     * @return builder instance
     */
    Builder content(NormContent content) {
      this.content = content;
      return this;
    }

    /**
     * Link a sanction to the norm.
     * 
     * @param sanction sanction to be linked
     * @return builder instance
     */
    Builder link(Sanction sanction) {
      sanctions.put(sanction.getId(), sanction);
      return this;
    }

    /**
     * Return a new {@link Norm} with the specified implementation.
     * 
     * @return new {@code INorm}
     */
    BasicNorm build() {
      return new BasicNorm(this);
    }
  }

  @Override
  public String getId() {
    return id;
  }

  @Override
  public Status getStatus() {
    return status;
  }

  @Override
  public LogicalFormula getCondition() {
    return condition;
  }

  @Override
  public String getIssuer() {
    return issuer;
  }

  @Override
  public NormContent getContent() {
    return content;
  }

  @Override
  public Set<Sanction> getSanctions() {
    return (Set<Sanction>) sanctions.values();
  }

  @Override
  public Set<String> getSanctionIds() {
    Set<String> sanctionIds = new HashSet<>();
    getSanctions().forEach(sanction -> sanctionIds.add(sanction.getId()));
    return sanctionIds;
  }

  @Override
  public boolean addSanction(Sanction sanction) {
    return sanctions.putIfAbsent(sanction.getId(), sanction) != null;
  }

  @Override
  public boolean removeSanction(String sanctionId) {
    return sanctions.remove(sanctionId) != null;
  }

  @Override
  public boolean enable() {
    if (status == Status.DISABLED) {
      status = Status.ENABLED;
      return true;
    }
    return false;
  }

  @Override
  public boolean disable() {
    if (status == Status.ENABLED) {
      status = Status.DISABLED;
      return true;
    }
    return false;
  }

  @Override
  public String toString() {
    return new StringBuilder(NAME + '(').append("id(" + id + "),")
                                        .append("status(" + status.lowercase() + "),")
                                        .append("condition(" + condition + "),")
                                        .append("issuer(" + issuer + "),")
                                        .append("content(" + content + "),")
                                        .append("sanction_ids(" + getSanctionIds() + ")")
                                        .toString();
  }
}
