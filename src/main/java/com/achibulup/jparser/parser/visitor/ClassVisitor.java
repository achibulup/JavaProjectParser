package com.achibulup.jparser.parser.visitor;

import com.achibulup.jparser.element.Package;
import com.achibulup.jparser.parser.ClassParser;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class ClassVisitor extends VoidVisitorAdapter<Package> {
  @Override
  public void visit(ClassOrInterfaceDeclaration classDecl, Package thisPackage) {
    thisPackage.addDirectClass(ClassParser.parse(classDecl));
  }

  @Override
  public void visit(EnumDeclaration enumDecl, Package thisPackage) {
    thisPackage.addDirectClass(ClassParser.parse(enumDecl));
  }
}
