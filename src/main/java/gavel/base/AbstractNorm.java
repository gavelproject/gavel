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
package gavel.base;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import gavel.api.common.LogicalFormula;
import gavel.api.common.Status;
import gavel.api.norm.Norm;
import gavel.api.sanction.Sanction;
import gavel.impl.common.StatusImpl;
import gavel.impl.norm.Norms;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author igorcadelima
 *
 */
@Data
@AllArgsConstructor
public abstract class AbstractNorm implements Norm {
  private final String id;

  @Setter(AccessLevel.NONE)
  private Status status = StatusImpl.ENABLED;

  private final LogicalFormula condition;
  private final String issuer;
  private final LogicalFormula content;

  @Getter(AccessLevel.NONE)
  private final Map<String, Sanction> sanctions;

  @Override
  public Set<Sanction> getSanctions() {
    return new HashSet<>(sanctions.values());
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
    return new StringBuilder(Norms.getStructureName() + '(').append("id(" + id + "),")
                                                            .append("status(" + status + "),")
                                                            .append("condition(" + condition + "),")
                                                            .append("issuer(" + issuer + "),")
                                                            .append("content(" + content + "),")
                                                            .append("sanction_ids("
                                                                + getSanctionIds() + ")")
                                                            .toString();
  }
}
