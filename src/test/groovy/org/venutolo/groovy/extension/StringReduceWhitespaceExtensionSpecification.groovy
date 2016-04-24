package org.venutolo.groovy.extension

import spock.lang.Specification

class StringReduceWhitespaceExtensionSpecification extends Specification {

    def "Reduces inner whitespace"() {
        given:
        final String s = 'a \t\n\f\r b'
        expect:
        s.reduceWhitespace() == 'a b'
    }

    def "Trims leading and trailing whitespace"() {
        given:
        final String s = ' \t\n\f\r ab \t\n\f\r '
        expect:
        s.reduceWhitespace() == 'ab'
    }

}
