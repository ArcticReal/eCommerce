package com.skytala.eCommerce.domain.product.relations.productPriceChange.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.productPriceChange.model.ProductPriceChange;

public class ProductPriceChangeMapper  {


	public static Map<String, Object> map(ProductPriceChange productpricechange) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(productpricechange.getProductPriceChangeId() != null ){
			returnVal.put("productPriceChangeId",productpricechange.getProductPriceChangeId());
}

		if(productpricechange.getProductId() != null ){
			returnVal.put("productId",productpricechange.getProductId());
}

		if(productpricechange.getProductPriceTypeId() != null ){
			returnVal.put("productPriceTypeId",productpricechange.getProductPriceTypeId());
}

		if(productpricechange.getProductPricePurposeId() != null ){
			returnVal.put("productPricePurposeId",productpricechange.getProductPricePurposeId());
}

		if(productpricechange.getCurrencyUomId() != null ){
			returnVal.put("currencyUomId",productpricechange.getCurrencyUomId());
}

		if(productpricechange.getProductStoreGroupId() != null ){
			returnVal.put("productStoreGroupId",productpricechange.getProductStoreGroupId());
}

		if(productpricechange.getFromDate() != null ){
			returnVal.put("fromDate",productpricechange.getFromDate());
}

		if(productpricechange.getThruDate() != null ){
			returnVal.put("thruDate",productpricechange.getThruDate());
}

		if(productpricechange.getPrice() != null ){
			returnVal.put("price",productpricechange.getPrice());
}

		if(productpricechange.getOldPrice() != null ){
			returnVal.put("oldPrice",productpricechange.getOldPrice());
}

		if(productpricechange.getChangedDate() != null ){
			returnVal.put("changedDate",productpricechange.getChangedDate());
}

		if(productpricechange.getChangedByUserLogin() != null ){
			returnVal.put("changedByUserLogin",productpricechange.getChangedByUserLogin());
}

		return returnVal;
}


	public static ProductPriceChange map(Map<String, Object> fields) {

		ProductPriceChange returnVal = new ProductPriceChange();

		if(fields.get("productPriceChangeId") != null) {
			returnVal.setProductPriceChangeId((String) fields.get("productPriceChangeId"));
}

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

		if(fields.get("oldPrice") != null) {
			returnVal.setOldPrice((BigDecimal) fields.get("oldPrice"));
}

		if(fields.get("changedDate") != null) {
			returnVal.setChangedDate((Timestamp) fields.get("changedDate"));
}

		if(fields.get("changedByUserLogin") != null) {
			returnVal.setChangedByUserLogin((String) fields.get("changedByUserLogin"));
}


		return returnVal;
 } 
	public static ProductPriceChange mapstrstr(Map<String, String> fields) throws Exception {

		ProductPriceChange returnVal = new ProductPriceChange();

		if(fields.get("productPriceChangeId") != null) {
			returnVal.setProductPriceChangeId((String) fields.get("productPriceChangeId"));
}

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

		if(fields.get("oldPrice") != null) {
String buf;
buf = fields.get("oldPrice");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setOldPrice(bd);
}

		if(fields.get("changedDate") != null) {
String buf = fields.get("changedDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setChangedDate(ibuf);
}

		if(fields.get("changedByUserLogin") != null) {
			returnVal.setChangedByUserLogin((String) fields.get("changedByUserLogin"));
}


		return returnVal;
 } 
	public static ProductPriceChange map(GenericValue val) {

ProductPriceChange returnVal = new ProductPriceChange();
		returnVal.setProductPriceChangeId(val.getString("productPriceChangeId"));
		returnVal.setProductId(val.getString("productId"));
		returnVal.setProductPriceTypeId(val.getString("productPriceTypeId"));
		returnVal.setProductPricePurposeId(val.getString("productPricePurposeId"));
		returnVal.setCurrencyUomId(val.getString("currencyUomId"));
		returnVal.setProductStoreGroupId(val.getString("productStoreGroupId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));
		returnVal.setPrice(val.getBigDecimal("price"));
		returnVal.setOldPrice(val.getBigDecimal("oldPrice"));
		returnVal.setChangedDate(val.getTimestamp("changedDate"));
		returnVal.setChangedByUserLogin(val.getString("changedByUserLogin"));


return returnVal;

}

public static ProductPriceChange map(HttpServletRequest request) throws Exception {

		ProductPriceChange returnVal = new ProductPriceChange();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("productPriceChangeId")) {
returnVal.setProductPriceChangeId(request.getParameter("productPriceChangeId"));
}

		if(paramMap.containsKey("productId"))  {
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
		if(paramMap.containsKey("oldPrice"))  {
String buf = request.getParameter("oldPrice");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setOldPrice(bd);
}
		if(paramMap.containsKey("changedDate"))  {
String buf = request.getParameter("changedDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setChangedDate(ibuf);
}
		if(paramMap.containsKey("changedByUserLogin"))  {
returnVal.setChangedByUserLogin(request.getParameter("changedByUserLogin"));
}
return returnVal;

}
}
