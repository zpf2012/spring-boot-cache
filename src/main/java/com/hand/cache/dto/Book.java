package com.hand.cache.dto;

import java.math.BigDecimal;

/**
 * @author Created by zhangpengfei on 2018/9/14 11:41.
 */
public class Book {
    private Long bookId;
    private String bookName;
    private BigDecimal bookPrice;

    public Long getBookId() {
        return bookId;
    }

    public Book setBookId(Long bookId) {
        this.bookId = bookId;
        return this;
    }

    public String getBookName() {
        return bookName;
    }

    public Book setBookName(String bookName) {
        this.bookName = bookName;
        return this;
    }

    public BigDecimal getBookPrice() {
        return bookPrice;
    }

    public Book setBookPrice(BigDecimal bookPrice) {
        this.bookPrice = bookPrice;
        return this;
    }
}
