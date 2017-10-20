package com.skytala.eCommerce.domain.product.relations.inventoryItem.mapper.labelType;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.model.labelType.InventoryItemLabelType;

public class InventoryItemLabelTypeMapper  {


	public static Map<String, Object> map(InventoryItemLabelType inventoryitemlabeltype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(inventoryitemlabeltype.getInventoryItemLabelTypeId() != null ){
			returnVal.put("inventoryItemLabelTypeId",inventoryitemlabeltype.getInventoryItemLabelTypeId());
}

		if(inventoryitemlabeltype.getParentTypeId() != null ){
			returnVal.put("parentTypeId",inventoryitemlabeltype.getParentTypeId());
}

		if(inventoryitemlabeltype.getHasTable() != null ){
			returnVal.put("hasTable",inventoryitemlabeltype.getHasTable());
}

		if(inventoryitemlabeltype.getDescription() != null ){
			returnVal.put("description",inventoryitemlabeltype.getDescription());
}

		return returnVal;
}


	public static InventoryItemLabelType map(Map<String, Object> fields) {

		InventoryItemLabelType returnVal = new InventoryItemLabelType();

		if(fields.get("inventoryItemLabelTypeId") != null) {
			returnVal.setInventoryItemLabelTypeId((String) fields.get("inventoryItemLabelTypeId"));
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
	public static InventoryItemLabelType mapstrstr(Map<String, String> fields) throws Exception {

		InventoryItemLabelType returnVal = new InventoryItemLabelType();

		if(fields.get("inventoryItemLabelTypeId") != null) {
			returnVal.setInventoryItemLabelTypeId((String) fields.get("inventoryItemLabelTypeId"));
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
	public static InventoryItemLabelType map(GenericValue val) {

InventoryItemLabelType returnVal = new InventoryItemLabelType();
		returnVal.setInventoryItemLabelTypeId(val.getString("inventoryItemLabelTypeId"));
		returnVal.setParentTypeId(val.getString("parentTypeId"));
		returnVal.setHasTable(val.getBoolean("hasTable"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static InventoryItemLabelType map(HttpServletRequest request) throws Exception {

		InventoryItemLabelType returnVal = new InventoryItemLabelType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("inventoryItemLabelTypeId")) {
returnVal.setInventoryItemLabelTypeId(request.getParameter("inventoryItemLabelTypeId"));
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
