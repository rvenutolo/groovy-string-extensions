package org.venutolo.groovyutils.extension

import spock.lang.Specification

class StringToSingleLineSpecification extends Specification {

    def "String.toSingleLine() join lines with no starting and ending newlines"() {
        given:
        final String noStartEndNewLines = '''first line
                                             second line
                                             third line'''
        expect:
        noStartEndNewLines.toSingleLine() == 'first line second line third line'
    }

    def "String.toSingleLine() joins lines with starting and ending newlines"() {
        given:
        final String withStartEndNewlines = '''
                first line
                second line
                third line
        '''
        expect:
        withStartEndNewlines.toSingleLine() == 'first line second line third line'
    }

    def "String.toSingleLine() does not remove multiple in-line whitespace chars"() {
        given:
        final String multipleInlineWhitespace = '''first \t\f\r line
                                                   second \t\f\r line
                                                   third \t\f\r line'''
        expect:
        multipleInlineWhitespace.toSingleLine() ==
                'first \t\f\r line second \t\f\r line third \t\f\r line'
    }

    def "String.toSingleLine() removes starting and ending multiple whitespace chars"() {
        given:
        final String startEndMultipleWhitespace = '''\t\f\r
                first line
                second line
                third line
        \t\f\r'''
        expect:
        startEndMultipleWhitespace.toSingleLine() == 'first line second line third line'
    }

}
