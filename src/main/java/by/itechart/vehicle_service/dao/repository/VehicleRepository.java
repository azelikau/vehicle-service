package by.itechart.vehicle_service.dao.repository;

import by.itechart.vehicle_service.dao.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer>,
    JpaSpecificationExecutor<Vehicle> {

}
