package by.itechart.vehicle_service.service;

import by.itechart.vehicle_service.api.model.request.VehiclePatchRequestDTO;
import by.itechart.vehicle_service.api.model.request.VehiclePostRequestDTO;
import by.itechart.vehicle_service.api.model.request.VehicleSearchParams;
import by.itechart.vehicle_service.api.model.response.VehicleResponseDTO;
import org.springframework.data.domain.Page;

public interface VehicleService {

  VehicleResponseDTO createVehicle(VehiclePostRequestDTO vehiclePostRequestDTO);

  VehicleResponseDTO updateVehicle(int vehicleId, VehiclePatchRequestDTO vehiclePatchRequestDTO);

  Page<VehicleResponseDTO> getVehicles(VehicleSearchParams vehicleSearchParams);

  void deleteVehicle(Integer vehicleId);
}
