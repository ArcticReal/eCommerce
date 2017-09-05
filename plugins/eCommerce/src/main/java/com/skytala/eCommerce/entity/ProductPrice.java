package com.skytala.eCommerce.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class ProductPrice {

	private String productId;
	private String productPriceTypeId;
	private String productPricePurposeId;
	private String currencyUomId;
	private String productStoreGroupId;
	private Timestamp fromDate;
	private Timestamp thruDate;
	private BigDecimal price;
	private String termUomId;
	private String customPriceCalcService;
	private BigDecimal priceWithoutTax;
	private BigDecimal priceWithTax;
	private BigDecimal taxAmount;
	private BigDecimal taxPercentage;
	private String taxAuthPartyId;
	private String taxAuthGeoId;
	private Boolean taxInPrice;
	private Timestamp createdDate;
	private String createdByUserLogin;
	private Timestamp lastModifiedDate;
	private String lastModifiedByUserLogin;

	public ProductPrice() {
		this.setCurrencyUomId("EUR");
		this.setProductStoreGroupId("SKYTALA_GROUP");
		Timestamp currentDate = new Timestamp(System.currentTimeMillis());
		currentDate.setNanos(0);
		this.setFromDate(currentDate);
		this.setProductPricePurposeId("PURCHASE");
		
	}
	
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductPriceTypeId() {
		return productPriceTypeId;
	}

	public void setProductPriceTypeId(String productPriceTypeId) {
		this.productPriceTypeId = productPriceTypeId;
	}

	public String getProductPricePurposeId() {
		return productPricePurposeId;
	}

	public void setProductPricePurposeId(String productPricePurposeId) {
		this.productPricePurposeId = productPricePurposeId;
	}

	public String getCurrencyUomId() {
		return currencyUomId;
	}

	public void setCurrencyUomId(String currencyUomId) {
		this.currencyUomId = currencyUomId;
	}

	public String getProductStoreGroupId() {
		return productStoreGroupId;
	}

	public void setProductStoreGroupId(String productStoreGroupId) {
		this.productStoreGroupId = productStoreGroupId;
	}

	public Timestamp getFromDate() {
		return fromDate;
	}

	public void setFromDate(Timestamp fromDate) {
		this.fromDate = fromDate;
	}

	public Timestamp getThruDate() {
		return thruDate;
	}

	public void setThruDate(Timestamp thruDate) {
		this.thruDate = thruDate;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getTermUomId() {
		return termUomId;
	}

	public void setTermUomId(String termUomId) {
		this.termUomId = termUomId;
	}

	public String getCustomPriceCalcService() {
		return customPriceCalcService;
	}

	public void setCustomPriceCalcService(String customPriceCalcService) {
		this.customPriceCalcService = customPriceCalcService;
	}

	public BigDecimal getPriceWithoutTax() {
		return priceWithoutTax;
	}

	public void setPriceWithoutTax(BigDecimal priceWithoutTax) {
		this.priceWithoutTax = priceWithoutTax;
	}

	public BigDecimal getPriceWithTax() {
		return priceWithTax;
	}

	public void setPriceWithTax(BigDecimal priceWithTax) {
		this.priceWithTax = priceWithTax;
	}

	public BigDecimal getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}

	public BigDecimal getTaxPercentage() {
		return taxPercentage;
	}

	public void setTaxPercentage(BigDecimal taxPercentage) {
		this.taxPercentage = taxPercentage;
	}

	public String getTaxAuthPartyId() {
		return taxAuthPartyId;
	}

	public void setTaxAuthPartyId(String taxAuthPartyId) {
		this.taxAuthPartyId = taxAuthPartyId;
	}

	public String getTaxAuthGeoId() {
		return taxAuthGeoId;
	}

	public void setTaxAuthGeoId(String taxAuthGeoId) {
		this.taxAuthGeoId = taxAuthGeoId;
	}

	public Boolean getTaxInPrice() {
		return taxInPrice;
	}

	public void setTaxInPrice(Boolean taxInPrice) {
		this.taxInPrice = taxInPrice;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedByUserLogin() {
		return createdByUserLogin;
	}

	public void setCreatedByUserLogin(String createdByUserLogin) {
		this.createdByUserLogin = createdByUserLogin;
	}

	public Timestamp getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Timestamp lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public String getLastModifiedByUserLogin() {
		return lastModifiedByUserLogin;
	}

	public void setLastModifiedByUserLogin(String lastModifiedByUserLogin) {
		this.lastModifiedByUserLogin = lastModifiedByUserLogin;
	}

}
