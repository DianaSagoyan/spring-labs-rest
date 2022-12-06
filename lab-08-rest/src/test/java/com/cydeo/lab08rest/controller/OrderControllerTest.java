package com.cydeo.lab08rest.controller;

import com.cydeo.lab08rest.dto.OrderDTO;
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
class OrderControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void createOrder() throws Exception {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setPaidPrice(BigDecimal.valueOf(23));
        orderDTO.setCustomerId(1L);
        orderDTO.setPaymentId(2L);
        orderDTO.setTotalPrice(BigDecimal.valueOf(234));
        orderDTO.setCartId(12L);

        mvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/order")
                        .content(toJsonString(orderDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.paidPrice").value(BigDecimal.valueOf(23)));
    }

    @Test
    public void updateOrder() throws Exception {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(1L);
        orderDTO.setPaidPrice(BigDecimal.valueOf(23));
        orderDTO.setCustomerId(1L);
        orderDTO.setPaymentId(2L);
        orderDTO.setTotalPrice(BigDecimal.valueOf(234));
        orderDTO.setCartId(12L);

        mvc.perform(MockMvcRequestBuilders
                        .put("/api/v1/order")
                        .content(toJsonString(orderDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.paidPrice").value(BigDecimal.valueOf(23)));
    }

    @Test
    public void getAddressList() throws Exception {

        ResultActions actions = mvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/order")
                        .accept(MediaType.APPLICATION_JSON));
                actions.andExpect(status().isOk());
    }


    @Test
    public void getOrderListByEmail() throws Exception {

        ResultActions actions = mvc.perform(MockMvcRequestBuilders
                .get("/api/v1/order/email/cpagitt4@slate.com")
                .accept(MediaType.APPLICATION_JSON));
        actions.andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].size()").value(6));
    }


    @Test
    public void getOrderListByPaymentMethod() throws Exception {

        ResultActions actions = mvc.perform(MockMvcRequestBuilders
                .get("/api/v1/order/paymentMethod/BUY_NOW_PAY_LATER")
                .accept(MediaType.APPLICATION_JSON));
        actions.andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].size()").value(6));
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
