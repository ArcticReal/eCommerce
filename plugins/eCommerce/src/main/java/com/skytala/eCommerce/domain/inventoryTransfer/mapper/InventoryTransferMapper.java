package com.skytala.eCommerce.domain.inventoryTransfer.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.inventoryTransfer.model.InventoryTransfer;

public class InventoryTransferMapper  {


	public static Map<String, Object> map(InventoryTransfer inventorytransfer) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(inventorytransfer.getInventoryTransferId() != null ){
			returnVal.put("inventoryTransferId",inventorytransfer.getInventoryTransferId());
}

		if(inventorytransfer.getStatusId() != null ){
			returnVal.put("statusId",inventorytransfer.getStatusId());
}

		if(inventorytransfer.getInventoryItemId() != null ){
			returnVal.put("inventoryItemId",inventorytransfer.getInventoryItemId());
}

		if(inventorytransfer.getFacilityId() != null ){
			returnVal.put("facilityId",inventorytransfer.getFacilityId());
}

		if(inventorytransfer.getLocationSeqId() != null ){
			returnVal.put("locationSeqId",inventorytransfer.getLocationSeqId());
}

		if(inventorytransfer.getContainerId() != null ){
			returnVal.put("containerId",inventorytransfer.getContainerId());
}

		if(inventorytransfer.getFacilityIdTo() != null ){
			returnVal.put("facilityIdTo",inventorytransfer.getFacilityIdTo());
}

		if(inventorytransfer.getLocationSeqIdTo() != null ){
			returnVal.put("locationSeqIdTo",inventorytransfer.getLocationSeqIdTo());
}

		if(inventorytransfer.getContainerIdTo() != null ){
			returnVal.put("containerIdTo",inventorytransfer.getContainerIdTo());
}

		if(inventorytransfer.getItemIssuanceId() != null ){
			returnVal.put("itemIssuanceId",inventorytransfer.getItemIssuanceId());
}

		if(inventorytransfer.getSendDate() != null ){
			returnVal.put("sendDate",inventorytransfer.getSendDate());
}

		if(inventorytransfer.getReceiveDate() != null ){
			returnVal.put("receiveDate",inventorytransfer.getReceiveDate());
}

		if(inventorytransfer.getComments() != null ){
			returnVal.put("comments",inventorytransfer.getComments());
}

