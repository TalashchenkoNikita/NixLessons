package com;

import com.model.Desktop;
import com.model.Manufacturer;
import com.model.Phone;
import com.model.TV;
import com.repository.DesktopRepository;
import com.repository.PhoneRepository;
import com.repository.TVRepository;
import com.service.PhoneService;
import org.apache.log4j.BasicConfigurator;

import java.util.List;


public class Main {
    public static void main(String[] args) {
        BasicConfigurator.configure();
        Desktop desktop = new Desktop("T1000",1,1300, "Terminator", Manufacturer.APPLE);
        Phone phone = new Phone("A70", 5, 500, "A70", Manufacturer.SAMSUNG);
        TV tv = new TV("Super", 1, 2000, "New", Manufacturer.SONY);
        PhoneRepository phoneRepository = new PhoneRepository();
        DesktopRepository desktopRepository = new DesktopRepository();
        TVRepository tvRepository = new TVRepository();
        tvRepository.save(tv);
        phoneRepository.save(phone);
        desktopRepository.save(desktop);
        phone.setPrice(600);
        phoneRepository.update(phone);
        List <Phone> phones = phoneRepository.getAll();
        System.out.println(phones.get(0));
        phoneRepository.delete(phone.getId());
        System.out.println(phones.isEmpty());
    }
}