package com.higgsup.intern.ebshop.jdbc.model;

public class Search {
   private String ebookName;
   private Long authorId;
   private Double priceFrom;
   private Double priceTo;
   private Long publisherId;
   private String isbn;

   public String getEbookName() {
      return ebookName;
   }

   public void setEbookName(String ebookName) {
      this.ebookName = ebookName;
   }

   public Long getAuthorId() {
      return authorId;
   }

   public void setAuthorId(Long authorId) {
      this.authorId = authorId;
   }

   public Double getPriceFrom() {
      return priceFrom;
   }

   public void setPriceFrom(Double priceFrom) {
      this.priceFrom = priceFrom;
   }

   public Double getPriceTo() {
      return priceTo;
   }

   public void setPriceTo(Double priceTo) {
      this.priceTo = priceTo;
   }

   public Long getPublisherId() {
      return publisherId;
   }

   public void setPublisherId(Long publisherId) {
      this.publisherId = publisherId;
   }

   public String getIsbn() {
      return isbn;
   }

   public void setIsbn(String isbn) {
      this.isbn = isbn;
   }
}
