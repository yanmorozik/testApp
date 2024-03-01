package com.morozik.app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateMyEntityDto {

    @NotNull(message = "Name not specified")
    private String name;

    @NotNull(message = "Value not specified")
    private Integer value;
}
