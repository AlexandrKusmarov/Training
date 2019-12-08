package main.java.trancomp.model;

public class Transporter {
    private String name;
    private String[] countryOfDelivery;
    private Transport[] transportType;
    private boolean isValidSize;
    private double priceOfDelivery;
    private Transportation transportation;

    public Transporter() {
    }

    public Transporter(String name, String[] countryOfDelivery, Transport[] transportType,
                       boolean isValidSize, double priceOfDelivery) {
        this.name = name;
        this.countryOfDelivery = countryOfDelivery;
        this.transportType = transportType;
        this.isValidSize = isValidSize;
        this.priceOfDelivery = priceOfDelivery;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getCountryOfDelivery() {
        return countryOfDelivery;
    }

    public void setCountryOfDelivery(String[] countryOfDelivery) {
        this.countryOfDelivery = countryOfDelivery;
    }

    public Transport[] getTransportType() {
        return transportType;
    }

    public void setTransportType(Transport[] transportType) {
        this.transportType = transportType;
    }

    public boolean isValidSize() {
        return isValidSize;
    }

    public void setValidSize(boolean validSize) {
        isValidSize = validSize;
    }

    public double getPriceOfDelivery() {
        return priceOfDelivery;
    }

    public void setPriceOfDelivery(double priceOfDelivery) {
        this.priceOfDelivery = priceOfDelivery;
    }
}
