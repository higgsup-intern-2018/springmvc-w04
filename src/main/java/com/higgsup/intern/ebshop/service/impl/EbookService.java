package com.higgsup.intern.ebshop.service.impl;

import com.higgsup.intern.ebshop.dto.AuthorDTO;
import com.higgsup.intern.ebshop.dto.EbookDTO;
import com.higgsup.intern.ebshop.dto.GenericResponseDTO;
import com.higgsup.intern.ebshop.dto.PublisherDTO;
import com.higgsup.intern.ebshop.exception.ResourceNotFoundException;
import com.higgsup.intern.ebshop.jpa.entity.Author;
import com.higgsup.intern.ebshop.jpa.entity.Ebook;
import com.higgsup.intern.ebshop.jpa.entity.Publisher;
import com.higgsup.intern.ebshop.jpa.repo.EbookRepository;

import com.higgsup.intern.ebshop.service.IEbookService;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EbookService implements IEbookService {
    private final EbookRepository ebookRepository;
    private final MapperFacade mapper;

    public EbookService(EbookRepository ebookRepository, MapperFacade mapper)
    {
        this.ebookRepository = ebookRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public EbookDTO findById(Long id) {
        Ebook ebook = ebookRepository.findOne(id);
        if(ebook == null)
        {
            throw new ResourceNotFoundException(String.format("Ebook with id = %d does not exist!", id));
        }
        Author author = ebookRepository.findAuthorByEbookId(id);
        Publisher publisher = ebookRepository.findPublisherByEbookId(id);
        AuthorDTO authorDTO = mapper.map(author, AuthorDTO.class);
        PublisherDTO publisherDTO = mapper.map(publisher, PublisherDTO.class);
        EbookDTO ebookDTO =  mapper.map(ebook, EbookDTO.class);
        ebookDTO.setAuthorDTO(authorDTO);
        ebookDTO.setPublisherDTO(publisherDTO);
        return ebookDTO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public GenericResponseDTO create(EbookDTO ebookDTO) {
        String isbn = ebookDTO.getIsbn();
        Integer newQuantity = ebookDTO.getQuantity();
        Ebook ebook = mapper.map(ebookDTO, Ebook.class);
        if (ebookRepository.findByIsbn(isbn) == null) {
            ebookRepository.save(ebook);
            return GenericResponseDTO.created();
        } else {
            Ebook originalEbook = ebookRepository.findByIsbn(isbn);
            ebook.setId(originalEbook.getId());
            ebook.setQuantity(newQuantity + originalEbook.getQuantity());
            ebookRepository.save(ebook);
            return GenericResponseDTO.updated()
                    .addAdditionalInfo(String.format("Ebook with isbn = %s exists! The ebook is updated. ", isbn));
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(EbookDTO ebookDTO) {
        Long id = ebookDTO.getId();
        if(ebookRepository.findOne(id) == null)
        {
            throw new ResourceNotFoundException(String.format("Ebook with id = %d does not exist!", id));
        }
        Ebook ebook = mapper.map(ebookDTO, Ebook.class);
        ebookRepository.save(ebook);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        if(ebookRepository.findOne(id) == null || ebookRepository.findOne(id).getDeleted())
        {
            throw new ResourceNotFoundException(String.format("Ebook with id = %d does not exist!", id));
        }
        ebookRepository.findOne(id).setDeleted(true);
    }
}
