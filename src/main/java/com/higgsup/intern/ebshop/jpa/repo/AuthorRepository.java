package com.higgsup.intern.ebshop.jpa.repo;

import com.higgsup.intern.ebshop.dto.AuthorDTO;
import com.higgsup.intern.ebshop.jpa.entity.Author;
import com.higgsup.intern.ebshop.jpa.entity.Ebook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {

    @Query("select count(a) from Ebook e join e.author a where a.id = :id")
    Integer countEbooksOfAuthor(@Param("id") Long id);

    @Query("select e from OrderDetails o join o.ebook e join e.author a where a.id = :id " +
            "group by o.ebook order by sum(o.quantity) desc ")
    List<Ebook> findTop3BestSelling(@Param("id") Long id, Pageable pageable);

    @Query("select a from OrderDetails o join o.ebook e join e.author a " +
            "group by a.id order by sum(o.quantity) desc")
    List<Author> findTop5BestSelling(Pageable pageable);

    @Query("select sum(o.quantity) from OrderDetails o join o.ebook e join e.author a where a.id = :id")
    Integer totalSold(@Param("id") Long id);
}
