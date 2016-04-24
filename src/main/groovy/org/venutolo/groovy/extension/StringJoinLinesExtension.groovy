package org.venutolo.groovy.extension

import java.util.regex.Pattern

class StringJoinLinesExtension {

    private static final Pattern WHITESPACE_NEWLINE_WHITESPACE = ~/\s*\n\s*/

    static String joinLines(final String self) {
        WHITESPACE_NEWLINE_WHITESPACE.matcher(self.trim()).replaceAll(' ')
    }

}
