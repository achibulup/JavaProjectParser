package com.achibulup.jparser.parser;

import com.achibulup.jparser.element.Class;
import com.achibulup.jparser.element.Type;
import com.achibulup.jparser.parser.visitor.MemberVisitor;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;

public class ClassParser {
  public static Class parse(ClassOrInterfaceDeclaration decl) {
    Class thisClass = new Class(decl.getNameAsString(), getKind(decl),
                                decl.getAccessSpecifier(), decl.getModifiers());
    var extendedTypes = thisClass.getExtendedTypes();
    decl.getExtendedTypes().forEach(extended
        -> extendedTypes.add(new Type(extended.getNameAsString())));
    var implementedTypes = thisClass.getImplementedTypes();
    decl.getImplementedTypes().forEach(implemented
        -> implementedTypes.add(new Type(implemented.getNameAsString())));
    var visitor = new MemberVisitor();
    decl.getMembers().forEach(mem -> mem.accept(visitor, thisClass));
    return thisClass;
  }

  public static Class parse(EnumDeclaration decl) {
    Class thisClass = new Class(decl.getNameAsString(), getKind(decl),
                                decl.getAccessSpecifier(), decl.getModifiers());
    var implementedTypes = thisClass.getImplementedTypes();
    decl.getImplementedTypes().forEach(implemented
        -> implementedTypes.add(new Type(implemented.getNameAsString())));
    var visitor = new MemberVisitor();
    decl.getMembers().forEach(mem -> mem.accept(visitor, thisClass));
    thisClass.organize();
    return thisClass;
  }

  public static Class.Kind getKind(ClassOrInterfaceDeclaration decl) {
    if (decl.isInterface()) {
      return Class.Kind.INTERFACE;
    }
    if (decl.isAbstract()) {
      return Class.Kind.ABSTRACT;
    }
    return Class.Kind.CONCRETE;
  }
  public static Class.Kind getKind(EnumDeclaration decl) {
    return Class.Kind.ENUM;
  }
}
