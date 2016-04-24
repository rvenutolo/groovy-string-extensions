package org.venutolo.groovy.extension

import org.codehaus.groovy.runtime.GStringImpl

import java.util.regex.Pattern

/**
 * This class defines new groovy methods which appear on {@code String} and {@code GString} classes
 * inside the Groovy environment. Static methods are used with the first parameter being the
 * destination class, i.e. {@code public static String joinLines(String self)} provides a {@code
 * joinLines ()} method for {@code String}.
 * <p>
 * NOTE: While this class contains many 'public' static methods, it is intended as an internal class
 * and should not be referenced directly. Future versions may move methods in this class to another
 * internal class, but aim to keep the method available from within Groovy.
 */
class JoinLinesExtension {

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
        WHITESPACE_NEWLINE_WHITESPACE.matcher(self.trim()).replaceAll(' ')
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
        originalStrings.eachWithIndex {final String originalString, final int i ->
            String newString = WHITESPACE_NEWLINE_WHITESPACE.matcher(originalString).replaceAll(' ')
            if (i == 0) {
                newString = LEADING_WHITESPACE.matcher(newString).replaceFirst('')
            }
            if (i == indexToTrimEnd) {
                newString = TRAILING_WHITESPACE.matcher(newString).replaceFirst('')
            }
            newStrings[i] = newString
        }
        new GStringImpl(self.values, newStrings)
    }

}
