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
package gavel.impl.norm;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

import jason.asSyntax.ASSyntax;
import jason.asSyntax.NumberTerm;

final class TemporalTerm {
  private Duration duration;

  private TemporalTerm(Duration duration) {
    this.duration = duration;
  }

  static TemporalTerm parse(String deadline) {
    String[] deadlineParts = deadline.trim()
                                     .split(" ");

    if (deadlineParts.length == 1) {
      return of(deadlineParts[0]);
    } else if (deadlineParts.length == 2) {
      return of(deadlineParts[0], deadlineParts[1]);
    }
    return null;
  }

  static TemporalTerm of(String moment) {
    if (moment.equalsIgnoreCase("never")) {
      return new TemporalTerm(Duration.ofMillis(Long.MAX_VALUE));
    } else if (moment.equalsIgnoreCase("now")) {
      return new TemporalTerm(Duration.ZERO);
    }
    return null;
  }

  static TemporalTerm of(String amount, String unit) {
    long amountValue = Long.parseLong(amount.trim());
    TemporalUnit tempUnit = ChronoUnit.valueOf(unit.trim()
                                                   .toUpperCase());
    return new TemporalTerm(Duration.of(amountValue, tempUnit));
  }

  NumberTerm toTerm() {
    return ASSyntax.createNumber(duration.toMillis());
  }
}
