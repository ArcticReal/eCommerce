package com.skytala.eCommerce.domain.order.dto;

import com.skytala.eCommerce.domain.cart.Position;
import com.skytala.eCommerce.domain.cart.dto.ShoppingCartItemDTO;
import com.skytala.eCommerce.domain.order.relations.orderHeader.model.OrderHeader;
import com.skytala.eCommerce.domain.order.relations.orderItem.model.OrderItem;
import com.skytala.eCommerce.domain.party.relations.postalAddress.model.PostalAddress;
import com.skytala.eCommerce.domain.product.dto.ProductListItemDTO;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public class OrderDetailsDTO {

    private String orderId;
    private Timestamp orderDate;
    private String statusId;

    // postal Address
    private String toName;
    private String address1;
    private String city;
    private String postalCode;
    private String countryGeoId;
    private String stateProvinceGeoId;

    private List<ShoppingCartItemDTO> products;
    private BigDecimal grandTotal;


    /*
        factory method
     */
    public static OrderDetailsDTO create(OrderHeader header,
                                  PostalAddress address,
                                  List<ShoppingCartItemDTO> products,
                                  BigDecimal grandTotal){

        OrderDetailsDTO dto = new OrderDetailsDTO(header);

        dto.setToName(address.getToName());
        dto.setAddress1(address.getAddress1());
        dto.setCity(address.getCity());
        dto.setPostalCode(address.getPostalCode());
        dto.setCountryGeoId(address.getCountryGeoId());
        dto.setStateProvinceGeoId(address.getStateProvinceGeoId());

        dto.setProducts(products);
        dto.setGrandTotal(grandTotal);

        return dto;

    }

    public OrderDetailsDTO(OrderHeader header) {

        this.orderId = header.getOrderId();
        this.orderDate = header.getOrderDate();
        this.statusId = header.getStatusId();
    }


    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
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

    public List<ShoppingCartItemDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ShoppingCartItemDTO> products) {
        this.products = products;
    }

    public BigDecimal getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(BigDecimal grandTotal) {
        this.grandTotal = grandTotal;
    }

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountryGeoId() {
        return countryGeoId;
    }

    public void setCountryGeoId(String countryGeoId) {
        this.countryGeoId = countryGeoId;
    }

    public String getStateProvinceGeoId() {
        return stateProvinceGeoId;
    }

    public void setStateProvinceGeoId(String stateProvinceGeoId) {
        this.stateProvinceGeoId = stateProvinceGeoId;
    }
}
