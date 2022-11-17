package com.achibulup.jparser.parser;

import com.achibulup.jparser.element.Project;
import com.achibulup.jparser.parser.visitor.FileVisitor;

import java.io.File;
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
}
