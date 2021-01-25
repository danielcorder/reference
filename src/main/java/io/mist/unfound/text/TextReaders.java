package io.mist.unfound.text;

//import org.junit.Assert;
//import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//import static org.hamcrest.CoreMatchers.containsString;

public class TextReaders {

    public static void main(String[] args) {//readTextFromTxt(File readFile) {

        try {
            File readFile = new File("src/main/resources/striving.txt");
            Scanner scanner = new Scanner(readFile);

            while (scanner.hasNextLine()) {
                String readLine = scanner.nextLine();
                System.out.println(readLine);
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        }
    }
}
