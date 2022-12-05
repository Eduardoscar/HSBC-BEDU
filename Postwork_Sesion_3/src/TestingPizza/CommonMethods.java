package TestingPizza;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CommonMethods {

    public void redirectStdOutput(String stdOutputPath) {
        PrintStream fileStream;
        try {
            fileStream = new PrintStream(stdOutputPath);
            System.setOut(fileStream);
        } catch (FileNotFoundException e) {
            System.out.println("Error: FileNotFoundException " + e.getMessage());
        }
    }

    public String readFileContents(String stdOutputPath) {
        String data = "";
        try {
            data = new String(Files.readAllBytes(Paths.get(stdOutputPath)));
        } catch (IOException e) {
            System.out.println("Error leyendo el archivo");
        }
        return data.trim();
    }

    public void redirectStdInput(String stdInputPath) {
        try {
            System.setIn(new FileInputStream(stdInputPath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}