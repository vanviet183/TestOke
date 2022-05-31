//package com.hit.product.adapter.web.base;
//
//import com.hit.product.applications.constants.Common;
//import com.hit.product.domain.dtos.pagination.PaginateDto;
//import com.hit.product.domain.dtos.pagination.PaginationDto;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
//import org.springframework.stereotype.Component;
//
//@Component
//public class BasePagination<E, R extends JpaRepository<E, ?> & JpaSpecificationExecutor<E>> {
//
//    private R repository;
//
//    public BasePagination() {
//
//    }
//    public BasePagination(R repository) {
//        this.repository = repository;
//    }
//
//    public PaginateDto<E> paginate(Integer page, Integer perPage) {
//        if (page == null || page <= 0) {
//            page = 1;
//        }
//        if (perPage == null || perPage <= 0) {
//            perPage = Common.PAGING_DEFAULT_LIMIT;
//        }
//        Page<E> pageData = repository.findAll(PageRequest.of(page, perPage, Sort.by("createdAt").descending()));
//
//        PaginationDto.Pagination pagination = new PaginationDto.Pagination(page, perPage, pageData.getTotalPages(), pageData.getTotalElements());
//        return new PaginateDto<>(pageData, pagination);
//    }
//
//    public PaginateDto<E> paginate(Integer page, Integer perPage, GenericSpecification<E> specification) {
//        if (page == null || page <= 0) {
//            page = 1;
//        }
//        if (perPage == null || perPage <= 0) {
//            perPage = Common.PAGING_DEFAULT_LIMIT;
//        }
//        Page<E> pageData = repository.findAll(specification, PageRequest.of(page - 1, perPage, specification.getSort()));
//        PaginationDto.Pagination pagination = new PaginationDto.Pagination(page, perPage, pageData.getTotalPages(), pageData.getTotalElements());
//        return new PaginateDto<>(pageData, pagination);
//    }
//
//
//}
