package com.skytala.eCommerce.domain.humanres.relations.perfReviewItem.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.humanres.relations.perfReviewItem.model.PerfReviewItem;

public class PerfReviewItemMapper  {


	public static Map<String, Object> map(PerfReviewItem perfreviewitem) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(perfreviewitem.getEmployeePartyId() != null ){
			returnVal.put("employeePartyId",perfreviewitem.getEmployeePartyId());
}

		if(perfreviewitem.getEmployeeRoleTypeId() != null ){
			returnVal.put("employeeRoleTypeId",perfreviewitem.getEmployeeRoleTypeId());
}

		if(perfreviewitem.getPerfReviewId() != null ){
			returnVal.put("perfReviewId",perfreviewitem.getPerfReviewId());
}

		if(perfreviewitem.getPerfReviewItemSeqId() != null ){
			returnVal.put("perfReviewItemSeqId",perfreviewitem.getPerfReviewItemSeqId());
}

		if(perfreviewitem.getPerfReviewItemTypeId() != null ){
			returnVal.put("perfReviewItemTypeId",perfreviewitem.getPerfReviewItemTypeId());
}

		if(perfreviewitem.getPerfRatingTypeId() != null ){
			returnVal.put("perfRatingTypeId",perfreviewitem.getPerfRatingTypeId());
}

		if(perfreviewitem.getComments() != null ){
			returnVal.put("comments",perfreviewitem.getComments());
}

		return returnVal;
}


	public static PerfReviewItem map(Map<String, Object> fields) {

		PerfReviewItem returnVal = new PerfReviewItem();

		if(fields.get("employeePartyId") != null) {
			returnVal.setEmployeePartyId((String) fields.get("employeePartyId"));
}

		if(fields.get("employeeRoleTypeId") != null) {
			returnVal.setEmployeeRoleTypeId((String) fields.get("employeeRoleTypeId"));
}

		if(fields.get("perfReviewId") != null) {
			returnVal.setPerfReviewId((String) fields.get("perfReviewId"));
}

		if(fields.get("perfReviewItemSeqId") != null) {
			returnVal.setPerfReviewItemSeqId((String) fields.get("perfReviewItemSeqId"));
}

		if(fields.get("perfReviewItemTypeId") != null) {
			returnVal.setPerfReviewItemTypeId((String) fields.get("perfReviewItemTypeId"));
}

		if(fields.get("perfRatingTypeId") != null) {
			returnVal.setPerfRatingTypeId((String) fields.get("perfRatingTypeId"));
}

		if(fields.get("comments") != null) {
			returnVal.setComments((String) fields.get("comments"));
}


		return returnVal;
 } 
	public static PerfReviewItem mapstrstr(Map<String, String> fields) throws Exception {

		PerfReviewItem returnVal = new PerfReviewItem();

		if(fields.get("employeePartyId") != null) {
			returnVal.setEmployeePartyId((String) fields.get("employeePartyId"));
}

		if(fields.get("employeeRoleTypeId") != null) {
			returnVal.setEmployeeRoleTypeId((String) fields.get("employeeRoleTypeId"));
}

		if(fields.get("perfReviewId") != null) {
			returnVal.setPerfReviewId((String) fields.get("perfReviewId"));
}

		if(fields.get("perfReviewItemSeqId") != null) {
			returnVal.setPerfReviewItemSeqId((String) fields.get("perfReviewItemSeqId"));
}

		if(fields.get("perfReviewItemTypeId") != null) {
			returnVal.setPerfReviewItemTypeId((String) fields.get("perfReviewItemTypeId"));
}

		if(fields.get("perfRatingTypeId") != null) {
			returnVal.setPerfRatingTypeId((String) fields.get("perfRatingTypeId"));
}

		if(fields.get("comments") != null) {
			returnVal.setComments((String) fields.get("comments"));
}


		return returnVal;
 } 
	public static PerfReviewItem map(GenericValue val) {

PerfReviewItem returnVal = new PerfReviewItem();
		returnVal.setEmployeePartyId(val.getString("employeePartyId"));
		returnVal.setEmployeeRoleTypeId(val.getString("employeeRoleTypeId"));
		returnVal.setPerfReviewId(val.getString("perfReviewId"));
		returnVal.setPerfReviewItemSeqId(val.getString("perfReviewItemSeqId"));
		returnVal.setPerfReviewItemTypeId(val.getString("perfReviewItemTypeId"));
		returnVal.setPerfRatingTypeId(val.getString("perfRatingTypeId"));
		returnVal.setComments(val.getString("comments"));


return returnVal;

}

public static PerfReviewItem map(HttpServletRequest request) throws Exception {

		PerfReviewItem returnVal = new PerfReviewItem();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("employeePartyId")) {
returnVal.setEmployeePartyId(request.getParameter("employeePartyId"));
}

		if(paramMap.containsKey("employeeRoleTypeId"))  {
returnVal.setEmployeeRoleTypeId(request.getParameter("employeeRoleTypeId"));
}
		if(paramMap.containsKey("perfReviewId"))  {
returnVal.setPerfReviewId(request.getParameter("perfReviewId"));
}
		if(paramMap.containsKey("perfReviewItemSeqId"))  {
returnVal.setPerfReviewItemSeqId(request.getParameter("perfReviewItemSeqId"));
}
		if(paramMap.containsKey("perfReviewItemTypeId"))  {
returnVal.setPerfReviewItemTypeId(request.getParameter("perfReviewItemTypeId"));
}
		if(paramMap.containsKey("perfRatingTypeId"))  {
returnVal.setPerfRatingTypeId(request.getParameter("perfRatingTypeId"));
}
		if(paramMap.containsKey("comments"))  {
returnVal.setComments(request.getParameter("comments"));
}
return returnVal;

}
}
