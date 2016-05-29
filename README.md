# groovy-string-extensions
[![License](https://img.shields.io/hexpm/l/plug.svg)](https://github.com/rvenutolo/groovy-string-extensions/blob/master/LICENSE)
[![Build Status](https://travis-ci.org/rvenutolo/groovy-string-extensions.svg?branch=master)](https://travis-ci.org/rvenutolo/groovy-string-extensions)

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
