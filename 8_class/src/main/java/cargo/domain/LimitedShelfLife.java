package main.java.cargo.domain;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

public class LimitedShelfLife extends Cargo {
    private Date produced;
    private Date expires;

    public LimitedShelfLife(Date produced, Date expires) {
        this.produced = produced;
        this.expires = expires;
    }

    public Date getProduced() {
        return produced;
    }

    public void setProduced(Date produced) {
        this.produced = produced;
    }

    public Date getExpires() {
        return expires;
    }

    public void setExpires(Date expires) {
        this.expires = expires;
    }

    @Override
    public String toString() {
        return "Cargo =>> LimitedShelfLife{" +
                "id=" + super.getId() +
                ", name='" + super.getName() +
                ", weight=" + super.getWeight() +
                ", cargoType=" + super.getCargoType() +
                ", transportations=" + Arrays.toString(super.getTransportations()) +
                "produced=" + produced +
                ", expires=" + expires +
                '}';
    }

    @Override
    public int compare(Cargo o1, Cargo o2) {
        return -1 * o1.getName().compareTo(o2.getName());
    }

}
