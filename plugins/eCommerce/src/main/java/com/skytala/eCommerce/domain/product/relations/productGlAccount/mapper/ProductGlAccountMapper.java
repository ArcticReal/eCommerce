package com.skytala.eCommerce.domain.product.relations.productGlAccount.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.productGlAccount.model.ProductGlAccount;

public class ProductGlAccountMapper  {


	public static Map<String, Object> map(ProductGlAccount productglaccount) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(productglaccount.getProductId() != null ){
			returnVal.put("productId",productglaccount.getProductId());
}

		if(productglaccount.getOrganizationPartyId() != null ){
			returnVal.put("organizationPartyId",productglaccount.getOrganizationPartyId());
}

		if(productglaccount.getGlAccountTypeId() != null ){
			returnVal.put("glAccountTypeId",productglaccount.getGlAccountTypeId());
}

		if(productglaccount.getGlAccountId() != null ){
			returnVal.put("glAccountId",productglaccount.getGlAccountId());
}

		return returnVal;
}


	public static ProductGlAccount map(Map<String, Object> fields) {

		ProductGlAccount returnVal = new ProductGlAccount();

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("organizationPartyId") != null) {
			returnVal.setOrganizationPartyId((String) fields.get("organizationPartyId"));
}

		if(fields.get("glAccountTypeId") != null) {
			returnVal.setGlAccountTypeId((String) fields.get("glAccountTypeId"));
}

		if(fields.get("glAccountId") != null) {
			returnVal.setGlAccountId((String) fields.get("glAccountId"));
}


		return returnVal;
 } 
	public static ProductGlAccount mapstrstr(Map<String, String> fields) throws Exception {

		ProductGlAccount returnVal = new ProductGlAccount();

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("organizationPartyId") != null) {
			returnVal.setOrganizationPartyId((String) fields.get("organizationPartyId"));
}

		if(fields.get("glAccountTypeId") != null) {
			returnVal.setGlAccountTypeId((String) fields.get("glAccountTypeId"));
}

		if(fields.get("glAccountId") != null) {
			returnVal.setGlAccountId((String) fields.get("glAccountId"));
}


		return returnVal;
 } 
	public static ProductGlAccount map(GenericValue val) {

ProductGlAccount returnVal = new ProductGlAccount();
		returnVal.setProductId(val.getString("productId"));
		returnVal.setOrganizationPartyId(val.getString("organizationPartyId"));
		returnVal.setGlAccountTypeId(val.getString("glAccountTypeId"));
		returnVal.setGlAccountId(val.getString("glAccountId"));


return returnVal;

}

public static ProductGlAccount map(HttpServletRequest request) throws Exception {

		ProductGlAccount returnVal = new ProductGlAccount();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("productId")) {
returnVal.setProductId(request.getParameter("productId"));
}

		if(paramMap.containsKey("organizationPartyId"))  {
returnVal.setOrganizationPartyId(request.getParameter("organizationPartyId"));
}
		if(paramMap.containsKey("glAccountTypeId"))  {
returnVal.setGlAccountTypeId(request.getParameter("glAccountTypeId"));
}
		if(paramMap.containsKey("glAccountId"))  {
returnVal.setGlAccountId(request.getParameter("glAccountId"));
}
return returnVal;

}
}
