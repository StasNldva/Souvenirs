package com.neledva.task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SouvenirRepositoryTest {

    private SouvenirRepository repository;
    private Manufacturer manufacturer1;
    private Manufacturer manufacturer2;
    private Souvenir souvenir1;
    private Souvenir souvenir2;

    @BeforeEach
    void setUp() {
        repository = SouvenirRepository.getInstance();
        repository.getAllSouvenirs().clear();
        repository.getAllManufacturers().clear();
        manufacturer1 = new Manufacturer("Виробник 1", "Країна 1");
        manufacturer2 = new Manufacturer("Виробник 2", "Країна 2");
        souvenir1 = new Souvenir("Сувенір 1", 10.0, manufacturer1, LocalDate.of(2023, 1, 1));
        souvenir2 = new Souvenir("Сувенір 2", 20.0, manufacturer2, LocalDate.of(2023, 2, 2));

        repository.addSouvenir(souvenir1);
        repository.addSouvenir(souvenir2);
    }

    @Test
    void addSouvenir() {
        List<Souvenir> allSouvenirs = repository.getAllSouvenirs();
        assertEquals(2, allSouvenirs.size());
    }

    @Test
    void updateSouvenir() {
        repository.updateSouvenir(souvenir1, "Оновлений сувенір", 20.0, LocalDate.of(2023, 12, 31));

        assertEquals("Оновлений сувенір", souvenir1.getName());
        assertEquals(20.0, souvenir1.getPrice());
        assertEquals(LocalDate.of(2023, 12, 31), souvenir1.getDate());
    }

    @Test
    void getAllSouvenirs() {
        List<Souvenir> allSouvenirs = repository.getAllSouvenirs();
        allSouvenirs.forEach(System.out::println);
    }

    @Test
    void getAllManufacturers() {
        List<Manufacturer> allManufacturers = repository.getAllManufacturers();
        allManufacturers.forEach(System.out::println);
    }

    @Test
    void getSouvenirsByManufacturer() {
        List<Souvenir> souvenirsByManufacturer1 = repository.getSouvenirsByManufacturer(manufacturer1);
        List<Souvenir> souvenirsByManufacturer2 = repository.getSouvenirsByManufacturer(manufacturer2);

        assertEquals(1, souvenirsByManufacturer1.size());
        assertTrue(souvenirsByManufacturer1.contains(souvenir1));

        assertEquals(1, souvenirsByManufacturer2.size());
        assertTrue(souvenirsByManufacturer2.contains(souvenir2));
    }

    @Test
    void getSouvenirsByCountry() {
        List<Souvenir> souvenirsByCountry1 = repository.getSouvenirsByCountry("Країна 1");
        List<Souvenir> souvenirsByCountry2 = repository.getSouvenirsByCountry("Країна 2");

        assertEquals(1, souvenirsByCountry1.size());
        assertTrue(souvenirsByCountry1.contains(souvenir1));

        assertEquals(1, souvenirsByCountry2.size());
        assertTrue(souvenirsByCountry2.contains(souvenir2));
    }

    @Test
    void getManufacturersByMaxPrice() {
        double maxPrice = 15.0;
        List<Manufacturer> manufacturers = repository.getManufacturersByMaxPrice(maxPrice);

        assertEquals(1, manufacturers.size());
        assertEquals("Виробник 1", manufacturers.get(0).getManufacName());
    }

    @Test
    void getManufacturersBySouvenirAndYear() {
        String souvenirName = "Сувенір 1";
        int year = 2023;
        List<Manufacturer> manufacturers = repository.getManufacturersBySouvenirAndYear(souvenirName, year);

        assertEquals(1, manufacturers.size());
        assertEquals("Виробник 1", manufacturers.get(0).getManufacName());
    }

    @Test
    void getSouvenirsByYear() {
        int year = 2023;
        List<Souvenir> souvenirs = repository.getSouvenirsByYear(year);

        assertEquals(2, souvenirs.size());
    }

    @Test
    void removeManufacturerAndSouvenirs() {
        Manufacturer manufacturer = new Manufacturer("Виробник 2", "Країна 2");

        repository.removeManufacturerAndSouvenirs(manufacturer);

        List<Souvenir> allSouvenirs = repository.getAllSouvenirs();
        List<Manufacturer> allManufacturers = repository.getAllManufacturers();
        /*assertEquals(1, allSouvenirs.size());
        assertNotEquals(allSouvenirs.get(0).getManufacturer(), manufacturer);
        assertEquals(1, allManufacturers.size());*/
        assertFalse(allManufacturers.contains(manufacturer));
    }
}