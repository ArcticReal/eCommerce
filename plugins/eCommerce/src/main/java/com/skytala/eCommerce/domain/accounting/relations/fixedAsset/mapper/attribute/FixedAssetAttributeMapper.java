package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.mapper.attribute;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.attribute.FixedAssetAttribute;

public class FixedAssetAttributeMapper  {


	public static Map<String, Object> map(FixedAssetAttribute fixedassetattribute) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(fixedassetattribute.getFixedAssetId() != null ){
			returnVal.put("fixedAssetId",fixedassetattribute.getFixedAssetId());
}

		if(fixedassetattribute.getAttrName() != null ){
			returnVal.put("attrName",fixedassetattribute.getAttrName());
}

		if(fixedassetattribute.getAttrValue() != null ){
			returnVal.put("attrValue",fixedassetattribute.getAttrValue());
}

		if(fixedassetattribute.getAttrDescription() != null ){
			returnVal.put("attrDescription",fixedassetattribute.getAttrDescription());
}

		return returnVal;
}


	public static FixedAssetAttribute map(Map<String, Object> fields) {

		FixedAssetAttribute returnVal = new FixedAssetAttribute();

		if(fields.get("fixedAssetId") != null) {
			returnVal.setFixedAssetId((String) fields.get("fixedAssetId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("attrValue") != null) {
			returnVal.setAttrValue((String) fields.get("attrValue"));
}

		if(fields.get("attrDescription") != null) {
			returnVal.setAttrDescription((String) fields.get("attrDescription"));
}


		return returnVal;
 } 
	public static FixedAssetAttribute mapstrstr(Map<String, String> fields) throws Exception {

		FixedAssetAttribute returnVal = new FixedAssetAttribute();

		if(fields.get("fixedAssetId") != null) {
			returnVal.setFixedAssetId((String) fields.get("fixedAssetId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("attrValue") != null) {
			returnVal.setAttrValue((String) fields.get("attrValue"));
}

		if(fields.get("attrDescription") != null) {
			returnVal.setAttrDescription((String) fields.get("attrDescription"));
}


		return returnVal;
 } 
	public static FixedAssetAttribute map(GenericValue val) {

FixedAssetAttribute returnVal = new FixedAssetAttribute();
		returnVal.setFixedAssetId(val.getString("fixedAssetId"));
		returnVal.setAttrName(val.getString("attrName"));
		returnVal.setAttrValue(val.getString("attrValue"));
		returnVal.setAttrDescription(val.getString("attrDescription"));


return returnVal;

}

public static FixedAssetAttribute map(HttpServletRequest request) throws Exception {

		FixedAssetAttribute returnVal = new FixedAssetAttribute();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("fixedAssetId")) {
returnVal.setFixedAssetId(request.getParameter("fixedAssetId"));
}

		if(paramMap.containsKey("attrName"))  {
returnVal.setAttrName(request.getParameter("attrName"));
}
		if(paramMap.containsKey("attrValue"))  {
returnVal.setAttrValue(request.getParameter("attrValue"));
}
		if(paramMap.containsKey("attrDescription"))  {
returnVal.setAttrDescription(request.getParameter("attrDescription"));
}
return returnVal;

}
}
