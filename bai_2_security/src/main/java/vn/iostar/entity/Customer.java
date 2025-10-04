package vn.iostar.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    private String id;
    
    @Column(nullable = false)
    private String name;
    
    @Column
    private String email;
    
    @Column
    private String phone;
    
    @Column
    private String address;
}