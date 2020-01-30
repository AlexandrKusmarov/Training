package cargo.domain;

import java.time.LocalDateTime;

public class FoodCargo extends Cargo {

  private LocalDateTime expirationLocalDateTime;
  private int storeTemperature;

  @Override
  public CargoType getCargoType() {
    return CargoType.FOOD;
  }

  public LocalDateTime getExpirationLocalDateTime() {
    return expirationLocalDateTime;
  }

  public void setExpirationLocalDateTime(LocalDateTime expirationLocalDateTime) {
    this.expirationLocalDateTime = expirationLocalDateTime;
  }

  public int getStoreTemperature() {
    return storeTemperature;
  }

  public void setStoreTemperature(int storeTemperature) {
    this.storeTemperature = storeTemperature;
  }
}
