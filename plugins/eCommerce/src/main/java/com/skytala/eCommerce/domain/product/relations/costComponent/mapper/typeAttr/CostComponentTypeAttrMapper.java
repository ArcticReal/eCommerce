package com.skytala.eCommerce.domain.product.relations.costComponent.mapper.typeAttr;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.costComponent.model.typeAttr.CostComponentTypeAttr;

public class CostComponentTypeAttrMapper  {


	public static Map<String, Object> map(CostComponentTypeAttr costcomponenttypeattr) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(costcomponenttypeattr.getCostComponentTypeId() != null ){
			returnVal.put("costComponentTypeId",costcomponenttypeattr.getCostComponentTypeId());
}

		if(costcomponenttypeattr.getAttrName() != null ){
			returnVal.put("attrName",costcomponenttypeattr.getAttrName());
}

		if(costcomponenttypeattr.getDescription() != null ){
			returnVal.put("description",costcomponenttypeattr.getDescription());
}

		return returnVal;
}


	public static CostComponentTypeAttr map(Map<String, Object> fields) {

		CostComponentTypeAttr returnVal = new CostComponentTypeAttr();

		if(fields.get("costComponentTypeId") != null) {
			returnVal.setCostComponentTypeId((String) fields.get("costComponentTypeId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static CostComponentTypeAttr mapstrstr(Map<String, String> fields) throws Exception {

		CostComponentTypeAttr returnVal = new CostComponentTypeAttr();

		if(fields.get("costComponentTypeId") != null) {
			returnVal.setCostComponentTypeId((String) fields.get("costComponentTypeId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static CostComponentTypeAttr map(GenericValue val) {

CostComponentTypeAttr returnVal = new CostComponentTypeAttr();
		returnVal.setCostComponentTypeId(val.getString("costComponentTypeId"));
		returnVal.setAttrName(val.getString("attrName"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static CostComponentTypeAttr map(HttpServletRequest request) throws Exception {

		CostComponentTypeAttr returnVal = new CostComponentTypeAttr();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("costComponentTypeId")) {
returnVal.setCostComponentTypeId(request.getParameter("costComponentTypeId"));
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
