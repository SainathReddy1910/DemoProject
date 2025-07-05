package com.example.demo.entity;

import com.example.demo.enums.Status;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
@ToString
public class Order {
    @Id
    private UUID uuid;

    @Column(name = "name")
    private String name;

    @Column(name = "cost")
    private double cost;

    @Column(name = "ordered_by")
    private String orderedBy;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "status")
    private Status status;
}
