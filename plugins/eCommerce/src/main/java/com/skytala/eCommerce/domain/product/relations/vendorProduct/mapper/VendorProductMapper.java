package com.skytala.eCommerce.domain.product.relations.vendorProduct.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.vendorProduct.model.VendorProduct;

public class VendorProductMapper  {


	public static Map<String, Object> map(VendorProduct vendorproduct) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(vendorproduct.getProductId() != null ){
			returnVal.put("productId",vendorproduct.getProductId());
}

		if(vendorproduct.getVendorPartyId() != null ){
			returnVal.put("vendorPartyId",vendorproduct.getVendorPartyId());
}

		if(vendorproduct.getProductStoreGroupId() != null ){
			returnVal.put("productStoreGroupId",vendorproduct.getProductStoreGroupId());
}

		return returnVal;
}


	public static VendorProduct map(Map<String, Object> fields) {

		VendorProduct returnVal = new VendorProduct();

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("vendorPartyId") != null) {
			returnVal.setVendorPartyId((String) fields.get("vendorPartyId"));
}

		if(fields.get("productStoreGroupId") != null) {
			returnVal.setProductStoreGroupId((String) fields.get("productStoreGroupId"));
}


		return returnVal;
 } 
	public static VendorProduct mapstrstr(Map<String, String> fields) throws Exception {

		VendorProduct returnVal = new VendorProduct();

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("vendorPartyId") != null) {
			returnVal.setVendorPartyId((String) fields.get("vendorPartyId"));
}

		if(fields.get("productStoreGroupId") != null) {
			returnVal.setProductStoreGroupId((String) fields.get("productStoreGroupId"));
}


		return returnVal;
 } 
	public static VendorProduct map(GenericValue val) {

VendorProduct returnVal = new VendorProduct();
		returnVal.setProductId(val.getString("productId"));
		returnVal.setVendorPartyId(val.getString("vendorPartyId"));
		returnVal.setProductStoreGroupId(val.getString("productStoreGroupId"));


return returnVal;

}

public static VendorProduct map(HttpServletRequest request) throws Exception {

		VendorProduct returnVal = new VendorProduct();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("productId")) {
returnVal.setProductId(request.getParameter("productId"));
}

		if(paramMap.containsKey("vendorPartyId"))  {
returnVal.setVendorPartyId(request.getParameter("vendorPartyId"));
}
		if(paramMap.containsKey("productStoreGroupId"))  {
returnVal.setProductStoreGroupId(request.getParameter("productStoreGroupId"));
}
return returnVal;

}
}
