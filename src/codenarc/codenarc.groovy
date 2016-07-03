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
            // for some reason, finalRegex (used to check final local variables)
            // requires uppercase letters, so set to non-final regex
            finalRegex = regex
        }
    }
    ruleset('rulesets/security.xml')
    ruleset('rulesets/serialization.xml')
    ruleset('rulesets/size.xml') {
        // TODO add GMetrics and add these rules?
        AbcMetric(enabled: false) // Requires the GMetrics jar
        CrapMetric(enabled: false) // Requires the GMetrics jar and a Cobertura coverage file
        CyclomaticComplexity(enabled: false) // Requires the GMetrics jar
    }
    ruleset('rulesets/unnecessary.xml')
    ruleset('rulesets/unused.xml')
}
