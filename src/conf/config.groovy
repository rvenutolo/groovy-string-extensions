import groovy.transform.CompileStatic
import org.codehaus.groovy.ast.ClassNode

// This script will add @CompileStatic to non-Spec classes that do not already
// have the annotation. I'd rather the class already be annotated with
// @CompileStatic so the IDE is aware that the class will be statically
// compiled, so this is really just a backup.
//
// I had modified this script to throw an exception when a class did not have
// a @CompileStatic annotation, but thought that was kind of ugly. Perhaps in
// the future I will write something to fail a build when a class does not have
// @CompileStatic, such as a custom CodeNarc rule.

withConfig(configuration) {
    source(
        classValidator: {final ClassNode classNode ->
            final boolean isSpecClass = classNode.nameWithoutPackage.endsWith('Spec')
            final boolean isCompileStaticAnnotated = classNode.annotations.any {
                it.classNode.nameWithoutPackage == 'CompileStatic'
            }
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
