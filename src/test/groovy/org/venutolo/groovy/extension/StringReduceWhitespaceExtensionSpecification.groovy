package org.venutolo.groovy.extension

import spock.lang.Specification

class StringReduceWhitespaceExtensionSpecification extends Specification {

    def "Reduces inner whitespace"() {
        given:
        final String s = 'a  b'
        expect:
        s.reduceWhitespace() == 'a b'
    }

    def "Trims leading and trailing whitespace"() {
        given:
        final String s = ' ab '
        expect:
        s.reduceWhitespace() == 'ab'
    }

}
