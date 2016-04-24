package org.venutolo.groovy.extension

import spock.lang.Specification

class StringJoinLinesExtensionSpecification extends Specification {

    def "joins multiple lines"() {
        given:
        final String s = '''a
        b
        c'''
        expect:
        s.joinLines() == 'a b c'
    }

    def "does not reduce inline whitespace"() {
        given:
        final String s = '''a  b'''
        expect:
        s.joinLines() == 'a  b'
    }

    def "trims leading or trailing whitespace without newline"() {
        given:
        final String s = ''' ab '''
        expect:
        s.joinLines() == 'ab'
    }

    def "trims leading or trailing whitespace that contains newline"() {
        given:
        final String s = '''
        a
        b
        '''
        expect:
        s.joinLines() == 'a b'
    }

}
