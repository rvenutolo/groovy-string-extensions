package org.venutolo.groovy.extension

import spock.lang.Shared
import spock.lang.Specification

class GStringTrimAndCollapseWhitespaceExtensionSpec extends Specification {

    // this is just used to force creation of GStrings
    @Shared
    private String junk = ''

    def "Reduces inner whitespace"() {
        given:
        final GString s = "a \t\n\f\r b${junk}"
        expect:
        s.trimAndCollapseWhitespace() == "a b${junk}"
    }

    def "Trims leading and trailing whitespace"() {
        given:
        final GString s = " \t\n\f\r ab${junk} \t\n\f\r "
        expect:
        s.trimAndCollapseWhitespace() == "ab${junk}"
    }

    def "Does not modify instance"() {
        given:
        final GString s = "  ${junk}  "
        when:
        s.trimAndCollapseWhitespace()
        then:
        s == "  ${junk}  "
    }

    def "Does not modify embedded String"() {
        given:
        final String embeddedString = '  embedded  string  '
        final GString s = "${embeddedString}"
        expect:
        s.trimAndCollapseWhitespace().toString() == embeddedString
    }

}
