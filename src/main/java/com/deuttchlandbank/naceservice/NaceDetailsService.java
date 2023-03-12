package com.deuttchlandbank.naceservice;

import com.deuttchlandbank.naceservice.rest.domain.NaceDetailsConvertor;
import com.deuttchlandbank.naceservice.rest.domain.NaceDetailsRequest;
import com.deuttchlandbank.naceservice.rest.domain.NaceDetailsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@RequiredArgsConstructor
@Service
public class NaceDetailsService {
    private final NaceDetailsRepository naceDetailsRepository;
    private final NaceDetailsConvertor naceDetailsConvertor;

    public NaceDetailsResponse putNaceDetails(NaceDetailsRequest request) {
        // Some fields cannot be null, so we need to add validation at both the database and code level.
        // Additionally, we need to consider the validation of the order.
        // It is not desirable to have duplicate values with the same order.
        // For a fast implementation, let's proceed with this.
        return naceDetailsConvertor.toNaceDetailsResponse(
                naceDetailsRepository.findByCode(request.getCode()).map(
                                naceDetails -> {
                                    naceDetails.setLevel(request.getLevel());
                                    naceDetails.setParent(request.getParent());
                                    naceDetails.setOrderPriority(request.getOrderPriority());
                                    naceDetails.setDescription(request.getDescription());
                                    naceDetails.setIncludes(request.getIncludes());
                                    naceDetails.setAlsoIncludes(request.getAlsoIncludes());
                                    naceDetails.setRulings(request.getRulings());
                                    naceDetails.setExcludes(request.getExcludes());
                                    naceDetails.setReferenceToIsicRev4(request.getReferenceToIsicRev4());
                                    naceDetailsRepository.save(naceDetails);

                                    return naceDetails;
                                })
                        .orElseGet(() -> naceDetailsRepository.save(naceDetailsConvertor.toNaceDetails(request)))
        );
    }

    public NaceDetailsResponse getNaceDetails(String code) {
        return naceDetailsRepository.findByCode(code).map(naceDetailsConvertor::toNaceDetailsResponse)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "NaceDetails not found"
                ));
    }

}
