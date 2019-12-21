package test.sandromax.orderbook.io;

import test.sandromax.orderbook.Launcher;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.LinkedList;
import java.util.Properties;

public class FileHandler implements IOHandler{

    private String inputFile;
    private String outputFile;

    public FileHandler() {

        try (InputStream input = Launcher.class.getClassLoader().getResourceAsStream("config.properties")) {

            Properties prop = new Properties();

            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
                return;
            }
            prop.load(input);

            inputFile = prop.getProperty("input.file");
            outputFile = prop.getProperty("output.file");

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public LinkedList<String> read() throws IOException {

        LinkedList<String> rows = new LinkedList<>();

        Files.lines(Paths.get(inputFile), StandardCharsets.UTF_8).forEach(str -> {
                rows.add(str);
        });

        return rows;
    }

    public boolean write(String row) throws IOException {
        String newLine = "\r\n";
        Path path = Paths.get(outputFile);

        Files.write(path, (row + newLine).getBytes(), StandardOpenOption.APPEND);
        return true;
    }

    public boolean clear() throws IOException {

        BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputFile));
        writer.write("");
        writer.flush();
        return true;
    }
}
