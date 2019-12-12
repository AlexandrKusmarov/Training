package main.java.model.cargo.domain;

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

}
