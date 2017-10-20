package com.skytala.eCommerce.domain.product.relations.product.mapper.featurePrice;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.product.model.featurePrice.ProductFeaturePrice;

public class ProductFeaturePriceMapper  {


	public static Map<String, Object> map(ProductFeaturePrice productfeatureprice) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(productfeatureprice.getProductFeatureId() != null ){
			returnVal.put("productFeatureId",productfeatureprice.getProductFeatureId());
}

		if(productfeatureprice.getProductPriceTypeId() != null ){
			returnVal.put("productPriceTypeId",productfeatureprice.getProductPriceTypeId());
}

		if(productfeatureprice.getCurrencyUomId() != null ){
			returnVal.put("currencyUomId",productfeatureprice.getCurrencyUomId());
}

		if(productfeatureprice.getFromDate() != null ){
			returnVal.put("fromDate",productfeatureprice.getFromDate());
}

		if(productfeatureprice.getThruDate() != null ){
			returnVal.put("thruDate",productfeatureprice.getThruDate());
}

		if(productfeatureprice.getPrice() != null ){
			returnVal.put("price",productfeatureprice.getPrice());
}

		if(productfeatureprice.getCreatedDate() != null ){
			returnVal.put("createdDate",productfeatureprice.getCreatedDate());
}

		if(productfeatureprice.getCreatedByUserLogin() != null ){
			returnVal.put("createdByUserLogin",productfeatureprice.getCreatedByUserLogin());
}

		if(productfeatureprice.getLastModifiedDate() != null ){
			returnVal.put("lastModifiedDate",productfeatureprice.getLastModifiedDate());
}

		if(productfeatureprice.getLastModifiedByUserLogin() != null ){
			returnVal.put("lastModifiedByUserLogin",productfeatureprice.getLastModifiedByUserLogin());
}

		return returnVal;
}


	public static ProductFeaturePrice map(Map<String, Object> fields) {

		ProductFeaturePrice returnVal = new ProductFeaturePrice();

		if(fields.get("productFeatureId") != null) {
			returnVal.setProductFeatureId((String) fields.get("productFeatureId"));
}

		if(fields.get("productPriceTypeId") != null) {
			returnVal.setProductPriceTypeId((String) fields.get("productPriceTypeId"));
}

		if(fields.get("currencyUomId") != null) {
			returnVal.setCurrencyUomId((String) fields.get("currencyUomId"));
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
	public static ProductFeaturePrice mapstrstr(Map<String, String> fields) throws Exception {

		ProductFeaturePrice returnVal = new ProductFeaturePrice();

		if(fields.get("productFeatureId") != null) {
			returnVal.setProductFeatureId((String) fields.get("productFeatureId"));
}

		if(fields.get("productPriceTypeId") != null) {
			returnVal.setProductPriceTypeId((String) fields.get("productPriceTypeId"));
}

		if(fields.get("currencyUomId") != null) {
			returnVal.setCurrencyUomId((String) fields.get("currencyUomId"));
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
	public static ProductFeaturePrice map(GenericValue val) {

ProductFeaturePrice returnVal = new ProductFeaturePrice();
		returnVal.setProductFeatureId(val.getString("productFeatureId"));
		returnVal.setProductPriceTypeId(val.getString("productPriceTypeId"));
		returnVal.setCurrencyUomId(val.getString("currencyUomId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));
		returnVal.setPrice(val.getBigDecimal("price"));
		returnVal.setCreatedDate(val.getTimestamp("createdDate"));
		returnVal.setCreatedByUserLogin(val.getString("createdByUserLogin"));
		returnVal.setLastModifiedDate(val.getTimestamp("lastModifiedDate"));
		returnVal.setLastModifiedByUserLogin(val.getString("lastModifiedByUserLogin"));


return returnVal;

}

public static ProductFeaturePrice map(HttpServletRequest request) throws Exception {

		ProductFeaturePrice returnVal = new ProductFeaturePrice();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("productFeatureId")) {
returnVal.setProductFeatureId(request.getParameter("productFeatureId"));
}

		if(paramMap.containsKey("productPriceTypeId"))  {
returnVal.setProductPriceTypeId(request.getParameter("productPriceTypeId"));
}
		if(paramMap.containsKey("currencyUomId"))  {
returnVal.setCurrencyUomId(request.getParameter("currencyUomId"));
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
