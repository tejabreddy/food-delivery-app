package com.hashedin.fooddelivery.orderservice.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "address")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer addressId;
    private String line1;
    private String line2;
    private String city;
    private String state;
    private Integer pinCode;
}
