package com.higgsup.intern.ebshop.jpa.repo;

import com.higgsup.intern.ebshop.jpa.entity.Author;
import com.higgsup.intern.ebshop.jpa.entity.Ebook;
import com.higgsup.intern.ebshop.jpa.entity.Publisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EbookRepository extends CrudRepository<Ebook, Long> {
    Ebook findByIsbn(String isbn);

    @Query("select e from OrderDetails o join o.ebook e group by o.ebook order by sum(o.quantity) desc ")
    List<Ebook> findTop10BestSellings(Pageable pageable);

    @Query("select e.author from Ebook e where e.id = :id")
    Author findAuthorByEbookId(@Param("id") Long id);

    @Query("select e.publisher from Ebook e where e.id = :id")
    Publisher findPublisherByEbookId(@Param("id") Long id);

    @Query("select sum(o.quantity) from OrderDetails o join o.ebook e where e.id = :id")
    Integer copiesSold(@Param("id") Long id);
}
