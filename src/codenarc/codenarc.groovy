// http://codenarc.sourceforge.net/codenarc-configuring-rules.html
ruleset {
    ruleset('rulesets/basic.xml')
    ruleset('rulesets/braces.xml')
    ruleset('rulesets/concurrency.xml')
    ruleset('rulesets/convention.xml')
    ruleset('rulesets/design.xml')
    ruleset('rulesets/dry.xml')
    ruleset('rulesets/enhanced.xml')
    ruleset('rulesets/exceptions.xml')
    ruleset('rulesets/formatting.xml')
    ruleset('rulesets/generic.xml')
    ruleset('rulesets/grails.xml')
    ruleset('rulesets/groovyism.xml')
    ruleset('rulesets/imports.xml')
    ruleset('rulesets/jdbc.xml')
    ruleset('rulesets/junit.xml')
    ruleset('rulesets/logging.xml')
    ruleset('rulesets/naming.xml') {
        VariableName {
            // For some reason finalRegex, which is used to check final local
            // variable names, requires uppercase letters, so set it to the
            // non-final regex.
            finalRegex = regex
        }
    }
    ruleset('rulesets/security.xml')
    ruleset('rulesets/serialization.xml')
    ruleset('rulesets/size.xml') {
        // Requires the GMetrics jar
        AbcMetric(enabled: false)
        // Requires the GMetrics jar and a Cobertura coverage file
        CrapMetric(enabled: false)
        // Requires the GMetrics jar
        CyclomaticComplexity(enabled: false)
    }
    ruleset('rulesets/unnecessary.xml')
    ruleset('rulesets/unused.xml')
}
