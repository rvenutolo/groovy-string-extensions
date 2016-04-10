package org.venutolo.groovyutils.extension

import org.codehaus.groovy.runtime.GStringImpl

import java.util.regex.Pattern

/**
 * This class defines new groovy methods which appear on <code>String</code>
 * and <code>GString</code> classes inside the Groovy environment. Static
 * methods are used with the first parameter being the destination class, i.e.
 * <code>public static String toSingleLine(String self)</code> provides a
 * <code>toSingleLine()</code> method for <code>String</code>.
 * <p>
 * NOTE: While this class contains many 'public' static methods, it is intended
 * as an internal class and should not be referenced directly. Future versions
 * may move methods in this class to another internal class, but aim to keep
 * the method available from within Groovy.
 */
class ToSingleLineExtension {

    private static final Pattern LEADING_WHITESPACE = ~/^\s/

    private static final Pattern TRAILING_WHITESPACE = ~/\s$/

    private static final Pattern WHITESPACE_NEWLINE_WHITESPACE = ~/\s*\n\s*/

    private static final Pattern MULTIPLE_WHITESPACE = ~/\s{2,}/

    private static String trimAndReplacePattern(final String string, final Pattern pattern) {
        pattern.matcher(string.trim()).replaceAll(' ')
    }

    static String toSingleLine(final String self) {
        trimAndReplacePattern(self, WHITESPACE_NEWLINE_WHITESPACE)
    }

    static String toSingleCondensedLine(final String self) {
        trimAndReplacePattern(self, MULTIPLE_WHITESPACE)
    }

    private static GString trimAndReplacePattern(final GString gString, final Pattern pattern) {
        final String[] originalStrings = gString.strings
        final String[] newStrings = new String[originalStrings.length]
        final int indexToTrimEnd = originalStrings.length - 1
        originalStrings.eachWithIndex {final String originalString, final int i ->
            String newString = pattern.matcher(originalString).replaceAll(' ')
            if (i == 0) {
                newString = LEADING_WHITESPACE.matcher(newString).replaceFirst('')
            }
            if (i == indexToTrimEnd) {
                newString = TRAILING_WHITESPACE.matcher(newString).replaceFirst('')
            }
            newStrings[i] = newString
        }
        new GStringImpl(gString.values, newStrings)
    }

    static GString toSingleLine(final GString self) {
        trimAndReplacePattern(self, WHITESPACE_NEWLINE_WHITESPACE)
    }

    static GString toSingleCondensedLine(final GString self) {
        trimAndReplacePattern(self, MULTIPLE_WHITESPACE)
    }

    static String toSingleLineString(final GString self) {
        self.toString().toSingleLine()
    }

    static String toSingleCondensedLineString(final GString self) {
        self.toString().toSingleCondensedLine()
    }

}
