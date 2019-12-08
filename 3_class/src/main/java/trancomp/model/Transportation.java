package main.java.trancomp.model;

import java.time.LocalDateTime;

public class Transportation {
    private String routeName;
    private LocalDateTime sendingDateTime;
    private LocalDateTime arrivalDateTime;
    private Transport selectedTransport;
    private boolean isSuccessfullDelivery;

    public Transportation() {
    }

    public Transportation(String routeName, LocalDateTime sendingDateTime, LocalDateTime arrivalDateTime,
                          Transport selectedTransport, boolean isSuccessfullDelivery) {
        this.routeName = routeName;
        this.sendingDateTime = sendingDateTime;
        this.arrivalDateTime = arrivalDateTime;
        this.selectedTransport = selectedTransport;
        this.isSuccessfullDelivery = isSuccessfullDelivery;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public LocalDateTime getSendingDateTime() {
        return sendingDateTime;
    }

    public void setSendingDateTime(LocalDateTime sendingDateTime) {
        this.sendingDateTime = sendingDateTime;
    }

    public LocalDateTime getArrivalDateTime() {
        return arrivalDateTime;
    }

    public void setArrivalDateTime(LocalDateTime arrivalDateTime) {
        this.arrivalDateTime = arrivalDateTime;
    }

    public Transport getSelectedTransport() {
        return selectedTransport;
    }

    public void setSelectedTransport(Transport selectedTransport) {
        this.selectedTransport = selectedTransport;
    }

    public boolean isSuccessfullDelivery() {
        return isSuccessfullDelivery;
    }

    public void setSuccessfullDelivery(boolean successfullDelivery) {
        isSuccessfullDelivery = successfullDelivery;
    }
}
