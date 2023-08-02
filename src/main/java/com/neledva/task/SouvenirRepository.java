package com.neledva.task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SouvenirRepository {

    private static SouvenirRepository instance;

    private List<Souvenir> souvenirs;
    private List<Manufacturer> manufacturers;

    private SouvenirRepository() {
        this.souvenirs = new ArrayList<>();
        this.manufacturers = new ArrayList<>();
    }

    public static SouvenirRepository getInstance() {
        if (instance == null) {
            instance = new SouvenirRepository();
        }
        return instance;
    }

    public void addSouvenir(Souvenir souvenir) {
        souvenirs.add(souvenir);
        Manufacturer manufacturer = souvenir.getManufacturer();
        if (!manufacturers.contains(manufacturer)) {
            manufacturers.add(manufacturer);
        }
    }

    public void updateSouvenir(Souvenir souvenir, String newName, double newPrice, LocalDate newDate) {
        souvenir.setName(newName);
        souvenir.setPrice(newPrice);
        souvenir.setDate(newDate);
    }

    public List<Souvenir> getAllSouvenirs() {
        return souvenirs;
    }

    public List<Manufacturer> getAllManufacturers() {
        return manufacturers;
    }

    public List<Souvenir> getSouvenirsByManufacturer(Manufacturer manufacturer) {
        return souvenirs.stream()
                .filter(souvenir -> souvenir.getManufacturer().equals(manufacturer))
                .collect(Collectors.toList());
    }

    public List<Souvenir> getSouvenirsByCountry(String country) {
        return souvenirs.stream()
                .filter(souvenir -> souvenir.getManufacturer().getCountry().equals(country))
                .collect(Collectors.toList());
    }

    public List<Manufacturer> getManufacturersByMaxPrice(double maxPrice) {
        return manufacturers.stream()
                .filter(manufacturer -> {
                    List<Souvenir> manufacturerSouvenirs = getSouvenirsByManufacturer(manufacturer);
                    return manufacturerSouvenirs.stream().anyMatch(souvenir -> souvenir.getPrice() < maxPrice);
                })
                .collect(Collectors.toList());
    }

    public List<Manufacturer> getManufacturersBySouvenirAndYear(String souvenirName, int year) {
        return souvenirs.stream()
                .filter(souvenir -> souvenir.getName().equals(souvenirName) && souvenir.getDate().getYear() == year)
                .map(Souvenir::getManufacturer)
                .distinct()
                .collect(Collectors.toList());
    }

    public List<Souvenir> getSouvenirsByYear(int year) {
        return souvenirs.stream()
                .filter(souvenir -> souvenir.getDate().getYear() == year)
                .collect(Collectors.toList());
    }

    public void removeManufacturerAndSouvenirs(Manufacturer manufacturer) {
        souvenirs.removeIf(souvenir -> souvenir.getManufacturer().equals(manufacturer));
        manufacturers.remove(manufacturer);
    }
}
