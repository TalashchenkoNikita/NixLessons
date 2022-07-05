package com.repository;

import com.model.Desktop;
import org.apache.log4j.Logger;

import java.util.*;

public class DesktopRepository{
    private final List<Desktop> desktops;
    final private static Logger LOG = Logger.getLogger(Logger.class.getName());

    public DesktopRepository() {
        desktops = new LinkedList<>();
    }

    public void save(Desktop desktop) {
        desktops.add(desktop);
        LOG.info("New Desktop " + desktop.getTitle() + " saved");
    }

    public void saveAll(List<Desktop> desktops) {
        for (Desktop desktop : desktops) {
            save(desktop);
        }
    }

    public boolean update(Desktop desktop) {
        final Optional<Desktop> result = findById(desktop.getId());
        if (result.isEmpty()) {
            return false;
        }
        final Desktop originPhone = result.get();
        DesktopRepository.DesktopCopy.copy(desktop, originPhone);
        return true;
    }

    public boolean delete(String id) {
        final Iterator<Desktop> iterator = desktops.iterator();
        while (iterator.hasNext()) {
            final Desktop desktop = iterator.next();
            if (desktop.getId().equals(id)) {
                LOG.info("Desktop " + desktop.getTitle() + " deleted");
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    public List<Desktop> getAll() {
        if (desktops.isEmpty()) {
            return Collections.emptyList();
        }
        return desktops;
    }

    public Optional<Desktop> findById(String id) {
        Desktop result = null;
        for (Desktop desktop : desktops) {
            if (desktop.getId().equals(id)) {
                result = desktop;
            }
        }
        return Optional.ofNullable(result);
    }

    private static class DesktopCopy {
        private static void copy(final Desktop from, final Desktop to) {
            to.setCount(from.getCount());
            to.setPrice(from.getPrice());
            to.setTitle(from.getTitle());
        }
    }
}
