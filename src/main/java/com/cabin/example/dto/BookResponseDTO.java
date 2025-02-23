package com.cabin.example.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class BookResponseDTO {
    private Long id;
    private String title;
    private BigDecimal price;
    private LocalDate publishDate;
    private LocalDateTime createdAt;

    public BookResponseDTO(Long id, String title, BigDecimal price, LocalDate publishDate, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.publishDate = publishDate;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}