package eva2.eva2.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name= "Hechizo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hechizo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable=false, length = 50)
    private String nombre;

    @Column(nullable=false)
    private String atributo;

    @Column(nullable=true)
    private int nivel = 0;

    @Column(nullable=false)
    private boolean enPos = false;

}
