package com.deuttchlandbank.naceservice;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name = "nace_details")
public class NaceDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //this can be too unique
    private Long orderPriority;
    private Integer level;
    @Column(unique = true)
    private String code;
    private String parent;
    private String description;
    @Column(length = 2024)
    private String includes;
    @Column(length = 2024)
    private String alsoIncludes;
    @Column(length = 2024)
    private String rulings;
    @Column(length = 2024)
    private String excludes;
    private String referenceToIsicRev4;
}
