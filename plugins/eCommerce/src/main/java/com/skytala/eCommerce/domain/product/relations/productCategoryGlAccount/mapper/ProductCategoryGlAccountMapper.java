package com.skytala.eCommerce.domain.product.relations.productCategoryGlAccount.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.productCategoryGlAccount.model.ProductCategoryGlAccount;

public class ProductCategoryGlAccountMapper  {


	public static Map<String, Object> map(ProductCategoryGlAccount productcategoryglaccount) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(productcategoryglaccount.getProductCategoryId() != null ){
			returnVal.put("productCategoryId",productcategoryglaccount.getProductCategoryId());
}

		if(productcategoryglaccount.getOrganizationPartyId() != null ){
			returnVal.put("organizationPartyId",productcategoryglaccount.getOrganizationPartyId());
}

		if(productcategoryglaccount.getGlAccountTypeId() != null ){
			returnVal.put("glAccountTypeId",productcategoryglaccount.getGlAccountTypeId());
}

		if(productcategoryglaccount.getGlAccountId() != null ){
			returnVal.put("glAccountId",productcategoryglaccount.getGlAccountId());
}

		return returnVal;
}


	public static ProductCategoryGlAccount map(Map<String, Object> fields) {

		ProductCategoryGlAccount returnVal = new ProductCategoryGlAccount();

		if(fields.get("productCategoryId") != null) {
			returnVal.setProductCategoryId((String) fields.get("productCategoryId"));
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
	public static ProductCategoryGlAccount mapstrstr(Map<String, String> fields) throws Exception {

		ProductCategoryGlAccount returnVal = new ProductCategoryGlAccount();

		if(fields.get("productCategoryId") != null) {
			returnVal.setProductCategoryId((String) fields.get("productCategoryId"));
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
	public static ProductCategoryGlAccount map(GenericValue val) {

ProductCategoryGlAccount returnVal = new ProductCategoryGlAccount();
		returnVal.setProductCategoryId(val.getString("productCategoryId"));
		returnVal.setOrganizationPartyId(val.getString("organizationPartyId"));
		returnVal.setGlAccountTypeId(val.getString("glAccountTypeId"));
		returnVal.setGlAccountId(val.getString("glAccountId"));


return returnVal;

}

public static ProductCategoryGlAccount map(HttpServletRequest request) throws Exception {

		ProductCategoryGlAccount returnVal = new ProductCategoryGlAccount();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("productCategoryId")) {
returnVal.setProductCategoryId(request.getParameter("productCategoryId"));
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
