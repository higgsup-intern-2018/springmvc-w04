package com.higgsup.intern.ebshop.service.impl;

import com.higgsup.intern.ebshop.dto.EbookDTO;
import com.higgsup.intern.ebshop.dto.EbookOrderDTO;
import com.higgsup.intern.ebshop.dto.EbookOrderListDTO;
import com.higgsup.intern.ebshop.exception.ResourceNotFoundException;
import com.higgsup.intern.ebshop.exception.ServiceException;
import com.higgsup.intern.ebshop.jpa.entity.Ebook;
import com.higgsup.intern.ebshop.jpa.repo.EbookRepositoy;
import com.higgsup.intern.ebshop.jpa.repo.IEbookRepository;
import com.higgsup.intern.ebshop.service.IEbookService;
import ma.glasnost.orika.MapperFacade;
import com.higgsup.intern.ebshop.dto.GenericResponseDTO;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EbookService implements IEbookService {

    private final EbookRepositoy ebookRepositoy;
    private final IEbookRepository iEbookRepository;
    private final MapperFacade mapper;

    public EbookService(EbookRepositoy ebookRepositoy, IEbookRepository iEbookRepository, MapperFacade mapper) {
        this.ebookRepositoy = ebookRepositoy;
        this.iEbookRepository = iEbookRepository;
        this.mapper = mapper;
    }


    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public EbookDTO findById(Long id) {
        Ebook ebook = ebookRepositoy.findOne(id);
        if (ebook == null) {
            throw new ResourceNotFoundException(String.format("Ebook with id = %d does not exist!", id));
        }
        return mapper.map(ebook, EbookDTO.class);
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public void update(EbookDTO ebookDTO) {
        Long id = ebookDTO.getId();
        if (ebookRepositoy.findOne(id) == null) {
            throw new ServiceException(String.format("Ebook with id = %d does not exist!", id));
        }

        Ebook ebook = mapper.map(ebookDTO, Ebook.class);
        ebookRepositoy.save(ebook);
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public GenericResponseDTO create(EbookDTO ebookDTO) {
        String isbn = ebookDTO.getIsbn();
        Integer newQuantity = ebookDTO.getQuantity();
        Ebook ebook = mapper.map(ebookDTO, Ebook.class);
        if (ebookRepositoy.findOne(Long.valueOf(isbn)) == null) {
            ebookRepositoy.save(ebook);
            return GenericResponseDTO.created();
        } else {
            Ebook originalEbook = ebookRepositoy.findOne(Long.valueOf(isbn));
            ebook.setQuantity(newQuantity + originalEbook.getQuantity());
            ebookRepositoy.save(ebook);
            return GenericResponseDTO.updated()
                    .addAdditionalInfo(String.format("Ebook with isbn = %s exists! The ebook is updated. ", isbn));
        }
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public void delete(Long id) {
        if (ebookRepositoy.findOne(id) == null) {
            throw new ServiceException(String.format("Ebook with id = %d does not exist!", id));
        }
        iEbookRepository.deleteEbook(id);
    }


    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public EbookOrderListDTO top10BestSellers() {
        List<EbookOrderDTO> ebookOrderDTOS = iEbookRepository.top10BestSeller();

        EbookOrderListDTO ebookOrderListDTO = new EbookOrderListDTO();
        ebookOrderListDTO.setEbookOrderDTOs(ebookOrderDTOS);

        return ebookOrderListDTO;
    }

}
