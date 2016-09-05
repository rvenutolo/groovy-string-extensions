# groovy-string-extensions
<!-- START_REMOVE_FOR_SITE -->
[![Build Status](https://travis-ci.org/rvenutolo/groovy-string-extensions.svg?branch=master)](https://travis-ci.org/rvenutolo/groovy-string-extensions)
[![Coverage Status](https://codecov.io/gh/rvenutolo/groovy-string-extensions/branch/master/graph/badge.svg)](https://codecov.io/gh/rvenutolo/groovy-string-extensions)
[![Dependency Status](https://www.versioneye.com/user/projects/57c3491212b52600166f89ad/badge.svg)](https://www.versioneye.com/user/projects/57c3491212b52600166f89ad)
[![Dependency Status](https://dependencyci.com/github/rvenutolo/groovy-string-extensions/badge)](https://dependencyci.com/github/rvenutolo/groovy-string-extensions)

[![Maven Site](https://img.shields.io/badge/Maven%20Site-0.1.0--SNAPSHOT-brightgreen.svg)](https://rvenutolo.github.io/groovy-string-extensions/snapshot/)
[![Groovydoc](https://img.shields.io/badge/Groovydoc-0.1.0--SNAPSHOT-blue.svg)](https://rvenutolo.github.io/groovy-string-extensions/snapshot/apidocs/)

[![License](https://img.shields.io/hexpm/l/plug.svg)](https://www.apache.org/licenses/LICENSE-2.0)
[![Sputnik](https://sputnik.ci/conf/badge)](https://sputnik.ci/app#/builds/rvenutolo/groovy-string-extensions)
<!-- END_REMOVE_FOR_SITE -->

A (very small) collection of Groovy (v2.0+) extensions for String and GString.

__Note:__ The GString methods do not modify the GString instance, and instead return a separate GString instance. The GString methods also do not modify the embedded values.

## Extensions Methods

### `.joinLines()`

```groovy
static String joinLines(String self)
static GString joinLines(GString self)
```

Provides a method to trim leading and trailing whitespace and join multiple with a single space.

#### Example:

```groovy
String s = '''
    This is a multi-line String
    that I want to join into one
    single line.
'''

assert s.joinLines() == 'This is a multi-line String that I want to join into one single line.'
```

### `.trimAndCollapseWhitespace()`

```groovy
static String trimAndCollapseWhitespace(String self)
static String trimAndCollapseWhitespace(GString self)
```

Provides a method to trim leading and trailing whitespace and collapse multiple whitespace characters to one single space.

#### Example:

```groovy
GString query = """
    SELECT *
    FROM   cities
    WHERE  country = ${country}
    AND    region  = ${region}
"""

assert query.trimAndCollapseWhitespace() == "SELECT * FROM cities WHERE country = ${country} AND region = ${region}"
```
