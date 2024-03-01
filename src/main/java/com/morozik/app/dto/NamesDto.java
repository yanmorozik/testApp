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
public class NamesDto {

    @NotNull(message = "First name not specified")
    private String firstName;

    @NotNull(message = "Second name not specified")
    private String secondName;
}
