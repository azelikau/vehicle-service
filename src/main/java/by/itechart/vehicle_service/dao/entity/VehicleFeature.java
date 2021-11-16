package by.itechart.vehicle_service.dao.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
public class VehicleFeature {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String name;

  private String description;

  @ManyToMany(mappedBy = "features")
  @EqualsAndHashCode.Exclude
  private Set<Vehicle> vehicles = new HashSet<>();
}
