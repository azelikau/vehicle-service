package by.itechart.vehicle_service.api.model.request;

import java.util.List;
import lombok.Data;

@Data
public class VehiclePostRequestDTO {

  private String number;
  private Integer vehicleTypeId;
  private List<Integer> vehicleFeatureIds;

}
