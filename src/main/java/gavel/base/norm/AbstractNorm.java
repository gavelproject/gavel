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
package gavel.base.norm;

import static gavel.impl.norm.Norms.getStructureName;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import gavel.api.common.LogicalFormula;
import gavel.api.common.Status;
import gavel.api.norm.Norm;
import gavel.api.sanction.Sanction;
import gavel.impl.common.DefaultStatus;
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
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class AbstractNorm implements Norm {
  private final String id;

  @Setter(AccessLevel.NONE)
  private Status status = DefaultStatus.ENABLED;

  private final LogicalFormula activation;
  private final String issuer;
  private final String target;
  private final LogicalFormula deactivation;
  private final LogicalFormula deadline;
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
                                                      .append("activation(" + activation + "),")
                                                      .append("issuer(" + issuer + "),")
                                                      .append("target(" + target + "),")
                                                      .append("deactivation(" + deactivation + "),")
                                                      .append("deadline(" + deadline + "),")
                                                      .append("content(" + content + "),")
                                                      .append(
                                                          "sanction_ids(" + getSanctionIds() + ")")
                                                      .toString();
  }
}
