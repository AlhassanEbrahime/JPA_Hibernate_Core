package org.project.entities;
import jakarta.persistence.*;
import org.project.entities.generators.CustomUUID;
import org.project.entities.keys.ProductKey;


@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue
    @CustomUUID
    private String id;

    private String name;

    private String address;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
