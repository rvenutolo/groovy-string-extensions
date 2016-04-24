package org.venutolo.groovy.extension

import spock.lang.Shared
import spock.lang.Specification

class GStringReduceWhitespaceExtensionSpecification extends Specification {

    // this is just used to force creation of GStrings
    @Shared
    private String junk = ''

    def "Reduces inner whitespace"() {
        given:
        final GString s = "a  b${junk}"
        expect:
        s.reduceWhitespace() == "a b${junk}"
    }

    def "Trims leading and trailing whitespace"() {
        given:
        final GString s = " ab${junk} "
        expect:
        s.reduceWhitespace() == "ab${junk}"
    }

    def "Does not modify instance"() {
        given:
        final GString s = """  ${junk}  """
        when:
        s.joinLines()
        then:
        s == """  ${junk}  """
    }

    def "Does not modify embedded String"() {
        given:
        final String embeddedString = '''embedded  string '''
        final GString s = "${embeddedString}"
        expect:
        s.joinLines().toString() == embeddedString
    }

}
