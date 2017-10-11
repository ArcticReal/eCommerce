package com.skytala.eCommerce.domain.product.relations.inventoryItemTypeAttr.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.inventoryItemTypeAttr.model.InventoryItemTypeAttr;

public class InventoryItemTypeAttrMapper  {


	public static Map<String, Object> map(InventoryItemTypeAttr inventoryitemtypeattr) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(inventoryitemtypeattr.getInventoryItemTypeId() != null ){
			returnVal.put("inventoryItemTypeId",inventoryitemtypeattr.getInventoryItemTypeId());
}

		if(inventoryitemtypeattr.getAttrName() != null ){
			returnVal.put("attrName",inventoryitemtypeattr.getAttrName());
}

		if(inventoryitemtypeattr.getDescription() != null ){
			returnVal.put("description",inventoryitemtypeattr.getDescription());
}

		return returnVal;
}


	public static InventoryItemTypeAttr map(Map<String, Object> fields) {

		InventoryItemTypeAttr returnVal = new InventoryItemTypeAttr();

		if(fields.get("inventoryItemTypeId") != null) {
			returnVal.setInventoryItemTypeId((String) fields.get("inventoryItemTypeId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static InventoryItemTypeAttr mapstrstr(Map<String, String> fields) throws Exception {

		InventoryItemTypeAttr returnVal = new InventoryItemTypeAttr();

		if(fields.get("inventoryItemTypeId") != null) {
			returnVal.setInventoryItemTypeId((String) fields.get("inventoryItemTypeId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static InventoryItemTypeAttr map(GenericValue val) {

InventoryItemTypeAttr returnVal = new InventoryItemTypeAttr();
		returnVal.setInventoryItemTypeId(val.getString("inventoryItemTypeId"));
		returnVal.setAttrName(val.getString("attrName"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static InventoryItemTypeAttr map(HttpServletRequest request) throws Exception {

		InventoryItemTypeAttr returnVal = new InventoryItemTypeAttr();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("inventoryItemTypeId")) {
returnVal.setInventoryItemTypeId(request.getParameter("inventoryItemTypeId"));
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
