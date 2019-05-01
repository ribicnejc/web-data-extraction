package utils;

import javafx.scene.shape.Path;

import java.nio.file.Files;
import java.nio.file.Paths;

public class HTMLHelper {
    public static void printMe() {
        System.out.println("lol");
    }

    public static String getHTMLString(String path) throws Exception {
        return new String(Files.readAllBytes(Paths.get(path)));
    }
}
