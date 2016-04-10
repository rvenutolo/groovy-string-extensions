package org.venutolo.groovyutils.extension

class ToSingleLineExtension {

    static String toSingleLine(final String self) {
        self.trim().replaceAll(/\s{2,}/, ' ')
    }

}
