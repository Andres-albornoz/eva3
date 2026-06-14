package eva2.eva2.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name= "Grimorio")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Grimorio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable=false, length = 50)
    private String nombre;

    @Column(nullable=false)
    private int capacidad = 10;

}