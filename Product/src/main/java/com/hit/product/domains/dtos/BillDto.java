package com.hit.product.domains.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BillDto {

    private String fullName;

    private String email;

    private String phone;

    private String address;

    private String city;

    private String district;

    private String wards;

}
