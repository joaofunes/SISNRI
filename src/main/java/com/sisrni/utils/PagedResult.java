package com.sisrni.utils;

import java.util.List;

public class PagedResult {

    private List list;
    private int counter;

    public PagedResult() {
        counter = 0;
    }

    public PagedResult(List list, int counter) {
        this.list = list;
        this.counter = counter;
    }

    @Override
    public String toString() {
        return "PagedDataInfo{" + "list=" + list + ", counter=" + counter + '}';
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}
