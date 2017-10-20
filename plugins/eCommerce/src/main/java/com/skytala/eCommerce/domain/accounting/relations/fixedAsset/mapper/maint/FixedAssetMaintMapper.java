package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.mapper.maint;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.maint.FixedAssetMaint;

public class FixedAssetMaintMapper  {


	public static Map<String, Object> map(FixedAssetMaint fixedassetmaint) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(fixedassetmaint.getFixedAssetId() != null ){
			returnVal.put("fixedAssetId",fixedassetmaint.getFixedAssetId());
}

		if(fixedassetmaint.getMaintHistSeqId() != null ){
			returnVal.put("maintHistSeqId",fixedassetmaint.getMaintHistSeqId());
}

		if(fixedassetmaint.getStatusId() != null ){
			returnVal.put("statusId",fixedassetmaint.getStatusId());
}

		if(fixedassetmaint.getProductMaintTypeId() != null ){
			returnVal.put("productMaintTypeId",fixedassetmaint.getProductMaintTypeId());
}

		if(fixedassetmaint.getProductMaintSeqId() != null ){
			returnVal.put("productMaintSeqId",fixedassetmaint.getProductMaintSeqId());
}

		if(fixedassetmaint.getScheduleWorkEffortId() != null ){
			returnVal.put("scheduleWorkEffortId",fixedassetmaint.getScheduleWorkEffortId());
}

		if(fixedassetmaint.getIntervalQuantity() != null ){
			returnVal.put("intervalQuantity",fixedassetmaint.getIntervalQuantity());
}

		if(fixedassetmaint.getIntervalUomId() != null ){
			returnVal.put("intervalUomId",fixedassetmaint.getIntervalUomId());
}

		if(fixedassetmaint.getIntervalMeterTypeId() != null ){
			returnVal.put("intervalMeterTypeId",fixedassetmaint.getIntervalMeterTypeId());
}

		if(fixedassetmaint.getPurchaseOrderId() != null ){
			returnVal.put("purchaseOrderId",fixedassetmaint.getPurchaseOrderId());
}

