package by.itechart.vehicle_service.api.controller;

import by.itechart.vehicle_service.api.model.request.VehiclePatchRequestDTO;
import by.itechart.vehicle_service.api.model.request.VehiclePostRequestDTO;
import by.itechart.vehicle_service.api.model.request.VehicleSearchParams;
import by.itechart.vehicle_service.api.model.response.VehicleResponseDTO;
import by.itechart.vehicle_service.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vehicle")
@RequiredArgsConstructor
public class VehicleController {

  private final VehicleService vehicleService;

  @PostMapping
  public VehicleResponseDTO createVehicle(
      @RequestBody VehiclePostRequestDTO vehiclePostRequestDTO) {
    return vehicleService.createVehicle(vehiclePostRequestDTO);
  }

  @PatchMapping("/{vehicleId}")
  public VehicleResponseDTO patchVehicle(@PathVariable("vehicleId") Integer vehicleId,
      @RequestBody VehiclePatchRequestDTO vehiclePatchRequestDTO) {
    return vehicleService.updateVehicle(vehicleId, vehiclePatchRequestDTO);
  }

  @DeleteMapping("/{vehicleId}")
  public void deleteVehicle(@PathVariable("vehicleId") Integer vehicleId) {
    vehicleService.deleteVehicle(vehicleId);
  }

  @GetMapping
  public Page<VehicleResponseDTO> getVehicles(VehicleSearchParams vehicleSearchParams) {

    return vehicleService.getVehicles(vehicleSearchParams);
  }

}
