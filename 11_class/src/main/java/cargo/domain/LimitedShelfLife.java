package main.java.cargo.domain;

import java.util.Date;

public class LimitedShelfLife<T extends Cargo> extends Cargo {
    private Date produced;
    private Date expires;
    private T type;

    public LimitedShelfLife(T type) {
        this.type = type;
    }

    public LimitedShelfLife(Date produced, Date expires) {
        this.produced = produced;
        this.expires = expires;
    }

    public T getType() {
        return type;
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
                ", transportations=" + super.getTransportations() +
                "produced=" + produced +
                ", expires=" + expires +
                '}';
    }
}
