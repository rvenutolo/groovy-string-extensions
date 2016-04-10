package org.venutolo.groovyutils.extension

import spock.lang.Shared
import spock.lang.Specification

class GStringToSingleLineSpecification extends Specification {

    // this is just used to force creation of GStrings
    @Shared
    private String junk = ''

    def "GString.toSingleLine() strips multiple leading spaces"() {
        given:
        final GString multipleLeadingSpaces = "\t\n\f\r  multiple ${junk} leading spaces"
        expect:
        multipleLeadingSpaces.toSingleLine() == "multiple ${junk} leading spaces"
    }

    def "GString.toSingleLine() strips multiple trailing spaces"() {
        given:
        final GString multipleTrailingSpaces = "multiple ${junk} trailing spaces  \t\n\f\r"
        expect:
        multipleTrailingSpaces.toSingleLine() == "multiple ${junk} trailing spaces"
    }

    def "GString.toSingleLine() strips leading newlines and whitespace"() {
        given:
        final GString leadingNewlinesAndWhitespace = """
                \t\n\f\r
                leading ${junk} newlines and whitespace"""
        expect:
        leadingNewlinesAndWhitespace.toSingleLine() == "leading ${junk} newlines and whitespace"
    }

    def "GString.toSingleLine() strips trailing newlines and whitespace"() {
        given:
        final GString trailingNewlineAndWhitespace = """trailing ${junk} newlines and whitespace
                                                        \t\n\f\r
                                                     """
        expect:
        trailingNewlineAndWhitespace.toSingleLine() == "trailing ${junk} newlines and whitespace"
    }

    def "GString.toSingleLine() joins lines with single space"() {
        given:
        final GString multiLine = """first ${junk} line
                                            second line
                                            third line"""
        expect:
        multiLine.toSingleLine() == "first ${junk} line second line third line"
    }

    def "GString.toSingleLine() condenses multiple whitespace to one space"() {
        given:
        final GString multipleSpaces =
                "before ${junk} multiple whitespace  \t\n\f\r  after multiple whitespace"
        expect:
        multipleSpaces.toSingleLine() ==
                "before ${junk} multiple whitespace after multiple whitespace"
    }

    def "GString.toSingleLine() does not modify instance"() {
        given:
        final GString original = " ${junk} "
        when:
        original.toSingleLine()
        then:
        original == " ${junk} "
    }

    def "GString.toSingleLine() does not modify GString's values"() {
        given:
        final value = '''
            some value with multiple   spaces and some newlines
        '''
        expect:
        "${value}".toSingleLine().toString() == value
    }

}
