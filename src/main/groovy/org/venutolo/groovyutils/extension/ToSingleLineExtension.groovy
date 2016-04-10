package org.venutolo.groovyutils.extension

import org.codehaus.groovy.runtime.GStringImpl

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
 *
 * @see org.codehaus.groovy.runtime.DefaultGroovyMethods
 */
class ToSingleLineExtension {

    static String toSingleLine(final String self) {
        self.trim().replaceAll(/\s*\n\s*/, ' ')
    }

    static String toSingleCondensedLine(final String self) {
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
