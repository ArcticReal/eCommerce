package com.skytala.eCommerce.domain.product.relations.product.mapper.storeVendorPayment;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.product.model.storeVendorPayment.ProductStoreVendorPayment;

public class ProductStoreVendorPaymentMapper  {


	public static Map<String, Object> map(ProductStoreVendorPayment productstorevendorpayment) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(productstorevendorpayment.getProductStoreId() != null ){
			returnVal.put("productStoreId",productstorevendorpayment.getProductStoreId());
}

		if(productstorevendorpayment.getVendorPartyId() != null ){
			returnVal.put("vendorPartyId",productstorevendorpayment.getVendorPartyId());
}

		if(productstorevendorpayment.getPaymentMethodTypeId() != null ){
			returnVal.put("paymentMethodTypeId",productstorevendorpayment.getPaymentMethodTypeId());
}

		if(productstorevendorpayment.getCreditCardEnumId() != null ){
			returnVal.put("creditCardEnumId",productstorevendorpayment.getCreditCardEnumId());
}

		return returnVal;
}


	public static ProductStoreVendorPayment map(Map<String, Object> fields) {

		ProductStoreVendorPayment returnVal = new ProductStoreVendorPayment();

		if(fields.get("productStoreId") != null) {
			returnVal.setProductStoreId((String) fields.get("productStoreId"));
}

		if(fields.get("vendorPartyId") != null) {
			returnVal.setVendorPartyId((String) fields.get("vendorPartyId"));
}

		if(fields.get("paymentMethodTypeId") != null) {
			returnVal.setPaymentMethodTypeId((String) fields.get("paymentMethodTypeId"));
}

		if(fields.get("creditCardEnumId") != null) {
			returnVal.setCreditCardEnumId((String) fields.get("creditCardEnumId"));
}


		return returnVal;
 } 
	public static ProductStoreVendorPayment mapstrstr(Map<String, String> fields) throws Exception {

		ProductStoreVendorPayment returnVal = new ProductStoreVendorPayment();

		if(fields.get("productStoreId") != null) {
			returnVal.setProductStoreId((String) fields.get("productStoreId"));
}

		if(fields.get("vendorPartyId") != null) {
			returnVal.setVendorPartyId((String) fields.get("vendorPartyId"));
}

		if(fields.get("paymentMethodTypeId") != null) {
			returnVal.setPaymentMethodTypeId((String) fields.get("paymentMethodTypeId"));
}

		if(fields.get("creditCardEnumId") != null) {
			returnVal.setCreditCardEnumId((String) fields.get("creditCardEnumId"));
}


		return returnVal;
 } 
	public static ProductStoreVendorPayment map(GenericValue val) {

ProductStoreVendorPayment returnVal = new ProductStoreVendorPayment();
		returnVal.setProductStoreId(val.getString("productStoreId"));
		returnVal.setVendorPartyId(val.getString("vendorPartyId"));
		returnVal.setPaymentMethodTypeId(val.getString("paymentMethodTypeId"));
		returnVal.setCreditCardEnumId(val.getString("creditCardEnumId"));


return returnVal;

}

public static ProductStoreVendorPayment map(HttpServletRequest request) throws Exception {

		ProductStoreVendorPayment returnVal = new ProductStoreVendorPayment();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("productStoreId")) {
returnVal.setProductStoreId(request.getParameter("productStoreId"));
}

		if(paramMap.containsKey("vendorPartyId"))  {
returnVal.setVendorPartyId(request.getParameter("vendorPartyId"));
}
		if(paramMap.containsKey("paymentMethodTypeId"))  {
returnVal.setPaymentMethodTypeId(request.getParameter("paymentMethodTypeId"));
}
		if(paramMap.containsKey("creditCardEnumId"))  {
returnVal.setCreditCardEnumId(request.getParameter("creditCardEnumId"));
}
return returnVal;

}
}
