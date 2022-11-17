package com.achibulup.jparser.format;

import com.achibulup.jparser.element.*;
import com.achibulup.jparser.element.Class;
import com.achibulup.jparser.element.Package;
import com.github.javaparser.ast.AccessSpecifier;
import com.github.javaparser.ast.Modifier;

import java.util.ArrayList;
import java.util.List;

public class Format {
  public static String toString(Project project) {
    StringBuilder builder = new StringBuilder();
    for (Package aPackage : project.getPackages()) {
      builder.append(aPackage.getFullName() + " :\n" + "=".repeat(60) + "\n\n");
      for (Class clazz : aPackage.getDirectClasses()) {
        builder.append(toString(clazz) + "\n\n");
      }
    }
    return builder.toString();
  }

  public static String toString(Class clazz) {
    StringBuilder builder = new StringBuilder();

    int width = 0;
    String className = clazz.getName();
    width = className.length();

    List<String> innerClasses = new ArrayList<>();
    for (Class innerClass : clazz.getInnerClasses()) {
      String innerClassStr = innerClassDeclString(innerClass);
      width = Math.max(width, innerClassStr.length());
      innerClasses.add(innerClassStr);
    }

    List<String> fields = new ArrayList<>();
    for (Field field : clazz.getFields()) {
      String fieldStr = toString(field);
      width = Math.max(width, fieldStr.length());
      fields.add(fieldStr);
    }

    List<String> constructors = new ArrayList<>();
    for (Constructor ctor : clazz.getConstructors()) {
      String ctorStr = toString(clazz, ctor);
      width = Math.max(width, ctorStr.length());
      constructors.add(ctorStr);
    }

    List<String> methods = new ArrayList<>();
    for (Method method : clazz.getMethods()) {
      String methodStr = toString(method);
      width = Math.max(width, methodStr.length());
      methods.add(methodStr);
    }

    builder.append(makeHorizontalLine(width));
    builder.append(makeBoxedRow(className, width));
    if (!innerClasses.isEmpty()) {
      builder.append(makeHorizontalLine(width));
      for (String line : innerClasses) {
        builder.append(makeBoxedRow(line, width));
      }
    }
    if (!fields.isEmpty()) {
      builder.append(makeHorizontalLine(width));
      for (String line : fields) {
        builder.append(makeBoxedRow(line, width));
      }
    }
    if (!constructors.isEmpty()) {
      builder.append(makeHorizontalLine(width));
      for (String line : constructors) {
        builder.append(makeBoxedRow(line, width));
      }
    }
    if (!methods.isEmpty()) {
      builder.append(makeHorizontalLine(width));
      for (String line : methods) {
        builder.append(makeBoxedRow(line, width));
      }
    }
    builder.append(makeHorizontalLine(width));

    return builder.toString();
  }

  public static String innerClassDeclString(Class innerClass) {
    String result = toString(innerClass.getAccessSpec());
    for (Modifier modf : innerClass.getModifiers()) {
      result += " " + modf;
    }
    result += " " + toString(innerClass.getKind()) + " " + innerClass.getName();
    return result;
  }

  public static String toString(Field field) {
    String result = toString(field.getAccessSpec());
    for (var modifier : field.getModifiers()) {
      result += " " + modifier;
    }
    result += " " + field.getType().getName() + " " + field.getName();
    return result;
  }

  public static String toString(Class clazz, Constructor ctor) {
    String result = toString(ctor.getAccessSpec());
    for (var modifier : ctor.getModifiers()) {
      result += " " + modifier;
    }
    result += " " + clazz.getName() + toString(ctor.getParameters());
    return result;
  }

  public static String toString(Method method) {
    String result = toString(method.getAccessSpec());
    for (var modifier : method.getModifiers()) {
      result += " " + modifier;
    }
    result += " " + method.getName() + toString(method.getParameters());
    return result;
  }

  public static String toString(List<Parameter> parameterList) {
    String result = "(";
    for (Parameter param : parameterList) {
      if (result.length() != 1) {
        result += ", ";
      }
      result += param.getType().getName() + " " + param.getName();
    }
    result += ")";
    return result;
  }

  public static String toString(Class.Kind kind) {
    switch (kind) {
      case CONCRETE :
        return "class";
      case ABSTRACT :
        return "abstract class";
      case INTERFACE :
        return "interface";
      case ENUM :
        return "enum";
      default :
        return "";
    }
  }

  public static String toString(AccessSpecifier spec) {
    switch (spec) {
      case PUBLIC :
        return "+";
      case PROTECTED :
        return "#";
      case NONE :
        return "~";
      case PRIVATE :
        return  "-";
      default :
        return "";
    }
  }

  private static String makeHorizontalLine(int width) {
    return "+-" + "-".repeat(width) + "-+\n";
  }

  private static String makeBoxedRow(String line, int width) {
    int padding = width - line.length();
    return "| " + line + " ".repeat(padding) + " |\n";
  }
}
