package com.skytala.eCommerce.domain.order.dto;

import com.skytala.eCommerce.domain.order.relations.orderHeader.model.OrderHeader;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class OrderListItemDTO {

    private String orderId;
    private Timestamp orderDate;

    private BigDecimal grandTotal;

    public OrderListItemDTO(OrderHeader header) {
        this.setOrderId(header.getOrderId());
        this.setOrderDate(header.getOrderDate());
        this.setGrandTotal(header.getGrandTotal());
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public BigDecimal getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(BigDecimal grandTotal) {
        this.grandTotal = grandTotal;
    }
}
