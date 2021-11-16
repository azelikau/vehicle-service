package by.itechart.vehicle_service.mapper;

import by.itechart.vehicle_service.api.model.request.VehiclePatchRequestDTO;
import by.itechart.vehicle_service.api.model.request.VehiclePostRequestDTO;
import by.itechart.vehicle_service.api.model.response.VehicleResponseDTO;
import by.itechart.vehicle_service.dao.entity.Vehicle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper
public interface VehicleMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "type", ignore = true)
  @Mapping(target = "features", ignore = true)
  @Mapping(target = "available", ignore = true)
  Vehicle toVehicle(VehiclePostRequestDTO vehiclePostRequestDTO);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "type", ignore = true)
  @Mapping(target = "features", ignore = true)
  void mapVehicleProperties(VehiclePatchRequestDTO patchRequest, @MappingTarget Vehicle vehicle);

  VehicleResponseDTO toVehicleResponse(Vehicle vehicle);
}
