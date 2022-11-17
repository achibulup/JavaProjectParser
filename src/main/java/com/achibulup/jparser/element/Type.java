package com.achibulup.jparser.element;

public class Type {
  private String name;

  public Type(String name) {
    setName(name);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

//  private com.github.javaparser.ast.type.Type type;
//  private com.github.javaparser.ast.type.Type nonArrayType;
//  private int rank = 0;
//  private Optional<Type> outerClass;
//  private Optional<Package> thePackage;

//  public Type(com.github.javaparser.ast.type.Type type, Optional<Type> outerClass, Optional<Package> thePackage) {
//    setType(type);
//    setOuterClass(outerClass);
//    setPackage(thePackage);
//  }
//
//  public String getName() {
//    return getType().asString();
//  }
//
//  public String getNameWithOuterClass() {
//    String result = getName();
//    if (!getOuterClass().isEmpty()) {
//      result = getOuterClass().get().getName() + "." + result;
//    }
//    return result;
//  }
//
//  public String getFullyQualifiedName() {
//    String result = getNameWithOuterClass();
//    if (!getPackage().isEmpty()) {
//      String packageName = getPackage().get().getFullName();
//      if (!packageName.isEmpty()) {
//        result = packageName + "." + result;
//      }
//    }
//    return result;
//  }
//
//  public int getRank() {
//    return rank;
//  }
//
//  public Optional<Type> getOuterClass() {
//    return outerClass;
//  }
//
//  public Optional<Package> getPackage() {
//    return thePackage;
//  }
//
//  public com.github.javaparser.ast.type.Type getType() {
//    return this.type;
//  }
//
//  public boolean isArray() {
//    return getRank() > 0;
//  }
//
//
//  public void setType(com.github.javaparser.ast.type.Type type) {
//    this.type = type;
//    this.nonArrayType = this.type;
//    this.rank = 0;
//    while (this.nonArrayType.isArrayType()) {
//      ++this.rank;
//      this.nonArrayType = this.nonArrayType.getElementType();
//    }
//  }
//
//  public void setOuterClass(Optional<Type> outerClass) {
//    this.outerClass = outerClass;
//  }
//
//  public void setPackage(Optional<Package> thePackage) {
//    this.thePackage = thePackage;
//  }
}
