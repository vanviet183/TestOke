package com.hit.product.adapter.web.base;

import com.hit.product.applications.constants.Common;
import com.hit.product.domains.dtos.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseController<T> {
    public ResponseEntity<?> resSuccess(T data) {
        Map<String, T> map = new HashMap<>();
        map.put("data", data);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseDto<>(HttpStatus.OK.value(), Common.RESPONSE_MESSAGE_SUCCESS, map)
        );
    }

    public ResponseEntity<?> resListSuccess(List<?> data) {
        Map<String, List<?>> map = new HashMap<>();
        map.put("data", data);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseDto<>(HttpStatus.OK.value(), Common.RESPONSE_MESSAGE_SUCCESS, map)
        );
    }

//    public ResponseEntity<?> resPagination(PaginateDto<?> paginateDto) {
//        PaginationDto<List<?>> paginationDto = new PaginationDto<>(
//                paginateDto.getPageData().getContent(),
//                paginateDto.getPagination()
//        );
//        return ResponseEntity.status(HttpStatus.OK).body(
//                new PaginationResponseDto<>(HttpStatus.OK.value(), Common.RESPONSE_MESSAGE_SUCCESS, paginationDto)
//        );
//    }
}
