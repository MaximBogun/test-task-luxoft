package com.deuttchlandbank.naceservice;

import com.deuttchlandbank.naceservice.rest.domain.NaceDetailsRequest;

public class TestDataUtil {

    public static NaceDetailsRequest buildNaceDetailsRequest() {
        NaceDetailsRequest request = new NaceDetailsRequest();
        request.setOrderPriority(1L);
        request.setLevel(1);
        request.setCode("A");
        request.setParent("Parent");
        request.setDescription("Test NACE description");
        request.setIncludes("Includes");
        request.setAlsoIncludes("AlsoIncludes");
        request.setRulings("Rulings");
        request.setExcludes("Excludes");
        request.setReferenceToIsicRev4("ReferenceToIsicRev4");
        return request;
    }
}
