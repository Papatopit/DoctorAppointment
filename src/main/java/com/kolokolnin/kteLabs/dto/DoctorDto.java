package com.kolokolnin.kteLabs.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;



@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DoctorDto {

    @NotBlank
    @Length(min = 1, max = 20)
    private String firstName;

    @NotBlank
    @Length(min = 1, max = 20)
    private String lastName;
}
