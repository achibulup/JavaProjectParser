package com.achibulup.jparser.cli;

import java.io.*;

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

  public static void main(String[] args) throws IOException {
    try {
      BufferedReader buffReader = new BufferedReader(new InputStreamReader(System.in));
      String path = null;
      if (args.length == 0) {
        System.out.print("Enter the folder or file to parse: ");
        path = buffReader.readLine();
      }
      var file = new File(path);
      if (!file.exists()) {
        throw new FileNotFoundException(file.getPath() + " not found");
      }
      Project project = ProjectParser.parse(file);
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
