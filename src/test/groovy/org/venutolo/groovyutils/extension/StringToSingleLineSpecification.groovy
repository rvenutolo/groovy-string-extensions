package org.venutolo.groovyutils.extension

import spock.lang.Specification

class StringToSingleLineSpecification extends Specification {

    def "String.toSingleLine() strips multiple leading spaces"() {
        given:
        final String multipleLeadingSpaces = '\t\n\f\r  multiple leading spaces'
        expect:
        multipleLeadingSpaces.toSingleLine() == 'multiple leading spaces'
    }

    def "String.toSingleLine() strips multiple trailing spaces"() {
        given:
        final String multipleTrailingSpaces = 'multiple trailing spaces  \t\n\f\r'
        expect:
        multipleTrailingSpaces.toSingleLine() == 'multiple trailing spaces'
    }

    def "String.toSingleLine() strips leading newlines and whitespace"() {
        given:
        final String leadingNewlinesAndWhitespace = '''
            \t\n\f\r
            leading newlines and whitespace'''
        expect:
        leadingNewlinesAndWhitespace.toSingleLine() == 'leading newlines and whitespace'
    }

    def "String.toSingleLine() strips trailing newlines and whitespace"() {
        given:
        final String trailingNewlineAndWhitespace = '''trailing newlines and whitespace
        \t\n\f\r
        '''
        expect:
        trailingNewlineAndWhitespace.toSingleLine() == 'trailing newlines and whitespace'
    }

    def "String.toSingleLine() joins lines with single space"() {
        given:
        final String multiLine = '''first line
        second line
        third line'''
        expect:
        multiLine.toSingleLine() == 'first line second line third line'
    }

    def "String.toSingleLine() condenses multiple whitespace to one space"() {
        given:
        final String multipleSpaces =
                'before multiple whitespace  \t\n\f\r  after multiple whitespace'
        expect:
        multipleSpaces.toSingleLine() ==
        'before multiple whitespace after multiple whitespace'
    }

}
