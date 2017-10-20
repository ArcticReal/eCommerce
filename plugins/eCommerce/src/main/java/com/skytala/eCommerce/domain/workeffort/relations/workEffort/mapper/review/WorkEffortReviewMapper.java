package com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.review;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.review.WorkEffortReview;

public class WorkEffortReviewMapper  {


	public static Map<String, Object> map(WorkEffortReview workeffortreview) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(workeffortreview.getWorkEffortId() != null ){
			returnVal.put("workEffortId",workeffortreview.getWorkEffortId());
}

		if(workeffortreview.getUserLoginId() != null ){
			returnVal.put("userLoginId",workeffortreview.getUserLoginId());
}

		if(workeffortreview.getReviewDate() != null ){
			returnVal.put("reviewDate",workeffortreview.getReviewDate());
}

		if(workeffortreview.getStatusId() != null ){
			returnVal.put("statusId",workeffortreview.getStatusId());
}

		if(workeffortreview.getPostedAnonymous() != null ){
			returnVal.put("postedAnonymous",workeffortreview.getPostedAnonymous());
}

		if(workeffortreview.getRating() != null ){
			returnVal.put("rating",workeffortreview.getRating());
}

		if(workeffortreview.getReviewText() != null ){
			returnVal.put("reviewText",workeffortreview.getReviewText());
}

		return returnVal;
}


	public static WorkEffortReview map(Map<String, Object> fields) {

		WorkEffortReview returnVal = new WorkEffortReview();

		if(fields.get("workEffortId") != null) {
			returnVal.setWorkEffortId((String) fields.get("workEffortId"));
}

		if(fields.get("userLoginId") != null) {
			returnVal.setUserLoginId((String) fields.get("userLoginId"));
}

		if(fields.get("reviewDate") != null) {
			returnVal.setReviewDate((Timestamp) fields.get("reviewDate"));
}

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}

		if(fields.get("postedAnonymous") != null) {
			returnVal.setPostedAnonymous((boolean) fields.get("postedAnonymous"));
}

		if(fields.get("rating") != null) {
			returnVal.setRating((BigDecimal) fields.get("rating"));
}

		if(fields.get("reviewText") != null) {
			returnVal.setReviewText((String) fields.get("reviewText"));
}


		return returnVal;
 } 
	public static WorkEffortReview mapstrstr(Map<String, String> fields) throws Exception {

		WorkEffortReview returnVal = new WorkEffortReview();

		if(fields.get("workEffortId") != null) {
			returnVal.setWorkEffortId((String) fields.get("workEffortId"));
}

		if(fields.get("userLoginId") != null) {
			returnVal.setUserLoginId((String) fields.get("userLoginId"));
}

		if(fields.get("reviewDate") != null) {
String buf = fields.get("reviewDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setReviewDate(ibuf);
}

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}

		if(fields.get("postedAnonymous") != null) {
String buf;
buf = fields.get("postedAnonymous");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setPostedAnonymous(ibuf);
}

		if(fields.get("rating") != null) {
String buf;
buf = fields.get("rating");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setRating(bd);
}

		if(fields.get("reviewText") != null) {
			returnVal.setReviewText((String) fields.get("reviewText"));
}


		return returnVal;
 } 
	public static WorkEffortReview map(GenericValue val) {

WorkEffortReview returnVal = new WorkEffortReview();
		returnVal.setWorkEffortId(val.getString("workEffortId"));
		returnVal.setUserLoginId(val.getString("userLoginId"));
		returnVal.setReviewDate(val.getTimestamp("reviewDate"));
		returnVal.setStatusId(val.getString("statusId"));
		returnVal.setPostedAnonymous(val.getBoolean("postedAnonymous"));
		returnVal.setRating(val.getBigDecimal("rating"));
		returnVal.setReviewText(val.getString("reviewText"));


return returnVal;

}

public static WorkEffortReview map(HttpServletRequest request) throws Exception {

		WorkEffortReview returnVal = new WorkEffortReview();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("workEffortId")) {
returnVal.setWorkEffortId(request.getParameter("workEffortId"));
}

		if(paramMap.containsKey("userLoginId"))  {
returnVal.setUserLoginId(request.getParameter("userLoginId"));
}
		if(paramMap.containsKey("reviewDate"))  {
String buf = request.getParameter("reviewDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setReviewDate(ibuf);
}
		if(paramMap.containsKey("statusId"))  {
returnVal.setStatusId(request.getParameter("statusId"));
}
		if(paramMap.containsKey("postedAnonymous"))  {
String buf = request.getParameter("postedAnonymous");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setPostedAnonymous(ibuf);
}
		if(paramMap.containsKey("rating"))  {
String buf = request.getParameter("rating");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setRating(bd);
}
		if(paramMap.containsKey("reviewText"))  {
returnVal.setReviewText(request.getParameter("reviewText"));
}
return returnVal;

}
}
