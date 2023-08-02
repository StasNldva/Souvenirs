package com.neledva.task;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadFile {
    public static List<Souvenir> loadFromFile(String filename) {
        List<Souvenir> souvenirs = new ArrayList<>();

        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(";");

                if (parts.length == 5) {
                    String name = parts[0];
                    double price = Double.parseDouble(parts[1]);
                    String manufacName = parts[2];
                    String country = parts[3];
                    LocalDate date = LocalDate.parse(parts[4]);

                    Manufacturer manufacturer = new Manufacturer(manufacName, country);
                    Souvenir souvenir = new Souvenir(name, price, manufacturer, date);

                    souvenirs.add(souvenir);
                }
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        }

        return souvenirs;
    }
}
