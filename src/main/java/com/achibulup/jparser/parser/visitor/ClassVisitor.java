package com.achibulup.jparser.parser.visitor;

import com.achibulup.jparser.element.Class;
import com.achibulup.jparser.element.Package;
import com.achibulup.jparser.parser.ClassParser;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class ClassVisitor extends VoidVisitorAdapter<Package> {
  @Override
  public void visit(ClassOrInterfaceDeclaration classDecl, Package thisPackage) {
    addThisAndAllInnerClassesToPackage(thisPackage, ClassParser.parse(classDecl));
  }

  @Override
  public void visit(EnumDeclaration enumDecl, Package thisPackage) {
    addThisAndAllInnerClassesToPackage(thisPackage, ClassParser.parse(enumDecl));
  }

  public static void addThisAndAllInnerClassesToPackage(final Package thisPackage, Class thisClass) {
    thisPackage.addDirectClass(thisClass);
    thisClass.getInnerClasses().forEach(c -> addThisAndAllInnerClassesToPackage(thisPackage, c));
  }
}
