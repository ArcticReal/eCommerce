package com.skytala.eCommerce.domain.accounting.relations.taxAuthority.mapper.category;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.model.category.TaxAuthorityCategory;

public class TaxAuthorityCategoryMapper  {


	public static Map<String, Object> map(TaxAuthorityCategory taxauthoritycategory) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(taxauthoritycategory.getTaxAuthGeoId() != null ){
			returnVal.put("taxAuthGeoId",taxauthoritycategory.getTaxAuthGeoId());
}

		if(taxauthoritycategory.getTaxAuthPartyId() != null ){
			returnVal.put("taxAuthPartyId",taxauthoritycategory.getTaxAuthPartyId());
}

		if(taxauthoritycategory.getProductCategoryId() != null ){
			returnVal.put("productCategoryId",taxauthoritycategory.getProductCategoryId());
}

		return returnVal;
}


	public static TaxAuthorityCategory map(Map<String, Object> fields) {

		TaxAuthorityCategory returnVal = new TaxAuthorityCategory();

		if(fields.get("taxAuthGeoId") != null) {
			returnVal.setTaxAuthGeoId((String) fields.get("taxAuthGeoId"));
}

		if(fields.get("taxAuthPartyId") != null) {
			returnVal.setTaxAuthPartyId((String) fields.get("taxAuthPartyId"));
}

		if(fields.get("productCategoryId") != null) {
			returnVal.setProductCategoryId((String) fields.get("productCategoryId"));
}


		return returnVal;
 } 
	public static TaxAuthorityCategory mapstrstr(Map<String, String> fields) throws Exception {

		TaxAuthorityCategory returnVal = new TaxAuthorityCategory();

		if(fields.get("taxAuthGeoId") != null) {
			returnVal.setTaxAuthGeoId((String) fields.get("taxAuthGeoId"));
}

		if(fields.get("taxAuthPartyId") != null) {
			returnVal.setTaxAuthPartyId((String) fields.get("taxAuthPartyId"));
}

		if(fields.get("productCategoryId") != null) {
			returnVal.setProductCategoryId((String) fields.get("productCategoryId"));
}


		return returnVal;
 } 
	public static TaxAuthorityCategory map(GenericValue val) {

TaxAuthorityCategory returnVal = new TaxAuthorityCategory();
		returnVal.setTaxAuthGeoId(val.getString("taxAuthGeoId"));
		returnVal.setTaxAuthPartyId(val.getString("taxAuthPartyId"));
		returnVal.setProductCategoryId(val.getString("productCategoryId"));


return returnVal;

}

public static TaxAuthorityCategory map(HttpServletRequest request) throws Exception {

		TaxAuthorityCategory returnVal = new TaxAuthorityCategory();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("taxAuthGeoId")) {
returnVal.setTaxAuthGeoId(request.getParameter("taxAuthGeoId"));
}

		if(paramMap.containsKey("taxAuthPartyId"))  {
returnVal.setTaxAuthPartyId(request.getParameter("taxAuthPartyId"));
}
		if(paramMap.containsKey("productCategoryId"))  {
returnVal.setProductCategoryId(request.getParameter("productCategoryId"));
}
return returnVal;

}
}
