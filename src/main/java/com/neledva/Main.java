package com.neledva;

import com.neledva.task.*;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }

    private void run() {
        String filename = "data.txt";
        String filename1 = "SaveData.txt";
        SouvenirRepository instance = SouvenirRepository.getInstance();

        List<Souvenir> souvenirs = ReadFile.loadFromFile(filename);

        System.out.println("Усі сувеніри та виробники:");
        for (Souvenir souvenir : souvenirs) {
            System.out.println(souvenir);
        }

        Souvenir souvenirToUpdate = souvenirs.get(0);
        instance.updateSouvenir(souvenirToUpdate, "Оновлений сувенір", 75.0, LocalDate.of(2022, 12, 25));

        Manufacturer manufacturerToSearch = new Manufacturer("Приватбанк", "Україна");
        List<Souvenir> souvenirsByManufacturer = instance.getSouvenirsByManufacturer(manufacturerToSearch);
        System.out.println("\nСувеніри виробника '" + manufacturerToSearch.getManufacName() + "':");
        for (Souvenir souvenir : souvenirsByManufacturer) {
            System.out.println(souvenir);
        }

        WriteFile.saveToFile(souvenirs, filename1);
    }

}