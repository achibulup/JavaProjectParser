package com.achibulup.jparser.element;

import com.github.javaparser.ast.AccessSpecifier;
import com.github.javaparser.ast.Modifier;

import java.util.ArrayList;
import java.util.List;

public class Method extends Declaration {
  private String name;
  private Type returnType;
  private final List<Parameter> parameters = new ArrayList<>();

  public Method(String name, Type returnType, AccessSpecifier accessSpec, List<Modifier> modifiers) {
    super(accessSpec, modifiers);
    setName(name);
    setReturnType(returnType);
  }

  public Method(String name, Type returnType, List<Parameter> parameters,
                AccessSpecifier accessSpec, List<Modifier> modifiers) {
    this(name, returnType, accessSpec, modifiers);
    this.parameters.addAll(parameters);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Type getReturnType() {
    return returnType;
  }

  public void setReturnType(Type returnType) {
    this.returnType = returnType;
  }

  public List<Parameter> getParameters() {
    return parameters;
  }

  public void addParameter(Parameter parameter) {
    parameters.add(parameter);
  }
}
