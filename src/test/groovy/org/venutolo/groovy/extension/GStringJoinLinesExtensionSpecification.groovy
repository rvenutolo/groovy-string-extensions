package org.venutolo.groovy.extension

import spock.lang.Shared
import spock.lang.Specification

class GStringJoinLinesExtensionSpecification extends Specification {

    // this is just used to force creation of GStrings
    @Shared
    private String junk = ''

    def "Joins multiple lines"() {
        given:
        final GString s = """a${junk}
        b${junk}
        c${junk}"""
        expect:
        s.joinLines() == "a${junk} b${junk} c${junk}"
    }

    def "Does not reduce inline whitespace"() {
        given:
        final GString s = "a${junk}  b${junk}"
        expect:
        s.joinLines() == "a${junk}  b${junk}"
    }

    def "Trims leading or trailing whitespace without newline"() {
        given:
        final GString s = " a${junk}b${junk} "
        expect:
        s.joinLines() == "a${junk}b${junk}"
    }

    def "Trims leading or trailing whitespace that contains newline"() {
        given:
        final GString s = """
        a${junk}
        b${junk}
        """
        expect:
        s.joinLines() == "a${junk} b${junk}"
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
