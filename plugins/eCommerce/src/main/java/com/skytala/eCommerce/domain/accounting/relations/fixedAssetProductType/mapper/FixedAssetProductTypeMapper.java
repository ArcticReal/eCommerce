package com.skytala.eCommerce.domain.accounting.relations.fixedAssetProductType.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetProductType.model.FixedAssetProductType;

public class FixedAssetProductTypeMapper  {


	public static Map<String, Object> map(FixedAssetProductType fixedassetproducttype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(fixedassetproducttype.getFixedAssetProductTypeId() != null ){
			returnVal.put("fixedAssetProductTypeId",fixedassetproducttype.getFixedAssetProductTypeId());
}

		if(fixedassetproducttype.getDescription() != null ){
			returnVal.put("description",fixedassetproducttype.getDescription());
}

		return returnVal;
}


	public static FixedAssetProductType map(Map<String, Object> fields) {

		FixedAssetProductType returnVal = new FixedAssetProductType();

		if(fields.get("fixedAssetProductTypeId") != null) {
			returnVal.setFixedAssetProductTypeId((String) fields.get("fixedAssetProductTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static FixedAssetProductType mapstrstr(Map<String, String> fields) throws Exception {

		FixedAssetProductType returnVal = new FixedAssetProductType();

		if(fields.get("fixedAssetProductTypeId") != null) {
			returnVal.setFixedAssetProductTypeId((String) fields.get("fixedAssetProductTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static FixedAssetProductType map(GenericValue val) {

FixedAssetProductType returnVal = new FixedAssetProductType();
		returnVal.setFixedAssetProductTypeId(val.getString("fixedAssetProductTypeId"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static FixedAssetProductType map(HttpServletRequest request) throws Exception {

		FixedAssetProductType returnVal = new FixedAssetProductType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("fixedAssetProductTypeId")) {
returnVal.setFixedAssetProductTypeId(request.getParameter("fixedAssetProductTypeId"));
}

		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
