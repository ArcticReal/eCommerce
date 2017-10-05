package com.skytala.eCommerce.domain.saleType.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.saleType.model.SaleType;

public class SaleTypeMapper  {


	public static Map<String, Object> map(SaleType saletype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(saletype.getSaleTypeId() != null ){
			returnVal.put("saleTypeId",saletype.getSaleTypeId());
}

		if(saletype.getDescription() != null ){
			returnVal.put("description",saletype.getDescription());
}

		return returnVal;
}


	public static SaleType map(Map<String, Object> fields) {

		SaleType returnVal = new SaleType();

		if(fields.get("saleTypeId") != null) {
			returnVal.setSaleTypeId((String) fields.get("saleTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static SaleType mapstrstr(Map<String, String> fields) throws Exception {

		SaleType returnVal = new SaleType();

		if(fields.get("saleTypeId") != null) {
			returnVal.setSaleTypeId((String) fields.get("saleTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static SaleType map(GenericValue val) {

SaleType returnVal = new SaleType();
		returnVal.setSaleTypeId(val.getString("saleTypeId"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static SaleType map(HttpServletRequest request) throws Exception {

		SaleType returnVal = new SaleType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("saleTypeId")) {
returnVal.setSaleTypeId(request.getParameter("saleTypeId"));
}

		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
