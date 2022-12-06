package com.cydeo.lab08rest.controller;

import com.cydeo.lab08rest.dto.AddressDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AddressControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getAddressList() throws Exception {

        ResultActions actions = mvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/address")
                        .accept(MediaType.APPLICATION_JSON));
                actions.andExpect(status().isOk())
                        .andExpect(MockMvcResultMatchers.jsonPath("$.data.size()").value(650));

    }

    @Test
    public void createAddress() throws Exception {
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setCustomerId(1L);
        addressDTO.setName("Home");
        addressDTO.setStreet("Street");
        addressDTO.setZipCode("34Asd");

        mvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/address")
                        .content(toJsonString(addressDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.name").value("Home"));

    }

    @Test
    public void updateAddress() throws Exception {
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setId(1L);
        addressDTO.setCustomerId(1L);
        addressDTO.setName("Home");
        addressDTO.setStreet("Street");
        addressDTO.setZipCode("34Asd");

        mvc.perform(MockMvcRequestBuilders
                        .put("/api/v1/address")
                        .content(toJsonString(addressDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.name").value("Home"));
    }

    @Test
    public void getAddressListByCustomerId() throws Exception {

        ResultActions actions = mvc.perform(MockMvcRequestBuilders
                .get("/api/v1/address/customer/1")
                .accept(MediaType.APPLICATION_JSON));
        actions.andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.size()").value(1));

    }

    @Test
    public void getAddressListByStartsWithAddress() throws Exception {

        ResultActions actions = mvc.perform(MockMvcRequestBuilders
                .get("/api/v1/address/startsWith/Tomscot")
                .accept(MediaType.APPLICATION_JSON));
        actions.andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.size()").value(2));

    }
    @Test
    public void getAddressListByCustomerAndName() throws Exception {
        ResultActions actions = mvc.perform(MockMvcRequestBuilders
                .get("/api/v1/address/customer/342/name/Home")
                .accept(MediaType.APPLICATION_JSON));
        actions.andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.size()").value(1));
    }


    private static String toJsonString(final Object obj) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            objectMapper.registerModule(new JavaTimeModule());
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
