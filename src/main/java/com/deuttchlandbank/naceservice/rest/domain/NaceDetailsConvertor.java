package com.deuttchlandbank.naceservice.rest.domain;

import com.deuttchlandbank.naceservice.NaceDetails;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NaceDetailsConvertor {

    NaceDetails toNaceDetails(NaceDetailsRequest naceDetailsRequest);

    NaceDetailsResponse toNaceDetailsResponse(NaceDetails naceDetails);
}
