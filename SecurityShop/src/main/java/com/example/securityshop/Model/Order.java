package com.example.securityshop.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "MyOrder")
public class Order {

//    id - quantity - totalPrice - dateReceived - status(new-inProgress-completed) (Add All Required Validation)

    //      ID:Integer
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //    quantity :Integer
    @NotNull(message = "quantity Can't Be Null")
    @Column(columnDefinition = "varchar(5) not null")
    private Integer quantity;

    //    totalPrice :Integer
    @NotNull(message = "TotalPrice Can't Be Null")
    @Column(columnDefinition = "varchar(5) not null")
    private Integer totalprice;



    //    DateReceived :Integer
    @NotNull(message = "Date Can't Be Null")
    @Column(columnDefinition = "varchar(5) not null")
    private Integer datereceived;

    //    Status :String
    @NotEmpty(message = "Status can't be empty")
    @Column(columnDefinition = "varchar(25) not null check ( status='new' or status='inProgress' or status='completed')")
    private String status;


    //------------------Relation-------------------------//

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnore
    private User user;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    @JsonIgnore
    private Product product;


}
