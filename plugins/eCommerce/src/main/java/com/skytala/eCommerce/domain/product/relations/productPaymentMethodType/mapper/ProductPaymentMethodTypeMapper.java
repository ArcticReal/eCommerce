package com.skytala.eCommerce.domain.product.relations.productPaymentMethodType.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.productPaymentMethodType.model.ProductPaymentMethodType;

public class ProductPaymentMethodTypeMapper  {


	public static Map<String, Object> map(ProductPaymentMethodType productpaymentmethodtype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(productpaymentmethodtype.getProductId() != null ){
			returnVal.put("productId",productpaymentmethodtype.getProductId());
}

		if(productpaymentmethodtype.getPaymentMethodTypeId() != null ){
			returnVal.put("paymentMethodTypeId",productpaymentmethodtype.getPaymentMethodTypeId());
}

		if(productpaymentmethodtype.getProductPricePurposeId() != null ){
			returnVal.put("productPricePurposeId",productpaymentmethodtype.getProductPricePurposeId());
}

		if(productpaymentmethodtype.getFromDate() != null ){
			returnVal.put("fromDate",productpaymentmethodtype.getFromDate());
}

		if(productpaymentmethodtype.getThruDate() != null ){
			returnVal.put("thruDate",productpaymentmethodtype.getThruDate());
}

		if(productpaymentmethodtype.getSequenceNum() != null ){
			returnVal.put("sequenceNum",productpaymentmethodtype.getSequenceNum());
}

		return returnVal;
}


	public static ProductPaymentMethodType map(Map<String, Object> fields) {

		ProductPaymentMethodType returnVal = new ProductPaymentMethodType();

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("paymentMethodTypeId") != null) {
			returnVal.setPaymentMethodTypeId((String) fields.get("paymentMethodTypeId"));
}

		if(fields.get("productPricePurposeId") != null) {
			returnVal.setProductPricePurposeId((String) fields.get("productPricePurposeId"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}

		if(fields.get("sequenceNum") != null) {
			returnVal.setSequenceNum((long) fields.get("sequenceNum"));
}


		return returnVal;
 } 
	public static ProductPaymentMethodType mapstrstr(Map<String, String> fields) throws Exception {

		ProductPaymentMethodType returnVal = new ProductPaymentMethodType();

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("paymentMethodTypeId") != null) {
			returnVal.setPaymentMethodTypeId((String) fields.get("paymentMethodTypeId"));
}

		if(fields.get("productPricePurposeId") != null) {
			returnVal.setProductPricePurposeId((String) fields.get("productPricePurposeId"));
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

		if(fields.get("sequenceNum") != null) {
String buf;
buf = fields.get("sequenceNum");
long ibuf = Long.parseLong(buf);
			returnVal.setSequenceNum(ibuf);
}


		return returnVal;
 } 
	public static ProductPaymentMethodType map(GenericValue val) {

ProductPaymentMethodType returnVal = new ProductPaymentMethodType();
		returnVal.setProductId(val.getString("productId"));
		returnVal.setPaymentMethodTypeId(val.getString("paymentMethodTypeId"));
		returnVal.setProductPricePurposeId(val.getString("productPricePurposeId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));
		returnVal.setSequenceNum(val.getLong("sequenceNum"));


return returnVal;

}

public static ProductPaymentMethodType map(HttpServletRequest request) throws Exception {

		ProductPaymentMethodType returnVal = new ProductPaymentMethodType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("productId")) {
returnVal.setProductId(request.getParameter("productId"));
}

		if(paramMap.containsKey("paymentMethodTypeId"))  {
returnVal.setPaymentMethodTypeId(request.getParameter("paymentMethodTypeId"));
}
		if(paramMap.containsKey("productPricePurposeId"))  {
returnVal.setProductPricePurposeId(request.getParameter("productPricePurposeId"));
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
		if(paramMap.containsKey("sequenceNum"))  {
String buf = request.getParameter("sequenceNum");
Long ibuf = Long.parseLong(buf);
returnVal.setSequenceNum(ibuf);
}
return returnVal;

}
}
