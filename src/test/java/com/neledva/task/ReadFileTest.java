package com.neledva.task;

import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class ReadFileTest {

    private static final String TEST_FILENAME = "readTest.txt";

    @Test
    public void loadFromFileTest() throws IOException {
        String testData = "Test Souvenir 1;10.0;Test Manufacturer 1;Test Country 1;2023-01-01\n" +
                "Test Souvenir 2;20.0;Test Manufacturer 2;Test Country 2;2023-02-02";
        createTestDataFile(testData);

        List<Souvenir> expectedSouvenirs = List.of(
                new Souvenir("Test Souvenir 1", 10.0, new Manufacturer("Test Manufacturer 1", "Test Country 1"), LocalDate.of(2023, 1, 1)),
                new Souvenir("Test Souvenir 2", 20.0, new Manufacturer("Test Manufacturer 2", "Test Country 2"), LocalDate.of(2023, 2, 2))
        );

        List<Souvenir> actualSouvenirs = ReadFile.loadFromFile(TEST_FILENAME);

        assertEquals(expectedSouvenirs.size(), actualSouvenirs.size());
        for (int i = 0; i < expectedSouvenirs.size(); i++) {
            assertEquals(expectedSouvenirs.get(i), actualSouvenirs.get(i));
        }
    }

    private void createTestDataFile(String testData) throws IOException {
        try (FileWriter writer = new FileWriter(TEST_FILENAME)) {
            writer.write(testData);
        }
    }
}