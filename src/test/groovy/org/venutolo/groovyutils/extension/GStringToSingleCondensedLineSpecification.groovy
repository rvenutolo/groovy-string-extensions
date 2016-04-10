package org.venutolo.groovyutils.extension

import spock.lang.Shared
import spock.lang.Specification

class GStringToSingleCondensedLineSpecification extends Specification {

    // this is just used to force creation of GStrings
    @Shared
    private String junk = ''

    def "GString.toSingleCondensedLine() strips multiple leading spaces"() {
        given:
        final GString multipleLeadingSpaces = "\t\n\f\r  multiple ${junk} leading spaces"
        expect:
        multipleLeadingSpaces.toSingleCondensedLine() == "multiple ${junk} leading spaces"
    }

    def "GString.toSingleCondensedLine() strips multiple trailing spaces"() {
        given:
        final GString multipleTrailingSpaces = "multiple ${junk} trailing spaces  \t\n\f\r"
        expect:
        multipleTrailingSpaces.toSingleCondensedLine() == "multiple ${junk} trailing spaces"
    }

    def "GString.toSingleCondensedLine() strips leading newlines and whitespace"() {
        given:
        final GString leadingNewlinesAndWhitespace = """
                \t\n\f\r
                leading ${junk} whitespace"""
        expect:
        leadingNewlinesAndWhitespace.toSingleCondensedLine() == "leading ${junk} whitespace"
    }

    def "GString.toSingleCondensedLine() strips trailing newlines and whitespace"() {
        given:
        final GString trailingNewlineAndWhitespace = """trailing ${junk} whitespace
                                                        \t\n\f\r
                                                     """
        expect:
        trailingNewlineAndWhitespace.toSingleCondensedLine() == "trailing ${junk} whitespace"
    }

    def "GString.toSingleCondensedLine() joins lines with single space"() {
        given:
        final GString multiLine = """first ${junk} line
                                            second line
                                            third line"""
        expect:
        multiLine.toSingleCondensedLine() == "first ${junk} line second line third line"
    }

    def "GString.toSingleCondensedLine() condenses multiple whitespace to one space"() {
        given:
        final GString multipleSpaces =
                "before ${junk} \t\n\f\r  after"
        expect:
        multipleSpaces.toSingleCondensedLine() == "before ${junk} after"
    }

    def "GString.toSingleCondensedLine() does not modify instance"() {
        given:
        final GString original = " ${junk} "
        when:
        original.toSingleCondensedLine()
        then:
        original == " ${junk} "
    }

    def "GString.toSingleCondensedLine() does not modify embedded values"() {
        given:
        final embeddedValue = '''
            some embeddedValue with multiple   spaces and some newlines
        '''
        expect:
        "${embeddedValue}".toSingleCondensedLine().toString() == embeddedValue
    }

}