		return returnVal;
}


	public static FixedAssetMaint map(Map<String, Object> fields) {

		FixedAssetMaint returnVal = new FixedAssetMaint();

		if(fields.get("fixedAssetId") != null) {
			returnVal.setFixedAssetId((String) fields.get("fixedAssetId"));
}

		if(fields.get("maintHistSeqId") != null) {
			returnVal.setMaintHistSeqId((String) fields.get("maintHistSeqId"));
}

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}

		if(fields.get("productMaintTypeId") != null) {
			returnVal.setProductMaintTypeId((String) fields.get("productMaintTypeId"));
}

		if(fields.get("productMaintSeqId") != null) {
			returnVal.setProductMaintSeqId((String) fields.get("productMaintSeqId"));
}

		if(fields.get("scheduleWorkEffortId") != null) {
			returnVal.setScheduleWorkEffortId((String) fields.get("scheduleWorkEffortId"));
}

		if(fields.get("intervalQuantity") != null) {
			returnVal.setIntervalQuantity((BigDecimal) fields.get("intervalQuantity"));
}

		if(fields.get("intervalUomId") != null) {
			returnVal.setIntervalUomId((String) fields.get("intervalUomId"));
}

		if(fields.get("intervalMeterTypeId") != null) {
			returnVal.setIntervalMeterTypeId((String) fields.get("intervalMeterTypeId"));
}

		if(fields.get("purchaseOrderId") != null) {
			returnVal.setPurchaseOrderId((String) fields.get("purchaseOrderId"));
}


		return returnVal;
 } 
	public static FixedAssetMaint mapstrstr(Map<String, String> fields) throws Exception {

		FixedAssetMaint returnVal = new FixedAssetMaint();

		if(fields.get("fixedAssetId") != null) {
			returnVal.setFixedAssetId((String) fields.get("fixedAssetId"));
}

		if(fields.get("maintHistSeqId") != null) {
			returnVal.setMaintHistSeqId((String) fields.get("maintHistSeqId"));
}

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}

		if(fields.get("productMaintTypeId") != null) {
			returnVal.setProductMaintTypeId((String) fields.get("productMaintTypeId"));
}

		if(fields.get("productMaintSeqId") != null) {
			returnVal.setProductMaintSeqId((String) fields.get("productMaintSeqId"));
}

		if(fields.get("scheduleWorkEffortId") != null) {
			returnVal.setScheduleWorkEffortId((String) fields.get("scheduleWorkEffortId"));
}

		if(fields.get("intervalQuantity") != null) {
String buf;
buf = fields.get("intervalQuantity");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setIntervalQuantity(bd);
}

		if(fields.get("intervalUomId") != null) {
			returnVal.setIntervalUomId((String) fields.get("intervalUomId"));
}

		if(fields.get("intervalMeterTypeId") != null) {
			returnVal.setIntervalMeterTypeId((String) fields.get("intervalMeterTypeId"));
}

		if(fields.get("purchaseOrderId") != null) {
			returnVal.setPurchaseOrderId((String) fields.get("purchaseOrderId"));
}


		return returnVal;
 } 
	public static FixedAssetMaint map(GenericValue val) {

FixedAssetMaint returnVal = new FixedAssetMaint();
		returnVal.setFixedAssetId(val.getString("fixedAssetId"));
		returnVal.setMaintHistSeqId(val.getString("maintHistSeqId"));
		returnVal.setStatusId(val.getString("statusId"));
		returnVal.setProductMaintTypeId(val.getString("productMaintTypeId"));
		returnVal.setProductMaintSeqId(val.getString("productMaintSeqId"));
		returnVal.setScheduleWorkEffortId(val.getString("scheduleWorkEffortId"));
		returnVal.setIntervalQuantity(val.getBigDecimal("intervalQuantity"));
		returnVal.setIntervalUomId(val.getString("intervalUomId"));
		returnVal.setIntervalMeterTypeId(val.getString("intervalMeterTypeId"));
		returnVal.setPurchaseOrderId(val.getString("purchaseOrderId"));


return returnVal;

}

public static FixedAssetMaint map(HttpServletRequest request) throws Exception {

		FixedAssetMaint returnVal = new FixedAssetMaint();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("fixedAssetId")) {
returnVal.setFixedAssetId(request.getParameter("fixedAssetId"));
}

		if(paramMap.containsKey("maintHistSeqId"))  {
returnVal.setMaintHistSeqId(request.getParameter("maintHistSeqId"));
}
		if(paramMap.containsKey("statusId"))  {
returnVal.setStatusId(request.getParameter("statusId"));
}
		if(paramMap.containsKey("productMaintTypeId"))  {
returnVal.setProductMaintTypeId(request.getParameter("productMaintTypeId"));
}
		if(paramMap.containsKey("productMaintSeqId"))  {
returnVal.setProductMaintSeqId(request.getParameter("productMaintSeqId"));
}
		if(paramMap.containsKey("scheduleWorkEffortId"))  {
returnVal.setScheduleWorkEffortId(request.getParameter("scheduleWorkEffortId"));
}
		if(paramMap.containsKey("intervalQuantity"))  {
String buf = request.getParameter("intervalQuantity");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setIntervalQuantity(bd);
}
		if(paramMap.containsKey("intervalUomId"))  {
returnVal.setIntervalUomId(request.getParameter("intervalUomId"));
}
		if(paramMap.containsKey("intervalMeterTypeId"))  {
returnVal.setIntervalMeterTypeId(request.getParameter("intervalMeterTypeId"));
}
		if(paramMap.containsKey("purchaseOrderId"))  {
returnVal.setPurchaseOrderId(request.getParameter("purchaseOrderId"));
}
return returnVal;

}
}
