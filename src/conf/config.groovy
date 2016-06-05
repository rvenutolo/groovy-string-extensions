import groovy.transform.CompileStatic

withConfig(configuration) {
    // apply @CompileStatic to non-test classes
    source(basenameValidator: {!it.endsWith('Spec')}) {
        ast(CompileStatic)
    }
}
