package com.repository;

import com.model.TV;
import org.apache.log4j.Logger;

import java.util.*;

public class TVRepository {
    private final List<TV> tvs;
    final private static Logger LOG = Logger.getLogger(Logger.class.getName());

    public TVRepository() {
        tvs = new LinkedList<>();
    }

    public void save(TV tv) {
        tvs.add(tv);
        LOG.info("New TV " + tv.getTitle() + " saved");
    }

    public void saveAll(List<TV> desktops) {
        for (TV tv : tvs) {
            save(tv);
        }
    }

    public boolean update(TV tv) {
        final Optional<TV> result = findById(tv.getId());
        if (result.isEmpty()) {
            return false;
        }
        final TV originTV = result.get();
        TVRepository.TVCopy.copy(tv, originTV);
        return true;
    }

    public boolean delete(String id) {
        final Iterator<TV> iterator = tvs.iterator();
        while (iterator.hasNext()) {
            final TV tv = iterator.next();
            if (tv.getId().equals(id)) {
                LOG.info("TV " + tv.getTitle() + " deleted");
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    public List<TV> getAll() {
        if (tvs.isEmpty()) {
            return Collections.emptyList();
        }
        return tvs;
    }

    public Optional<TV> findById(String id) {
        TV result = null;
        for (TV tv : tvs) {
            if (tv.getId().equals(id)) {
                result = tv;
            }
        }
        return Optional.ofNullable(result);
    }

    private static class TVCopy {
        private static void copy(final TV from, final TV to) {
            to.setCount(from.getCount());
            to.setPrice(from.getPrice());
            to.setTitle(from.getTitle());
        }
    }
}