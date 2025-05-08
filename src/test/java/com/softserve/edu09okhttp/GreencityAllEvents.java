package com.softserve.edu09okhttp;

import java.util.List;

class Page {
    private int id;
    private String title;

    public Page(int id, String title) {
        this.id = id;
        this.title = title;
    }

    @Override
    public String toString() {
        return "   Page{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}


public class GreencityAllEvents {
    private List<Page> page;
    private int totalElements;
    private int currentPage;
    private int totalPages;
    private int number;
    private boolean hasPrevious;
    private boolean hasNext;
    private boolean first;
    private boolean last;

    public GreencityAllEvents(List<Page> page, int totalElements, int currentPage,
                              int totalPages, int number, boolean hasPrevious,
                              boolean hasNext, boolean first, boolean last) {
        this.page = page;
        this.totalElements = totalElements;
        this.currentPage = currentPage;
        this.totalPages = totalPages;
        this.number = number;
        this.hasPrevious = hasPrevious;
        this.hasNext = hasNext;
        this.first = first;
        this.last = last;
    }

    @Override
    public String toString() {
        return "GreencityAllEvents{" +
                "page=" + page +
                ", \ntotalElements=" + totalElements +
                ", currentPage=" + currentPage +
                ", totalPages=" + totalPages +
                ", number=" + number +
                ", hasPrevious=" + hasPrevious +
                ", hasNext=" + hasNext +
                ", first=" + first +
                ", last=" + last +
                '}';
    }
}