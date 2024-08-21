package com.example.demo.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class PageUtils {
    public static Pageable pageOf(int page, int size) {
        return PageRequest.of(page - 1, size);
    }
}
