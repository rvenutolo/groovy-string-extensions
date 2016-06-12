import groovy.transform.CompileStatic
import org.codehaus.groovy.ast.ClassNode

withConfig(configuration) {
    source(
        classValidator: {final ClassNode classNode ->
            final boolean applyCompileStatic = !classNode.nameWithoutPackage.endsWith('Spec')
            if (applyCompileStatic) {
                println("Applying @CompileStatic to $classNode")
            }
            applyCompileStatic
        }
    ) {
        ast(CompileStatic)
    }
}
