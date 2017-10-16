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
package gavel.base.repo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import gavel.api.common.Uuid;
import gavel.api.repo.DeFacto;
import gavel.api.sanction.SanctionApplication;
import gavel.api.sanction.SanctionDecision;
import gavel.api.sanction.SanctionOutcome;

/**
 * @author igorcadelima
 *
 */
public abstract class AbstractInMemDeFacto implements DeFacto {
  protected final Map<Uuid, SanctionDecision> decisions;
  protected final Map<Uuid, SanctionApplication> applications;
  protected final Map<Uuid, SanctionOutcome> outcomes;

  protected AbstractInMemDeFacto() {
    decisions = new HashMap<>();
    applications = new HashMap<>();
    outcomes = new HashMap<>();
  }

  @Override
  public Set<SanctionDecision> getDecisions() {
    return new HashSet<SanctionDecision>(decisions.values());
  }

  @Override
  public Set<SanctionApplication> getApplications() {
    return new HashSet<SanctionApplication>(applications.values());
  }

  @Override
  public Set<SanctionOutcome> getOutcomes() {
    return new HashSet<SanctionOutcome>(outcomes.values());
  }

  @Override
  public boolean addDecision(SanctionDecision sd) {
    return decisions.putIfAbsent(sd.getId(), sd) == null;
  }

  @Override
  public boolean addApplication(SanctionApplication sa) {
    return applications.putIfAbsent(sa.getId(), sa) == null;
  }

  @Override
  public boolean addOutcome(SanctionOutcome so) {
    return outcomes.putIfAbsent(so.getId(), so) == null;
  }
}
