package com.skytala.eCommerce.domain.product.relations.productStoreVendorShipment.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.productStoreVendorShipment.model.ProductStoreVendorShipment;

public class ProductStoreVendorShipmentMapper  {


	public static Map<String, Object> map(ProductStoreVendorShipment productstorevendorshipment) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(productstorevendorshipment.getProductStoreId() != null ){
			returnVal.put("productStoreId",productstorevendorshipment.getProductStoreId());
}

		if(productstorevendorshipment.getVendorPartyId() != null ){
			returnVal.put("vendorPartyId",productstorevendorshipment.getVendorPartyId());
}

		if(productstorevendorshipment.getShipmentMethodTypeId() != null ){
			returnVal.put("shipmentMethodTypeId",productstorevendorshipment.getShipmentMethodTypeId());
}

		if(productstorevendorshipment.getCarrierPartyId() != null ){
			returnVal.put("carrierPartyId",productstorevendorshipment.getCarrierPartyId());
}

		return returnVal;
}


	public static ProductStoreVendorShipment map(Map<String, Object> fields) {

		ProductStoreVendorShipment returnVal = new ProductStoreVendorShipment();

		if(fields.get("productStoreId") != null) {
			returnVal.setProductStoreId((String) fields.get("productStoreId"));
}

		if(fields.get("vendorPartyId") != null) {
			returnVal.setVendorPartyId((String) fields.get("vendorPartyId"));
}

		if(fields.get("shipmentMethodTypeId") != null) {
			returnVal.setShipmentMethodTypeId((String) fields.get("shipmentMethodTypeId"));
}

		if(fields.get("carrierPartyId") != null) {
			returnVal.setCarrierPartyId((String) fields.get("carrierPartyId"));
}


		return returnVal;
 } 
	public static ProductStoreVendorShipment mapstrstr(Map<String, String> fields) throws Exception {

		ProductStoreVendorShipment returnVal = new ProductStoreVendorShipment();

		if(fields.get("productStoreId") != null) {
			returnVal.setProductStoreId((String) fields.get("productStoreId"));
}

		if(fields.get("vendorPartyId") != null) {
			returnVal.setVendorPartyId((String) fields.get("vendorPartyId"));
}

		if(fields.get("shipmentMethodTypeId") != null) {
			returnVal.setShipmentMethodTypeId((String) fields.get("shipmentMethodTypeId"));
}

		if(fields.get("carrierPartyId") != null) {
			returnVal.setCarrierPartyId((String) fields.get("carrierPartyId"));
}


		return returnVal;
 } 
	public static ProductStoreVendorShipment map(GenericValue val) {

ProductStoreVendorShipment returnVal = new ProductStoreVendorShipment();
		returnVal.setProductStoreId(val.getString("productStoreId"));
		returnVal.setVendorPartyId(val.getString("vendorPartyId"));
		returnVal.setShipmentMethodTypeId(val.getString("shipmentMethodTypeId"));
		returnVal.setCarrierPartyId(val.getString("carrierPartyId"));


return returnVal;

}

public static ProductStoreVendorShipment map(HttpServletRequest request) throws Exception {

		ProductStoreVendorShipment returnVal = new ProductStoreVendorShipment();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("productStoreId")) {
returnVal.setProductStoreId(request.getParameter("productStoreId"));
}

		if(paramMap.containsKey("vendorPartyId"))  {
returnVal.setVendorPartyId(request.getParameter("vendorPartyId"));
}
		if(paramMap.containsKey("shipmentMethodTypeId"))  {
returnVal.setShipmentMethodTypeId(request.getParameter("shipmentMethodTypeId"));
}
		if(paramMap.containsKey("carrierPartyId"))  {
returnVal.setCarrierPartyId(request.getParameter("carrierPartyId"));
}
return returnVal;

}
}
