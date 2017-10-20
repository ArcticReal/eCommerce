package com.skytala.eCommerce.domain.product.relations.costComponent.mapper.attribute;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.costComponent.model.attribute.CostComponentAttribute;

public class CostComponentAttributeMapper  {


	public static Map<String, Object> map(CostComponentAttribute costcomponentattribute) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(costcomponentattribute.getCostComponentId() != null ){
			returnVal.put("costComponentId",costcomponentattribute.getCostComponentId());
}

		if(costcomponentattribute.getAttrName() != null ){
			returnVal.put("attrName",costcomponentattribute.getAttrName());
}

		if(costcomponentattribute.getAttrValue() != null ){
			returnVal.put("attrValue",costcomponentattribute.getAttrValue());
}

		if(costcomponentattribute.getAttrDescription() != null ){
			returnVal.put("attrDescription",costcomponentattribute.getAttrDescription());
}

		return returnVal;
}


	public static CostComponentAttribute map(Map<String, Object> fields) {

		CostComponentAttribute returnVal = new CostComponentAttribute();

		if(fields.get("costComponentId") != null) {
			returnVal.setCostComponentId((String) fields.get("costComponentId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("attrValue") != null) {
			returnVal.setAttrValue((long) fields.get("attrValue"));
}

		if(fields.get("attrDescription") != null) {
			returnVal.setAttrDescription((String) fields.get("attrDescription"));
}


		return returnVal;
 } 
	public static CostComponentAttribute mapstrstr(Map<String, String> fields) throws Exception {

		CostComponentAttribute returnVal = new CostComponentAttribute();

		if(fields.get("costComponentId") != null) {
			returnVal.setCostComponentId((String) fields.get("costComponentId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("attrValue") != null) {
String buf;
buf = fields.get("attrValue");
long ibuf = Long.parseLong(buf);
			returnVal.setAttrValue(ibuf);
}

		if(fields.get("attrDescription") != null) {
			returnVal.setAttrDescription((String) fields.get("attrDescription"));
}


		return returnVal;
 } 
	public static CostComponentAttribute map(GenericValue val) {

CostComponentAttribute returnVal = new CostComponentAttribute();
		returnVal.setCostComponentId(val.getString("costComponentId"));
		returnVal.setAttrName(val.getString("attrName"));
		returnVal.setAttrValue(val.getLong("attrValue"));
		returnVal.setAttrDescription(val.getString("attrDescription"));


return returnVal;

}

public static CostComponentAttribute map(HttpServletRequest request) throws Exception {

		CostComponentAttribute returnVal = new CostComponentAttribute();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("costComponentId")) {
returnVal.setCostComponentId(request.getParameter("costComponentId"));
}

		if(paramMap.containsKey("attrName"))  {
returnVal.setAttrName(request.getParameter("attrName"));
}
		if(paramMap.containsKey("attrValue"))  {
String buf = request.getParameter("attrValue");
Long ibuf = Long.parseLong(buf);
returnVal.setAttrValue(ibuf);
}
		if(paramMap.containsKey("attrDescription"))  {
returnVal.setAttrDescription(request.getParameter("attrDescription"));
}
return returnVal;

}
}
