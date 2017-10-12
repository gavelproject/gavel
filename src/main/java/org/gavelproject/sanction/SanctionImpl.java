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

import gavel.api.common.Status;
import gavel.api.sanction.Sanction;
import gavel.api.sanction.SanctionBuilder;
import gavel.api.sanction.SanctionCategory;
import gavel.impl.common.StatusImpl;
import jason.asSyntax.LogicalFormula;

/**
 * This class provides a basic implementation of the {@link Sanction} interface.
 * 
 * @author igorcadelima
 *
 */
final class SanctionImpl implements Sanction {
  private String id;
  private Status status = StatusImpl.ENABLED;
  private LogicalFormula condition;
  private SanctionCategory category;
  private LogicalFormula content;

  /**
   * Constructs an {@link AbstractSanction} with the properties specified in {@code builder}.
   * 
   * In order to successfully create a sanction, none of the builder's attributes should be
   * {@code null}.
   */
  private SanctionImpl(Builder builder) {
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

  static final class Builder implements SanctionBuilder {
    private String id;
    private Status status = StatusImpl.ENABLED;
    private LogicalFormula condition;
    private SanctionCategory category;
    private LogicalFormula content;

    public Builder setId(String id) {
      this.id = id;
      return this;
    }

    public Builder setStatus(Status status) {
      this.status = status;
      return this;
    }

    public Builder setCondition(LogicalFormula condition) {
      this.condition = condition;
      return this;
    }

    public Builder setCategory(SanctionCategory category) {
      this.category = category;
      return this;
    }

    public Builder setContent(LogicalFormula content) {
      this.content = content;
      return this;
    }

    public SanctionImpl build() {
      return new SanctionImpl(this);
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
  public SanctionCategory getCategory() {
    return category;
  }

  @Override
  public LogicalFormula getContent() {
    return content;
  }

  @Override
  public boolean enable() {
    if (status == StatusImpl.DISABLED) {
      status = StatusImpl.ENABLED;
      return true;
    }
    return false;
  }

  @Override
  public boolean disable() {
    if (status == StatusImpl.ENABLED) {
      status = StatusImpl.DISABLED;
      return true;
    }
    return false;
  }

  @Override
  public String toString() {
    return new StringBuilder(NAME + '(').append("id(" + id + "),")
                                        .append("status(" + status + "),")
                                        .append("condition(" + condition + "),")
                                        .append("category(" + category + "),")
                                        .append(content + ")")
                                        .toString();
  }
}
