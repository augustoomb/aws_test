package br.com.microservices.orchestrated.orderservice.core.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // APLICAÇÃO É QUEM GERA O id
    private Integer id;

    @Column
    private String name;

    @Column
    private double value;
}
