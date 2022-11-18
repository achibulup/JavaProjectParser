package com.achibulup.jparser.element;

import com.github.javaparser.ast.Modifier;

import java.util.ArrayList;
import java.util.List;

public class Parameter {
  private Type type;
  private String name;
  private final List<Modifier> modifiers = new ArrayList<>();

  public Parameter(Type type, String name) {
    setType(type);
    setName(name);
  }

  public Parameter(Type type, String name, List<Modifier> modifiers) {
    setType(type);
    setName(name);
    this.modifiers.addAll(modifiers);
  }

  public Type getType() {
    return type;
  }

  public void setType(Type type) {
    this.type = type;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Modifier> getModifiers() {
    return modifiers;
  }

  public void addModifier(Modifier modifier) {
    modifiers.add(modifier);
  }
}
