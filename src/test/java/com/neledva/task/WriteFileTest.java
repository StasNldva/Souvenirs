package com.neledva.task;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class WriteFileTest {

    private static final String TEST_FILENAME = "saveTest.txt";
    @Test
    void saveToFile() throws IOException {
        List<Souvenir> souvenirsToSave = new ArrayList<>();
        souvenirsToSave.add(new Souvenir("Stanislav Neledva", 10.0, new Manufacturer("Monobank", "Ukraine"), LocalDate.of(2023, 1, 1)));
        souvenirsToSave.add(new Souvenir("Grampy as a nickname", 20.0, new Manufacturer("Steam", "USA"), LocalDate.of(2023, 2, 2)));

        // Act
        WriteFile.saveToFile(souvenirsToSave, TEST_FILENAME);

        // Assert
        List<String> lines = Files.readAllLines(Path.of(TEST_FILENAME));
        assertEquals(souvenirsToSave.size(), lines.size());

        for (int i = 0; i < souvenirsToSave.size(); i++) {
            Souvenir souvenir = souvenirsToSave.get(i);
            String expectedLine = souvenir.getName() + ";" + souvenir.getPrice() + ";" +
                    souvenir.getManufacturer().getManufacName() + ";" + souvenir.getManufacturer().getCountry() + ";" +
                    souvenir.getDate().toString();
            assertEquals(expectedLine, lines.get(i));
        }
    }

    @Test
    public void saveToFileNullList() {
        assertThrows(NullPointerException.class, () -> WriteFile.saveToFile(null, TEST_FILENAME));
    }
}