package com.wjq.repository;

import com.wjq.domain.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

/**
 * <p>Description : bookshop
 * <p>Date : 2018/1/16 15:26
 * <p>@author : wjq
 */
public interface BookRepository extends JpaRepository<Book,Long> ,JpaSpecificationExecutor<Book>{
    //@EntityGraph(attributePaths = {"category","authors"})
    List<Book> findByName(String name);

    List<Book> findByNameAndCategoryName(String bookName,String categoryName);

    //jpql语句
    @Query("select b from Book b where b.name like ?1 and b.category.name  = ?2 order by b.name desc")
    Page<Book> findBooks(String bookName , String categoryName, Pageable pageable);

    @Modifying
    @Transactional
    @Query("update Book b set b.name=:name where b.id=:bookId")
    Integer updateBook(@Param("name") String name,@Param("bookId") Long id);

    @Modifying
    @Transactional
    @Query("update Book b set b.name=?1 where b.id=?2")
    void updateBook2(String name,Long id);

}
