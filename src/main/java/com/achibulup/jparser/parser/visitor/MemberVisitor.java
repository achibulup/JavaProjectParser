package com.achibulup.jparser.parser.visitor;

import java.util.List;
import java.util.Optional;

import com.achibulup.jparser.element.*;
import com.achibulup.jparser.element.Class;
import com.achibulup.jparser.parser.ClassParser;
import com.github.javaparser.ast.AccessSpecifier;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class MemberVisitor extends VoidVisitorAdapter<Class> {
  @Override
  public void visit(ClassOrInterfaceDeclaration classDecl, Class outerClass) {
    Class innerClass = ClassParser.parse(classDecl);
    innerClass.setOuterClass(Optional.of(outerClass));
    outerClass.addInnerClass(innerClass);
  }

  @Override
  public void visit(EnumDeclaration enumDecl, Class outerClass) {
    Class innerEnum = ClassParser.parse(enumDecl);
    innerEnum.setOuterClass(Optional.of(outerClass));
    outerClass.addInnerClass(innerEnum);
  }

  @Override
  public void visit(FieldDeclaration fieldDecl, final Class outerClass) {
    final AccessSpecifier accessSpec = fieldDecl.getAccessSpecifier();
    final List<Modifier> modifiers = fieldDecl.getModifiers();
    fieldDecl.getVariables().forEach(v
        -> outerClass.addField(new Field(v.getNameAsString(), new Type(v.getTypeAsString()), accessSpec, modifiers)));
  }

  @Override
  public void visit(ConstructorDeclaration ctorDecl, Class outerClass) {
    AccessSpecifier accessSpec = ctorDecl.getAccessSpecifier();
    List<Modifier> modifiers = ctorDecl.getModifiers();
    Constructor ctor = new Constructor(accessSpec, modifiers);
    for (com.github.javaparser.ast.body.Parameter paramDecl : ctorDecl.getParameters()) {
      Parameter param = new Parameter(new Type(paramDecl.getTypeAsString()),
          paramDecl.getNameAsString(), paramDecl.getModifiers());
      ctor.addParameter(param);
    }
    outerClass.addConstructor(ctor);
  }

  @Override
  public void visit(MethodDeclaration methodDecl, Class outerClass) {
    AccessSpecifier accessSpec = methodDecl.getAccessSpecifier();
    List<Modifier> modifiers = methodDecl.getModifiers();
    Method method = new Method(methodDecl.getNameAsString(),
        new Type(methodDecl.getTypeAsString()), accessSpec, modifiers);
    for (com.github.javaparser.ast.body.Parameter paramDecl : methodDecl.getParameters()) {
      Parameter param = new Parameter(new Type(paramDecl.getTypeAsString()),
          paramDecl.getNameAsString(), paramDecl.getModifiers());
      method.addParameter(param);
    }
    outerClass.addMethod(method);
  }
}
