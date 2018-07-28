package com.higgsup.intern.ebshop.web.controller;

        import com.higgsup.intern.ebshop.dto.AuthorListDTO;
        import com.higgsup.intern.ebshop.service.IAuthorService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {
    private final IAuthorService authorService;

    @Autowired
    public StatisticsController(IAuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/top-5-best-selling-authors")
    public ResponseEntity<AuthorListDTO> getTop5Author() {
        AuthorListDTO top5Author = authorService.findTop5BestSellingAuthors();
        return ResponseEntity.ok(top5Author);
    }
}
