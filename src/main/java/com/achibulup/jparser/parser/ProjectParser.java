package com.achibulup.jparser.parser;

import com.achibulup.jparser.element.Project;
import com.achibulup.jparser.parser.visitor.FileVisitor;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;

public class ProjectParser {
  public static Project parse(File file) throws FileNotFoundException {
    if (!file.exists()) {
      throw new FileNotFoundException(file + " not found!");
    }
    Project project = new Project();
    new FileVisitor().visit(file, project);
    return project;
  }

  public static Project parse(File file, FileFilter filter) throws FileNotFoundException {
    if (!file.exists()) {
      throw new FileNotFoundException(file + " not found!");
    }
    Project project = new Project();
    new FileVisitor(filter).visit(file, project);
    return project;
  }
}
