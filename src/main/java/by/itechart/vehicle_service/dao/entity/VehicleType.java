package by.itechart.vehicle_service.dao.entity;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
public class VehicleType {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String name;

  private String code;

  private String description;

  @OneToMany(mappedBy = "type")
  @EqualsAndHashCode.Exclude
  private Set<Vehicle> vehicle;

}
