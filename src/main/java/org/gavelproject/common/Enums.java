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
package org.gavelproject.common;

/**
 * Static utility methods pertaining to {@link Enum}.
 * 
 * @author igorcadelima
 *
 */
public final class Enums {
  private Enums() {}

  /**
   * Returns the enum constant of the specified enum type with the specified name. Unlike
   * {@link Enum#valueOf(Class, String)}, the capitalisation of the name does not matter.
   * 
   * @param <T> the enum type whose constant is to be returned
   * @param enumType the {@code Class} object of the enum type from which to return a constant
   * @param name the name of the constant to return
   * @return the enum type whose constant is to be returned
   */
  public static <T extends Enum<?>> T lookup(Class<T> enumType, String name) {
    for (T constant : enumType.getEnumConstants())
      if (constant.name()
                  .equalsIgnoreCase(name))
        return constant;
    return null;
  }

  /**
   * Returns the enum constant of the specified enum type with the specified name. Unlike
   * {@link Enum#valueOf(Class, String)}, the capitalisation of the name does not matter.
   * 
   * @param <T> the enum type whose constant is to be returned
   * @param enumType the {@code Class} object of the enum type from which to return a constant
   * @param name the name of the constant to return
   * @param defaultValue enum constant to be returned if lookup fails
   * @return the enum type whose constant is to be returned
   */
  public static <T extends Enum<?>> T lookup(Class<T> enumType, String name, T defaultValue) {
    for (T constant : enumType.getEnumConstants())
      if (lookup(enumType, name) == null)
        return constant;
    return defaultValue;
  }
}