		return returnVal;
}


	public static InventoryTransfer map(Map<String, Object> fields) {

		InventoryTransfer returnVal = new InventoryTransfer();

		if(fields.get("inventoryTransferId") != null) {
			returnVal.setInventoryTransferId((String) fields.get("inventoryTransferId"));
}

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}

		if(fields.get("inventoryItemId") != null) {
			returnVal.setInventoryItemId((String) fields.get("inventoryItemId"));
}

		if(fields.get("facilityId") != null) {
			returnVal.setFacilityId((String) fields.get("facilityId"));
}

		if(fields.get("locationSeqId") != null) {
			returnVal.setLocationSeqId((String) fields.get("locationSeqId"));
}

		if(fields.get("containerId") != null) {
			returnVal.setContainerId((String) fields.get("containerId"));
}

		if(fields.get("facilityIdTo") != null) {
			returnVal.setFacilityIdTo((String) fields.get("facilityIdTo"));
}

		if(fields.get("locationSeqIdTo") != null) {
			returnVal.setLocationSeqIdTo((String) fields.get("locationSeqIdTo"));
}

		if(fields.get("containerIdTo") != null) {
			returnVal.setContainerIdTo((String) fields.get("containerIdTo"));
}

		if(fields.get("itemIssuanceId") != null) {
			returnVal.setItemIssuanceId((String) fields.get("itemIssuanceId"));
}

		if(fields.get("sendDate") != null) {
			returnVal.setSendDate((Timestamp) fields.get("sendDate"));
}

		if(fields.get("receiveDate") != null) {
			returnVal.setReceiveDate((Timestamp) fields.get("receiveDate"));
}

		if(fields.get("comments") != null) {
			returnVal.setComments((String) fields.get("comments"));
}


		return returnVal;
 } 
	public static InventoryTransfer mapstrstr(Map<String, String> fields) throws Exception {

		InventoryTransfer returnVal = new InventoryTransfer();

		if(fields.get("inventoryTransferId") != null) {
			returnVal.setInventoryTransferId((String) fields.get("inventoryTransferId"));
}

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}

		if(fields.get("inventoryItemId") != null) {
			returnVal.setInventoryItemId((String) fields.get("inventoryItemId"));
}

		if(fields.get("facilityId") != null) {
			returnVal.setFacilityId((String) fields.get("facilityId"));
}

		if(fields.get("locationSeqId") != null) {
			returnVal.setLocationSeqId((String) fields.get("locationSeqId"));
}

		if(fields.get("containerId") != null) {
			returnVal.setContainerId((String) fields.get("containerId"));
}

		if(fields.get("facilityIdTo") != null) {
			returnVal.setFacilityIdTo((String) fields.get("facilityIdTo"));
}

		if(fields.get("locationSeqIdTo") != null) {
			returnVal.setLocationSeqIdTo((String) fields.get("locationSeqIdTo"));
}

		if(fields.get("containerIdTo") != null) {
			returnVal.setContainerIdTo((String) fields.get("containerIdTo"));
}

		if(fields.get("itemIssuanceId") != null) {
			returnVal.setItemIssuanceId((String) fields.get("itemIssuanceId"));
}

		if(fields.get("sendDate") != null) {
String buf = fields.get("sendDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setSendDate(ibuf);
}

		if(fields.get("receiveDate") != null) {
String buf = fields.get("receiveDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setReceiveDate(ibuf);
}

		if(fields.get("comments") != null) {
			returnVal.setComments((String) fields.get("comments"));
}


		return returnVal;
 } 
	public static InventoryTransfer map(GenericValue val) {

InventoryTransfer returnVal = new InventoryTransfer();
		returnVal.setInventoryTransferId(val.getString("inventoryTransferId"));
		returnVal.setStatusId(val.getString("statusId"));
		returnVal.setInventoryItemId(val.getString("inventoryItemId"));
		returnVal.setFacilityId(val.getString("facilityId"));
		returnVal.setLocationSeqId(val.getString("locationSeqId"));
		returnVal.setContainerId(val.getString("containerId"));
		returnVal.setFacilityIdTo(val.getString("facilityIdTo"));
		returnVal.setLocationSeqIdTo(val.getString("locationSeqIdTo"));
		returnVal.setContainerIdTo(val.getString("containerIdTo"));
		returnVal.setItemIssuanceId(val.getString("itemIssuanceId"));
		returnVal.setSendDate(val.getTimestamp("sendDate"));
		returnVal.setReceiveDate(val.getTimestamp("receiveDate"));
		returnVal.setComments(val.getString("comments"));


return returnVal;

}

public static InventoryTransfer map(HttpServletRequest request) throws Exception {

		InventoryTransfer returnVal = new InventoryTransfer();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("inventoryTransferId")) {
returnVal.setInventoryTransferId(request.getParameter("inventoryTransferId"));
}

		if(paramMap.containsKey("statusId"))  {
returnVal.setStatusId(request.getParameter("statusId"));
}
		if(paramMap.containsKey("inventoryItemId"))  {
returnVal.setInventoryItemId(request.getParameter("inventoryItemId"));
}
		if(paramMap.containsKey("facilityId"))  {
returnVal.setFacilityId(request.getParameter("facilityId"));
}
		if(paramMap.containsKey("locationSeqId"))  {
returnVal.setLocationSeqId(request.getParameter("locationSeqId"));
}
		if(paramMap.containsKey("containerId"))  {
returnVal.setContainerId(request.getParameter("containerId"));
}
		if(paramMap.containsKey("facilityIdTo"))  {
returnVal.setFacilityIdTo(request.getParameter("facilityIdTo"));
}
		if(paramMap.containsKey("locationSeqIdTo"))  {
returnVal.setLocationSeqIdTo(request.getParameter("locationSeqIdTo"));
}
		if(paramMap.containsKey("containerIdTo"))  {
returnVal.setContainerIdTo(request.getParameter("containerIdTo"));
}
		if(paramMap.containsKey("itemIssuanceId"))  {
returnVal.setItemIssuanceId(request.getParameter("itemIssuanceId"));
}
		if(paramMap.containsKey("sendDate"))  {
String buf = request.getParameter("sendDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setSendDate(ibuf);
}
		if(paramMap.containsKey("receiveDate"))  {
String buf = request.getParameter("receiveDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setReceiveDate(ibuf);
}
		if(paramMap.containsKey("comments"))  {
returnVal.setComments(request.getParameter("comments"));
}
return returnVal;

}
}
