package com.neledva.task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class WriteFile {
    public static void saveToFile(List<Souvenir> souvenirs, String filename) {
        try {
            File file = new File(filename);
            PrintWriter writer = new PrintWriter(file);

            for (Souvenir souvenir : souvenirs) {
                Manufacturer manufacturer = souvenir.getManufacturer();
                String line = souvenir.getName() + ";" + souvenir.getPrice() + ";" +
                        manufacturer.getManufacName() + ";" + manufacturer.getCountry() + ";" +
                        souvenir.getDate().toString();
                writer.println(line);
            }

            writer.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        }
    }
}
