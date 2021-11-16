package by.itechart.vehicle_service.service.impl;

import static java.lang.String.format;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import by.itechart.vehicle_service.api.model.request.VehiclePatchRequestDTO;
import by.itechart.vehicle_service.api.model.request.VehiclePostRequestDTO;
import by.itechart.vehicle_service.api.model.request.VehicleSearchParams;
import by.itechart.vehicle_service.api.model.response.VehicleResponseDTO;
import by.itechart.vehicle_service.dao.entity.Vehicle;
import by.itechart.vehicle_service.dao.entity.VehicleFeature;
import by.itechart.vehicle_service.dao.entity.VehicleType;
import by.itechart.vehicle_service.dao.repository.VehicleFeatureRepository;
import by.itechart.vehicle_service.dao.repository.VehicleRepository;
import by.itechart.vehicle_service.dao.repository.VehicleTypeRepository;
import by.itechart.vehicle_service.dao.repository.specification.VehicleSpecification;
import by.itechart.vehicle_service.mapper.VehicleMapper;
import by.itechart.vehicle_service.service.VehicleService;
import java.util.HashSet;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@Transactional
public class VehicleServiceImpl implements VehicleService {

  private final VehicleMapper vehicleMapper;
  private final VehicleTypeRepository vehicleTypeRepository;
  private final VehicleFeatureRepository vehicleFeatureRepository;
  private final VehicleRepository vehicleRepository;

  @Override
  public VehicleResponseDTO createVehicle(VehiclePostRequestDTO vehiclePostRequestDTO) {
    if (isNull(vehiclePostRequestDTO.getVehicleTypeId())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Vehicle type must not be null");
    }

    VehicleType vehicleType =
        vehicleTypeRepository.getById(vehiclePostRequestDTO.getVehicleTypeId());

    List<VehicleFeature> features =
        vehicleFeatureRepository.findAllById(vehiclePostRequestDTO.getVehicleFeatureIds());

    Vehicle vehicle = vehicleMapper.toVehicle(vehiclePostRequestDTO);

    vehicle.setAvailable(true);
    vehicle.setType(vehicleType);
    vehicle.setFeatures(new HashSet<>(features));

    return vehicleMapper.toVehicleResponse(vehicleRepository.save(vehicle));
  }

  @Override
  public VehicleResponseDTO updateVehicle(int vehicleId,
      VehiclePatchRequestDTO vehiclePatchRequestDTO) {

    Vehicle vehicle = vehicleRepository.findById(vehicleId)
        .orElseThrow(() ->
            new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                format("Vehicle with id %d has not been found", vehicleId)));

    vehicleMapper.mapVehicleProperties(vehiclePatchRequestDTO, vehicle);

    if (nonNull(vehiclePatchRequestDTO.getVehicleTypeId())) {
      VehicleType vehicleType =
          vehicleTypeRepository.getById(vehiclePatchRequestDTO.getVehicleTypeId());

      vehicle.setType(vehicleType);
    }

    if (nonNull(vehiclePatchRequestDTO.getVehicleFeatureIds())) {
      List<VehicleFeature> features =
          vehicleFeatureRepository.findAllById(vehiclePatchRequestDTO.getVehicleFeatureIds());

      vehicle.setFeatures(new HashSet<>(features));
    }

    return vehicleMapper.toVehicleResponse(vehicleRepository.save(vehicle));
  }

  @Override
  public Page<VehicleResponseDTO> getVehicles(VehicleSearchParams vehicleSearchParams) {
    Specification<Vehicle> vehicleSpec =
        Specification.where(VehicleSpecification.available(vehicleSearchParams.getAvailable()))
            .and(VehicleSpecification.type(vehicleSearchParams.getTypeName()))
            .and(VehicleSpecification.containsFeatures(vehicleSearchParams.getFeatureNames()))
            .and(VehicleSpecification.numberContains(vehicleSearchParams.getNumberTerm()));

    Page<Vehicle> vehiclePage = vehicleRepository.findAll(vehicleSpec,
        PageRequest.of(vehicleSearchParams.getPage(), vehicleSearchParams.getSize()));

    return vehiclePage.map(vehicleMapper::toVehicleResponse);
  }

  @Override
  public void deleteVehicle(Integer vehicleId) {
    vehicleRepository.deleteById(vehicleId);
  }
}
