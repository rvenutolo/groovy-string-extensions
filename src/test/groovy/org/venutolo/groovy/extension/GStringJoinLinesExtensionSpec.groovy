/**
 * Copyright (C) 2016 Rick Venutolo (license@venutolo.org)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
