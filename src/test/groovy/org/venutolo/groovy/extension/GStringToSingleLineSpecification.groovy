package org.venutolo.groovy.extension

import spock.lang.Shared
import spock.lang.Specification

class GStringToSingleLineSpecification extends Specification {

    // this is just used to force creation of GStrings
    @Shared
    private String junk = ''

    def "GString.toSingleLine() joins lines with no starting and ending newlines"() {
        given:
        final GString noStartEndNewLines = """first ${junk} line
                                             second ${junk} line
                                             third ${junk} line"""
        expect:
        noStartEndNewLines.toSingleLine() == "first ${junk} line second ${junk} line third ${junk} line"
    }

    def "GString.toSingleLine() joins lines with starting and ending newlines"() {
        given:
        final GString withStartEndNewlines = """
                first ${junk} line
                second ${junk} line
                third ${junk} line
        """
        expect:
        withStartEndNewlines.toSingleLine() == "first ${junk} line second ${junk} line third ${junk} line"
    }

    def "GString.toSingleLine() does not remove multiple in-line whitespace chars"() {
        given:
        final GString multipleInlineWhitespace = """first ${junk} \t\f\r line
                                                   second ${junk} \t\f\r line"""
        expect:
        multipleInlineWhitespace.toSingleLine() == "first ${junk} \t\f\r line second ${junk} \t\f\r line"
    }

    def "GString.toSingleLine() removes starting and ending multiple whitespace chars"() {
        given:
        final GString startEndMultipleWhitespace = """\t\f\r
                first ${junk} line
                second ${junk} line
        \t\f\r"""
        expect:
        startEndMultipleWhitespace.toSingleLine() == "first ${junk} line second ${junk} line"
    }

    def "GString.toSingleLine() does not modify instance"() {
        given:
        final GString original = " ${junk} "
        when:
        original.toSingleLine()
        then:
        original == " ${junk} "
    }

    def "GString.toSingleLine() does not modify embedded values"() {
        given:
        final embeddedValue = '''
            some embeddedValue with multiple   spaces and some newlines
        '''
        expect:
        "${embeddedValue}".toSingleLine().toString() == embeddedValue
    }

}
