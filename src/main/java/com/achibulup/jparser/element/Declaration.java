package com.achibulup.jparser.element;

import com.github.javaparser.ast.AccessSpecifier;
import com.github.javaparser.ast.Modifier;

import java.util.List;

public abstract class Declaration extends HaveModifier implements Comparable<Declaration> {
  private AccessSpecifier accessSpec;

  public Declaration(AccessSpecifier accessSpec, List<Modifier> modifiers) {
    super(modifiers);
    setAccessSpec(accessSpec);
  }

  public AccessSpecifier getAccessSpec() {
    return accessSpec;
  }

  public void setAccessSpec(AccessSpecifier accessSpec) {
    this.accessSpec = accessSpec;
  }

  /**
   * Compare two declartion according to access spec and static modifier.
   * @param another the object to be compared.
   * @return result of comparison
   */
  @Override
  public int compareTo(Declaration another) {
    int compAccSpec = getAccSpecOrder(this.getAccessSpec()) - getAccSpecOrder(another.getAccessSpec());
    if (compAccSpec != 0) {
      return compAccSpec;
    }
    int thisIsStatic = (this.isStatic()) ? 1 : 0;
    int anotherIsStatic = (another.isStatic()) ? 1 : 0;
    return anotherIsStatic - thisIsStatic;
  }

  public boolean isStatic() {
    for (Modifier modf : getModifiers()) {
      if(modf.getKeyword() == Modifier.Keyword.STATIC) {
        return true;
      }
    };
    return false;
  }


  private static int getAccSpecOrder(AccessSpecifier acc) {
    switch (acc) {
      case PUBLIC :
        return 0;
      case PROTECTED :
        return 1;
      case NONE :
        return 2;
      case PRIVATE :
        return 3;
      default :
        return -1;
    }
  }
}
