package com.deuttchlandbank.naceservice.rest;


import com.deuttchlandbank.naceservice.NaceDetailsService;
import com.deuttchlandbank.naceservice.rest.domain.NaceDetailsRequest;
import com.deuttchlandbank.naceservice.rest.domain.NaceDetailsResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/nace-details")
@Api(tags = "NACE Details")
public class NaceDetailsController {

    private final NaceDetailsService naceDetailsService;

    @PutMapping
    @ApiOperation(value = "Persist NACE data in database")
    public NaceDetailsResponse putNaceDetails(@RequestBody NaceDetailsRequest request) {
        return naceDetailsService.putNaceDetails(request);
    }

    @GetMapping("/{code}")
    @ApiOperation(value = "Get NACE data for given code")
    public NaceDetailsResponse getNaceDetails(@PathVariable String code) {
        return naceDetailsService.getNaceDetails(code);
    }
}
