package com.achibulup.jparser.cli;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.AccessSpecifier;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.FieldDeclaration;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;

public class Cli {
  public static void main(String[] args) {
    System.out.println(args.length);
    System.out.println(String.join("\n", args));
    try {
      var file = new File("src/main/java/com/achibulup/jparser/cli/Cli.java");
      if (!file.exists()) {
        throw new FileNotFoundException(file.getPath() + "not found");
      }
      CompilationUnit compilationUnit = StaticJavaParser.parse(file);//new File("D:/CODE/java/sandboxes/javaparsertest/demo/src/main/java/com/achibulup/example/App.java"));
      compilationUnit.findAll(FieldDeclaration.class)
          .forEach(f ->
              f.getVariables().forEach(v -> {
                Collection<String> describerTokens = new ArrayList<>();
                var accessSpec = f.getAccessSpecifier();
                String accessSpecAsString = accessSpec.equals(AccessSpecifier.NONE) ? "default" : accessSpec.toString();
                accessSpecAsString = accessSpecAsString.toLowerCase();
                describerTokens.add(accessSpecAsString);
                if (f.isStatic()) {
                  describerTokens.add("static");
                }
                describerTokens.add("field");
                describerTokens.add(v.getNameAsString());
                describerTokens.add(":");
                describerTokens.add(v.getTypeAsString());
                System.out.println(String.join(" ", describerTokens));
              }));
      // System.out.println("Field " + f.getElementType() + f.getRange().map(r -> r.begin.line).orElse(-1)));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
