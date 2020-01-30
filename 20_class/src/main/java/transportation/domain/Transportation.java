package transportation.domain;

import cargo.domain.Cargo;
import carrier.domain.Carrier;
import common.business.domain.BaseEntity;

import java.time.LocalDateTime;

public class Transportation extends BaseEntity {

  private Cargo cargo;
  private Carrier carrier;
  private String description;
  private String billTo;
  private LocalDateTime transportationBeginLocalDateTime;

  public LocalDateTime getTransportationBeginLocalDateTime() {
    return transportationBeginLocalDateTime;
  }

  public void setTransportationBeginLocalDateTime(LocalDateTime transportationBeginLocalDateTime) {
    this.transportationBeginLocalDateTime = transportationBeginLocalDateTime;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getBillTo() {
    return billTo;
  }

  public void setBillTo(String billTo) {
    this.billTo = billTo;
  }

  public Cargo getCargo() {
    return cargo;
  }

  public void setCargo(Cargo cargo) {
    this.cargo = cargo;
  }

  public Carrier getCarrier() {
    return carrier;
  }

  public void setCarrier(Carrier carrier) {
    this.carrier = carrier;
  }

  @Override
  public String toString() {
    return "Transportation{" +
        "description='" + description + '\'' +
        ", Carrier='" + carrier.getName() + '\'' +
        ", Cargo=" + cargo.getName() +
        '}';
  }
}
