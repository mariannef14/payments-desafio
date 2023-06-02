package com.flexpag.microservicepagamento.model.entities;


import jakarta.persistence.*;
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

    @Column(columnDefinition = "uuid", updatable = false)
    private UUID uuid;

    private LocalDate createAt;

    private LocalDate updateAt;

    private LocalDate removedAt;

    @PrePersist
    private void generateUUID() {
        this.uuid = UUID.randomUUID();
    }
}
