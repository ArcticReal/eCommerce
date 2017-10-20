package com.skytala.eCommerce.domain.product.relations.product.mapper.price;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.product.model.price.ProductPrice;

public class ProductPriceMapper  {


	public static Map<String, Object> map(ProductPrice productprice) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(productprice.getProductId() != null ){
			returnVal.put("productId",productprice.getProductId());
}

		if(productprice.getProductPriceTypeId() != null ){
			returnVal.put("productPriceTypeId",productprice.getProductPriceTypeId());
}

		if(productprice.getProductPricePurposeId() != null ){
			returnVal.put("productPricePurposeId",productprice.getProductPricePurposeId());
}

		if(productprice.getCurrencyUomId() != null ){
			returnVal.put("currencyUomId",productprice.getCurrencyUomId());
}

		if(productprice.getProductStoreGroupId() != null ){
			returnVal.put("productStoreGroupId",productprice.getProductStoreGroupId());
}

		if(productprice.getFromDate() != null ){
			returnVal.put("fromDate",productprice.getFromDate());
}

		if(productprice.getThruDate() != null ){
			returnVal.put("thruDate",productprice.getThruDate());
}

		if(productprice.getPrice() != null ){
			returnVal.put("price",productprice.getPrice());
}

		if(productprice.getTermUomId() != null ){
			returnVal.put("termUomId",productprice.getTermUomId());
}

		if(productprice.getCustomPriceCalcService() != null ){
			returnVal.put("customPriceCalcService",productprice.getCustomPriceCalcService());
}

		if(productprice.getPriceWithoutTax() != null ){
			returnVal.put("priceWithoutTax",productprice.getPriceWithoutTax());
}

		if(productprice.getPriceWithTax() != null ){
			returnVal.put("priceWithTax",productprice.getPriceWithTax());
}

		if(productprice.getTaxAmount() != null ){
			returnVal.put("taxAmount",productprice.getTaxAmount());
}

		if(productprice.getTaxPercentage() != null ){
			returnVal.put("taxPercentage",productprice.getTaxPercentage());
}

		if(productprice.getTaxAuthPartyId() != null ){
			returnVal.put("taxAuthPartyId",productprice.getTaxAuthPartyId());
}

		if(productprice.getTaxAuthGeoId() != null ){
			returnVal.put("taxAuthGeoId",productprice.getTaxAuthGeoId());
}

		if(productprice.getTaxInPrice() != null ){
			returnVal.put("taxInPrice",productprice.getTaxInPrice());
}

		if(productprice.getCreatedDate() != null ){
			returnVal.put("createdDate",productprice.getCreatedDate());
}

		if(productprice.getCreatedByUserLogin() != null ){
			returnVal.put("createdByUserLogin",productprice.getCreatedByUserLogin());
}

		if(productprice.getLastModifiedDate() != null ){
			returnVal.put("lastModifiedDate",productprice.getLastModifiedDate());
}

		if(productprice.getLastModifiedByUserLogin() != null ){
			returnVal.put("lastModifiedByUserLogin",productprice.getLastModifiedByUserLogin());
}

