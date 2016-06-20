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

import spock.lang.Specification

class StringJoinLinesExtensionSpec extends Specification {

    def "Joins lines with no starting and ending newlines"() {
        given:
        final String s = '''a\t\f\r
        \t\f\rb\t\f\r
        \t\f\rc'''
        expect:
        s.joinLines() == 'a b c'
    }

    def "Joins/trims lines with starting and ending newlines"() {
        given:
        final String s = '''
        \t\f\ra\t\f\r
        \t\f\rb\t\f\r
        '''
        expect:
        s.joinLines() == 'a b'
    }

    def "Does not collapse inline whitespace"() {
        given:
        final String s = 'a \t\f\r b'
        expect:
        s.joinLines() == 'a \t\f\r b'
    }

    def "Trims leading or trailing whitespace without newline"() {
        given:
        final String s = ' \t\f\r ab \t\f\r'
        expect:
        s.joinLines() == 'ab'
    }

}
