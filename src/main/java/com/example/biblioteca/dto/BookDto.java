package com.example.biblioteca.dto;


public class BookDto {
    private String name;
    private String description;
    private Integer quantity;
    private Long categoryId;

    public Long getCategoryId() {
        return categoryId;
    }
    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}
