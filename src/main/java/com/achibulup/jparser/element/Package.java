package com.achibulup.jparser.element;

import java.util.ArrayList;
import java.util.List;

public class Package {
  private String fullName;

  private final List<Class> directClasses = new ArrayList<>();

  public Package(String fullName) {
    setFullName(fullName);
  }

  public String getFullName() {
    return fullName;
  }

  public List<Class> getDirectClasses() {
    return directClasses;
  }

  public void addDirectClass(Class clazz) {
    this.directClasses.add(clazz);
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }
}
