package com.higgsup.intern.ebshop.service.impl;

import com.higgsup.intern.ebshop.dto.AuthorDTO;
import com.higgsup.intern.ebshop.dto.CustomerDTO;
import com.higgsup.intern.ebshop.dto.ItemInfoDTO;
import com.higgsup.intern.ebshop.dto.PublisherDTO;
import com.higgsup.intern.ebshop.jpa.entity.Ebook;
import com.higgsup.intern.ebshop.jpa.repo.IEbookRepository;
import com.higgsup.intern.ebshop.service.IStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

public class StatisticsService implements IStatisticsService {
    private final IEbookRepository iEbookRepository;

    @Autowired
    public StatisticsService(IEbookRepository iEbookRepository) {
        this.iEbookRepository = iEbookRepository;
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<ItemInfoDTO> findTop10BestSellingEbooks() {
        List<ItemInfoDTO> itemInfoDTOs = new ArrayList<>();

        List<Ebook> ebooks = iEbookRepository.top10BestSellingEbooks();
        ItemInfoDTO itemInfoDTO = new ItemInfoDTO();

        for (Ebook ebook: ebooks){
            itemInfoDTO.setTitle(ebook.getTitle());
            itemInfoDTO.setPublisherName(ebook.getPublisher().getName());
            itemInfoDTO.setAuthorFirstName(ebook.getAuthor().getFirstName());
            itemInfoDTO.setAuthorLastName(ebook.getAuthor().getLastName());
            itemInfoDTO.setCopiesSold(iEbookRepository.copiesSold(ebook.getId()));
            itemInfoDTOs.add(itemInfoDTO);
        }

        return itemInfoDTOs;
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<CustomerDTO> findTop5HighestPrice() {
        return null;
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<CustomerDTO> findTop5MostBuyCustomers() {
        return null;
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<PublisherDTO> findTop5BestSellingPublisher() {
        return null;
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<AuthorDTO> findTop5BestSellingAuthors() {
        return null;
    }
}
