# groovy-string-extensions
A (very small) collection of Groovy (v2.0+) extensions for String and GString. 

__Note:__ The GString methods do not mutate the GString instance.

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
    This is a multi-line string
    that I want to join into one
    single line.
'''

assert s.joinLines() == 'This is a multi-line string that I want to join into one single line.'
```
    
### `.reduceWhitespace()`

```groovy
static String reduceWhitespace(String self)
static String reduceWhitespace(GString self)
```
    
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
