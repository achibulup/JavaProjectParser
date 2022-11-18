package com.achibulup.jparser.element;

import com.github.javaparser.ast.AccessSpecifier;
import com.github.javaparser.ast.Modifier;

import java.util.ArrayList;
import java.util.List;

public class Constructor extends Declaration {
  private final List<Parameter> parameters = new ArrayList<>();

  public Constructor(AccessSpecifier accessSpec, List<Modifier> modifiers) {
    super(accessSpec, modifiers);
  }

  public Constructor(List<Parameter> parameters, AccessSpecifier accessSpec, List<Modifier> modifiers) {
    this(accessSpec, modifiers);
    this.parameters.addAll(parameters);
  }

  public List<Parameter> getParameters() {
    return parameters;
  }

  public void addParameter(Parameter parameter) {
    parameters.add(parameter);
  }
}
