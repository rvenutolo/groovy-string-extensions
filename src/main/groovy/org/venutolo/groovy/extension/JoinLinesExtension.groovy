package org.venutolo.groovy.extension

import org.codehaus.groovy.runtime.GStringImpl

import java.util.regex.Pattern

/**
 * This class defines new groovy methods which appear on <code>String</code>
 * and <code>GString</code> classes inside the Groovy environment. Static
 * methods are used with the first parameter being the destination class, i.e.
 * <code>public static String joinLines(String self)</code> provides a
 * <code>joinLines()</code> method for <code>String</code>.
 * <p>
 * NOTE: While this class contains many 'public' static methods, it is intended
 * as an internal class and should not be referenced directly. Future versions
 * may move methods in this class to another internal class, but aim to keep
 * the method available from within Groovy.
 */
class JoinLinesExtension {

    private static final Pattern LEADING_WHITESPACE = ~/^\s+/

    private static final Pattern TRAILING_WHITESPACE = ~/\s+$/

    private static final Pattern WHITESPACE_NEWLINE_WHITESPACE = ~/\s*\n\s*/

    static String joinLines(final String self) {
        WHITESPACE_NEWLINE_WHITESPACE.matcher(self.trim()).replaceAll(' ')
    }

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
