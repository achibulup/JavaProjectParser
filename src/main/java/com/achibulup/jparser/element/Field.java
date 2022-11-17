package com.achibulup.jparser.element;

import com.github.javaparser.ast.AccessSpecifier;
import com.github.javaparser.ast.Modifier;

import java.util.List;

public class Field extends Declaration {
  private String name;
  private Type type;

  public Field(String name, Type type, AccessSpecifier accessSpec, List<Modifier> modifiers) {
    super(accessSpec, modifiers);
    setName(name);
    setType(type);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Type getType() {
    return type;
  }

  public void setType(Type type) {
    this.type = type;
  }
}
