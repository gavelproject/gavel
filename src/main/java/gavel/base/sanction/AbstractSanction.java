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
package gavel.base.sanction;

import static gavel.impl.sanction.Sanctions.getStructureName;

import gavel.api.common.LogicalFormula;
import gavel.api.common.Status;
import gavel.api.sanction.Sanction;
import gavel.api.sanction.SanctionCategory;
import gavel.impl.common.DefaultStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

/**
 * @author igorcadelima
 *
 */
@Data
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class AbstractSanction implements Sanction {
  private final String id;

  @Setter(AccessLevel.NONE)
  private Status status = DefaultStatus.ENABLED;

  private final LogicalFormula condition;
  private final SanctionCategory category;
  private final LogicalFormula content;

  @Override
  public boolean enable() {
    if (status == DefaultStatus.DISABLED) {
      status = DefaultStatus.ENABLED;
      return true;
    }
    return false;
  }

  @Override
  public boolean disable() {
    if (status == DefaultStatus.ENABLED) {
      status = DefaultStatus.DISABLED;
      return true;
    }
    return false;
  }

  @Override
  public String toString() {
    return new StringBuilder(getStructureName() + '(').append("id(" + id + "),")
                                                      .append("status(" + status + "),")
                                                      .append("condition(" + condition + "),")
                                                      .append(category)
                                                      .append(",content(" + content + "))")
                                                      .toString();
  }
}
