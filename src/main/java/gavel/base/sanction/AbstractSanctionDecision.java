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

import static gavel.impl.sanction.SanctionDecisions.getStructureName;

import gavel.api.common.Uuid;
import gavel.api.norm.Norm;
import gavel.api.sanction.Sanction;
import gavel.api.sanction.SanctionDecision;
import gavel.impl.common.DefaultUuid;
import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @author igorcadelima
 *
 */
@Data
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class AbstractSanctionDecision implements SanctionDecision {
  private final Uuid id = DefaultUuid.newInstance();
  private final long time;
  private final String detectorId;
  private final String evaluatorId;
  private final String targetId;
  private final Norm normInstance;
  private final Sanction sanctionInstance;
  private final Cause cause;

  @Override
  public String toString() {
    return new StringBuilder(getStructureName() + '(').append("id('" + id + "'),")
                                                      .append("time(" + time + "),")
                                                      .append("detector(" + detectorId + "),")
                                                      .append("evaluator(" + evaluatorId + "),")
                                                      .append("target(" + targetId + "),")
                                                      .append(normInstance.toString() + ",")
                                                      .append(sanctionInstance.toString() + ",")
                                                      .append("cause(" + cause + "))")
                                                      .toString();
  }
}
