package com.achibulup.jparser.format;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.achibulup.jparser.element.Class;
import com.achibulup.jparser.element.Constructor;
import com.achibulup.jparser.element.Declaration;
import com.achibulup.jparser.element.Field;
import com.achibulup.jparser.element.Method;
import com.achibulup.jparser.element.Package;
import com.achibulup.jparser.element.Parameter;
import com.achibulup.jparser.element.Project;
import com.github.javaparser.ast.AccessSpecifier;

public class Format {
  public static enum Alignment { LEFT, RIGHT, MIDDLE };

  public static String toString(Project project) {
    StringBuilder builder = new StringBuilder();
    for (Package aPackage : project.getPackages()) {
      builder.append("\n" + aPackage.getFullName() + " :\n" + "=".repeat(60) + "\n\n");
      for (Class clazz : aPackage.getDirectClasses()) {
        builder.append(toString(clazz) + "\n");
      }
    }
    return builder.toString();
  }

  public static String toString(Class clazz) {
    StringBuilder builder = new StringBuilder();

    List<String> className = new ArrayList<>();
    switch (clazz.getKind()) {
      case CONCRETE :
        break;
      case ABSTRACT:
        className.add("<<abstract>>");
        break;
      case INTERFACE:
        className.add("<<interface>>");
        break;
      case ENUM:
        className.add("<<enumeration>>");
        break;
    }
    className.add(clazz.getName());

    List<String> enumEntries = new ArrayList<>();
    if (clazz.getKind() == Class.Kind.ENUM) {
      for (String valueName : clazz.getEnumEntries()) {
        enumEntries.add(valueName);
      }
    }

    List<String> innerClasses = new ArrayList<>();
    for (Class innerClass : clazz.getInnerClasses()) {
      String innerClassStr = innerClassDeclString(innerClass);
      innerClasses.add(innerClassStr);
    }

    List<String> fields = new ArrayList<>();
    for (Field field : clazz.getFields()) {
      String fieldStr = toString(field);
      fields.add(fieldStr);
    }

    List<String> constructors = new ArrayList<>();
    for (Constructor ctor : clazz.getConstructors()) {
      String ctorStr = toString(clazz, ctor);
      constructors.add(ctorStr);
    }

    List<String> methods = new ArrayList<>();
    for (Method method : clazz.getMethods()) {
      String methodStr = toString(method);
      methods.add(methodStr);
    }

    int width = 0;
    for (List<String> block : Arrays.asList(className, enumEntries, innerClasses, fields, constructors, methods)) {
      for (String line : block) {
        width = Math.max(width, line.length());
      }
    }

    builder.append(makeHorizontalLine(width));;
    for (String line : className) {
      builder.append(makeBoxedRow(line, width, Alignment.MIDDLE));
    }

    for (List<String> block : Arrays.asList(enumEntries, innerClasses, fields, constructors, methods)) {
      if (!block.isEmpty()) {
        builder.append(makeHorizontalLine(width));
        for (String line : block) {
          builder.append(makeBoxedRow(line, width, Alignment.LEFT));
        }
      }
    }
    builder.append(makeHorizontalLine(width));

    return builder.toString();
  }

  public static String innerClassDeclString(Class innerClass) {
    return makeDeclHeader(innerClass) + toString(innerClass.getKind()) + " " + innerClass.getUnqualifiedName();
  }

  public static String toString(Field field) {
    return makeDeclHeader(field) + field.getType().getName() + " " + field.getName();
  }

  public static String toString(Class clazz, Constructor ctor) {
    return makeDeclHeader(clazz) + clazz.getName() + toString(ctor.getParameters());
  }

  public static String toString(Method method) {
    return makeDeclHeader(method)
         + method.getReturnType().getName() + " " + method.getName() + toString(method.getParameters());
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

  public static String makeDeclHeader(Declaration decl) {
    String result = toString(decl.getAccessSpec()) + " ";
    for (var modifier : decl.getModifiers()) {
      result += modifier;
    }
    return result;
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
    return makeBoxedRow(line, width, Alignment.LEFT);
  }

  private static String makeBoxedRow(String line, int width, Alignment alignment) {
    int padding = width - line.length();
    switch (alignment) {
      case LEFT :
        return "| " + line + " ".repeat(padding) + " |\n";
      case RIGHT:
        return "| " + " ".repeat(padding) + line + " |\n";
      default:
        return "| " + " ".repeat(padding / 2) + line + " ".repeat((padding + 1) / 2) + " |\n";
    }
  }
}
