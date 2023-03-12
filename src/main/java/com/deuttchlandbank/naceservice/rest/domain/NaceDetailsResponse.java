package com.deuttchlandbank.naceservice.rest.domain;

import lombok.Data;

@Data
public class NaceDetailsResponse {
    private int id;
    private long orderPriority;
    private Integer level;
    private String code;
    private String parent;
    private String description;
    private String includes;
    private String alsoIncludes;
    private String rulings;
    private String excludes;
    private String referenceToIsicRev4;
}
