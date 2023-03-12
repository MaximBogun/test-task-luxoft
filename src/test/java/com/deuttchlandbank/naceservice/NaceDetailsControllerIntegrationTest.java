package com.deuttchlandbank.naceservice;

import com.deuttchlandbank.naceservice.rest.domain.NaceDetailsRequest;
import com.deuttchlandbank.naceservice.rest.domain.NaceDetailsResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.deuttchlandbank.naceservice.TestDataUtil.buildNaceDetailsRequest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = NaceServiceApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-test.properties")
public class NaceDetailsControllerIntegrationTest {

    @Autowired
    private NaceDetailsService naceDetailsService;

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void testPutNaceDetails() throws Exception {
        NaceDetailsRequest request = buildNaceDetailsRequest();

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/nace-details")
                        .content(asJsonString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();

        NaceDetailsResponse response = new ObjectMapper().readValue(responseBody, NaceDetailsResponse.class);
        assertNaceDetailsResponse(request, response);
    }

    @Test
    public void testPutNaceDetailsWhenUseSavedCode() throws Exception {
        NaceDetailsRequest request = buildNaceDetailsRequest();
        request.setCode("B");

        naceDetailsService.putNaceDetails(request);

        request.setOrderPriority(100L);
        request.setDescription("New test NACE description");
        request.setRulings("New rullings");
        request.setIncludes("New inclused");
        request.setReferenceToIsicRev4("New  Isic");
        request.setExcludes("New excludes");
        request.setAlsoIncludes("Also includes");

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/nace-details")
                        .content(asJsonString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();

        NaceDetailsResponse response = new ObjectMapper().readValue(responseBody, NaceDetailsResponse.class);
        assertNaceDetailsResponse(request, response);
    }

    @Test
    public void testGetNaceDetails() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/nace-details/{code}", "A")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();
        NaceDetailsResponse response = new ObjectMapper().readValue(responseBody, NaceDetailsResponse.class);

        assertNotNull(response);
        assertEquals(1L, response.getOrderPriority());
        assertEquals(1, response.getLevel());
        assertEquals("A", response.getCode());
        assertEquals("Parent", response.getParent());
        assertEquals("Test NACE description", response.getDescription());
        assertEquals("Includes", response.getIncludes());
        assertEquals("AlsoIncludes", response.getAlsoIncludes());
        assertEquals("Rulings", response.getRulings());
        assertEquals("Excludes", response.getExcludes());
        assertEquals("ReferenceToIsicRev4", response.getReferenceToIsicRev4());
        assertEquals("Test NACE description", response.getDescription());
    }

    @Test
    public void testGetNaceDetailsWhenCodeNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/nace-details/{code}", "C")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    private static String asJsonString(Object obj) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }

    private void assertNaceDetailsResponse(NaceDetailsRequest request, NaceDetailsResponse response) {
        assertNotNull(response);
        assertEquals(request.getOrderPriority(), response.getOrderPriority());
        assertEquals(request.getLevel(), response.getLevel());
        assertEquals(request.getCode(), response.getCode());
        assertEquals(request.getParent(), response.getParent());
        assertEquals(request.getDescription(), response.getDescription());
        assertEquals(request.getIncludes(), response.getIncludes());
        assertEquals(request.getAlsoIncludes(), response.getAlsoIncludes());
        assertEquals(request.getRulings(), response.getRulings());
        assertEquals(request.getExcludes(), response.getExcludes());
        assertEquals(request.getReferenceToIsicRev4(), response.getReferenceToIsicRev4());
        assertEquals(request.getDescription(), response.getDescription());
    }

}
