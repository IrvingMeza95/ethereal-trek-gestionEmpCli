package com.TeamCode.serviciousuarios.dtos;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmpleadoDTO extends PersonaDTO {
    private String cargo;
    private Double sueldo;
}
