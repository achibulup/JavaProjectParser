package com.achibulup.jparser.cli;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.achibulup.jparser.format.Format;
import com.achibulup.jparser.element.Project;
import com.achibulup.jparser.parser.ProjectParser;
import com.achibulup.jparser.parser.visitor.FileVisitor;

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

  public static void main(String[] args) throws IOException {
    List<String> argList = new ArrayList<String>(Arrays.asList(args));
    argList.removeIf(String::isEmpty);
    try {
      BufferedReader buffReader = new BufferedReader(new InputStreamReader(System.in));
      String path = null;
      if (true) {
        System.out.print("Enter the folder or file to parse: ");
        path = buffReader.readLine();
      } else {
        System.out.println(argList.size());
        for (String arg : argList) {
          System.out.println(arg.length() + " " + arg);
        }
      }
      var file = new File(path);
      if (!file.exists()) {
        throw new FileNotFoundException(file.getPath() + " not found");
      }
      FileFilter filter = FileVisitor.DEFAULT_FILTER;
      if (file.isFile() && !file.getPath().endsWith(".java")) {
        System.out.println("Warning: the file is not a java file\n");
        filter = (aFile) -> true;
      }
      Project project = ProjectParser.parse(file, filter);
      Reader parseResult = new StringReader(Format.toString(project));
      BufferedReader resultReader = new BufferedReader(parseResult);
      OutputStream output = new FileOutputStream("parse-result.txt");
      BufferedWriter outputWriter = new BufferedWriter(new OutputStreamWriter(output));
      transfer(resultReader, outputWriter);
      output.close();
      System.out.println("\n"
          + "=".repeat(30) + "\n"
          + "Parse successful" + "\n"
          + "=".repeat(30) + "\n"
          + "\n"
          + "Parse result saved at parse-result.txt");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void transfer(BufferedReader reader, Writer writer) throws IOException {
    while (true) {
      String nextLine = reader.readLine();
      if (nextLine == null) {
        break;
      }
      if (writer instanceof PrintWriter) {
        ((PrintWriter) writer).println(nextLine);
      } else {
        writer.write(nextLine);
        writer.write("\n");
      }
    }
    writer.flush();
  }
}
