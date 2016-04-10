package org.venutolo.groovyutils.extension

import spock.lang.Specification

class StringToSingleCondensedLineSpecification extends Specification {

    def "String.toSingleCondensedLine() strips multiple leading spaces"() {
        given:
        final String multipleLeadingSpaces = '\t\n\f\r  multiple leading spaces'
        expect:
        multipleLeadingSpaces.toSingleCondensedLine() == 'multiple leading spaces'
    }

    def "String.toSingleCondensedLine() strips multiple trailing spaces"() {
        given:
        final String multipleTrailingSpaces = 'multiple trailing spaces  \t\n\f\r'
        expect:
        multipleTrailingSpaces.toSingleCondensedLine() == 'multiple trailing spaces'
    }

    def "String.toSingleCondensedLine() strips leading newlines and whitespace"() {
        given:
        final String leadingNewlinesAndWhitespace = '''
                \t\n\f\r
                leading newlines and whitespace'''
        expect:
        leadingNewlinesAndWhitespace.toSingleCondensedLine() == 'leading newlines and whitespace'
    }

    def "String.toSingleCondensedLine() strips trailing newlines and whitespace"() {
        given:
        final String trailingNewlineAndWhitespace = '''trailing newlines and whitespace
                                                       \t\n\f\r
                                                    '''
        expect:
        trailingNewlineAndWhitespace.toSingleCondensedLine() == 'trailing newlines and whitespace'
    }

    def "String.toSingleCondensedLine() joins lines with single space"() {
        given:
        final String multiLine = '''first line
                                    second line
                                    third line'''
        expect:
        multiLine.toSingleCondensedLine() == 'first line second line third line'
    }

    def "String.toSingleCondensedLine() condenses multiple whitespace to one space"() {
        given:
        final String multipleSpaces =
                'before multiple whitespace  \t\n\f\r  after multiple whitespace'
        expect:
        multipleSpaces.toSingleCondensedLine() ==
        'before multiple whitespace after multiple whitespace'
    }

}
