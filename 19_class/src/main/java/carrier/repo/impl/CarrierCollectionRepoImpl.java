package carrier.repo.impl;


import static storage.Storage.carrierCollection;

import cargo.domain.Cargo;
import carrier.domain.Carrier;
import carrier.repo.CarrierRepo;
import storage.IdGenerator;

import java.util.*;

public class CarrierCollectionRepoImpl implements CarrierRepo {

    @Override
    public void save(Carrier carrier) {
        carrier.setId(IdGenerator.generateId());
        carrierCollection.add(carrier);
    }

    @Override
    public Optional<Cargo> findById(Long id) {
        Optional<Long> optionalId = Optional.ofNullable(id);
        for (Carrier carrier : carrierCollection) {
            if (optionalId.isPresent() && carrier.getId().equals(optionalId.get())) {
                return carrier;
            }
        }
        return null;
//    for (Carrier carrier : carrierCollection) {
//      if (carrier.getId().equals(id)) {
//        return carrier;
//      }
//    }
    }

    @Override
    public Carrier getByIdFetchingTransportations(long id) {
        return findById(id);
    }

    @Override
    public Carrier[] findByName(String name) {
        List<Carrier> result = new ArrayList<>();

        for (Carrier carrier : carrierCollection) {
            if (Objects.equals(carrier.getName(), name)) {
                result.add(carrier);
            }
        }

        return result.toArray(new Carrier[0]);
    }

    @Override
    public boolean deleteById(Long id) {
        Iterator<Carrier> iter = carrierCollection.iterator();

        boolean removed = false;
        while (iter.hasNext()) {
            if (iter.next().getId().equals(id)) {
                iter.remove();
                removed = true;
                break;
            }
        }

        return removed;
    }

    @Override
    public List<Carrier> getAll() {
        return carrierCollection;
    }

    @Override
    public int countAll() {
        return carrierCollection.size();
    }

    @Override
    public boolean update(Carrier carrier) {
        return true;
    }

}
