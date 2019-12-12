package main.java.model.cargo.domain;

public class UnlimitedShelfLife extends Cargo {
    private boolean isComposite;
    private boolean fragility;

    public UnlimitedShelfLife(boolean isComposite, boolean fragility) {
        this.isComposite = isComposite;
        this.fragility = fragility;
    }

    public boolean isComposite() {
        return isComposite;
    }

    public void setComposite(boolean composite) {
        isComposite = composite;
    }

    public boolean isFragility() {
        return fragility;
    }

    public void setFragility(boolean fragility) {
        this.fragility = fragility;
    }
}
