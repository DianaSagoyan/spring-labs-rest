package com.cydeo.lab08rest.controller;

import com.cydeo.lab08rest.dto.DiscountDTO;
import com.cydeo.lab08rest.enums.DiscountType;
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

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class DiscountControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getDiscountList() throws Exception {

        ResultActions actions = mvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/discount")
                        .accept(MediaType.APPLICATION_JSON));
                actions.andExpect(status().isOk())
                        .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].size()").value(4));
    }


    @Test
    public void getDiscountListByName() throws Exception {

        ResultActions actions = mvc.perform(MockMvcRequestBuilders
                .get("/api/v1/discount/50 dollar")
                .accept(MediaType.APPLICATION_JSON));
        actions.andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.name").value("50 dollar"));

    }

    @Test
    public void createDiscount() throws Exception {
        DiscountDTO discountDTO = new DiscountDTO();
        discountDTO.setDiscount(new BigDecimal(12));
        discountDTO.setName("get 152");
        discountDTO.setDiscountType(DiscountType.AMOUNT_BASED);

        mvc.perform(MockMvcRequestBuilders
                .post("/api/v1/discount")
                .content(toJsonString(discountDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void updateDiscount() throws Exception {
        DiscountDTO discountDTO = new DiscountDTO();
        discountDTO.setId(1L);
        discountDTO.setDiscount(new BigDecimal(12));
        discountDTO.setName("get 12");
        discountDTO.setDiscountType(DiscountType.AMOUNT_BASED);


        mvc.perform(MockMvcRequestBuilders
                        .put("/api/v1/discount")
                        .content(toJsonString(discountDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

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
