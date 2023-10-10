package com.delacasa.courses.model;

import org.springframework.data.domain.Page;

import java.util.List;


public record MyPage<T>(List<T> items, int page, int limit, int pageTotal, long itemTotal, int nbItems) {

    public MyPage(Page<T> page) {
        this(page.getContent(), page.getNumber(), page.getSize(), page.getTotalPages(),
                page.getTotalElements(), page.getNumberOfElements());
    }

}



