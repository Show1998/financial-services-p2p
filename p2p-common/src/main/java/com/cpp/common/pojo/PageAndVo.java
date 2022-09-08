package com.cpp.common.pojo;

import java.io.Serializable;
import java.util.List;

public class PageAndVo <T> implements Serializable {
    private static final long serialVersionUID = -5830995304862056320L;
    private List<T> list;
    private Integer total;

    public PageAndVo() {
    }

    public PageAndVo(List<T> list, Integer total) {
        this.list = list;
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
