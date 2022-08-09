package com.example.karrotmarket.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.Month;

@Embeddable
@AllArgsConstructor
@Getter
@NoArgsConstructor
public class DealRequestTimeDetail {
    @NotBlank
    private Month month;
    @Min(1)
    @Max(31)
    @NotBlank
    private int day;

    @Min(0)
    @Max(23)
    @NotBlank
    private int hour;
    @Min(0)
    @Max(59)
    @NotBlank
    private int minute;
}
