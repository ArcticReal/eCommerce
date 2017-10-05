package com.skytala.eCommerce.domain.taxAuthorityRateType.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.taxAuthorityRateType.model.TaxAuthorityRateType;

public class TaxAuthorityRateTypeMapper  {


	public static Map<String, Object> map(TaxAuthorityRateType taxauthorityratetype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(taxauthorityratetype.getTaxAuthorityRateTypeId() != null ){
			returnVal.put("taxAuthorityRateTypeId",taxauthorityratetype.getTaxAuthorityRateTypeId());
}

		if(taxauthorityratetype.getDescription() != null ){
			returnVal.put("description",taxauthorityratetype.getDescription());
}

		return returnVal;
}


	public static TaxAuthorityRateType map(Map<String, Object> fields) {

		TaxAuthorityRateType returnVal = new TaxAuthorityRateType();

		if(fields.get("taxAuthorityRateTypeId") != null) {
			returnVal.setTaxAuthorityRateTypeId((String) fields.get("taxAuthorityRateTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static TaxAuthorityRateType mapstrstr(Map<String, String> fields) throws Exception {

		TaxAuthorityRateType returnVal = new TaxAuthorityRateType();

		if(fields.get("taxAuthorityRateTypeId") != null) {
			returnVal.setTaxAuthorityRateTypeId((String) fields.get("taxAuthorityRateTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static TaxAuthorityRateType map(GenericValue val) {

TaxAuthorityRateType returnVal = new TaxAuthorityRateType();
		returnVal.setTaxAuthorityRateTypeId(val.getString("taxAuthorityRateTypeId"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static TaxAuthorityRateType map(HttpServletRequest request) throws Exception {

		TaxAuthorityRateType returnVal = new TaxAuthorityRateType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("taxAuthorityRateTypeId")) {
returnVal.setTaxAuthorityRateTypeId(request.getParameter("taxAuthorityRateTypeId"));
}

		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
