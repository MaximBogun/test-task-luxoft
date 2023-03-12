package com.deuttchlandbank.naceservice;

import com.deuttchlandbank.naceservice.rest.domain.NaceDetailsConvertor;
import com.deuttchlandbank.naceservice.rest.domain.NaceDetailsRequest;
import com.deuttchlandbank.naceservice.rest.domain.NaceDetailsResponse;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Disabled("Contains tests for cache, for future improvements")
class NaceDetailsServiceTest {
    @Mock
    private NaceDetailsRepository naceDetailsRepository;

    @Mock
    private NaceDetailsConvertor naceDetailsConvertor;

    private NaceDetailsService naceDetailsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        naceDetailsService = new NaceDetailsService(naceDetailsRepository, naceDetailsConvertor);
    }

    @Test
    void shouldInvalidateCacheAndGetRefreshedValue() {
        NaceDetailsRequest request = TestDataUtil.buildNaceDetailsRequest();

        naceDetailsService.putNaceDetails(request);

        request.setDescription("New");

        naceDetailsService.putNaceDetails(request);

        when(naceDetailsConvertor.toNaceDetailsResponse(Mockito.any())).thenReturn(new NaceDetailsResponse());
        when(naceDetailsRepository.findByCode(Mockito.any())).thenReturn(Optional.of(new NaceDetails()));

        NaceDetailsResponse response = naceDetailsService.getNaceDetails(request.getCode());

        verify(naceDetailsRepository, times(1)).findByCode(any());
        assertEquals(response.getDescription(), "New");
    }

    @Test
    void shouldGetCachedNaceDetails() {
        when(naceDetailsConvertor.toNaceDetailsResponse(Mockito.any())).thenReturn(new NaceDetailsResponse());
        when(naceDetailsRepository.findByCode(Mockito.any())).thenReturn(Optional.of(new NaceDetails()));

        naceDetailsService.getNaceDetails("A");

        verify(naceDetailsRepository, times(0)).findByCode(any());
    }
}
