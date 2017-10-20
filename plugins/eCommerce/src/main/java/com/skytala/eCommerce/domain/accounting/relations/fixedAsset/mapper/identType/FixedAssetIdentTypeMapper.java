package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.mapper.identType;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.identType.FixedAssetIdentType;

public class FixedAssetIdentTypeMapper  {


	public static Map<String, Object> map(FixedAssetIdentType fixedassetidenttype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(fixedassetidenttype.getFixedAssetIdentTypeId() != null ){
			returnVal.put("fixedAssetIdentTypeId",fixedassetidenttype.getFixedAssetIdentTypeId());
}

		if(fixedassetidenttype.getDescription() != null ){
			returnVal.put("description",fixedassetidenttype.getDescription());
}

		return returnVal;
}


	public static FixedAssetIdentType map(Map<String, Object> fields) {

		FixedAssetIdentType returnVal = new FixedAssetIdentType();

		if(fields.get("fixedAssetIdentTypeId") != null) {
			returnVal.setFixedAssetIdentTypeId((String) fields.get("fixedAssetIdentTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static FixedAssetIdentType mapstrstr(Map<String, String> fields) throws Exception {

		FixedAssetIdentType returnVal = new FixedAssetIdentType();

		if(fields.get("fixedAssetIdentTypeId") != null) {
			returnVal.setFixedAssetIdentTypeId((String) fields.get("fixedAssetIdentTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static FixedAssetIdentType map(GenericValue val) {

FixedAssetIdentType returnVal = new FixedAssetIdentType();
		returnVal.setFixedAssetIdentTypeId(val.getString("fixedAssetIdentTypeId"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static FixedAssetIdentType map(HttpServletRequest request) throws Exception {

		FixedAssetIdentType returnVal = new FixedAssetIdentType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("fixedAssetIdentTypeId")) {
returnVal.setFixedAssetIdentTypeId(request.getParameter("fixedAssetIdentTypeId"));
}

		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
