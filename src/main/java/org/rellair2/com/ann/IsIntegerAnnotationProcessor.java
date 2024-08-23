package org.rellair2.com.ann;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.util.Set;

@SupportedAnnotationTypes("IsInteger")
@SupportedSourceVersion(SourceVersion.RELEASE_21)
public class IsIntegerAnnotationProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        System.err.println("IsIntegerAnnotationProcessor.process()");
        processingEnv.getMessager().printMessage(Diagnostic.Kind.MANDATORY_WARNING,"Annotation @IsInteger is used");
        for (Element element : roundEnv.getElementsAnnotatedWith(IsInteger.class)) {
            if (element.getKind() == ElementKind.METHOD) {
                ExecutableElement method = (ExecutableElement) element;

                // Проверка типа возвращаемого значения
                if (!method.getReturnType().toString().equals("int")) {
                    processingEnv.getMessager().printMessage(
                            Diagnostic.Kind.ERROR,
                            "Annotation @IsInteger can de applied only to methods with 'int' return type",
                            element);
                }
            }
        }
        return true;
    }
}
