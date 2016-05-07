# groovy-string-extensions
A (very small) collection of Groovy (v2.0+) extensions for String and GString. 

__Note:__ The GString methods do not modify the GString instance, and instead return a separate GString instance. Values embedded in the GString are not modified.

## Extensions Methods

### `.joinLines()`

Provides a method to trim leading and trailing whitespace and join multiple with a single space.

#### Example:

```groovy
String s = '''
    This is a multi-line string
    that I want to join into one
    single line.
'''

assert s.joinLines() == 'This is a multi-line string that I want to join into one single line.'
```
    
### `.reduceWhitespace()`

Provides a method to trim leading and trailing whitespace and reduce multiple whitespace characters to one single space.

#### Example:

```groovy
GString query = '''
    SELECT *
    FROM   cities
    WHERE  country = ${country}
    AND    region  = ${region}
'''

assert query.reduceWhitespace() == 'SELECT * FROM cities WHERE country = ${country} AND region = ${region}'
```
