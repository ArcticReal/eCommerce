package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.mapper.typeAttr;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.typeAttr.FixedAssetTypeAttr;

public class FixedAssetTypeAttrMapper  {


	public static Map<String, Object> map(FixedAssetTypeAttr fixedassettypeattr) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(fixedassettypeattr.getFixedAssetTypeId() != null ){
			returnVal.put("fixedAssetTypeId",fixedassettypeattr.getFixedAssetTypeId());
}

		if(fixedassettypeattr.getAttrName() != null ){
			returnVal.put("attrName",fixedassettypeattr.getAttrName());
}

		if(fixedassettypeattr.getDescription() != null ){
			returnVal.put("description",fixedassettypeattr.getDescription());
}

		return returnVal;
}


	public static FixedAssetTypeAttr map(Map<String, Object> fields) {

		FixedAssetTypeAttr returnVal = new FixedAssetTypeAttr();

		if(fields.get("fixedAssetTypeId") != null) {
			returnVal.setFixedAssetTypeId((String) fields.get("fixedAssetTypeId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static FixedAssetTypeAttr mapstrstr(Map<String, String> fields) throws Exception {

		FixedAssetTypeAttr returnVal = new FixedAssetTypeAttr();

		if(fields.get("fixedAssetTypeId") != null) {
			returnVal.setFixedAssetTypeId((String) fields.get("fixedAssetTypeId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static FixedAssetTypeAttr map(GenericValue val) {

FixedAssetTypeAttr returnVal = new FixedAssetTypeAttr();
		returnVal.setFixedAssetTypeId(val.getString("fixedAssetTypeId"));
		returnVal.setAttrName(val.getString("attrName"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static FixedAssetTypeAttr map(HttpServletRequest request) throws Exception {

		FixedAssetTypeAttr returnVal = new FixedAssetTypeAttr();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("fixedAssetTypeId")) {
returnVal.setFixedAssetTypeId(request.getParameter("fixedAssetTypeId"));
}

		if(paramMap.containsKey("attrName"))  {
returnVal.setAttrName(request.getParameter("attrName"));
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
