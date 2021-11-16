package by.itechart.vehicle_service.api.model.response;

import java.util.List;
import lombok.Data;

@Data
public class VehicleResponseDTO {

  private int id;
  private String number;
  private boolean available;
  private VehicleTypeResponse type;
  private List<VehicleFeatureResponse> features;

}
