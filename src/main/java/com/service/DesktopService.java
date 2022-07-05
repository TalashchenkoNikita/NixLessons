package com.service;

import com.model.Desktop;
import com.model.Manufacturer;
import com.repository.DesktopRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class DesktopService {
    private static final Random RANDOM = new Random();
    private static final DesktopRepository REPOSITORY = new DesktopRepository();

    public void createAndSavePhones(int count) {
        List<Desktop> desktops = new LinkedList<>();
        for (int i = 0; i < count; i++) {
            desktops.add(new Desktop(
                    "Title-" + RANDOM.nextInt(1000),
                    RANDOM.nextInt(500),
                    RANDOM.nextDouble(1000.0),
                    "Model-" + RANDOM.nextInt(10),
                    getRandomManufacturer()
            ));
        }
        REPOSITORY.saveAll(desktops);
    }

    private Manufacturer getRandomManufacturer() {
        final Manufacturer[] values = Manufacturer.values();
        final int index = RANDOM.nextInt(values.length);
        return values[index];
    }

    public void printAll() {
        for (Desktop desktop : REPOSITORY.getAll()) {
            System.out.println(desktop);
        }
    }
}
