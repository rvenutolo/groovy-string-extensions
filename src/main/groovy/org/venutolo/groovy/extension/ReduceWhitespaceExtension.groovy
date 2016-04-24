package org.venutolo.groovy.extension

import org.codehaus.groovy.runtime.GStringImpl

import java.util.regex.Pattern

class ReduceWhitespaceExtension {

    private static final Pattern LEADING_WHITESPACE = ~/^\s/

    private static final Pattern TRAILING_WHITESPACE = ~/\s$/

    private static final Pattern MULTIPLE_WHITESPACE = ~/\s{2,}/

    static String reduceWhitespace(final String self) {
        MULTIPLE_WHITESPACE.matcher(self.trim()).replaceAll(' ')
    }

    static GString reduceWhitespace(final GString self) {
        final String[] originalStrings = self.strings
        final String[] newStrings = new String[originalStrings.length]
        final int indexToTrimEnd = originalStrings.length - 1
        originalStrings.eachWithIndex {final String originalString, final int i ->
            String newString = MULTIPLE_WHITESPACE.matcher(originalString).replaceAll(' ')
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
