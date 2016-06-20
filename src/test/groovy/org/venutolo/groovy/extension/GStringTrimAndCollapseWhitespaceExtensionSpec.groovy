/*
 * Copyright © 2016 Rick Venutolo
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
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

class GStringTrimAndCollapseWhitespaceExtensionSpec extends Specification {

    // this is just used to force creation of GStrings
    @Shared
    private String junk = ''

    def "Collapses inner whitespace"() {
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
