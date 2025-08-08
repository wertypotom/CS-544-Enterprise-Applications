package PromptStuffing.partB;

import jakarta.persistence.*;

@Entity
public class ProductEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Double price;

    public ProductEntity() {}
    public ProductEntity(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ProductEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
