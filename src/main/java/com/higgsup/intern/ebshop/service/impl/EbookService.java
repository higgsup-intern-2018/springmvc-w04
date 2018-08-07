package com.higgsup.intern.ebshop.service.impl;

import com.higgsup.intern.ebshop.dto.EbookDTO;
import com.higgsup.intern.ebshop.dto.GenericResponseDTO;
import com.higgsup.intern.ebshop.exception.ServiceException;
import com.higgsup.intern.ebshop.jpa.entity.Ebook;
import com.higgsup.intern.ebshop.jpa.repo.IEbookRepository;
import com.higgsup.intern.ebshop.service.IEbookService;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EbookService implements IEbookService {
    private final IEbookRepository ebookRepository;
    private final MapperFacade mapper;

    public EbookService(IEbookRepository ebookRepository, MapperFacade mapper) {
        this.ebookRepository = ebookRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void update(EbookDTO ebookDTO) {
        Long id = ebookDTO.getId();
        if (ebookRepository.findOne(id) == null) {
            throw new ServiceException(String.format("Ebook with id = %d does not exist!", id));
        }
        Ebook ebook = mapper.map(ebookDTO,Ebook.class);
        ebookRepository.save(ebook);
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public GenericResponseDTO create(EbookDTO ebookDTO) {
        String isbn = ebookDTO.getIsbn();
        Integer newQuantity = ebookDTO.getQuantity();
        Ebook ebook = mapper.map(ebookDTO, Ebook.class);
        if (ebookRepository.findEbookByIsbn(isbn) == null) {
            ebookRepository.save(ebook);
            return GenericResponseDTO.created();
        } else {
            Ebook originalEbook = ebookRepository.findEbookByIsbn(isbn);
            ebook.setQuantity(newQuantity + originalEbook.getQuantity());
            ebookRepository.save(ebook);
            return GenericResponseDTO.updated()
                    .addAdditionalInfo(String.format("Ebook with isbn = %s exists! The ebook is updated. ", isbn));
        }
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void delete(Long id) {
        if (ebookRepository.findOne(id) == null) {
            throw new ServiceException(String.format("Ebook with id = %d does not exist!", id));
        }
        ebookRepository.delete(id);
    }
}
