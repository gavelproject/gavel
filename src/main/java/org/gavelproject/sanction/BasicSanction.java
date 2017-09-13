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

import static org.gavelproject.sanction.Sanctions.NAME;

import org.gavelproject.common.Status;

import jason.asSyntax.Atom;
import jason.asSyntax.LogicalFormula;

/**
 * This class provides a basic implementation of the {@link Sanction} interface.
 * 
 * @author igorcadelima
 *
 */
final class BasicSanction implements Sanction {
  private Atom id;
  private Status status = Status.ENABLED;
  private LogicalFormula condition;
  private SanctionCategory category;
  private LogicalFormula content;

  /**
   * Constructs an {@link AbstractSanction} with the properties specified in {@code builder}.
   * 
   * In order to successfully create a sanction, none of the builder's attributes should be
   * {@code null}.
   */
  private BasicSanction(Builder builder) {
    if ((builder.id != null) && (builder.condition != null) && (builder.category != null)
        && (builder.content != null)) {
      this.id = builder.id;
      this.status = builder.status;
      this.condition = builder.condition;
      this.category = builder.category;
      this.content = builder.content;
    } else {
      throw new RuntimeException(
          "The following properties should not be null: id, condition, category, content");
    }
  }

  static final class Builder {
    private Atom id;
    private Status status = Status.ENABLED;
    private LogicalFormula condition;
    private SanctionCategory category;
    private LogicalFormula content;

    /**
     * Set id for the sanction.
     * 
     * @param id sanction's id
     * @return builder builder instance
     */
    public Builder id(Atom id) {
      this.id = id;
      return this;
    }

    /**
     * Set status of the sanction.
     * 
     * @param status sanction's status
     * @return builder builder instance
     */
    public Builder status(Status status) {
      this.status = status;
      return this;
    }

    /**
     * Set condition for the sanction.
     * 
     * @param condition sanction's activation condition
     * @return builder builder instance
     */
    public Builder condition(LogicalFormula condition) {
      this.condition = condition;
      return this;
    }

    /**
     * Set category for the sanction.
     * 
     * @param category sanction category
     * @return builder builder instance
     */
    public Builder category(SanctionCategory category) {
      this.category = category;
      return this;
    }

    /**
     * Set content for the sanction.
     * 
     * @param content sanctions's content
     * @return builder builder instance
     */
    public Builder content(LogicalFormula content) {
      this.content = content;
      return this;
    }

    /**
     * Return a new {@link Sanction} with the specified implementation.
     * 
     * @return new {@code ISanction}
     */
    public BasicSanction build() {
      return new BasicSanction(this);
    }
  }

  @Override
  public Atom getId() {
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
  public SanctionCategory getCategory() {
    return category;
  }

  @Override
  public LogicalFormula getContent() {
    return content;
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
                                        .append("category(" + category + "),")
                                        .append(content + ")")
                                        .toString();
  }
}
