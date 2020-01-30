package carrier.repo.impl;


import static common.business.repo.CommonRepoHelper.findEntityIndexInArrayStorageById;
import static storage.Storage.carrierArray;
import static storage.Storage.carrierIndex;

import cargo.domain.Cargo;
import carrier.domain.Carrier;
import carrier.repo.CarrierRepo;
import common.solutions.utils.ArrayUtils;
import storage.IdGenerator;

import java.util.*;

public class CarrierArrayRepoImpl implements CarrierRepo {

    private static final Carrier[] EMPTY_CARRIER_ARRAY = new Carrier[0];

    @Override
    public void save(Carrier carrier) {
        if (carrierIndex == carrierArray.length) {
            Carrier[] newCarriers = new Carrier[carrierArray.length * 2];
            ArrayUtils.copyArray(carrierArray, newCarriers);
            carrierArray = newCarriers;
        }

        carrier.setId(IdGenerator.generateId());
        carrierArray[carrierIndex] = carrier;
        carrierIndex++;
    }

    @Override
    public boolean update(Carrier carrier) {
        return true;
    }

    @Override
    public Carrier getByIdFetchingTransportations(long id) {
        return findById(id);
    }

    @Override
    public Carrier[] findByName(String name) {
        Carrier[] searchResultWithNullableElems = getByNameIncludingNullElements(name);
        if (searchResultWithNullableElems == null || searchResultWithNullableElems.length == 0) {
            return EMPTY_CARRIER_ARRAY;
        } else {
            return excludeNullableElementsFromArray(searchResultWithNullableElems);
        }
    }

    private Carrier[] getByNameIncludingNullElements(String name) {
        Carrier[] result = new Carrier[carrierArray.length];

        int curIndex = 0;
        for (Carrier carrier : carrierArray) {
            if (carrier != null && Objects.equals(carrier.getName(), name)) {
                result[curIndex++] = carrier;
            }
        }

        return result;
    }


    private Carrier[] excludeNullableElementsFromArray(Carrier[] carriers) {
        int sizeOfArrWithNotNullElems = 0;

        for (Carrier carrier : carriers) {
            if (carrier != null) {
                sizeOfArrWithNotNullElems++;
            }
        }

        if (sizeOfArrWithNotNullElems == 0) {
            return EMPTY_CARRIER_ARRAY;
        } else {
            Carrier[] result = new Carrier[sizeOfArrWithNotNullElems];
            int index = 0;
            for (Carrier carrier : carriers) {
                if (carrier != null) {
                    result[index++] = carrier;
                }
            }
            return result;
        }
    }

    @Override
    public List<Carrier> getAll() {
        Carrier[] carriers = excludeNullableElementsFromArray(carrierArray);
        return carriers.length == 0 ? Collections.emptyList() : Arrays.asList(carrierArray);
    }

    @Override
    public int countAll() {
        return this.getAll().size();
    }

    @Override
    public Optional<Cargo> findById(Long id) {
        Optional<Long> optionalId = Optional.ofNullable(id);
        for (Carrier carrier : carrierArray) {
            if (Objects.nonNull(carrier) && optionalId.isPresent()
                    && carrier.getId().equals(optionalId.get())) {
                return carrier;
            }
        }
        return null;
//        for (Carrier carrier : carrierArray) {
//            if (carrier != null && carrier.getId().equals(id)) {
//                return carrier;
//            }
//        }
    }

    @Override
    public boolean deleteById(Long id) {
        Integer indexToDelete = findEntityIndexInArrayStorageById(carrierArray, id);

        if (indexToDelete == null) {
            return false;
        } else {
            ArrayUtils.removeElement(carrierArray, indexToDelete);
            return true;
        }
    }
}
