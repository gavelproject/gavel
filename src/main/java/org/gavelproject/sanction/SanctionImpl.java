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

import gavel.api.common.LogicalFormula;
import gavel.api.common.Status;
import gavel.api.sanction.Sanction;
import gavel.api.sanction.SanctionBuilder;
import gavel.api.sanction.SanctionCategory;
import gavel.impl.common.StatusImpl;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.Setter;

/**
 * This class provides a basic implementation of the {@link Sanction} interface.
 * 
 * @author igorcadelima
 *
 */
@Builder(builderClassName = "Builder")
@Data
final class SanctionImpl implements Sanction {
  private final String id;

  @Default
  @Setter(AccessLevel.NONE)
  private Status status = StatusImpl.ENABLED;

  private final LogicalFormula condition;
  private final SanctionCategory category;
  private final LogicalFormula content;

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

  public static final class Builder implements SanctionBuilder {
  }
}
