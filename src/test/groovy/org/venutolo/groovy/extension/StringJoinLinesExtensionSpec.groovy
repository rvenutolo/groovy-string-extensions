package org.venutolo.groovy.extension

import spock.lang.Specification

class StringJoinLinesExtensionSpec extends Specification {

    def "Joins lines with no starting and ending newlines"() {
        given:
        final String s = '''a\t\f\r
        \t\f\rb\t\f\r
        \t\f\rc'''
        expect:
        s.joinLines() == 'a b c'
    }

    def "Joins/trims lines with starting and ending newlines"() {
        given:
        final String s = '''
        \t\f\ra\t\f\r
        \t\f\rb\t\f\r
        '''
        expect:
        s.joinLines() == 'a b'
    }

    def "Does not collapse inline whitespace"() {
        given:
        final String s = 'a \t\f\r b'
        expect:
        s.joinLines() == 'a \t\f\r b'
    }

    def "Trims leading or trailing whitespace without newline"() {
        given:
        final String s = ' \t\f\r ab \t\f\r'
        expect:
        s.joinLines() == 'ab'
    }

}
