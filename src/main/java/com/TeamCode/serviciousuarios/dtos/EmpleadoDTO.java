package com.TeamCode.serviciousuarios.dtos;

import com.TeamCode.serviciousuarios.enums.Cargo;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmpleadoDTO extends PersonaDTO {
    private Cargo cargo;
    private Double sueldo;
}
