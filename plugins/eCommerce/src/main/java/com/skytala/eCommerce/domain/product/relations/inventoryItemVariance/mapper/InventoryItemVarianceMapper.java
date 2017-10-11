package com.skytala.eCommerce.domain.product.relations.inventoryItemVariance.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.inventoryItemVariance.model.InventoryItemVariance;

public class InventoryItemVarianceMapper  {


	public static Map<String, Object> map(InventoryItemVariance inventoryitemvariance) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(inventoryitemvariance.getInventoryItemId() != null ){
			returnVal.put("inventoryItemId",inventoryitemvariance.getInventoryItemId());
}

		if(inventoryitemvariance.getPhysicalInventoryId() != null ){
			returnVal.put("physicalInventoryId",inventoryitemvariance.getPhysicalInventoryId());
}

		if(inventoryitemvariance.getVarianceReasonId() != null ){
			returnVal.put("varianceReasonId",inventoryitemvariance.getVarianceReasonId());
}

		if(inventoryitemvariance.getAvailableToPromiseVar() != null ){
			returnVal.put("availableToPromiseVar",inventoryitemvariance.getAvailableToPromiseVar());
}

		if(inventoryitemvariance.getQuantityOnHandVar() != null ){
			returnVal.put("quantityOnHandVar",inventoryitemvariance.getQuantityOnHandVar());
}

		if(inventoryitemvariance.getComments() != null ){
			returnVal.put("comments",inventoryitemvariance.getComments());
}

		return returnVal;
}


	public static InventoryItemVariance map(Map<String, Object> fields) {

		InventoryItemVariance returnVal = new InventoryItemVariance();

		if(fields.get("inventoryItemId") != null) {
			returnVal.setInventoryItemId((String) fields.get("inventoryItemId"));
}

		if(fields.get("physicalInventoryId") != null) {
			returnVal.setPhysicalInventoryId((String) fields.get("physicalInventoryId"));
}

		if(fields.get("varianceReasonId") != null) {
			returnVal.setVarianceReasonId((String) fields.get("varianceReasonId"));
}

		if(fields.get("availableToPromiseVar") != null) {
			returnVal.setAvailableToPromiseVar((BigDecimal) fields.get("availableToPromiseVar"));
}

		if(fields.get("quantityOnHandVar") != null) {
			returnVal.setQuantityOnHandVar((BigDecimal) fields.get("quantityOnHandVar"));
}

		if(fields.get("comments") != null) {
			returnVal.setComments((String) fields.get("comments"));
}


		return returnVal;
 } 
	public static InventoryItemVariance mapstrstr(Map<String, String> fields) throws Exception {

		InventoryItemVariance returnVal = new InventoryItemVariance();

		if(fields.get("inventoryItemId") != null) {
			returnVal.setInventoryItemId((String) fields.get("inventoryItemId"));
}

		if(fields.get("physicalInventoryId") != null) {
			returnVal.setPhysicalInventoryId((String) fields.get("physicalInventoryId"));
}

		if(fields.get("varianceReasonId") != null) {
			returnVal.setVarianceReasonId((String) fields.get("varianceReasonId"));
}

		if(fields.get("availableToPromiseVar") != null) {
String buf;
buf = fields.get("availableToPromiseVar");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setAvailableToPromiseVar(bd);
}

		if(fields.get("quantityOnHandVar") != null) {
String buf;
buf = fields.get("quantityOnHandVar");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setQuantityOnHandVar(bd);
}

		if(fields.get("comments") != null) {
			returnVal.setComments((String) fields.get("comments"));
}


		return returnVal;
 } 
	public static InventoryItemVariance map(GenericValue val) {

InventoryItemVariance returnVal = new InventoryItemVariance();
		returnVal.setInventoryItemId(val.getString("inventoryItemId"));
		returnVal.setPhysicalInventoryId(val.getString("physicalInventoryId"));
		returnVal.setVarianceReasonId(val.getString("varianceReasonId"));
		returnVal.setAvailableToPromiseVar(val.getBigDecimal("availableToPromiseVar"));
		returnVal.setQuantityOnHandVar(val.getBigDecimal("quantityOnHandVar"));
		returnVal.setComments(val.getString("comments"));


return returnVal;

}

public static InventoryItemVariance map(HttpServletRequest request) throws Exception {

		InventoryItemVariance returnVal = new InventoryItemVariance();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("inventoryItemId")) {
returnVal.setInventoryItemId(request.getParameter("inventoryItemId"));
}

		if(paramMap.containsKey("physicalInventoryId"))  {
returnVal.setPhysicalInventoryId(request.getParameter("physicalInventoryId"));
}
		if(paramMap.containsKey("varianceReasonId"))  {
returnVal.setVarianceReasonId(request.getParameter("varianceReasonId"));
}
		if(paramMap.containsKey("availableToPromiseVar"))  {
String buf = request.getParameter("availableToPromiseVar");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setAvailableToPromiseVar(bd);
}
		if(paramMap.containsKey("quantityOnHandVar"))  {
String buf = request.getParameter("quantityOnHandVar");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setQuantityOnHandVar(bd);
}
		if(paramMap.containsKey("comments"))  {
returnVal.setComments(request.getParameter("comments"));
}
return returnVal;

}
}
