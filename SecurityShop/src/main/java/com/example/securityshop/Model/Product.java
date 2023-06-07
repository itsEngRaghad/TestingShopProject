package com.example.securityshop.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Product {

//    id - name - price (Add All Required Validation)
    //      ID:Integer
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //    username :String
    @NotEmpty(message = "username shouldn't be empty")
    @Column(columnDefinition = "varchar(25) not null")
    private String name;

    //    Price :Integer
    @NotNull(message = "Price Can't Be Null")
    @Column(columnDefinition = "varchar(5) not null")
    private Integer price;

    //------------------Relation-------------------------//

    //define one-many relation Customer with order
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "product")
    private Set<Order> orderSet;
}
