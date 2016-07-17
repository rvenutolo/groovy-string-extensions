/*
 * Copyright © 2016 Rick Venutolo
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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

    String name = 'ClassWithoutCompileStatic'
    int priority = 1
    String description = 'Classes must be annotated with @CompileStatic.'
    Class astVisitorClass = EnforceCompileStaticAstVisitor

}

class EnforceCompileStaticAstVisitor extends AbstractAstVisitor {

    @Override
    protected void visitClassComplete(final ClassNode node) {
        final boolean hasCompileStatic = node.annotations.any {
            it.classNode.nameWithoutPackage == 'CompileStatic'
        }
        if (!hasCompileStatic) {
            addViolation(node, "${node.name} is not annotated with @CompileStatic")
        }
    }

}
