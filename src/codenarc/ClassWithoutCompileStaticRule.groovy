import org.codehaus.groovy.ast.ClassNode
import org.codenarc.rule.AbstractAstVisitor
import org.codenarc.rule.AbstractAstVisitorRule

/**
 * CodeNarc rule that enforces use of @CompileStatic. I prefer checking classes
 * for the annotation (and failing), rather than adding the annotation via a
 * compiler configuration script, so that the IDE is aware the class is to be
 * statically compiled.
 */
class ClassWithoutCompileStaticRule extends AbstractAstVisitorRule {

    private static final String DESCRIPTION = 'Classes must be annotated with @CompileStatic.'

    String name = 'ClassWithoutCompileStatic'
    int priority = 1
    String description = DESCRIPTION
    Class astVisitorClass = EnforceCompileStaticAstVisitor

}

class EnforceCompileStaticAstVisitor extends AbstractAstVisitor {

    @Override
    protected void visitClassComplete(final ClassNode node) {
        final boolean hasCompileStatic = node.annotations.any {
            it.classNode.nameWithoutPackage == 'CompileStatic'
        }
        if (!hasCompileStatic) {
            addViolation(node, ClassWithoutCompileStaticRule.DESCRIPTION)
        }
    }

}