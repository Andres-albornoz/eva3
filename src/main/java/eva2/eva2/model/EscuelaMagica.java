package eva2.eva2.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name= "EscuelaMagica")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EscuelaMagica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable=false, length = 50)
    private String nombre;

    @Column(nullable=true)
    private String especialidad;

}