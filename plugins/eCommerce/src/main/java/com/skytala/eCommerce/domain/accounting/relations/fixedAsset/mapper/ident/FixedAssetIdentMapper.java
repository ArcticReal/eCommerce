package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.mapper.ident;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.ident.FixedAssetIdent;

public class FixedAssetIdentMapper  {


	public static Map<String, Object> map(FixedAssetIdent fixedassetident) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(fixedassetident.getFixedAssetId() != null ){
			returnVal.put("fixedAssetId",fixedassetident.getFixedAssetId());
}

		if(fixedassetident.getFixedAssetIdentTypeId() != null ){
			returnVal.put("fixedAssetIdentTypeId",fixedassetident.getFixedAssetIdentTypeId());
}

		if(fixedassetident.getIdValue() != null ){
			returnVal.put("idValue",fixedassetident.getIdValue());
}

		return returnVal;
}


	public static FixedAssetIdent map(Map<String, Object> fields) {

		FixedAssetIdent returnVal = new FixedAssetIdent();

		if(fields.get("fixedAssetId") != null) {
			returnVal.setFixedAssetId((String) fields.get("fixedAssetId"));
}

		if(fields.get("fixedAssetIdentTypeId") != null) {
			returnVal.setFixedAssetIdentTypeId((String) fields.get("fixedAssetIdentTypeId"));
}

		if(fields.get("idValue") != null) {
			returnVal.setIdValue((String) fields.get("idValue"));
}


		return returnVal;
 } 
	public static FixedAssetIdent mapstrstr(Map<String, String> fields) throws Exception {

		FixedAssetIdent returnVal = new FixedAssetIdent();

		if(fields.get("fixedAssetId") != null) {
			returnVal.setFixedAssetId((String) fields.get("fixedAssetId"));
}

		if(fields.get("fixedAssetIdentTypeId") != null) {
			returnVal.setFixedAssetIdentTypeId((String) fields.get("fixedAssetIdentTypeId"));
}

		if(fields.get("idValue") != null) {
			returnVal.setIdValue((String) fields.get("idValue"));
}


		return returnVal;
 } 
	public static FixedAssetIdent map(GenericValue val) {

FixedAssetIdent returnVal = new FixedAssetIdent();
		returnVal.setFixedAssetId(val.getString("fixedAssetId"));
		returnVal.setFixedAssetIdentTypeId(val.getString("fixedAssetIdentTypeId"));
		returnVal.setIdValue(val.getString("idValue"));


return returnVal;

}

public static FixedAssetIdent map(HttpServletRequest request) throws Exception {

		FixedAssetIdent returnVal = new FixedAssetIdent();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("fixedAssetId")) {
returnVal.setFixedAssetId(request.getParameter("fixedAssetId"));
}

		if(paramMap.containsKey("fixedAssetIdentTypeId"))  {
returnVal.setFixedAssetIdentTypeId(request.getParameter("fixedAssetIdentTypeId"));
}
		if(paramMap.containsKey("idValue"))  {
returnVal.setIdValue(request.getParameter("idValue"));
}
return returnVal;

}
}
