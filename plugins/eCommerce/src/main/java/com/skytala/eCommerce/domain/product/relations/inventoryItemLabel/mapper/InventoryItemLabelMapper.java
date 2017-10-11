package com.skytala.eCommerce.domain.product.relations.inventoryItemLabel.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.inventoryItemLabel.model.InventoryItemLabel;

public class InventoryItemLabelMapper  {


	public static Map<String, Object> map(InventoryItemLabel inventoryitemlabel) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(inventoryitemlabel.getInventoryItemLabelId() != null ){
			returnVal.put("inventoryItemLabelId",inventoryitemlabel.getInventoryItemLabelId());
}

		if(inventoryitemlabel.getInventoryItemLabelTypeId() != null ){
			returnVal.put("inventoryItemLabelTypeId",inventoryitemlabel.getInventoryItemLabelTypeId());
}

		if(inventoryitemlabel.getDescription() != null ){
			returnVal.put("description",inventoryitemlabel.getDescription());
}

		return returnVal;
}


	public static InventoryItemLabel map(Map<String, Object> fields) {

		InventoryItemLabel returnVal = new InventoryItemLabel();

		if(fields.get("inventoryItemLabelId") != null) {
			returnVal.setInventoryItemLabelId((String) fields.get("inventoryItemLabelId"));
}

		if(fields.get("inventoryItemLabelTypeId") != null) {
			returnVal.setInventoryItemLabelTypeId((String) fields.get("inventoryItemLabelTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static InventoryItemLabel mapstrstr(Map<String, String> fields) throws Exception {

		InventoryItemLabel returnVal = new InventoryItemLabel();

		if(fields.get("inventoryItemLabelId") != null) {
			returnVal.setInventoryItemLabelId((String) fields.get("inventoryItemLabelId"));
}

		if(fields.get("inventoryItemLabelTypeId") != null) {
			returnVal.setInventoryItemLabelTypeId((String) fields.get("inventoryItemLabelTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static InventoryItemLabel map(GenericValue val) {

InventoryItemLabel returnVal = new InventoryItemLabel();
		returnVal.setInventoryItemLabelId(val.getString("inventoryItemLabelId"));
		returnVal.setInventoryItemLabelTypeId(val.getString("inventoryItemLabelTypeId"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static InventoryItemLabel map(HttpServletRequest request) throws Exception {

		InventoryItemLabel returnVal = new InventoryItemLabel();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("inventoryItemLabelId")) {
returnVal.setInventoryItemLabelId(request.getParameter("inventoryItemLabelId"));
}

		if(paramMap.containsKey("inventoryItemLabelTypeId"))  {
returnVal.setInventoryItemLabelTypeId(request.getParameter("inventoryItemLabelTypeId"));
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
