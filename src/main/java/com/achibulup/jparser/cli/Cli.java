package com.achibulup.jparser.cli;

import java.io.File;
import java.io.FileNotFoundException;

import com.achibulup.jparser.format.Format;
import com.achibulup.jparser.element.Project;
import com.achibulup.jparser.parser.ProjectParser;

public class Cli {
  public static int a, e;
  public static final int b = 0;
  public double c = 0;

  public static class Nested {
    public static class Nested2{

    }
  }

  public enum BRUH {
    FIRST, SECOND
  }

  public static void main(String[] args) {
    System.out.println(args.length);
    System.out.println(String.join("\n", args));

    try {
      var file = new File("src/main/java/com/achibulup/jparser");
      if (!file.exists()) {
        throw new FileNotFoundException(file.getPath() + "not found");
      }
      Project project = ProjectParser.parse(file);
      System.out.println(Format.toString(project));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
