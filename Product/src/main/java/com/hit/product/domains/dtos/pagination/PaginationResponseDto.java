package com.hit.product.domains.dtos.pagination;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PaginationResponseDto<T> {
    private Integer status;
    private String message;
    private PaginationDto<T> result;
}
