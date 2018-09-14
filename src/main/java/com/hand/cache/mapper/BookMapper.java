package com.hand.cache.mapper;

import com.hand.cache.dto.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Created by zhangpengfei on 2018/9/14 11:42.
 */
@Repository
@Mapper
public interface BookMapper {

    @Select("select book_id, book_name, book_price from book")
    List<Book> getBooks();
}
