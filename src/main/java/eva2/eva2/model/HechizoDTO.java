package eva2.eva2.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HechizoDTO {
    @NotBlank(message = "El nombre no puede estar en blanco")
    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
    private String nombre;

    @NotBlank(message = "El atributo no puede estar en blanco")
    @Size(min = 3, max = 20, message = "El atributo debe tener entre 3 y 20 caracteres")
    private String atributo;
}
