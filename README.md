# groovy-string-extensions
A (very small) collection of Groovy (v2.0+) extensions for String and GString.

# Extensions Methods

## `.joinLines()`

    static String joinLines(String self)
    static GString joinLines(GString self)
    
Provides a method to trim leading and trailing whitespace and join multiple with a single space.

Example:

    String s = '''
        This is a multi-line string
        that I want to join into one
        single line.
    '''
    
    assert s.joinLines() == 'This is a multi-line string that I want to join into one single line.'
    
## `.reduceWhitespace()`

    static String reduceWhitespace(String self)
    static String reduceWhitespace(GString self)
    
Provides a method to trim leading and trailing whitespace and reduce multiple whitespace characters to one single space.

Example:

    GString query = '''
        SELECT *
        FROM   cities
        WHERE  country = ${country}
        AND    region  = ${region}
    '''
    
    assert query.reduceWhitespace() == 'SELECT * FROM cities WHERE country = ${country} AND region = ${region}
