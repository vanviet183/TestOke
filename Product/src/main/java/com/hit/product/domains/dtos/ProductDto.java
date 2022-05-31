package com.hit.product.domains.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private String title;

    private String shortDescription;

    private String longDescription;

    private Double price;

    private String brand;

    private String type;

    private String gender;

}
