package by.itechart.vehicle_service.dao.entity;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Data
public class Vehicle {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String number;

  @Column(name = "available")
  private boolean isAvailable;

  @ManyToOne
  @Fetch(FetchMode.JOIN)
  @JoinColumn(name = "vehicle_type_id")
  private VehicleType type;

  @ManyToMany
  @JoinTable(name = "vehicle_to_vehicle_feature",
      joinColumns = @JoinColumn(name = "vehicle_id"),
      inverseJoinColumns = @JoinColumn(name = "vehicle_feature_id"))
  @Fetch(FetchMode.SUBSELECT)
  private Set<VehicleFeature> features;
}
