package com.skytala.eCommerce.domain.contentApproval.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.contentApproval.model.ContentApproval;

public class ContentApprovalMapper  {


	public static Map<String, Object> map(ContentApproval contentapproval) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(contentapproval.getContentApprovalId() != null ){
			returnVal.put("contentApprovalId",contentapproval.getContentApprovalId());
}

		if(contentapproval.getContentId() != null ){
			returnVal.put("contentId",contentapproval.getContentId());
}

		if(contentapproval.getContentRevisionSeqId() != null ){
			returnVal.put("contentRevisionSeqId",contentapproval.getContentRevisionSeqId());
}

		if(contentapproval.getPartyId() != null ){
			returnVal.put("partyId",contentapproval.getPartyId());
}

		if(contentapproval.getRoleTypeId() != null ){
			returnVal.put("roleTypeId",contentapproval.getRoleTypeId());
}

		if(contentapproval.getApprovalStatusId() != null ){
			returnVal.put("approvalStatusId",contentapproval.getApprovalStatusId());
}

		if(contentapproval.getApprovalDate() != null ){
			returnVal.put("approvalDate",contentapproval.getApprovalDate());
}

		if(contentapproval.getSequenceNum() != null ){
			returnVal.put("sequenceNum",contentapproval.getSequenceNum());
}

		if(contentapproval.getComments() != null ){
			returnVal.put("comments",contentapproval.getComments());
}

		return returnVal;
}


	public static ContentApproval map(Map<String, Object> fields) {

		ContentApproval returnVal = new ContentApproval();

		if(fields.get("contentApprovalId") != null) {
			returnVal.setContentApprovalId((String) fields.get("contentApprovalId"));
}

		if(fields.get("contentId") != null) {
			returnVal.setContentId((String) fields.get("contentId"));
}

		if(fields.get("contentRevisionSeqId") != null) {
			returnVal.setContentRevisionSeqId((String) fields.get("contentRevisionSeqId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("roleTypeId") != null) {
			returnVal.setRoleTypeId((String) fields.get("roleTypeId"));
}

		if(fields.get("approvalStatusId") != null) {
			returnVal.setApprovalStatusId((String) fields.get("approvalStatusId"));
}

		if(fields.get("approvalDate") != null) {
			returnVal.setApprovalDate((Timestamp) fields.get("approvalDate"));
}

		if(fields.get("sequenceNum") != null) {
			returnVal.setSequenceNum((long) fields.get("sequenceNum"));
}

		if(fields.get("comments") != null) {
			returnVal.setComments((String) fields.get("comments"));
}


		return returnVal;
 } 
	public static ContentApproval mapstrstr(Map<String, String> fields) throws Exception {

		ContentApproval returnVal = new ContentApproval();

		if(fields.get("contentApprovalId") != null) {
			returnVal.setContentApprovalId((String) fields.get("contentApprovalId"));
}

		if(fields.get("contentId") != null) {
			returnVal.setContentId((String) fields.get("contentId"));
}

		if(fields.get("contentRevisionSeqId") != null) {
			returnVal.setContentRevisionSeqId((String) fields.get("contentRevisionSeqId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("roleTypeId") != null) {
			returnVal.setRoleTypeId((String) fields.get("roleTypeId"));
}

		if(fields.get("approvalStatusId") != null) {
			returnVal.setApprovalStatusId((String) fields.get("approvalStatusId"));
}

		if(fields.get("approvalDate") != null) {
String buf = fields.get("approvalDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setApprovalDate(ibuf);
}

		if(fields.get("sequenceNum") != null) {
String buf;
buf = fields.get("sequenceNum");
long ibuf = Long.parseLong(buf);
			returnVal.setSequenceNum(ibuf);
}

		if(fields.get("comments") != null) {
			returnVal.setComments((String) fields.get("comments"));
}


		return returnVal;
 } 
	public static ContentApproval map(GenericValue val) {

ContentApproval returnVal = new ContentApproval();
		returnVal.setContentApprovalId(val.getString("contentApprovalId"));
		returnVal.setContentId(val.getString("contentId"));
		returnVal.setContentRevisionSeqId(val.getString("contentRevisionSeqId"));
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setRoleTypeId(val.getString("roleTypeId"));
		returnVal.setApprovalStatusId(val.getString("approvalStatusId"));
		returnVal.setApprovalDate(val.getTimestamp("approvalDate"));
		returnVal.setSequenceNum(val.getLong("sequenceNum"));
		returnVal.setComments(val.getString("comments"));


return returnVal;

}

public static ContentApproval map(HttpServletRequest request) throws Exception {

		ContentApproval returnVal = new ContentApproval();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("contentApprovalId")) {
returnVal.setContentApprovalId(request.getParameter("contentApprovalId"));
}

		if(paramMap.containsKey("contentId"))  {
returnVal.setContentId(request.getParameter("contentId"));
}
		if(paramMap.containsKey("contentRevisionSeqId"))  {
returnVal.setContentRevisionSeqId(request.getParameter("contentRevisionSeqId"));
}
		if(paramMap.containsKey("partyId"))  {
returnVal.setPartyId(request.getParameter("partyId"));
}
		if(paramMap.containsKey("roleTypeId"))  {
returnVal.setRoleTypeId(request.getParameter("roleTypeId"));
}
		if(paramMap.containsKey("approvalStatusId"))  {
returnVal.setApprovalStatusId(request.getParameter("approvalStatusId"));
}
		if(paramMap.containsKey("approvalDate"))  {
String buf = request.getParameter("approvalDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setApprovalDate(ibuf);
}
		if(paramMap.containsKey("sequenceNum"))  {
String buf = request.getParameter("sequenceNum");
Long ibuf = Long.parseLong(buf);
returnVal.setSequenceNum(ibuf);
}
		if(paramMap.containsKey("comments"))  {
returnVal.setComments(request.getParameter("comments"));
}
return returnVal;

}
}
