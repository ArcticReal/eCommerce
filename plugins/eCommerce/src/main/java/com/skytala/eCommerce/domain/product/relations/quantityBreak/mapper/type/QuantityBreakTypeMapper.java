package com.skytala.eCommerce.domain.product.relations.quantityBreak.mapper.type;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.quantityBreak.model.type.QuantityBreakType;

public class QuantityBreakTypeMapper  {


	public static Map<String, Object> map(QuantityBreakType quantitybreaktype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(quantitybreaktype.getQuantityBreakTypeId() != null ){
			returnVal.put("quantityBreakTypeId",quantitybreaktype.getQuantityBreakTypeId());
}

		if(quantitybreaktype.getDescription() != null ){
			returnVal.put("description",quantitybreaktype.getDescription());
}

		return returnVal;
}


	public static QuantityBreakType map(Map<String, Object> fields) {

		QuantityBreakType returnVal = new QuantityBreakType();

		if(fields.get("quantityBreakTypeId") != null) {
			returnVal.setQuantityBreakTypeId((String) fields.get("quantityBreakTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static QuantityBreakType mapstrstr(Map<String, String> fields) throws Exception {

		QuantityBreakType returnVal = new QuantityBreakType();

		if(fields.get("quantityBreakTypeId") != null) {
			returnVal.setQuantityBreakTypeId((String) fields.get("quantityBreakTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static QuantityBreakType map(GenericValue val) {

QuantityBreakType returnVal = new QuantityBreakType();
		returnVal.setQuantityBreakTypeId(val.getString("quantityBreakTypeId"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static QuantityBreakType map(HttpServletRequest request) throws Exception {

		QuantityBreakType returnVal = new QuantityBreakType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("quantityBreakTypeId")) {
returnVal.setQuantityBreakTypeId(request.getParameter("quantityBreakTypeId"));
}

		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
