package org.venutolo.groovyutils.extension

import org.codehaus.groovy.runtime.GStringImpl

class ToSingleLineExtension {

    static String toSingleLine(final String self) {
        self.trim().replaceAll(/\s{2,}/, ' ')
    }

    static GString toSingleLine(final GString self) {
        final String[] originalStrings = self.strings
        final String[] newStrings = new String[originalStrings.length]
        final int indexToTrimEnd = originalStrings.length - 1
        originalStrings.eachWithIndex {final String originalString, final int i ->
            String newString = originalString.replaceAll(/\s{2,}/, ' ')
            if (i == 0) {
                newString = newString.replaceFirst(/^\s/, '')
            }
            if (i == indexToTrimEnd) {
                newString = newString.replaceFirst(/\s$/, '')
            }
            newStrings[i] = newString
        }
        new GStringImpl(self.values, newStrings)
    }

}
