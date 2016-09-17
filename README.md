# groovy-string-extensions
<!-- START_REMOVE_FOR_SITE -->
[![Build Status](https://travis-ci.org/rvenutolo/groovy-string-extensions.svg?branch=master)](https://travis-ci.org/rvenutolo/groovy-string-extensions)
[![Coverage Status](https://codecov.io/gh/rvenutolo/groovy-string-extensions/branch/master/graph/badge.svg)](https://codecov.io/gh/rvenutolo/groovy-string-extensions)
[![Dependency Status](https://www.versioneye.com/user/projects/57c3491212b52600166f89ad/badge.svg)](https://www.versioneye.com/user/projects/57c3491212b52600166f89ad)
[![Dependency Status](https://dependencyci.com/github/rvenutolo/groovy-string-extensions/badge)](https://dependencyci.com/github/rvenutolo/groovy-string-extensions)
[![Dependencies](https://app.updateimpact.com/badge/766637912447127552/org.venutolo%3Agroovy-string-extensions%3A0.1.0.svg?config=compile)](https://app.updateimpact.com/latest/766637912447127552/org.venutolo%3Agroovy-string-extensions%3A0.1.0)
                            
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.venutolo/groovy-string-extensions/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.venutolo/groovy-string-extensions)
[![GitHub release](https://img.shields.io/github/release/venutolo/groovy-string-extensions.svg)](https://github.com/rvenutolo/groovy-string-extensions/releases)

[![Maven Site](https://img.shields.io/badge/Maven%20Site-0.1.0-brightgreen.svg)](https://rvenutolo.github.io/groovy-string-extensions/0.1.0/)
[![Groovydoc](https://img.shields.io/badge/Groovydoc-0.1.0-blue.svg)](https://rvenutolo.github.io/groovy-string-extensions/0.1.0/apidocs/)

[![License](https://img.shields.io/hexpm/l/plug.svg)](https://www.apache.org/licenses/LICENSE-2.0)
[![Sputnik](https://sputnik.ci/conf/badge)](https://sputnik.ci/app#/builds/rvenutolo/groovy-string-extensions)

A (very small) collection of Groovy (v2.0+) extensions for String and GString.

---
<!-- END_REMOVE_FOR_SITE -->
#### Maven:

```xml
<dependency>
    <groupId>org.venutolo</groupId>
    <artifactId>groovy-string-extensions</artifactId>
    <version>0.1.0</version>
</dependency>
```

#### Gradle:

```groovy
compile 'org.venutolo:groovy-string-extensions:0.1.0'
```

---

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

<!-- START_REMOVE_FOR_SITE -->
---
 
### Make Release

```bash
./mvnw -B release:clean release:prepare release:perform
```

### Update Maven Wrapper

Use Maven Wrapper plugin
```bash
mvn -N io.takari:maven:wrapper -Dmaven=3.3.9
```

Download latest scripts
```bash
wget https://raw.githubusercontent.com/takari/maven-wrapper/master/mvnw
wget https://raw.githubusercontent.com/takari/maven-wrapper/master/mvnw.cmd
```
<!-- END_REMOVE_FOR_SITE -->
