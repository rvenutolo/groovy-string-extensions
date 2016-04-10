package org.venutolo.groovyutils.extension

import spock.lang.Specification

class StringToSingleLineSpecification extends Specification {

    def "String.toSingleLine() join lines with no starting and ending newlines"() {
        given:
        final String multiLine = '''first line
                                    second line
                                    third line'''
        expect:
        multiLine.toSingleLine() == 'first line second line third line'
    }

    def "String.toSingleLine() joins lines with starting and ending newlines"() {
        given:
        final String multiLine = '''
                first line
                second line
                third line
        '''
        expect:
        multiLine.toSingleLine() == 'first line second line third line'
    }

    def "String.toSingleLine() does not remove multiple in-line whitespace chars"() {
        given:
        final String multiLine = '''first \t\f\r line
                                    second \t\f\r line
                                    third \t\f\r line'''
        expect:
        multiLine.toSingleLine() == 'first \t\f\r line second \t\f\r line third \t\f\r line'
    }

    def "String.toSingleLine() removes starting and ending multiple whitespace chars"() {
        given:
        final String multiLine = '''\t\f\r
                first line
                second line
                third line
        \t\f\r'''
        expect:
        multiLine.toSingleLine() == 'first line second line third line'
    }

}
