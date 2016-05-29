package org.venutolo.groovy.extension

import spock.lang.Shared
import spock.lang.Specification

class GStringJoinLinesExtensionSpec extends Specification {

    // this is just used to force creation of GStrings
    @Shared
    private String junk = ''

    def "Joins lines with no starting and ending newlines"() {
        given:
        final GString s = """a${junk}\t\f\r
        \t\f\rb${junk}\t\f\r
        \t\f\rc${junk}"""
        expect:
        s.joinLines() == "a${junk} b${junk} c${junk}"
    }

    def "Joins/trims lines with starting and ending newlines"() {
        given:
        final GString s = """
        \t\f\ra${junk}\t\f\r
        \t\f\rb${junk}\t\f\r
        """
        expect:
        s.joinLines() == "a${junk} b${junk}"
    }

    def "Does not collapse inline whitespace"() {
        given:
        final GString s = "a${junk} \t\f\r b${junk}"
        expect:
        s.joinLines() == "a${junk} \t\f\r b${junk}"
    }

    def "Trims leading or trailing whitespace without newline"() {
        given:
        final GString s = " \t\f\r a${junk}b${junk} \t\f\r "
        expect:
        s.joinLines() == "a${junk}b${junk}"
    }

    def "Does not modify instance"() {
        given:
        final GString s = """
        ${junk}
        """
        when:
        s.joinLines()
        then:
        s == """
        ${junk}
        """
    }

    def "Does not modify embedded String"() {
        given:
        final String embeddedString = '''
        embedded string
        '''
        final GString s = "${embeddedString}"
        expect:
        s.joinLines().toString() == embeddedString
    }

}
