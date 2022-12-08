package com.cydeo.lab08rest.service;

import com.cydeo.lab08rest.dto.OrderDTO;
import com.cydeo.lab08rest.enums.PaymentMethod;

import java.util.List;

public interface OrderService {
    List<OrderDTO> getAddressList();
    List<OrderDTO> getOrderListByPaymentMethod(PaymentMethod paymentMethod);
    List<OrderDTO> getOrderListByEmail(String email);
    OrderDTO update(OrderDTO order);
    OrderDTO save(OrderDTO order);


}
