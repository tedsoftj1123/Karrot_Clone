package com.example.karrotmarket.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

@Embeddable
@NoArgsConstructor
@Getter
@AllArgsConstructor
public class DealRequestLocationDetail {
    @NotBlank
    private String dong;
    @NotBlank
    private String detail;
}
