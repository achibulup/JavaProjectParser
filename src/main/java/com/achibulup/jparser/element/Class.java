package com.achibulup.jparser.element;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.github.javaparser.ast.AccessSpecifier;
import com.github.javaparser.ast.Modifier;

public class Class extends Declaration {
  public enum Kind { CONCRETE, ABSTRACT, INTERFACE, ENUM }

  private String unqualifiedName;
  private Kind kind;
  private Optional<Class> outerClass = Optional.empty();
  private final List<Type> extendedTypes = new ArrayList<>();
  private final List<Type> implementedTypes = new ArrayList<>();

  private final List<String> enumEntries = new ArrayList<>();
  private final List<Class> innerClasses = new ArrayList<>();
  private final List<Field> fields = new ArrayList<>();
  private final List<Constructor> constructors = new ArrayList<>();
  private final List<Method> methods = new ArrayList<>();

  public Class(String unqualifiedName, Kind kind, AccessSpecifier accessSpec, List<Modifier> modifiers) {
    super(accessSpec, modifiers);
    setName(unqualifiedName);
    setKind(kind);
  }

  public String getName() {
    String result = getUnqualifiedName();
    if (!getOuterClass().isEmpty()) {
      result = getOuterClass().get().getName() + "." + result;
    }
    return result;
  }

  public String getUnqualifiedName() {
    return unqualifiedName;
  }

  public Kind getKind() {
    return kind;
  }

  public Optional<Class> getOuterClass() {
    return outerClass;
  }

  public List<Type> getExtendedTypes() {
    return extendedTypes;
  }

  public List<Type> getImplementedTypes() {
    return implementedTypes;
  }

  public List<String> getEnumEntries() {
    if (getKind() != Kind.ENUM) {
      throw new RuntimeException("this class is not enum");
    }
    return enumEntries;
  }

  public List<Class> getInnerClasses() {
    return innerClasses;
  }

  public List<Field> getFields() {
    return fields;
  }

  public List<Constructor> getConstructors() {
    return constructors;
  }

  public List<Method> getMethods() {
    return methods;
  }

  public void setName(String unqualifiedName) {
    this.unqualifiedName = unqualifiedName;
  }

  public void setKind(Kind kind) {
    this.kind = kind;
  }

  public void setOuterClass(Optional<Class> outerClass) {
    this.outerClass = outerClass;
  }

  public void addExtendedType(Type extended) {
    this.extendedTypes.add(extended);
  }

  public void addImplementedType(Type implemented) {
    implementedTypes.add(implemented);
  }

  public void addEnumEntry(String valueName) {
    if (getKind() != Kind.ENUM) {
      throw new RuntimeException("this class is not enum");
    }
    enumEntries.add(valueName);
  }

  public void addInnerClass(Class innerClass) {
    innerClasses.add(innerClass);
  }

  public void addConstructor(Constructor constructor) {
    constructors.add(constructor);
  }

  public void addField(Field field) {
    fields.add(field);
  }

  public void addMethod(Method method) {
    methods.add(method);
  }

  /**
   * Sort the members by access specifier and static modifier
   */
  public void organize() {
    Collections.sort(getInnerClasses());
    Collections.sort(getFields());
    Collections.sort(getConstructors());
    Collections.sort(getMethods());
  }
}
