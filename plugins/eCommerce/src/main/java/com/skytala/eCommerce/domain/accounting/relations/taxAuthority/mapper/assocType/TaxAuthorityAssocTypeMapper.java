package com.skytala.eCommerce.domain.accounting.relations.taxAuthority.mapper.assocType;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.model.assocType.TaxAuthorityAssocType;

public class TaxAuthorityAssocTypeMapper  {


	public static Map<String, Object> map(TaxAuthorityAssocType taxauthorityassoctype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(taxauthorityassoctype.getTaxAuthorityAssocTypeId() != null ){
			returnVal.put("taxAuthorityAssocTypeId",taxauthorityassoctype.getTaxAuthorityAssocTypeId());
}

		if(taxauthorityassoctype.getDescription() != null ){
			returnVal.put("description",taxauthorityassoctype.getDescription());
}

		return returnVal;
}


	public static TaxAuthorityAssocType map(Map<String, Object> fields) {

		TaxAuthorityAssocType returnVal = new TaxAuthorityAssocType();

		if(fields.get("taxAuthorityAssocTypeId") != null) {
			returnVal.setTaxAuthorityAssocTypeId((String) fields.get("taxAuthorityAssocTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static TaxAuthorityAssocType mapstrstr(Map<String, String> fields) throws Exception {

		TaxAuthorityAssocType returnVal = new TaxAuthorityAssocType();

		if(fields.get("taxAuthorityAssocTypeId") != null) {
			returnVal.setTaxAuthorityAssocTypeId((String) fields.get("taxAuthorityAssocTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static TaxAuthorityAssocType map(GenericValue val) {

TaxAuthorityAssocType returnVal = new TaxAuthorityAssocType();
		returnVal.setTaxAuthorityAssocTypeId(val.getString("taxAuthorityAssocTypeId"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static TaxAuthorityAssocType map(HttpServletRequest request) throws Exception {

		TaxAuthorityAssocType returnVal = new TaxAuthorityAssocType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("taxAuthorityAssocTypeId")) {
returnVal.setTaxAuthorityAssocTypeId(request.getParameter("taxAuthorityAssocTypeId"));
}

		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
