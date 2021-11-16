package by.itechart.vehicle_service.dao.repository.specification;

import static java.lang.String.format;
import static java.util.Objects.isNull;

import by.itechart.vehicle_service.dao.entity.Vehicle;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;

public class VehicleSpecification {


  public static Specification<Vehicle> numberContains(String numberTerm) {
    return isNull(numberTerm) ?
        emptySpec() :
        (vehicle, cq, cb) -> cb.like(vehicle.get("number"), format("%%%s%%", numberTerm));
  }

  public static Specification<Vehicle> available(Boolean isAvailable) {
    return isNull(isAvailable) ?
        emptySpec() :
        (vehicle, cq, cb) -> cb.equal(vehicle.get("isAvailable"), isAvailable);
  }

  public static Specification<Vehicle> containsFeatures(List<String> featureNames) {
    return isNull(featureNames) ?
        emptySpec() :
        (vehicle, cq, cb) -> {
          cq.groupBy(vehicle).having(cb.equal(cb.count(vehicle), featureNames.size()));

          return vehicle.join("features").get("name").in(featureNames);
        };
  }

  public static Specification<Vehicle> type(String typeName) {
    return isNull(typeName) ?
        emptySpec() :
        (vehicle, cq, cb) -> cb.equal(vehicle.join("type").get("name"), typeName);
  }

  private static Specification<Vehicle> emptySpec() {
    return (vehicle, cq, cb) -> null;
  }

}
