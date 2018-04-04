package com.wjq;

import com.wjq.domain.Author;
import com.wjq.domain.Book;
import com.wjq.repository.AuthorRepository;
import com.wjq.repository.BookRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;

import javax.persistence.criteria.*;
import java.util.List;

/**
 * <p>Description : bookshop
 * <p>Date : 2018/1/16 15:30
 * <p>@author : wjq
 */
public class RepositoryTest extends BookShopApplicationTests {
    @Autowired
    BookRepository bookRepository;

    @Test
    public void test() {
//        bookRepository.findByName("战争与和平");
//        bookRepository.save(new Book());
//        bookRepository.findOne(1L);
//        bookRepository.findAll(new Sort(Sort.Direction.ASC,"id","name"));//按id name 升序
//        bookRepository.findAll(new Sort(new Sort.Order("id"),new Sort.Order(Sort.Direction.DESC,"name")));//id升序，name降序
//        Pageable pageable = new PageRequest(0,1,new Sort(new Sort.Order("id"),new Sort.Order(Sort.Direction.DESC,"name")));
//        Page<Book> all = bookRepository.findAll(pageable);
//        System.out.println(all.getContent().size());//1
//        System.out.println(all.getTotalElements());//总的2
        Book book = new Book();
        book.setName("战争与和平");
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example<Book> example = Example.of(book, exampleMatcher);
        Pageable pageable = new PageRequest(0, 1, new Sort(new Sort.Order("id"), new Sort.Order(Sort.Direction.DESC, "name")));
        Page<Book> page = bookRepository.findAll(example, pageable);

    }

    @Test
    public void test2() {
//        Pageable pageable = new PageRequest(0, 1, new Sort(new Sort.Order("id"), new Sort.Order(Sort.Direction.DESC, "name")));
//
//        //List<Book> list = bookRepository.findByNameAndCategoryName("战争与和平","文学");
//        Page<Book> page = bookRepository.findBooks("xxx","yy",pageable);
        Integer id = bookRepository.updateBook("xxxx", 2L);
        System.out.println(id);
    }


    @Test
    public void test3() {
        Specification<Book> spec = new Specification<Book>() {
            @Override
            public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate p1 = cb.equal(root.get("name"), "xxxx");
                Predicate p2 = cb.equal(root.get("category").get("name"), "文化");
                Predicate p3 = cb.and(p1, p2);
                //用root对象指定抓取的方式
                root.fetch("category", JoinType.LEFT);
                return p3;
            }
        };
        bookRepository.findAll(spec);
    }

    @Test
    public void test4() {
        //打开事务
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionAttribute());
        Book book = bookRepository.findOne(2L);//查询出来的对象，放到持久化上下文
        book.setName("哈哈");
//        Book save = bookRepository.save(book);//这个update不需要，事务提交，持久化上下文会自动脏检查，发现与数据库不一样会自动更新
//        Book save2 = bookRepository.saveAndFlush(book);//flush立刻同步，持久化状态与数据库立刻同步，但是没有被commit，提交事务才会真正起作用
        //提交事务
        transactionManager.commit(status);
    }
    @Autowired
    private PlatformTransactionManager transactionManager;
    @Test
    public void test5() {
//        System.out.println("第一次");
//        Book book = bookRepository.findOne(1L);
//        System.out.println("第二次");
//        Book book2 = bookRepository.findOne(1L);
//        book.setName("哈哈");
//        bookRepository.save(book);//没有被update
        Book book = bookRepository.findOne(1L);
        book.setName("xxx");
        bookRepository.saveAndFlush(book);
    }


    @Test
    public void test6(){
        List<Book> book = bookRepository.findByName("哈哈");
        System.out.println(book.get(0).getCategory().getName());
//        Book book1 = bookRepository.findOne(1L);
//        System.out.println(book1.getCategory().getName());
    }

    @Test
    public void test7(){
//        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionAttribute());
//        PrintBook book = new PrintBook();
//        book.setPrintDate(new Date());
//        book.setName("Pbook");
//        bookRepository.save(book);
//        Ebook ebook = new Ebook();
//        ebook.setName("Ebook");
//        bookRepository.save(ebook);
//        transactionManager.commit(status);

        List<Book> books = bookRepository.findAll();
        books.stream().forEach(book1 -> System.out.println(book1.getClass().getSimpleName()));
    }
    @Autowired
    private AuthorRepository authorRepository;
    @Test
    public void test8(){
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionAttribute());
        Author author = new Author();
        authorRepository.saveAndFlush(author);
        transactionManager.commit(status);
    }
}
