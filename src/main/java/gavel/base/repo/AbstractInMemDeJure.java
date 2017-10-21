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

import gavel.api.norm.Norm;
import gavel.api.nslink.NsLink;
import gavel.api.repo.DeJure;
import gavel.api.sanction.Sanction;
import gavel.impl.norm.Norms;
import gavel.impl.sanction.Sanctions;

public class AbstractInMemDeJure implements DeJure {
  protected Map<String, Norm> norms;
  protected Map<String, Sanction> sanctions;
  protected Map<String, Set<NsLink>> nsLinks;

  protected AbstractInMemDeJure() {
    norms = new HashMap<>();
    sanctions = new HashMap<>();
    nsLinks = new HashMap<>();
  }

  protected AbstractInMemDeJure(Set<Norm> norms, Set<Sanction> sanctions, Set<NsLink> nsLinks) {
    super();
    norms.forEach(norm -> this.norms.put(norm.getId(), norm));
    sanctions.forEach(sanction -> this.sanctions.put(sanction.getId(), sanction));
    nsLinks.forEach(link -> this.nsLinks.get(link.getNormId())
                                        .add(link));
  }

  @Override
  public Set<Norm> getNorms() {
    return new HashSet<Norm>(norms.values());
  }

  @Override
  public Norm getNorm(String id) {
    return Norms.newInstance(norms.get(id));
  }

  @Override
  public boolean addNorm(Norm norm) {
    if (norms.containsKey(norm.getId())) {
      return norms.put(norm.getId(), norm) != null;
    }
    return norms.put(norm.getId(), norm) == null
        && nsLinks.put(norm.getId(), new HashSet<>()) == null;
  }

  @Override
  public Norm removeNorm(String id) {
    nsLinks.remove(id);
    return norms.remove(id);
  }

  @Override
  public boolean enableNorm(String id) {
    Norm n = norms.get(id);
    if (n != null) {
      return n.enable();
    }
    return false;
  }

  @Override
  public boolean disableNorm(String id) {
    Norm n = norms.get(id);
    if (n != null) {
      return n.disable();
    }
    return false;
  }

  @Override
  public Set<Sanction> getSanctions() {
    return new HashSet<Sanction>(sanctions.values());
  }

  @Override
  public Sanction getSanction(String id) {
    return Sanctions.newInstance(sanctions.get(id));
  }

  @Override
  public boolean addSanction(Sanction sanction) {
    if (sanctions.containsKey(sanction.getId())) {
      return sanctions.put(sanction.getId(), sanction) != null;
    }
    return sanctions.put(sanction.getId(), sanction) == null;
  }

  @Override
  public Sanction removeSanction(String id) {
    Sanction sanction = sanctions.remove(id);
    if (sanction != null) {
      for (Set<NsLink> linkSet : nsLinks.values()) {
        linkSet.removeIf(link -> link.getSanctionId()
                                     .equals(id));
      }
    }
    return sanction;
  }

  @Override
  public boolean enableSanction(String id) {
    Sanction s = sanctions.get(id);
    if (s != null) {
      return s.enable();
    }
    return false;
  }

  @Override
  public boolean disableSanction(String id) {
    Sanction s = sanctions.get(id);
    if (s != null) {
      return s.disable();
    }
    return false;
  }

  @Override
  public Set<NsLink> getNsLinks() {
    Set<NsLink> nsLinks = new HashSet<>();
    this.nsLinks.values()
                .forEach(link -> nsLinks.addAll(link));
    return nsLinks;
  }

  @Override
  public boolean addNsLink(NsLink nsLink) {
    if (nsLinks.containsKey(nsLink.getNormId()) && sanctions.containsKey(nsLink.getSanctionId())) {
      return nsLinks.get(nsLink.getNormId())
                    .add(nsLink);
    }
    return false;
  }

  @Override
  public NsLink removeNsLink(String normId, String sanctionId) {
    Set<NsLink> links = nsLinks.get(normId);
    if (links != null) {
      for (NsLink link : links) {
        if (link.getSanctionId()
                .equals(sanctionId)) {
          links.remove(link);
          return link;
        }
      }
    }
    return null;
  }

  @Override
  public boolean enableNsLink(String normId, String sanctionId) {
    Set<NsLink> links = nsLinks.get(normId);
    if (links != null) {
      for (NsLink link : links) {
        if (link.getSanctionId()
                .equals(sanctionId)) {
          return link.enable();
        }
      }
    }
    return false;
  }

  @Override
  public boolean disableNsLink(String normId, String sanctionId) {
    Set<NsLink> links = nsLinks.get(normId);
    if (links != null) {
      for (NsLink link : links) {
        if (link.getSanctionId()
                .equals(sanctionId)) {
          return link.disable();
        }
      }
    }
    return false;
  }
}
