package cargo.service;

import cargo.domain.Cargo;
import cargo.exception.unckecked.CargoDeleteConstraintViolationException;
import cargo.repo.CargoRepo;
import cargo.search.CargoSearchCondition;
import transportation.domain.Transportation;

import javax.swing.text.html.Option;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class CargoServiceImpl implements CargoService {

    private CargoRepo cargoRepo;

    public CargoServiceImpl(CargoRepo cargoRepo) {
        this.cargoRepo = cargoRepo;
    }

    @Override
    public void save(Cargo cargo) {
        cargoRepo.save(cargo);
    }

    @Override
    public Cargo findById(Long id) {
        Optional<Long> optionalLong = Optional.ofNullable(id);
        return optionalLong.map(aLong -> cargoRepo.findById(aLong)).orElse(null);
//    if (id != null) {
//      return cargoRepo.findById(id);
//    }
    }

    @Override
    public Cargo getByIdFetchingTransportations(Long id) {
        if (id != null) {
            return cargoRepo.getByIdFetchingTransportations(id);
        }
        return null;
    }

    @Override
    public List<Cargo> getAll() {
        return cargoRepo.getAll();
    }

    @Override
    public int countAll() {
        return this.cargoRepo.countAll();
    }

    @Override
    public List<Cargo> findByName(String name) {
        Cargo[] found = cargoRepo.findByName(name);
        return (found == null || found.length == 0) ? Collections.emptyList() : Arrays.asList(found);
    }

    @Override
    public boolean deleteById(Long id) {
        Cargo cargo = this.getByIdFetchingTransportations(id);

        if (cargo != null) {
            List<Transportation> transportations = cargo.getTransportations();
            boolean hasTransportations = transportations != null && transportations.size() > 0;
            if (hasTransportations) {
                throw new CargoDeleteConstraintViolationException(id);
            }

            return cargoRepo.deleteById(id);
        } else {
            return false;
        }
    }

    @Override
    public void printAll() {
        List<Cargo> allCargos = cargoRepo.getAll();
        allCargos.forEach(System.out::println);

//    for (Cargo cargo : allCargos) {
//      System.out.println(cargo);
//    }
    }

    @Override
    public boolean update(Cargo cargo) {
        Optional<Cargo> optionalCargo = Optional.ofNullable(cargo);
        if (optionalCargo.isPresent()) {
            return cargoRepo.update(cargo);
        }
//    if (cargo != null) {
//      return cargoRepo.update(cargo);
//    }

        return false;
    }

    @Override
    public List<Cargo> search(CargoSearchCondition cargoSearchCondition) {
        return cargoRepo.search(cargoSearchCondition);
    }
}
