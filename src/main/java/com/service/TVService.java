package com.service;

import com.model.Manufacturer;
import com.model.TV;
import com.repository.TVRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class TVService {
    private static final Random RANDOM = new Random();
    private static final TVRepository REPOSITORY = new TVRepository();

    public void createAndSavePhones(int count) {
        List<TV> tvs = new LinkedList<>();
        for (int i = 0; i < count; i++) {
            tvs.add(new TV(
                    "Title-" + RANDOM.nextInt(1000),
                    RANDOM.nextInt(500),
                    RANDOM.nextDouble(1000.0),
                    "Model-" + RANDOM.nextInt(10),
                    getRandomManufacturer()
            ));
        }
        REPOSITORY.saveAll(tvs);
    }

    private Manufacturer getRandomManufacturer() {
        final Manufacturer[] values = Manufacturer.values();
        final int index = RANDOM.nextInt(values.length);
        return values[index];
    }

    public void printAll() {
        for (TV tv : REPOSITORY.getAll()) {
            System.out.println(tv);
        }
    }
}
