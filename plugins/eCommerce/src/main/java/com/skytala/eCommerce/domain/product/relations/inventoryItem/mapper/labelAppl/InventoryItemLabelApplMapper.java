package com.skytala.eCommerce.domain.product.relations.inventoryItem.mapper.labelAppl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.model.labelAppl.InventoryItemLabelAppl;

public class InventoryItemLabelApplMapper  {


	public static Map<String, Object> map(InventoryItemLabelAppl inventoryitemlabelappl) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(inventoryitemlabelappl.getInventoryItemId() != null ){
			returnVal.put("inventoryItemId",inventoryitemlabelappl.getInventoryItemId());
}

		if(inventoryitemlabelappl.getInventoryItemLabelTypeId() != null ){
			returnVal.put("inventoryItemLabelTypeId",inventoryitemlabelappl.getInventoryItemLabelTypeId());
}

		if(inventoryitemlabelappl.getInventoryItemLabelId() != null ){
			returnVal.put("inventoryItemLabelId",inventoryitemlabelappl.getInventoryItemLabelId());
}

		if(inventoryitemlabelappl.getSequenceNum() != null ){
			returnVal.put("sequenceNum",inventoryitemlabelappl.getSequenceNum());
}

		return returnVal;
}


	public static InventoryItemLabelAppl map(Map<String, Object> fields) {

		InventoryItemLabelAppl returnVal = new InventoryItemLabelAppl();

		if(fields.get("inventoryItemId") != null) {
			returnVal.setInventoryItemId((String) fields.get("inventoryItemId"));
}

		if(fields.get("inventoryItemLabelTypeId") != null) {
			returnVal.setInventoryItemLabelTypeId((String) fields.get("inventoryItemLabelTypeId"));
}

		if(fields.get("inventoryItemLabelId") != null) {
			returnVal.setInventoryItemLabelId((String) fields.get("inventoryItemLabelId"));
}

		if(fields.get("sequenceNum") != null) {
			returnVal.setSequenceNum((long) fields.get("sequenceNum"));
}


		return returnVal;
 } 
	public static InventoryItemLabelAppl mapstrstr(Map<String, String> fields) throws Exception {

		InventoryItemLabelAppl returnVal = new InventoryItemLabelAppl();

		if(fields.get("inventoryItemId") != null) {
			returnVal.setInventoryItemId((String) fields.get("inventoryItemId"));
}

		if(fields.get("inventoryItemLabelTypeId") != null) {
			returnVal.setInventoryItemLabelTypeId((String) fields.get("inventoryItemLabelTypeId"));
}

		if(fields.get("inventoryItemLabelId") != null) {
			returnVal.setInventoryItemLabelId((String) fields.get("inventoryItemLabelId"));
}

		if(fields.get("sequenceNum") != null) {
String buf;
buf = fields.get("sequenceNum");
long ibuf = Long.parseLong(buf);
			returnVal.setSequenceNum(ibuf);
}


		return returnVal;
 } 
	public static InventoryItemLabelAppl map(GenericValue val) {

InventoryItemLabelAppl returnVal = new InventoryItemLabelAppl();
		returnVal.setInventoryItemId(val.getString("inventoryItemId"));
		returnVal.setInventoryItemLabelTypeId(val.getString("inventoryItemLabelTypeId"));
		returnVal.setInventoryItemLabelId(val.getString("inventoryItemLabelId"));
		returnVal.setSequenceNum(val.getLong("sequenceNum"));


return returnVal;

}

public static InventoryItemLabelAppl map(HttpServletRequest request) throws Exception {

		InventoryItemLabelAppl returnVal = new InventoryItemLabelAppl();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("inventoryItemId")) {
returnVal.setInventoryItemId(request.getParameter("inventoryItemId"));
}

		if(paramMap.containsKey("inventoryItemLabelTypeId"))  {
returnVal.setInventoryItemLabelTypeId(request.getParameter("inventoryItemLabelTypeId"));
}
		if(paramMap.containsKey("inventoryItemLabelId"))  {
returnVal.setInventoryItemLabelId(request.getParameter("inventoryItemLabelId"));
}
		if(paramMap.containsKey("sequenceNum"))  {
String buf = request.getParameter("sequenceNum");
Long ibuf = Long.parseLong(buf);
returnVal.setSequenceNum(ibuf);
}
return returnVal;

}
}
