package com.skytala.eCommerce.domain.product.relations.inventoryItem.mapper.type;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.model.type.InventoryItemType;

public class InventoryItemTypeMapper  {


	public static Map<String, Object> map(InventoryItemType inventoryitemtype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(inventoryitemtype.getInventoryItemTypeId() != null ){
			returnVal.put("inventoryItemTypeId",inventoryitemtype.getInventoryItemTypeId());
}

		if(inventoryitemtype.getParentTypeId() != null ){
			returnVal.put("parentTypeId",inventoryitemtype.getParentTypeId());
}

		if(inventoryitemtype.getHasTable() != null ){
			returnVal.put("hasTable",inventoryitemtype.getHasTable());
}

		if(inventoryitemtype.getDescription() != null ){
			returnVal.put("description",inventoryitemtype.getDescription());
}

		return returnVal;
}


	public static InventoryItemType map(Map<String, Object> fields) {

		InventoryItemType returnVal = new InventoryItemType();

		if(fields.get("inventoryItemTypeId") != null) {
			returnVal.setInventoryItemTypeId((String) fields.get("inventoryItemTypeId"));
}

		if(fields.get("parentTypeId") != null) {
			returnVal.setParentTypeId((String) fields.get("parentTypeId"));
}

		if(fields.get("hasTable") != null) {
			returnVal.setHasTable((boolean) fields.get("hasTable"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static InventoryItemType mapstrstr(Map<String, String> fields) throws Exception {

		InventoryItemType returnVal = new InventoryItemType();

		if(fields.get("inventoryItemTypeId") != null) {
			returnVal.setInventoryItemTypeId((String) fields.get("inventoryItemTypeId"));
}

		if(fields.get("parentTypeId") != null) {
			returnVal.setParentTypeId((String) fields.get("parentTypeId"));
}

		if(fields.get("hasTable") != null) {
String buf;
buf = fields.get("hasTable");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setHasTable(ibuf);
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static InventoryItemType map(GenericValue val) {

InventoryItemType returnVal = new InventoryItemType();
		returnVal.setInventoryItemTypeId(val.getString("inventoryItemTypeId"));
		returnVal.setParentTypeId(val.getString("parentTypeId"));
		returnVal.setHasTable(val.getBoolean("hasTable"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static InventoryItemType map(HttpServletRequest request) throws Exception {

		InventoryItemType returnVal = new InventoryItemType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("inventoryItemTypeId")) {
returnVal.setInventoryItemTypeId(request.getParameter("inventoryItemTypeId"));
}

		if(paramMap.containsKey("parentTypeId"))  {
returnVal.setParentTypeId(request.getParameter("parentTypeId"));
}
		if(paramMap.containsKey("hasTable"))  {
String buf = request.getParameter("hasTable");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setHasTable(ibuf);
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
