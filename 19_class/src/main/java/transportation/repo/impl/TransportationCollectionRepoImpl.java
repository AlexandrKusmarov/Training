package transportation.repo.impl;


import static storage.Storage.transportationCollection;

import storage.IdGenerator;
import transportation.domain.Transportation;
import transportation.repo.TransportationRepo;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class TransportationCollectionRepoImpl implements TransportationRepo {

    @Override
    public void save(Transportation transportation) {
        transportation.setId(IdGenerator.generateId());
        transportationCollection.add(transportation);
    }

    @Override
    public Transportation findById(Long id) {
        Optional<Long> optionalId = Optional.ofNullable(id);
        for (Transportation transportation : transportationCollection) {
            if (optionalId.isPresent() && Objects.nonNull(transportation)
                    && transportation.getId().equals(optionalId.get())) {
                return transportation;
            }
        }
//    for (Transportation transportation : transportationCollection) {
//      if (transportation.getId().equals(id)) {
//        return transportation;
//      }
//    }

        return null;
    }

    @Override
    public List<Transportation> getAll() {
        return transportationCollection;
    }

    @Override
    public boolean update(Transportation transportation) {
        return true;
    }

    @Override
    public boolean deleteById(Long id) {
        boolean deleted = false;

        Iterator<Transportation> iter = transportationCollection.iterator();
        while (iter.hasNext()) {
            if (iter.next().getId().equals(id)) {
                iter.remove();
                deleted = true;
                break;
            }
        }
        return deleted;
    }

    @Override
    public int countAll() {
        return transportationCollection.size();
    }
}
