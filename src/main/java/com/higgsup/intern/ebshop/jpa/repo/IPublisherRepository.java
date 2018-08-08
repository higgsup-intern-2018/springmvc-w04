package com.higgsup.intern.ebshop.jpa.repo;

import com.higgsup.intern.ebshop.jpa.entity.Ebook;
import com.higgsup.intern.ebshop.jpa.entity.Publisher;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPublisherRepository extends CrudRepository<Publisher,Long> {

    @Query("select count(p) from Ebook e join e.publisher p where p.id = :id")
    Integer countEbooksOfPublisher(@Param("id") Long id);

    @Query("select e from OrderDetails o join o.ebook e join e.publisher p where p.id = :id " +
            "group by o.ebook order by sum(o.quantity) desc")
    List<Ebook> top5BookOfPublisher(@Param("id") Long id, Pageable pageable);

    @Query(" select p from OrderDetails o join o.ebook e join e.publisher p" +
            " group by p.id order by sum(o.quantity) DESC ")
    List<Publisher> findTop5BestSellingPublishers(Pageable pageable);

    @Query("select sum(o.quantity) from OrderDetails o join o.ebook e join e.publisher p where p.id = :id")
    Integer sumEbooksOfPublisher(@Param("id") Long id);
}
