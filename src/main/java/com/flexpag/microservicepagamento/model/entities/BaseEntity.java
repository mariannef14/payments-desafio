package com.flexpag.microservicepagamento.model.entities;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;


@MappedSuperclass
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = -5926868552941669903L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    private LocalDate createAt;

    private LocalDate updateAt;

    private LocalDate removedAt;
}
