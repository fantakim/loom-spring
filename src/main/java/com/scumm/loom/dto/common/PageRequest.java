package com.scumm.loom.dto.common;

import org.springframework.data.domain.Sort;

public class PageRequest {

    private int page = 1;
    private int size = 20;
    private Sort.Direction direction = Sort.Direction.ASC;

    public void setPage(int page) {
        this.page = page <= 0 ? 1 : page;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setDirection(Sort.Direction direction) {
        this.direction = direction;
    }

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }

    public Sort.Direction getDirection() {
        return direction;
    }

    public org.springframework.data.domain.PageRequest of() {
        return org.springframework.data.domain.PageRequest.of(page - 1, size, direction, "id");
    }
}