		return returnVal;
}


	public static ProductPrice map(Map<String, Object> fields) {

		ProductPrice returnVal = new ProductPrice();

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("productPriceTypeId") != null) {
			returnVal.setProductPriceTypeId((String) fields.get("productPriceTypeId"));
}

		if(fields.get("productPricePurposeId") != null) {
			returnVal.setProductPricePurposeId((String) fields.get("productPricePurposeId"));
}

		if(fields.get("currencyUomId") != null) {
			returnVal.setCurrencyUomId((String) fields.get("currencyUomId"));
}

		if(fields.get("productStoreGroupId") != null) {
			returnVal.setProductStoreGroupId((String) fields.get("productStoreGroupId"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}

		if(fields.get("price") != null) {
			returnVal.setPrice((BigDecimal) fields.get("price"));
}

		if(fields.get("termUomId") != null) {
			returnVal.setTermUomId((String) fields.get("termUomId"));
}

		if(fields.get("customPriceCalcService") != null) {
			returnVal.setCustomPriceCalcService((String) fields.get("customPriceCalcService"));
}

		if(fields.get("priceWithoutTax") != null) {
			returnVal.setPriceWithoutTax((BigDecimal) fields.get("priceWithoutTax"));
}

		if(fields.get("priceWithTax") != null) {
			returnVal.setPriceWithTax((BigDecimal) fields.get("priceWithTax"));
}

		if(fields.get("taxAmount") != null) {
			returnVal.setTaxAmount((BigDecimal) fields.get("taxAmount"));
}

		if(fields.get("taxPercentage") != null) {
			returnVal.setTaxPercentage((BigDecimal) fields.get("taxPercentage"));
}

		if(fields.get("taxAuthPartyId") != null) {
			returnVal.setTaxAuthPartyId((String) fields.get("taxAuthPartyId"));
}

		if(fields.get("taxAuthGeoId") != null) {
			returnVal.setTaxAuthGeoId((String) fields.get("taxAuthGeoId"));
}

		if(fields.get("taxInPrice") != null) {
			returnVal.setTaxInPrice((boolean) fields.get("taxInPrice"));
}

		if(fields.get("createdDate") != null) {
			returnVal.setCreatedDate((Timestamp) fields.get("createdDate"));
}

		if(fields.get("createdByUserLogin") != null) {
			returnVal.setCreatedByUserLogin((String) fields.get("createdByUserLogin"));
}

		if(fields.get("lastModifiedDate") != null) {
			returnVal.setLastModifiedDate((Timestamp) fields.get("lastModifiedDate"));
}

		if(fields.get("lastModifiedByUserLogin") != null) {
			returnVal.setLastModifiedByUserLogin((String) fields.get("lastModifiedByUserLogin"));
}


		return returnVal;
 } 
	public static ProductPrice mapstrstr(Map<String, String> fields) throws Exception {

		ProductPrice returnVal = new ProductPrice();

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("productPriceTypeId") != null) {
			returnVal.setProductPriceTypeId((String) fields.get("productPriceTypeId"));
}

		if(fields.get("productPricePurposeId") != null) {
			returnVal.setProductPricePurposeId((String) fields.get("productPricePurposeId"));
}

		if(fields.get("currencyUomId") != null) {
			returnVal.setCurrencyUomId((String) fields.get("currencyUomId"));
}

		if(fields.get("productStoreGroupId") != null) {
			returnVal.setProductStoreGroupId((String) fields.get("productStoreGroupId"));
}

		if(fields.get("fromDate") != null) {
String buf = fields.get("fromDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setFromDate(ibuf);
}

		if(fields.get("thruDate") != null) {
String buf = fields.get("thruDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setThruDate(ibuf);
}

		if(fields.get("price") != null) {
String buf;
buf = fields.get("price");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setPrice(bd);
}

		if(fields.get("termUomId") != null) {
			returnVal.setTermUomId((String) fields.get("termUomId"));
}

		if(fields.get("customPriceCalcService") != null) {
			returnVal.setCustomPriceCalcService((String) fields.get("customPriceCalcService"));
}

		if(fields.get("priceWithoutTax") != null) {
String buf;
buf = fields.get("priceWithoutTax");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setPriceWithoutTax(bd);
}

		if(fields.get("priceWithTax") != null) {
String buf;
buf = fields.get("priceWithTax");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setPriceWithTax(bd);
}

		if(fields.get("taxAmount") != null) {
String buf;
buf = fields.get("taxAmount");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setTaxAmount(bd);
}

		if(fields.get("taxPercentage") != null) {
String buf;
buf = fields.get("taxPercentage");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setTaxPercentage(bd);
}

		if(fields.get("taxAuthPartyId") != null) {
			returnVal.setTaxAuthPartyId((String) fields.get("taxAuthPartyId"));
}

		if(fields.get("taxAuthGeoId") != null) {
			returnVal.setTaxAuthGeoId((String) fields.get("taxAuthGeoId"));
}

		if(fields.get("taxInPrice") != null) {
String buf;
buf = fields.get("taxInPrice");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setTaxInPrice(ibuf);
}

		if(fields.get("createdDate") != null) {
String buf = fields.get("createdDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setCreatedDate(ibuf);
}

		if(fields.get("createdByUserLogin") != null) {
			returnVal.setCreatedByUserLogin((String) fields.get("createdByUserLogin"));
}

		if(fields.get("lastModifiedDate") != null) {
String buf = fields.get("lastModifiedDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setLastModifiedDate(ibuf);
}

		if(fields.get("lastModifiedByUserLogin") != null) {
			returnVal.setLastModifiedByUserLogin((String) fields.get("lastModifiedByUserLogin"));
}


		return returnVal;
 } 
	public static ProductPrice map(GenericValue val) {

ProductPrice returnVal = new ProductPrice();
		returnVal.setProductId(val.getString("productId"));
		returnVal.setProductPriceTypeId(val.getString("productPriceTypeId"));
		returnVal.setProductPricePurposeId(val.getString("productPricePurposeId"));
		returnVal.setCurrencyUomId(val.getString("currencyUomId"));
		returnVal.setProductStoreGroupId(val.getString("productStoreGroupId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));
		returnVal.setPrice(val.getBigDecimal("price"));
		returnVal.setTermUomId(val.getString("termUomId"));
		returnVal.setCustomPriceCalcService(val.getString("customPriceCalcService"));
		returnVal.setPriceWithoutTax(val.getBigDecimal("priceWithoutTax"));
		returnVal.setPriceWithTax(val.getBigDecimal("priceWithTax"));
		returnVal.setTaxAmount(val.getBigDecimal("taxAmount"));
		returnVal.setTaxPercentage(val.getBigDecimal("taxPercentage"));
		returnVal.setTaxAuthPartyId(val.getString("taxAuthPartyId"));
		returnVal.setTaxAuthGeoId(val.getString("taxAuthGeoId"));
		returnVal.setTaxInPrice(val.getBoolean("taxInPrice"));
		returnVal.setCreatedDate(val.getTimestamp("createdDate"));
		returnVal.setCreatedByUserLogin(val.getString("createdByUserLogin"));
		returnVal.setLastModifiedDate(val.getTimestamp("lastModifiedDate"));
		returnVal.setLastModifiedByUserLogin(val.getString("lastModifiedByUserLogin"));


return returnVal;

}

public static ProductPrice map(HttpServletRequest request) throws Exception {

		ProductPrice returnVal = new ProductPrice();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("productId")) {
returnVal.setProductId(request.getParameter("productId"));
}

		if(paramMap.containsKey("productPriceTypeId"))  {
returnVal.setProductPriceTypeId(request.getParameter("productPriceTypeId"));
}
		if(paramMap.containsKey("productPricePurposeId"))  {
returnVal.setProductPricePurposeId(request.getParameter("productPricePurposeId"));
}
		if(paramMap.containsKey("currencyUomId"))  {
returnVal.setCurrencyUomId(request.getParameter("currencyUomId"));
}
		if(paramMap.containsKey("productStoreGroupId"))  {
returnVal.setProductStoreGroupId(request.getParameter("productStoreGroupId"));
}
		if(paramMap.containsKey("fromDate"))  {
String buf = request.getParameter("fromDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setFromDate(ibuf);
}
		if(paramMap.containsKey("thruDate"))  {
String buf = request.getParameter("thruDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setThruDate(ibuf);
}
		if(paramMap.containsKey("price"))  {
String buf = request.getParameter("price");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setPrice(bd);
}
		if(paramMap.containsKey("termUomId"))  {
returnVal.setTermUomId(request.getParameter("termUomId"));
}
		if(paramMap.containsKey("customPriceCalcService"))  {
returnVal.setCustomPriceCalcService(request.getParameter("customPriceCalcService"));
}
		if(paramMap.containsKey("priceWithoutTax"))  {
String buf = request.getParameter("priceWithoutTax");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setPriceWithoutTax(bd);
}
		if(paramMap.containsKey("priceWithTax"))  {
String buf = request.getParameter("priceWithTax");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setPriceWithTax(bd);
}
		if(paramMap.containsKey("taxAmount"))  {
String buf = request.getParameter("taxAmount");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setTaxAmount(bd);
}
		if(paramMap.containsKey("taxPercentage"))  {
String buf = request.getParameter("taxPercentage");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setTaxPercentage(bd);
}
		if(paramMap.containsKey("taxAuthPartyId"))  {
returnVal.setTaxAuthPartyId(request.getParameter("taxAuthPartyId"));
}
		if(paramMap.containsKey("taxAuthGeoId"))  {
returnVal.setTaxAuthGeoId(request.getParameter("taxAuthGeoId"));
}
		if(paramMap.containsKey("taxInPrice"))  {
String buf = request.getParameter("taxInPrice");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setTaxInPrice(ibuf);
}
		if(paramMap.containsKey("createdDate"))  {
String buf = request.getParameter("createdDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setCreatedDate(ibuf);
}
		if(paramMap.containsKey("createdByUserLogin"))  {
returnVal.setCreatedByUserLogin(request.getParameter("createdByUserLogin"));
}
		if(paramMap.containsKey("lastModifiedDate"))  {
String buf = request.getParameter("lastModifiedDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setLastModifiedDate(ibuf);
}
		if(paramMap.containsKey("lastModifiedByUserLogin"))  {
returnVal.setLastModifiedByUserLogin(request.getParameter("lastModifiedByUserLogin"));
}
return returnVal;

}
}
