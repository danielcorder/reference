package io.mist.unfound.text;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TextReaders {

    public static void main(String[] args) {

        try {
            File textFile = new File("src/main/resources/striving.txt");
            Scanner scanner = new Scanner(textFile);

            while (scanner.hasNextLine()) {
                String readLine = scanner.nextLine();
                System.out.println(readLine);
            }
            System.out.println();

        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        }

        String[] HEADERS = { "ones", "twos", "threes", "fours", "fives"};

        try {
            Reader in = new FileReader("src/main/resources/numbers.csv");
            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .withHeader(HEADERS)
                    .withFirstRecordAsHeader()
                    .parse(in);

            List<String> ones = new ArrayList<>();
            List<String> twos = new ArrayList<>();
            List<String> threes = new ArrayList<>();
            List<String> fours = new ArrayList<>();
            List<String> fives = new ArrayList<>();

            for (CSVRecord record : records) {

                ones.add(record.get("ones"));
                twos.add(record.get("twos"));
                threes.add(record.get("threes"));
                fours.add(record.get("fours"));
                fives.add(record.get("fives"));
            }

            System.out.print("Ones: ");
            for (String s : ones) {
                System.out.print(s + " ");
            }
            System.out.println();
            System.out.print("Twos: ");
            for (String s : twos) {
                System.out.print(s + " ");
            }
            System.out.println();
            System.out.print("Threes: ");
            for (String s : threes) {
                System.out.print(s + " ");
            }
            System.out.println();
            System.out.print("Fours: ");
            for (String s : fours) {
                System.out.print(s + " ");
            }
            System.out.println();
            System.out.print("Fives: ");
            for (String s : fives) {
                System.out.print(s + " ");
            }
            System.out.println();

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
