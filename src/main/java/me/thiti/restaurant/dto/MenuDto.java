package me.thiti.restaurant.dto;

import java.util.UUID;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class MenuDto {
    
    private UUID id;

    @NotBlank
    private String name;

    @NotNull
    @Min(value = 0)
    private double price;

    @NotBlank
    private String category;
}
