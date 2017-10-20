package com.skytala.eCommerce.domain.product.relations.inventoryItem.mapper.attribute;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.model.attribute.InventoryItemAttribute;

public class InventoryItemAttributeMapper  {


	public static Map<String, Object> map(InventoryItemAttribute inventoryitemattribute) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(inventoryitemattribute.getInventoryItemId() != null ){
			returnVal.put("inventoryItemId",inventoryitemattribute.getInventoryItemId());
}

		if(inventoryitemattribute.getAttrName() != null ){
			returnVal.put("attrName",inventoryitemattribute.getAttrName());
}

		if(inventoryitemattribute.getAttrValue() != null ){
			returnVal.put("attrValue",inventoryitemattribute.getAttrValue());
}

		if(inventoryitemattribute.getAttrDescription() != null ){
			returnVal.put("attrDescription",inventoryitemattribute.getAttrDescription());
}

		return returnVal;
}


	public static InventoryItemAttribute map(Map<String, Object> fields) {

		InventoryItemAttribute returnVal = new InventoryItemAttribute();

		if(fields.get("inventoryItemId") != null) {
			returnVal.setInventoryItemId((String) fields.get("inventoryItemId"));
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
	public static InventoryItemAttribute mapstrstr(Map<String, String> fields) throws Exception {

		InventoryItemAttribute returnVal = new InventoryItemAttribute();

		if(fields.get("inventoryItemId") != null) {
			returnVal.setInventoryItemId((String) fields.get("inventoryItemId"));
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
	public static InventoryItemAttribute map(GenericValue val) {

InventoryItemAttribute returnVal = new InventoryItemAttribute();
		returnVal.setInventoryItemId(val.getString("inventoryItemId"));
		returnVal.setAttrName(val.getString("attrName"));
		returnVal.setAttrValue(val.getLong("attrValue"));
		returnVal.setAttrDescription(val.getString("attrDescription"));


return returnVal;

}

public static InventoryItemAttribute map(HttpServletRequest request) throws Exception {

		InventoryItemAttribute returnVal = new InventoryItemAttribute();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("inventoryItemId")) {
returnVal.setInventoryItemId(request.getParameter("inventoryItemId"));
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
