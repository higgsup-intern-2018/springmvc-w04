package com.higgsup.intern.ebshop.service.impl;

import com.higgsup.intern.ebshop.dto.AuthorDTO;
import com.higgsup.intern.ebshop.dto.CustomerDTO;
import com.higgsup.intern.ebshop.dto.EbookInfoDTO;
import com.higgsup.intern.ebshop.dto.PublisherDTO;
import com.higgsup.intern.ebshop.jpa.entity.Author;
import com.higgsup.intern.ebshop.jpa.entity.Customer;
import com.higgsup.intern.ebshop.jpa.entity.Ebook;
import com.higgsup.intern.ebshop.jpa.entity.Publisher;
import com.higgsup.intern.ebshop.jpa.repo.AuthorRepository;
import com.higgsup.intern.ebshop.jpa.repo.CustomerRepository;
import com.higgsup.intern.ebshop.jpa.repo.EbookRepository;
import com.higgsup.intern.ebshop.jpa.repo.PublisherRepository;
import com.higgsup.intern.ebshop.service.IStatisticsService;
import ma.glasnost.orika.MapperFacade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatisticsService implements IStatisticsService {
    private final EbookRepository ebookRepository;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;
    private final CustomerRepository customerRepository;
    private final MapperFacade mapper;

    public StatisticsService(EbookRepository ebookRepository, AuthorRepository authorRepository,
                             PublisherRepository publisherRepository, CustomerRepository customerRepository,
                             MapperFacade mapper) {
        this.ebookRepository = ebookRepository;
        this.authorRepository = authorRepository;
        this.publisherRepository = publisherRepository;
        this.customerRepository = customerRepository;
        this.mapper = mapper;
    }

    @Override
    public List<EbookInfoDTO> findTop10BestSellingEbooks() {
        List<Ebook> top10EbooksBestSellings = ebookRepository.findTop10BestSellings(new PageRequest(0, 10));
        List<EbookInfoDTO> ebookInfoDTOs = new ArrayList<>();
        for(Ebook e : top10EbooksBestSellings)
        {
            EbookInfoDTO ebookInfoDTO = new EbookInfoDTO();
            ebookInfoDTO.setTitle(e.getTitle());
            ebookInfoDTO.setAuthorFirstName(e.getAuthor().getFirstName());
            ebookInfoDTO.setAuthorLastName(e.getAuthor().getLastName());
            ebookInfoDTO.setPublisher(e.getPublisher().getName());
            ebookInfoDTO.setPrice(e.getPrice());
            ebookInfoDTO.setCopiesSold(ebookRepository.copiesSold(e.getId()));
            ebookInfoDTOs.add(ebookInfoDTO);
        }
        return ebookInfoDTOs;
    }

    @Override
    public List<CustomerDTO> findTop5HighestPrice() {
        List<Customer> customers = customerRepository.getTop5HighestPrice(new PageRequest(0, 5));
        List<CustomerDTO> customerDTOs = mapper.mapAsList(customers, CustomerDTO.class);
        for(CustomerDTO customerDTO : customerDTOs)
        {
            customerDTO.setQuantity(customerRepository.quantity(customerDTO.getId()));
            customerDTO.setTotalPriceOfOrders(customerRepository.totalPrice(customerDTO.getId()));
        }
        return customerDTOs;
    }

    @Override
    public List<CustomerDTO> findTop5MostBuyCustomers() {
        List<Customer> customers = customerRepository.getTop5MostBuying(new PageRequest(0, 5));
        List<CustomerDTO> customerDTOs = mapper.mapAsList(customers, CustomerDTO.class);
        for(CustomerDTO customerDTO : customerDTOs)
        {
            customerDTO.setQuantity(customerRepository.quantity(customerDTO.getId()));
            customerDTO.setTotalPriceOfOrders(customerRepository.totalPrice(customerDTO.getId()));
        }
        return customerDTOs;
    }

    @Override
    public List<PublisherDTO> findTop5BestSellingPublisher() {
        List<Publisher> publishers = publisherRepository.findTop5PublisherSelling(new PageRequest(0, 5));
        List<PublisherDTO> publisherDTOs = mapper.mapAsList(publishers, PublisherDTO.class);
        for(PublisherDTO publisherDTO : publisherDTOs)
        {
            publisherDTO.setCopiesSold(authorRepository.totalSold(publisherDTO.getId()));
        }
        return publisherDTOs;
    }

    @Override
    public List<AuthorDTO> findTop5BestSellingAuthors() {
        List<Author> authors = authorRepository.findTop5BestSelling(new PageRequest(0, 5));
        List<AuthorDTO> authorDTOs = mapper.mapAsList(authors, AuthorDTO.class);
        for(AuthorDTO authorDTO : authorDTOs)
        {
            authorDTO.setCopiesSold(authorRepository.totalSold(authorDTO.getId()));
        }
        return authorDTOs;
    }
}
