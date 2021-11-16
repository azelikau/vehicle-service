package by.itechart.vehicle_service.api.model.request;

import java.util.List;
import lombok.Data;

@Data
public class VehicleSearchParams {

  private Integer page = 0;
  private Integer size = 25;
  private String numberTerm;
  private String typeName;
  private Boolean available;
  private List<String> featureNames;

}
