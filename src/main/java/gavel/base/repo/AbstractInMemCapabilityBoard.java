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

import java.util.HashSet;
import java.util.Set;

import gavel.api.repo.CapabilityBoard;
import gavel.impl.capability.DefaultCapability;

/**
 * @author igorcadelima
 *
 */
public abstract class AbstractInMemCapabilityBoard implements CapabilityBoard {
  protected final Set<String> detectors;
  protected final Set<String> evaluators;
  protected final Set<String> executors;
  protected final Set<String> controllers;
  protected final Set<String> legislators;

  protected AbstractInMemCapabilityBoard() {
    detectors = new HashSet<>();
    evaluators = new HashSet<>();
    executors = new HashSet<>();
    controllers = new HashSet<>();
    legislators = new HashSet<>();
  }

  @Override
  public DefaultCapability[] getCapabilities() {
    return DefaultCapability.values();
  }

  @Override
  public Set<String> getDetectors() {
    return new HashSet<>(detectors);
  }

  @Override
  public Set<String> getEvaluators() {
    return new HashSet<>(evaluators);
  }

  @Override
  public Set<String> getExecutors() {
    return new HashSet<>(executors);
  }

  @Override
  public Set<String> getControllers() {
    return new HashSet<>(controllers);
  }

  @Override
  public Set<String> getLegislators() {
    return new HashSet<>(legislators);
  }

  @Override
  public Object acquireCapability(DefaultCapability capability) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean registerAg(String agId, DefaultCapability capability) {
    switch (capability) {
      case DETECTOR:
        detectors.add(agId);
        break;
      case EVALUATOR:
        evaluators.add(agId);
        break;
      case EXECUTOR:
        executors.add(agId);
        break;
      case CONTROLLER:
        controllers.add(agId);
        break;
      case LEGISLATOR:
        legislators.add(agId);
    }
    return true;
  }

  @Override
  public boolean unregisterAg(String agId, DefaultCapability capability) {
    switch (capability) {
      case DETECTOR:
        detectors.remove(agId);
        break;
      case EVALUATOR:
        evaluators.remove(agId);
        break;
      case EXECUTOR:
        executors.remove(agId);
        break;
      case CONTROLLER:
        controllers.remove(agId);
        break;
      case LEGISLATOR:
        legislators.remove(agId);
    }
    return true;
  }
}
