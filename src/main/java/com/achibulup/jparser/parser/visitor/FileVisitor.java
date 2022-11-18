package com.achibulup.jparser.parser.visitor;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;

import com.achibulup.jparser.element.Package;
import com.achibulup.jparser.element.Project;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;

public class FileVisitor {
  public static final FileFilter DEFAULT_FILTER = (file) -> (!file.isFile() || file.getPath().endsWith(".java"));
  private FileFilter filter = DEFAULT_FILTER;

  public FileVisitor() {
  }

  public FileVisitor(FileFilter filter) {
    setFilter(filter);
  }

  public FileFilter getFilter() {
    return filter;
  }

  public void setFilter(FileFilter filter) {
    this.filter = filter;
  }

  public void visit(File file, Project project) {
    if (!file.exists()) {
      throw new RuntimeException(new FileNotFoundException(file + " not found!"));
    }
    if (!this.filter.accept(file)) {
      return;
    }
    if (file.isDirectory()) {
      for (File subFile : file.listFiles(this.filter)) {
        visit(subFile, project);
      }
    } else if (file.isFile()) {
      visitFile(file, project);
    }
  }

  public void visitFile(File file, Project project) {
    try {
      CompilationUnit compUnit = StaticJavaParser.parse(file);
      String packageFullName = compUnit.getPackageDeclaration().map(pd -> pd.getNameAsString()).orElse("");
      Package thisPackage = project.addPackage(packageFullName);
      var visitor = new ClassVisitor();
      for (var classDecl : compUnit.getTypes()) {
        classDecl.accept(visitor, thisPackage);
      }
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
  }
}
