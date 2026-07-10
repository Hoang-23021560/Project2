package com.javaweb.repository.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "buildingtype")
public class BuildingTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "code")
    private String code;

    @ManyToOne
    @JoinColumn(name = "buildingId")
    private BuildingEntity buildingType;

}
