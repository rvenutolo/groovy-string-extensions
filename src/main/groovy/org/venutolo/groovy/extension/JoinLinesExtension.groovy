/*
 * Copyright © 2016 Rick Venutolo
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.venutolo.groovy.extension

import groovy.transform.CompileStatic
import org.codehaus.groovy.runtime.GStringImpl

import java.util.regex.Pattern

/**
 * This class defines new {@code joinLines()} methods which appear on {@code String} and {@code
 * GString} classes inside the Groovy environment. Static methods are used with the first parameter
 * being the destination class, i.e. {@code public static String joinLines(String self)} provides a
 * {@code joinLines()} method for {@code String}.
 * <p>
 * NOTE: While this class contains many 'public' static methods, it is intended as an internal class
 * and should not be referenced directly. Future versions may move methods in this class to another
 * internal class, but aim to keep the methods available from within Groovy.
 */
@CompileStatic
final class JoinLinesExtension {

    private JoinLinesExtension() {
    }

    private static final String SINGLE_SPACE = ' '

    private static final String EMPTY_STRING = ''

    private static final Pattern LEADING_WHITESPACE = ~/^\s+/

    private static final Pattern TRAILING_WHITESPACE = ~/\s+$/

    private static final Pattern WHITESPACE_NEWLINE_WHITESPACE = ~/\s*\n\s*/

    /**
     * Trims leading and trailing whitespace and joins multiple with a single space. This use the
     * Java {@code Pattern} definition of "whitespace", (i.e. matches the {@code \s} predefined
     * character class). Any occurrence of a newline character ({@code \n}) surrounded by any amount
     * of whitespace ({@code \s}) is replaced with a single space.
     *
     * @param self a {@code String}
     * @return a trimmed and single-line {@code String}
     */
    static String joinLines(final String self) {
        WHITESPACE_NEWLINE_WHITESPACE.matcher(self.trim()).replaceAll(SINGLE_SPACE)
    }

    /**
     * Trims leading and trailing whitespace and joins multiple lines with a single space. This use
     * the Java {@code Pattern} definition of "whitespace", (i.e. matches the {@code \s} predefined
     * character class). Any occurrence of a newline character ({@code \n}) surrounded by any amount
     * of whitespace ({@code \s}) is replaced with a single space. This does not modify the given
     * {@code GString}, nor does it modify the {@code GString}'s embedded values.
     *
     * @param self a {@code GString}
     * @return a trimmed and single-line {@code GString}
     */
    static GString joinLines(final GString self) {
        final String[] originalStrings = self.strings
        final String[] newStrings = new String[originalStrings.length]
        final int indexToTrimEnd = originalStrings.length - 1
        originalStrings.eachWithIndex { final String originalString, final int i ->
            String newString =
                WHITESPACE_NEWLINE_WHITESPACE.matcher(originalString).replaceAll(SINGLE_SPACE)
            if (i == 0) {
                newString = LEADING_WHITESPACE.matcher(newString).replaceFirst(EMPTY_STRING)
            }
            if (i == indexToTrimEnd) {
                newString = TRAILING_WHITESPACE.matcher(newString).replaceFirst(EMPTY_STRING)
            }
            newStrings[i] = newString
        }
        new GStringImpl(self.values, newStrings)
    }

}
