package carrier.repo.impl;


import carrier.domain.Carrier;
import carrier.repo.CarrierRepo;
import storage.IdGenerator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import static storage.Storage.carrierCollection;

public class CarrierCollectionRepoImpl implements CarrierRepo {

  @Override
  public void save(Carrier carrier) {
    carrier.setId(IdGenerator.generateId());
    carrierCollection.add(carrier);
  }

  @Override
  public Carrier findById(Long id) {
    for (Carrier carrier : carrierCollection) {
      if (carrier.getId().equals(id)) {
        return carrier;
      }
    }

    return null;
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
