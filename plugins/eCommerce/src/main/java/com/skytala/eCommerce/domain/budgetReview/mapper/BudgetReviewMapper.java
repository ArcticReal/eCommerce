package com.skytala.eCommerce.domain.budgetReview.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.budgetReview.model.BudgetReview;

public class BudgetReviewMapper  {


	public static Map<String, Object> map(BudgetReview budgetreview) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(budgetreview.getBudgetId() != null ){
			returnVal.put("budgetId",budgetreview.getBudgetId());
}

		if(budgetreview.getBudgetReviewId() != null ){
			returnVal.put("budgetReviewId",budgetreview.getBudgetReviewId());
}

		if(budgetreview.getPartyId() != null ){
			returnVal.put("partyId",budgetreview.getPartyId());
}

		if(budgetreview.getBudgetReviewResultTypeId() != null ){
			returnVal.put("budgetReviewResultTypeId",budgetreview.getBudgetReviewResultTypeId());
}

		if(budgetreview.getReviewDate() != null ){
			returnVal.put("reviewDate",budgetreview.getReviewDate());
}

		return returnVal;
}


	public static BudgetReview map(Map<String, Object> fields) {

		BudgetReview returnVal = new BudgetReview();

		if(fields.get("budgetId") != null) {
			returnVal.setBudgetId((String) fields.get("budgetId"));
}

		if(fields.get("budgetReviewId") != null) {
			returnVal.setBudgetReviewId((String) fields.get("budgetReviewId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("budgetReviewResultTypeId") != null) {
			returnVal.setBudgetReviewResultTypeId((String) fields.get("budgetReviewResultTypeId"));
}

		if(fields.get("reviewDate") != null) {
			returnVal.setReviewDate((Timestamp) fields.get("reviewDate"));
}


		return returnVal;
 } 
	public static BudgetReview mapstrstr(Map<String, String> fields) throws Exception {

		BudgetReview returnVal = new BudgetReview();

		if(fields.get("budgetId") != null) {
			returnVal.setBudgetId((String) fields.get("budgetId"));
}

		if(fields.get("budgetReviewId") != null) {
			returnVal.setBudgetReviewId((String) fields.get("budgetReviewId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("budgetReviewResultTypeId") != null) {
			returnVal.setBudgetReviewResultTypeId((String) fields.get("budgetReviewResultTypeId"));
}

		if(fields.get("reviewDate") != null) {
String buf = fields.get("reviewDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setReviewDate(ibuf);
}


		return returnVal;
 } 
	public static BudgetReview map(GenericValue val) {

BudgetReview returnVal = new BudgetReview();
		returnVal.setBudgetId(val.getString("budgetId"));
		returnVal.setBudgetReviewId(val.getString("budgetReviewId"));
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setBudgetReviewResultTypeId(val.getString("budgetReviewResultTypeId"));
		returnVal.setReviewDate(val.getTimestamp("reviewDate"));


return returnVal;

}

public static BudgetReview map(HttpServletRequest request) throws Exception {

		BudgetReview returnVal = new BudgetReview();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("budgetId")) {
returnVal.setBudgetId(request.getParameter("budgetId"));
}

		if(paramMap.containsKey("budgetReviewId"))  {
returnVal.setBudgetReviewId(request.getParameter("budgetReviewId"));
}
		if(paramMap.containsKey("partyId"))  {
returnVal.setPartyId(request.getParameter("partyId"));
}
		if(paramMap.containsKey("budgetReviewResultTypeId"))  {
returnVal.setBudgetReviewResultTypeId(request.getParameter("budgetReviewResultTypeId"));
}
		if(paramMap.containsKey("reviewDate"))  {
String buf = request.getParameter("reviewDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setReviewDate(ibuf);
}
return returnVal;

}
}
