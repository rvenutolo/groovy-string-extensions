import groovy.transform.CompileStatic
import org.codehaus.groovy.ast.ClassNode

withConfig(configuration) {
    source(
        classValidator: {final ClassNode classNode ->
            final boolean isSpecClass = classNode.nameWithoutPackage.endsWith('Spec')
            final boolean isCompileStaticAnnotated =
                classNode.annotations*.classNode*.nameWithoutPackage.contains('CompileStatic')
            final boolean applyCompileStatic = !isSpecClass && !isCompileStaticAnnotated
            if (applyCompileStatic) {
                println("Applying @CompileStatic to $classNode")
            }
            applyCompileStatic
        }
    ) {
        ast(CompileStatic)
    }
}
