package com.achibulup.jparser.element;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.github.javaparser.ast.Modifier;

public abstract class HaveModifier {
  private static final List<Modifier.Keyword> ACCESS_SPEC_MODIFIER = Arrays.asList(
      Modifier.Keyword.PUBLIC,
      Modifier.Keyword.PROTECTED,
      Modifier.Keyword.PRIVATE
  );

  private List<Modifier> modifiers = new ArrayList<>();

  public HaveModifier(List<Modifier> modifiers) {
    this.modifiers.addAll(modifiers);
    this.modifiers.removeIf(modf -> ACCESS_SPEC_MODIFIER.contains(modf.getKeyword()));
  }

  public List<Modifier> getModifiers() {
    return modifiers;
  }

  public void addModifier(Modifier modifier) {
    modifiers.add(modifier);
  }
}
