package org.venutolo.groovy.extension

import spock.lang.Specification

class StringTrimAndCollapseWhitespaceExtensionSpec extends Specification {

    def "Collapses inner whitespace"() {
        given:
        final String s = 'a \t\n\f\r b'
        expect:
        s.trimAndCollapseWhitespace() == 'a b'
    }

    def "Trims leading and trailing whitespace"() {
        given:
        final String s = ' \t\n\f\r ab \t\n\f\r '
        expect:
        s.trimAndCollapseWhitespace() == 'ab'
    }

}
