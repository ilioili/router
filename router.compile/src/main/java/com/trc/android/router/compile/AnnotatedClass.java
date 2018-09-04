package com.trc.android.router.compile;

import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.util.HashSet;

import javax.lang.model.element.Modifier;

class AnnotatedClass {

    private HashSet<String> typeElementList;

    AnnotatedClass(HashSet<String> typeElements) {
        typeElementList = typeElements;
    }


    JavaFile generateFile() {
        //generateMethod
        MethodSpec.Builder getAnnotatedClasses = MethodSpec.methodBuilder("getAnnotatedClasses")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .returns(Class[].class);
        getAnnotatedClasses.addStatement("return CLASSES");

        StringBuilder classInitCodeBlock = new StringBuilder();
        classInitCodeBlock.append("new Class[]{\n");
        boolean isFirst = true;
        for (String t : typeElementList) {
            if (isFirst) isFirst = false;
            else classInitCodeBlock.append(',').append('\n');
            classInitCodeBlock.append("  ").append(t).append(".class");
        }
        classInitCodeBlock.append('}');
        FieldSpec.Builder classesField = FieldSpec.builder(Class[].class, "CLASSES")
                .addModifiers(Modifier.PRIVATE, Modifier.STATIC, Modifier.FINAL)
                .initializer(CodeBlock.of(classInitCodeBlock.toString()));

        //generaClass
        TypeSpec injectClass = TypeSpec.classBuilder("AddressList")
                .addModifiers(Modifier.PUBLIC)
                .addJavadoc("Automatically generated file. DO NOT MODIFY\n")
                .addMethod(getAnnotatedClasses.build())
                .addField(classesField.build())
                .build();
        return JavaFile.builder("com.trc.android.router.build", injectClass).build();
    }
}