package com.skytala.eCommerce.domain.perfReview.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.perfReview.model.PerfReview;

public class PerfReviewMapper  {


	public static Map<String, Object> map(PerfReview perfreview) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(perfreview.getEmployeePartyId() != null ){
			returnVal.put("employeePartyId",perfreview.getEmployeePartyId());
}

		if(perfreview.getEmployeeRoleTypeId() != null ){
			returnVal.put("employeeRoleTypeId",perfreview.getEmployeeRoleTypeId());
}

		if(perfreview.getPerfReviewId() != null ){
			returnVal.put("perfReviewId",perfreview.getPerfReviewId());
}

		if(perfreview.getManagerPartyId() != null ){
			returnVal.put("managerPartyId",perfreview.getManagerPartyId());
}

		if(perfreview.getManagerRoleTypeId() != null ){
			returnVal.put("managerRoleTypeId",perfreview.getManagerRoleTypeId());
}

		if(perfreview.getPaymentId() != null ){
			returnVal.put("paymentId",perfreview.getPaymentId());
}

		if(perfreview.getEmplPositionId() != null ){
			returnVal.put("emplPositionId",perfreview.getEmplPositionId());
}

		if(perfreview.getFromDate() != null ){
			returnVal.put("fromDate",perfreview.getFromDate());
}

		if(perfreview.getThruDate() != null ){
			returnVal.put("thruDate",perfreview.getThruDate());
}

		if(perfreview.getComments() != null ){
			returnVal.put("comments",perfreview.getComments());
}

		return returnVal;
}


	public static PerfReview map(Map<String, Object> fields) {

		PerfReview returnVal = new PerfReview();

		if(fields.get("employeePartyId") != null) {
			returnVal.setEmployeePartyId((String) fields.get("employeePartyId"));
}

		if(fields.get("employeeRoleTypeId") != null) {
			returnVal.setEmployeeRoleTypeId((String) fields.get("employeeRoleTypeId"));
}

		if(fields.get("perfReviewId") != null) {
			returnVal.setPerfReviewId((String) fields.get("perfReviewId"));
}

		if(fields.get("managerPartyId") != null) {
			returnVal.setManagerPartyId((String) fields.get("managerPartyId"));
}

		if(fields.get("managerRoleTypeId") != null) {
			returnVal.setManagerRoleTypeId((String) fields.get("managerRoleTypeId"));
}

		if(fields.get("paymentId") != null) {
			returnVal.setPaymentId((String) fields.get("paymentId"));
}

		if(fields.get("emplPositionId") != null) {
			returnVal.setEmplPositionId((String) fields.get("emplPositionId"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}

		if(fields.get("comments") != null) {
			returnVal.setComments((String) fields.get("comments"));
}


		return returnVal;
 } 
	public static PerfReview mapstrstr(Map<String, String> fields) throws Exception {

		PerfReview returnVal = new PerfReview();

		if(fields.get("employeePartyId") != null) {
			returnVal.setEmployeePartyId((String) fields.get("employeePartyId"));
}

		if(fields.get("employeeRoleTypeId") != null) {
			returnVal.setEmployeeRoleTypeId((String) fields.get("employeeRoleTypeId"));
}

		if(fields.get("perfReviewId") != null) {
			returnVal.setPerfReviewId((String) fields.get("perfReviewId"));
}

		if(fields.get("managerPartyId") != null) {
			returnVal.setManagerPartyId((String) fields.get("managerPartyId"));
}

		if(fields.get("managerRoleTypeId") != null) {
			returnVal.setManagerRoleTypeId((String) fields.get("managerRoleTypeId"));
}

		if(fields.get("paymentId") != null) {
			returnVal.setPaymentId((String) fields.get("paymentId"));
}

		if(fields.get("emplPositionId") != null) {
			returnVal.setEmplPositionId((String) fields.get("emplPositionId"));
}

		if(fields.get("fromDate") != null) {
String buf = fields.get("fromDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setFromDate(ibuf);
}

		if(fields.get("thruDate") != null) {
String buf = fields.get("thruDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setThruDate(ibuf);
}

		if(fields.get("comments") != null) {
			returnVal.setComments((String) fields.get("comments"));
}


		return returnVal;
 } 
	public static PerfReview map(GenericValue val) {

PerfReview returnVal = new PerfReview();
		returnVal.setEmployeePartyId(val.getString("employeePartyId"));
		returnVal.setEmployeeRoleTypeId(val.getString("employeeRoleTypeId"));
		returnVal.setPerfReviewId(val.getString("perfReviewId"));
		returnVal.setManagerPartyId(val.getString("managerPartyId"));
		returnVal.setManagerRoleTypeId(val.getString("managerRoleTypeId"));
		returnVal.setPaymentId(val.getString("paymentId"));
		returnVal.setEmplPositionId(val.getString("emplPositionId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));
		returnVal.setComments(val.getString("comments"));


return returnVal;

}

public static PerfReview map(HttpServletRequest request) throws Exception {

		PerfReview returnVal = new PerfReview();

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
		if(paramMap.containsKey("managerPartyId"))  {
returnVal.setManagerPartyId(request.getParameter("managerPartyId"));
}
		if(paramMap.containsKey("managerRoleTypeId"))  {
returnVal.setManagerRoleTypeId(request.getParameter("managerRoleTypeId"));
}
		if(paramMap.containsKey("paymentId"))  {
returnVal.setPaymentId(request.getParameter("paymentId"));
}
		if(paramMap.containsKey("emplPositionId"))  {
returnVal.setEmplPositionId(request.getParameter("emplPositionId"));
}
		if(paramMap.containsKey("fromDate"))  {
String buf = request.getParameter("fromDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setFromDate(ibuf);
}
		if(paramMap.containsKey("thruDate"))  {
String buf = request.getParameter("thruDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setThruDate(ibuf);
}
		if(paramMap.containsKey("comments"))  {
returnVal.setComments(request.getParameter("comments"));
}
return returnVal;

}
}
