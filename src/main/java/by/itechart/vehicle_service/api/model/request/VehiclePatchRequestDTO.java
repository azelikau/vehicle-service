package by.itechart.vehicle_service.api.model.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class VehiclePatchRequestDTO extends VehiclePostRequestDTO {

  private boolean available;

}
