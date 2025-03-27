package org.project.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import org.project.entities.keys.ProductKey;

@Entity
@Table(name = "products")
@IdClass(ProductKey.class)
public class Product {

    @Id
    private String code;

    @Id
    private long number;

    private String color;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Product{" +
                "code='" + code + '\'' +
                ", number=" + number +
                ", color='" + color + '\'' +
                '}';
    }
}
