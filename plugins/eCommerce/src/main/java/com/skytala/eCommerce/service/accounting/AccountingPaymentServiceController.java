package com.skytala.eCommerce.service.accounting;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ofbiz.service.GenericServiceException;
import org.apache.ofbiz.service.LocalDispatcher;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.service.ServiceAuthException;
import org.apache.ofbiz.service.ServiceValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service/accountingPayment")
public class AccountingPaymentServiceController{

	@RequestMapping(method = RequestMethod.POST, value = "/createPaymentContent")
	public ResponseEntity<Object> createPaymentContent(HttpSession session, @RequestParam(value="paymentContentTypeId") String paymentContentTypeId, @RequestParam(value="paymentId") String paymentId, @RequestParam(value="contentId") String contentId, @RequestParam(value="ownerContentId", required=false) String ownerContentId, @RequestParam(value="templateDataResourceId", required=false) String templateDataResourceId, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="instanceOfContentId", required=false) String instanceOfContentId, @RequestParam(value="contentTypeId", required=false) String contentTypeId, @RequestParam(value="description", required=false) String description, @RequestParam(value="mimeTypeId", required=false) String mimeTypeId, @RequestParam(value="childLeafCount", required=false) Long childLeafCount, @RequestParam(value="serviceName", required=false) String serviceName, @RequestParam(value="customMethodId", required=false) String customMethodId, @RequestParam(value="characterSetId", required=false) String characterSetId, @RequestParam(value="thruDate", required=false) Timestamp thruDate, @RequestParam(value="contentName", required=false) Long contentName, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="privilegeEnumId", required=false) String privilegeEnumId, @RequestParam(value="dataSourceId", required=false) String dataSourceId, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="localeString", required=false) String localeString, @RequestParam(value="dataResourceId", required=false) String dataResourceId, @RequestParam(value="decoratorContentId", required=false) String decoratorContentId, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin, @RequestParam(value="childBranchCount", required=false) Long childBranchCount) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("paymentContentTypeId",paymentContentTypeId);
		paramMap.put("paymentId",paymentId);
		paramMap.put("contentId",contentId);
		paramMap.put("ownerContentId",ownerContentId);
		paramMap.put("templateDataResourceId",templateDataResourceId);
		paramMap.put("lastModifiedDate",lastModifiedDate);
		paramMap.put("instanceOfContentId",instanceOfContentId);
		paramMap.put("contentTypeId",contentTypeId);
		paramMap.put("description",description);
		paramMap.put("mimeTypeId",mimeTypeId);
		paramMap.put("childLeafCount",childLeafCount);
		paramMap.put("serviceName",serviceName);
		paramMap.put("customMethodId",customMethodId);
		paramMap.put("characterSetId",characterSetId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("contentName",contentName);
		paramMap.put("fromDate",fromDate);
		paramMap.put("privilegeEnumId",privilegeEnumId);
		paramMap.put("dataSourceId",dataSourceId);
		paramMap.put("lastModifiedByUserLogin",lastModifiedByUserLogin);
		paramMap.put("createdDate",createdDate);
		paramMap.put("statusId",statusId);
		paramMap.put("localeString",localeString);
		paramMap.put("dataResourceId",dataResourceId);
		paramMap.put("decoratorContentId",decoratorContentId);
		paramMap.put("createdByUserLogin",createdByUserLogin);
		paramMap.put("childBranchCount",childBranchCount);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createPaymentContent", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deletePaymentGroupType")
	public ResponseEntity<Object> deletePaymentGroupType(HttpSession session, @RequestParam(value="paymentGroupTypeId") String paymentGroupTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("paymentGroupTypeId",paymentGroupTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deletePaymentGroupType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateBillingAccountTermAttr")
	public ResponseEntity<Object> updateBillingAccountTermAttr(HttpSession session, @RequestParam(value="billingAccountTermId") String billingAccountTermId, @RequestParam(value="attrName") String attrName, @RequestParam(value="attrValue", required=false) Long attrValue) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("billingAccountTermId",billingAccountTermId);
		paramMap.put("attrName",attrName);
		paramMap.put("attrValue",attrValue);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateBillingAccountTermAttr", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createPaymentType")
	public ResponseEntity<Object> createPaymentType(HttpSession session, @RequestParam(value="paymentTypeId", required=false) String paymentTypeId, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="hasTable", required=false) String hasTable, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("paymentTypeId",paymentTypeId);
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createPaymentType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteDeduction")
	public ResponseEntity<Object> deleteDeduction(HttpSession session, @RequestParam(value="deductionId") String deductionId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("deductionId",deductionId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteDeduction", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updatePayment")
	public ResponseEntity<Object> updatePayment(HttpSession session, @RequestParam(value="paymentId") String paymentId, @RequestParam(value="roleTypeIdTo", required=false) String roleTypeIdTo, @RequestParam(value="finAccountTransId", required=false) String finAccountTransId, @RequestParam(value="partyIdFrom", required=false) String partyIdFrom, @RequestParam(value="amount", required=false) BigDecimal amount, @RequestParam(value="paymentPreferenceId", required=false) String paymentPreferenceId, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="actualCurrencyAmount", required=false) BigDecimal actualCurrencyAmount, @RequestParam(value="overrideGlAccountId", required=false) String overrideGlAccountId, @RequestParam(value="paymentMethodTypeId", required=false) String paymentMethodTypeId, @RequestParam(value="actualCurrencyUomId", required=false) String actualCurrencyUomId, @RequestParam(value="paymentTypeId", required=false) String paymentTypeId, @RequestParam(value="currencyUomId", required=false) String currencyUomId, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="paymentMethodId", required=false) String paymentMethodId, @RequestParam(value="paymentGatewayResponseId", required=false) String paymentGatewayResponseId, @RequestParam(value="paymentRefNum", required=false) String paymentRefNum, @RequestParam(value="partyIdTo", required=false) String partyIdTo, @RequestParam(value="effectiveDate", required=false) Timestamp effectiveDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("paymentId",paymentId);
		paramMap.put("roleTypeIdTo",roleTypeIdTo);
		paramMap.put("finAccountTransId",finAccountTransId);
		paramMap.put("partyIdFrom",partyIdFrom);
		paramMap.put("amount",amount);
		paramMap.put("paymentPreferenceId",paymentPreferenceId);
		paramMap.put("comments",comments);
		paramMap.put("actualCurrencyAmount",actualCurrencyAmount);
		paramMap.put("overrideGlAccountId",overrideGlAccountId);
		paramMap.put("paymentMethodTypeId",paymentMethodTypeId);
		paramMap.put("actualCurrencyUomId",actualCurrencyUomId);
		paramMap.put("paymentTypeId",paymentTypeId);
		paramMap.put("currencyUomId",currencyUomId);
		paramMap.put("statusId",statusId);
		paramMap.put("paymentMethodId",paymentMethodId);
		paramMap.put("paymentGatewayResponseId",paymentGatewayResponseId);
		paramMap.put("paymentRefNum",paymentRefNum);
		paramMap.put("partyIdTo",partyIdTo);
		paramMap.put("effectiveDate",effectiveDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updatePayment", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/removePaymentApplication")
	public ResponseEntity<Object> removePaymentApplication(HttpSession session, @RequestParam(value="paymentApplicationId") String paymentApplicationId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("paymentApplicationId",paymentApplicationId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removePaymentApplication", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updatePaymentContent")
	public ResponseEntity<Object> updatePaymentContent(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="paymentContentTypeId") String paymentContentTypeId, @RequestParam(value="paymentId") String paymentId, @RequestParam(value="contentId") String contentId, @RequestParam(value="ownerContentId", required=false) String ownerContentId, @RequestParam(value="templateDataResourceId", required=false) String templateDataResourceId, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="instanceOfContentId", required=false) String instanceOfContentId, @RequestParam(value="contentTypeId", required=false) String contentTypeId, @RequestParam(value="description", required=false) String description, @RequestParam(value="mimeTypeId", required=false) String mimeTypeId, @RequestParam(value="childLeafCount", required=false) Long childLeafCount, @RequestParam(value="serviceName", required=false) String serviceName, @RequestParam(value="customMethodId", required=false) String customMethodId, @RequestParam(value="characterSetId", required=false) String characterSetId, @RequestParam(value="thruDate", required=false) Timestamp thruDate, @RequestParam(value="contentName", required=false) Long contentName, @RequestParam(value="privilegeEnumId", required=false) String privilegeEnumId, @RequestParam(value="dataSourceId", required=false) String dataSourceId, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="localeString", required=false) String localeString, @RequestParam(value="dataResourceId", required=false) String dataResourceId, @RequestParam(value="decoratorContentId", required=false) String decoratorContentId, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin, @RequestParam(value="childBranchCount", required=false) Long childBranchCount) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("paymentContentTypeId",paymentContentTypeId);
		paramMap.put("paymentId",paymentId);
		paramMap.put("contentId",contentId);
		paramMap.put("ownerContentId",ownerContentId);
		paramMap.put("templateDataResourceId",templateDataResourceId);
		paramMap.put("lastModifiedDate",lastModifiedDate);
		paramMap.put("instanceOfContentId",instanceOfContentId);
		paramMap.put("contentTypeId",contentTypeId);
		paramMap.put("description",description);
		paramMap.put("mimeTypeId",mimeTypeId);
		paramMap.put("childLeafCount",childLeafCount);
		paramMap.put("serviceName",serviceName);
		paramMap.put("customMethodId",customMethodId);
		paramMap.put("characterSetId",characterSetId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("contentName",contentName);
		paramMap.put("privilegeEnumId",privilegeEnumId);
		paramMap.put("dataSourceId",dataSourceId);
		paramMap.put("lastModifiedByUserLogin",lastModifiedByUserLogin);
		paramMap.put("createdDate",createdDate);
		paramMap.put("statusId",statusId);
		paramMap.put("localeString",localeString);
		paramMap.put("dataResourceId",dataResourceId);
		paramMap.put("decoratorContentId",decoratorContentId);
		paramMap.put("createdByUserLogin",createdByUserLogin);
		paramMap.put("childBranchCount",childBranchCount);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updatePaymentContent", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updatePaymentType")
	public ResponseEntity<Object> updatePaymentType(HttpSession session, @RequestParam(value="paymentTypeId") String paymentTypeId, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="hasTable", required=false) String hasTable, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("paymentTypeId",paymentTypeId);
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updatePaymentType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/checkAndCreateBatchForValidPayments")
	public ResponseEntity<Object> checkAndCreateBatchForValidPayments(HttpSession session, @RequestParam(value="paymentIds") List paymentIds, @RequestParam(value="paymentGroupTypeId") String paymentGroupTypeId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="paymentGroupName", required=false) String paymentGroupName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("paymentIds",paymentIds);
		paramMap.put("paymentGroupTypeId",paymentGroupTypeId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("paymentGroupName",paymentGroupName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("checkAndCreateBatchForValidPayments", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/getInvoicePaymentInfoListByDueDateOffset")
	public ResponseEntity<Object> getInvoicePaymentInfoListByDueDateOffset(HttpSession session, @RequestParam(value="invoiceTypeId") String invoiceTypeId, @RequestParam(value="daysOffset") Long daysOffset, @RequestParam(value="partyIdFrom", required=false) String partyIdFrom, @RequestParam(value="partyId", required=false) String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("invoiceTypeId",invoiceTypeId);
		paramMap.put("daysOffset",daysOffset);
		paramMap.put("partyIdFrom",partyIdFrom);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getInvoicePaymentInfoListByDueDateOffset", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updatePaymentBudgetAllocation")
	public ResponseEntity<Object> updatePaymentBudgetAllocation(HttpSession session, @RequestParam(value="paymentId") String paymentId, @RequestParam(value="budgetItemSeqId") String budgetItemSeqId, @RequestParam(value="budgetId") String budgetId, @RequestParam(value="amount", required=false) BigDecimal amount) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("paymentId",paymentId);
		paramMap.put("budgetItemSeqId",budgetItemSeqId);
		paramMap.put("budgetId",budgetId);
		paramMap.put("amount",amount);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updatePaymentBudgetAllocation", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createPaymentApplication")
	public ResponseEntity<Object> createPaymentApplication(HttpSession session, @RequestParam(value="paymentId") String paymentId, @RequestParam(value="toPaymentId", required=false) String toPaymentId, @RequestParam(value="amountApplied", required=false) BigDecimal amountApplied, @RequestParam(value="taxAuthGeoId", required=false) String taxAuthGeoId, @RequestParam(value="invoiceId", required=false) String invoiceId, @RequestParam(value="billingAccountId", required=false) String billingAccountId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("paymentId",paymentId);
		paramMap.put("toPaymentId",toPaymentId);
		paramMap.put("amountApplied",amountApplied);
		paramMap.put("taxAuthGeoId",taxAuthGeoId);
		paramMap.put("invoiceId",invoiceId);
		paramMap.put("billingAccountId",billingAccountId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createPaymentApplication", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deletePaymentBudgetAllocation")
	public ResponseEntity<Object> deletePaymentBudgetAllocation(HttpSession session, @RequestParam(value="paymentId") String paymentId, @RequestParam(value="budgetItemSeqId") String budgetItemSeqId, @RequestParam(value="budgetId") String budgetId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("paymentId",paymentId);
		paramMap.put("budgetItemSeqId",budgetItemSeqId);
		paramMap.put("budgetId",budgetId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deletePaymentBudgetAllocation", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/setPaymentStatus")
	public ResponseEntity<Object> setPaymentStatus(HttpSession session, @RequestParam(value="statusId") String statusId, @RequestParam(value="paymentId") String paymentId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("statusId",statusId);
		paramMap.put("paymentId",paymentId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("setPaymentStatus", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updatePaymentGroupType")
	public ResponseEntity<Object> updatePaymentGroupType(HttpSession session, @RequestParam(value="paymentGroupTypeId") String paymentGroupTypeId, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="hasTable", required=false) String hasTable, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("paymentGroupTypeId",paymentGroupTypeId);
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updatePaymentGroupType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/checkPaymentInvoices")
	public ResponseEntity<Object> checkPaymentInvoices(HttpSession session, @RequestParam(value="paymentId") String paymentId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("paymentId",paymentId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("checkPaymentInvoices", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deletePaymentContentType")
	public ResponseEntity<Object> deletePaymentContentType(HttpSession session, @RequestParam(value="paymentContentTypeId") String paymentContentTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("paymentContentTypeId",paymentContentTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deletePaymentContentType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createBillingAccountTermAttr")
	public ResponseEntity<Object> createBillingAccountTermAttr(HttpSession session, @RequestParam(value="billingAccountTermId") String billingAccountTermId, @RequestParam(value="attrName") String attrName, @RequestParam(value="attrValue", required=false) Long attrValue) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("billingAccountTermId",billingAccountTermId);
		paramMap.put("attrName",attrName);
		paramMap.put("attrValue",attrValue);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createBillingAccountTermAttr", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createMatchingPaymentApplication")
	public ResponseEntity<Object> createMatchingPaymentApplication(HttpSession session, @RequestParam(value="paymentId", required=false) String paymentId, @RequestParam(value="invoiceId", required=false) String invoiceId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("paymentId",paymentId);
		paramMap.put("invoiceId",invoiceId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createMatchingPaymentApplication", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updatePaymentApplicationDef")
	public ResponseEntity<Object> updatePaymentApplicationDef(HttpSession session, @RequestParam(value="paymentId") String paymentId, @RequestParam(value="toPaymentId", required=false) String toPaymentId, @RequestParam(value="amountApplied", required=false) BigDecimal amountApplied, @RequestParam(value="overrideGlAccountId", required=false) String overrideGlAccountId, @RequestParam(value="taxAuthGeoId", required=false) String taxAuthGeoId, @RequestParam(value="invoiceId", required=false) String invoiceId, @RequestParam(value="invoiceItemSeqId", required=false) String invoiceItemSeqId, @RequestParam(value="billingAccountId", required=false) String billingAccountId, @RequestParam(value="paymentApplicationId", required=false) String paymentApplicationId, @RequestParam(value="invoiceProcessing", required=false) String invoiceProcessing) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("paymentId",paymentId);
		paramMap.put("toPaymentId",toPaymentId);
		paramMap.put("amountApplied",amountApplied);
		paramMap.put("overrideGlAccountId",overrideGlAccountId);
		paramMap.put("taxAuthGeoId",taxAuthGeoId);
		paramMap.put("invoiceId",invoiceId);
		paramMap.put("invoiceItemSeqId",invoiceItemSeqId);
		paramMap.put("billingAccountId",billingAccountId);
		paramMap.put("paymentApplicationId",paymentApplicationId);
		paramMap.put("invoiceProcessing",invoiceProcessing);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updatePaymentApplicationDef", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateDeduction")
	public ResponseEntity<Object> updateDeduction(HttpSession session, @RequestParam(value="deductionId") String deductionId, @RequestParam(value="amount", required=false) BigDecimal amount, @RequestParam(value="paymentId", required=false) String paymentId, @RequestParam(value="deductionTypeId", required=false) String deductionTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("deductionId",deductionId);
		paramMap.put("amount",amount);
		paramMap.put("paymentId",paymentId);
		paramMap.put("deductionTypeId",deductionTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateDeduction", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deletePaymentType")
	public ResponseEntity<Object> deletePaymentType(HttpSession session, @RequestParam(value="paymentTypeId") String paymentTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("paymentTypeId",paymentTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deletePaymentType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createDeduction")
	public ResponseEntity<Object> createDeduction(HttpSession session, @RequestParam(value="amount", required=false) BigDecimal amount, @RequestParam(value="paymentId", required=false) String paymentId, @RequestParam(value="deductionTypeId", required=false) String deductionTypeId, @RequestParam(value="deductionId", required=false) String deductionId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("amount",amount);
		paramMap.put("paymentId",paymentId);
		paramMap.put("deductionTypeId",deductionTypeId);
		paramMap.put("deductionId",deductionId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createDeduction", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/removePaymentContent")
	public ResponseEntity<Object> removePaymentContent(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="paymentContentTypeId") String paymentContentTypeId, @RequestParam(value="paymentId") String paymentId, @RequestParam(value="contentId") String contentId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("paymentContentTypeId",paymentContentTypeId);
		paramMap.put("paymentId",paymentId);
		paramMap.put("contentId",contentId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removePaymentContent", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createPaymentGroupAndMember")
	public ResponseEntity<Object> createPaymentGroupAndMember(HttpSession session, @RequestParam(value="paymentIds") List paymentIds, @RequestParam(value="paymentGroupTypeId") String paymentGroupTypeId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="paymentGroupName", required=false) String paymentGroupName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("paymentIds",paymentIds);
		paramMap.put("paymentGroupTypeId",paymentGroupTypeId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("paymentGroupName",paymentGroupName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createPaymentGroupAndMember", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createPaymentAndApplicationForParty")
	public ResponseEntity<Object> createPaymentAndApplicationForParty(HttpSession session, @RequestParam(value="invoices") List invoices, @RequestParam(value="paymentMethodId") String paymentMethodId, @RequestParam(value="partyId") String partyId, @RequestParam(value="finAccountId", required=false) String finAccountId, @RequestParam(value="organizationPartyId", required=false) String organizationPartyId, @RequestParam(value="checkStartNumber", required=false) Long checkStartNumber, @RequestParam(value="paymentMethodTypeId", required=false) String paymentMethodTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("invoices",invoices);
		paramMap.put("paymentMethodId",paymentMethodId);
		paramMap.put("partyId",partyId);
		paramMap.put("finAccountId",finAccountId);
		paramMap.put("organizationPartyId",organizationPartyId);
		paramMap.put("checkStartNumber",checkStartNumber);
		paramMap.put("paymentMethodTypeId",paymentMethodTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createPaymentAndApplicationForParty", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updatePaymentAttribute")
	public ResponseEntity<Object> updatePaymentAttribute(HttpSession session, @RequestParam(value="paymentId") String paymentId, @RequestParam(value="attrName") String attrName, @RequestParam(value="attrDescription", required=false) String attrDescription, @RequestParam(value="attrValue", required=false) Long attrValue) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("paymentId",paymentId);
		paramMap.put("attrName",attrName);
		paramMap.put("attrDescription",attrDescription);
		paramMap.put("attrValue",attrValue);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updatePaymentAttribute", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deletePaymentTypeAttr")
	public ResponseEntity<Object> deletePaymentTypeAttr(HttpSession session, @RequestParam(value="paymentTypeId") String paymentTypeId, @RequestParam(value="attrName") String attrName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("paymentTypeId",paymentTypeId);
		paramMap.put("attrName",attrName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deletePaymentTypeAttr", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createPaymentContentType")
	public ResponseEntity<Object> createPaymentContentType(HttpSession session, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="paymentContentTypeId", required=false) String paymentContentTypeId, @RequestParam(value="hasTable", required=false) String hasTable, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("paymentContentTypeId",paymentContentTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createPaymentContentType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteDeductionType")
	public ResponseEntity<Object> deleteDeductionType(HttpSession session, @RequestParam(value="deductionTypeId") String deductionTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("deductionTypeId",deductionTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteDeductionType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/cancelCheckRunPayments")
	public ResponseEntity<Object> cancelCheckRunPayments(HttpSession session, @RequestParam(value="paymentGroupId") String paymentGroupId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("paymentGroupId",paymentGroupId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("cancelCheckRunPayments", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updatePaymentApplication")
	public ResponseEntity<Object> updatePaymentApplication(HttpSession session, @RequestParam(value="paymentId") String paymentId, @RequestParam(value="toPaymentId", required=false) String toPaymentId, @RequestParam(value="amountApplied", required=false) BigDecimal amountApplied, @RequestParam(value="overrideGlAccountId", required=false) String overrideGlAccountId, @RequestParam(value="taxAuthGeoId", required=false) String taxAuthGeoId, @RequestParam(value="invoiceId", required=false) String invoiceId, @RequestParam(value="invoiceItemSeqId", required=false) String invoiceItemSeqId, @RequestParam(value="billingAccountId", required=false) String billingAccountId, @RequestParam(value="paymentApplicationId", required=false) String paymentApplicationId, @RequestParam(value="invoiceProcessing", required=false) String invoiceProcessing) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("paymentId",paymentId);
		paramMap.put("toPaymentId",toPaymentId);
		paramMap.put("amountApplied",amountApplied);
		paramMap.put("overrideGlAccountId",overrideGlAccountId);
		paramMap.put("taxAuthGeoId",taxAuthGeoId);
		paramMap.put("invoiceId",invoiceId);
		paramMap.put("invoiceItemSeqId",invoiceItemSeqId);
		paramMap.put("billingAccountId",billingAccountId);
		paramMap.put("paymentApplicationId",paymentApplicationId);
		paramMap.put("invoiceProcessing",invoiceProcessing);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updatePaymentApplication", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createPaymentFromOrder")
	public ResponseEntity<Object> createPaymentFromOrder(HttpSession session, @RequestParam(value="orderId") String orderId, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="paymentMethodId", required=false) String paymentMethodId, @RequestParam(value="paymentRefNum", required=false) String paymentRefNum) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderId",orderId);
		paramMap.put("comments",comments);
		paramMap.put("paymentMethodId",paymentMethodId);
		paramMap.put("paymentRefNum",paymentRefNum);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createPaymentFromOrder", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createDeductionType")
	public ResponseEntity<Object> createDeductionType(HttpSession session, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="hasTable", required=false) String hasTable, @RequestParam(value="deductionTypeId", required=false) String deductionTypeId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("deductionTypeId",deductionTypeId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createDeductionType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/getInvoicePaymentInfoList")
	public ResponseEntity<Object> getInvoicePaymentInfoList(HttpSession session, @RequestParam(value="invoiceId", required=false) String invoiceId, @RequestParam(value="invoice", required=false) org.apache.ofbiz.entity.GenericValue invoice) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("invoiceId",invoiceId);
		paramMap.put("invoice",invoice);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getInvoicePaymentInfoList", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/voidPayment")
	public ResponseEntity<Object> voidPayment(HttpSession session, @RequestParam(value="paymentId") String paymentId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("paymentId",paymentId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("voidPayment", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/cancelPaymentBatch")
	public ResponseEntity<Object> cancelPaymentBatch(HttpSession session, @RequestParam(value="paymentGroupId") String paymentGroupId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("paymentGroupId",paymentGroupId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("cancelPaymentBatch", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/getPaymentRunningTotal")
	public ResponseEntity<Object> getPaymentRunningTotal(HttpSession session, @RequestParam(value="paymentIds") List paymentIds, @RequestParam(value="organizationPartyId", required=false) String organizationPartyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("paymentIds",paymentIds);
		paramMap.put("organizationPartyId",organizationPartyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getPaymentRunningTotal", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/getPaymentGroupReconciliationId")
	public ResponseEntity<Object> getPaymentGroupReconciliationId(HttpSession session, @RequestParam(value="paymentGroupId") String paymentGroupId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("paymentGroupId",paymentGroupId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getPaymentGroupReconciliationId", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createPaymentBudgetAllocation")
	public ResponseEntity<Object> createPaymentBudgetAllocation(HttpSession session, @RequestParam(value="paymentId") String paymentId, @RequestParam(value="budgetItemSeqId") String budgetItemSeqId, @RequestParam(value="budgetId") String budgetId, @RequestParam(value="amount", required=false) BigDecimal amount) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("paymentId",paymentId);
		paramMap.put("budgetItemSeqId",budgetItemSeqId);
		paramMap.put("budgetId",budgetId);
		paramMap.put("amount",amount);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createPaymentBudgetAllocation", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createPaymentAttribute")
	public ResponseEntity<Object> createPaymentAttribute(HttpSession session, @RequestParam(value="paymentId") String paymentId, @RequestParam(value="attrName") String attrName, @RequestParam(value="attrDescription", required=false) String attrDescription, @RequestParam(value="attrValue", required=false) Long attrValue) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("paymentId",paymentId);
		paramMap.put("attrName",attrName);
		paramMap.put("attrDescription",attrDescription);
		paramMap.put("attrValue",attrValue);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createPaymentAttribute", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateDeductionType")
	public ResponseEntity<Object> updateDeductionType(HttpSession session, @RequestParam(value="deductionTypeId") String deductionTypeId, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="hasTable", required=false) String hasTable, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("deductionTypeId",deductionTypeId);
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateDeductionType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updatePaymentTypeAttr")
	public ResponseEntity<Object> updatePaymentTypeAttr(HttpSession session, @RequestParam(value="paymentTypeId") String paymentTypeId, @RequestParam(value="attrName") String attrName, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("paymentTypeId",paymentTypeId);
		paramMap.put("attrName",attrName);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updatePaymentTypeAttr", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createPaymentTypeAttr")
	public ResponseEntity<Object> createPaymentTypeAttr(HttpSession session, @RequestParam(value="paymentTypeId") String paymentTypeId, @RequestParam(value="attrName") String attrName, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("paymentTypeId",paymentTypeId);
		paramMap.put("attrName",attrName);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createPaymentTypeAttr", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/getPayments")
	public ResponseEntity<Object> getPayments(HttpSession session, @RequestParam(value="paymentGroupId", required=false) String paymentGroupId, @RequestParam(value="finAccountTransId", required=false) String finAccountTransId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("paymentGroupId",paymentGroupId);
		paramMap.put("finAccountTransId",finAccountTransId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getPayments", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deletePaymentAttribute")
	public ResponseEntity<Object> deletePaymentAttribute(HttpSession session, @RequestParam(value="paymentId") String paymentId, @RequestParam(value="attrName") String attrName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("paymentId",paymentId);
		paramMap.put("attrName",attrName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deletePaymentAttribute", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createPayment")
	public ResponseEntity<Object> createPayment(HttpSession session, @RequestParam(value="partyIdFrom") String partyIdFrom, @RequestParam(value="amount") BigDecimal amount, @RequestParam(value="paymentTypeId") String paymentTypeId, @RequestParam(value="partyIdTo") String partyIdTo, @RequestParam(value="roleTypeIdTo", required=false) String roleTypeIdTo, @RequestParam(value="finAccountTransId", required=false) String finAccountTransId, @RequestParam(value="paymentPreferenceId", required=false) String paymentPreferenceId, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="actualCurrencyAmount", required=false) BigDecimal actualCurrencyAmount, @RequestParam(value="overrideGlAccountId", required=false) String overrideGlAccountId, @RequestParam(value="paymentMethodTypeId", required=false) String paymentMethodTypeId, @RequestParam(value="actualCurrencyUomId", required=false) String actualCurrencyUomId, @RequestParam(value="currencyUomId", required=false) String currencyUomId, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="paymentMethodId", required=false) String paymentMethodId, @RequestParam(value="paymentGatewayResponseId", required=false) String paymentGatewayResponseId, @RequestParam(value="paymentId", required=false) String paymentId, @RequestParam(value="paymentRefNum", required=false) String paymentRefNum, @RequestParam(value="effectiveDate", required=false) Timestamp effectiveDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("partyIdFrom",partyIdFrom);
		paramMap.put("amount",amount);
		paramMap.put("paymentTypeId",paymentTypeId);
		paramMap.put("partyIdTo",partyIdTo);
		paramMap.put("roleTypeIdTo",roleTypeIdTo);
		paramMap.put("finAccountTransId",finAccountTransId);
		paramMap.put("paymentPreferenceId",paymentPreferenceId);
		paramMap.put("comments",comments);
		paramMap.put("actualCurrencyAmount",actualCurrencyAmount);
		paramMap.put("overrideGlAccountId",overrideGlAccountId);
		paramMap.put("paymentMethodTypeId",paymentMethodTypeId);
		paramMap.put("actualCurrencyUomId",actualCurrencyUomId);
		paramMap.put("currencyUomId",currencyUomId);
		paramMap.put("statusId",statusId);
		paramMap.put("paymentMethodId",paymentMethodId);
		paramMap.put("paymentGatewayResponseId",paymentGatewayResponseId);
		paramMap.put("paymentId",paymentId);
		paramMap.put("paymentRefNum",paymentRefNum);
		paramMap.put("effectiveDate",effectiveDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createPayment", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updatePaymentContentType")
	public ResponseEntity<Object> updatePaymentContentType(HttpSession session, @RequestParam(value="paymentContentTypeId") String paymentContentTypeId, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="hasTable", required=false) String hasTable, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("paymentContentTypeId",paymentContentTypeId);
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updatePaymentContentType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createPaymentAndPaymentGroupForInvoices")
	public ResponseEntity<Object> createPaymentAndPaymentGroupForInvoices(HttpSession session, @RequestParam(value="paymentMethodId") String paymentMethodId, @RequestParam(value="organizationPartyId") String organizationPartyId, @RequestParam(value="invoiceIds") List invoiceIds, @RequestParam(value="checkStartNumber", required=false) Long checkStartNumber, @RequestParam(value="paymentMethodTypeId", required=false) String paymentMethodTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("paymentMethodId",paymentMethodId);
		paramMap.put("organizationPartyId",organizationPartyId);
		paramMap.put("invoiceIds",invoiceIds);
		paramMap.put("checkStartNumber",checkStartNumber);
		paramMap.put("paymentMethodTypeId",paymentMethodTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createPaymentAndPaymentGroupForInvoices", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createPaymentGroupType")
	public ResponseEntity<Object> createPaymentGroupType(HttpSession session, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="hasTable", required=false) String hasTable, @RequestParam(value="description", required=false) String description, @RequestParam(value="paymentGroupTypeId", required=false) String paymentGroupTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("description",description);
		paramMap.put("paymentGroupTypeId",paymentGroupTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createPaymentGroupType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/quickSendPayment")
	public ResponseEntity<Object> quickSendPayment(HttpSession session, @RequestParam(value="paymentId") String paymentId, @RequestParam(value="roleTypeIdTo", required=false) String roleTypeIdTo, @RequestParam(value="finAccountTransId", required=false) String finAccountTransId, @RequestParam(value="partyIdFrom", required=false) String partyIdFrom, @RequestParam(value="amount", required=false) BigDecimal amount, @RequestParam(value="paymentPreferenceId", required=false) String paymentPreferenceId, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="actualCurrencyAmount", required=false) BigDecimal actualCurrencyAmount, @RequestParam(value="overrideGlAccountId", required=false) String overrideGlAccountId, @RequestParam(value="paymentMethodTypeId", required=false) String paymentMethodTypeId, @RequestParam(value="actualCurrencyUomId", required=false) String actualCurrencyUomId, @RequestParam(value="paymentTypeId", required=false) String paymentTypeId, @RequestParam(value="currencyUomId", required=false) String currencyUomId, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="paymentMethodId", required=false) String paymentMethodId, @RequestParam(value="paymentGatewayResponseId", required=false) String paymentGatewayResponseId, @RequestParam(value="paymentRefNum", required=false) String paymentRefNum, @RequestParam(value="partyIdTo", required=false) String partyIdTo, @RequestParam(value="effectiveDate", required=false) Timestamp effectiveDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("paymentId",paymentId);
		paramMap.put("roleTypeIdTo",roleTypeIdTo);
		paramMap.put("finAccountTransId",finAccountTransId);
		paramMap.put("partyIdFrom",partyIdFrom);
		paramMap.put("amount",amount);
		paramMap.put("paymentPreferenceId",paymentPreferenceId);
		paramMap.put("comments",comments);
		paramMap.put("actualCurrencyAmount",actualCurrencyAmount);
		paramMap.put("overrideGlAccountId",overrideGlAccountId);
		paramMap.put("paymentMethodTypeId",paymentMethodTypeId);
		paramMap.put("actualCurrencyUomId",actualCurrencyUomId);
		paramMap.put("paymentTypeId",paymentTypeId);
		paramMap.put("currencyUomId",currencyUomId);
		paramMap.put("statusId",statusId);
		paramMap.put("paymentMethodId",paymentMethodId);
		paramMap.put("paymentGatewayResponseId",paymentGatewayResponseId);
		paramMap.put("paymentRefNum",paymentRefNum);
		paramMap.put("partyIdTo",partyIdTo);
		paramMap.put("effectiveDate",effectiveDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("quickSendPayment", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createPaymentAndApplication")
	public ResponseEntity<Object> createPaymentAndApplication(HttpSession session, @RequestParam(value="partyIdFrom") String partyIdFrom, @RequestParam(value="amount") BigDecimal amount, @RequestParam(value="paymentTypeId") String paymentTypeId, @RequestParam(value="statusId") String statusId, @RequestParam(value="partyIdTo") String partyIdTo, @RequestParam(value="roleTypeIdTo", required=false) String roleTypeIdTo, @RequestParam(value="finAccountTransId", required=false) String finAccountTransId, @RequestParam(value="paymentPreferenceId", required=false) String paymentPreferenceId, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="actualCurrencyAmount", required=false) BigDecimal actualCurrencyAmount, @RequestParam(value="overrideGlAccountId", required=false) String overrideGlAccountId, @RequestParam(value="invoiceItemSeqId", required=false) String invoiceItemSeqId, @RequestParam(value="billingAccountId", required=false) String billingAccountId, @RequestParam(value="paymentMethodTypeId", required=false) String paymentMethodTypeId, @RequestParam(value="actualCurrencyUomId", required=false) String actualCurrencyUomId, @RequestParam(value="currencyUomId", required=false) String currencyUomId, @RequestParam(value="paymentMethodId", required=false) String paymentMethodId, @RequestParam(value="paymentGatewayResponseId", required=false) String paymentGatewayResponseId, @RequestParam(value="paymentId", required=false) String paymentId, @RequestParam(value="paymentRefNum", required=false) String paymentRefNum, @RequestParam(value="taxAuthGeoId", required=false) String taxAuthGeoId, @RequestParam(value="invoiceId", required=false) String invoiceId, @RequestParam(value="effectiveDate", required=false) Timestamp effectiveDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("partyIdFrom",partyIdFrom);
		paramMap.put("amount",amount);
		paramMap.put("paymentTypeId",paymentTypeId);
		paramMap.put("statusId",statusId);
		paramMap.put("partyIdTo",partyIdTo);
		paramMap.put("roleTypeIdTo",roleTypeIdTo);
		paramMap.put("finAccountTransId",finAccountTransId);
		paramMap.put("paymentPreferenceId",paymentPreferenceId);
		paramMap.put("comments",comments);
		paramMap.put("actualCurrencyAmount",actualCurrencyAmount);
		paramMap.put("overrideGlAccountId",overrideGlAccountId);
		paramMap.put("invoiceItemSeqId",invoiceItemSeqId);
		paramMap.put("billingAccountId",billingAccountId);
		paramMap.put("paymentMethodTypeId",paymentMethodTypeId);
		paramMap.put("actualCurrencyUomId",actualCurrencyUomId);
		paramMap.put("currencyUomId",currencyUomId);
		paramMap.put("paymentMethodId",paymentMethodId);
		paramMap.put("paymentGatewayResponseId",paymentGatewayResponseId);
		paramMap.put("paymentId",paymentId);
		paramMap.put("paymentRefNum",paymentRefNum);
		paramMap.put("taxAuthGeoId",taxAuthGeoId);
		paramMap.put("invoiceId",invoiceId);
		paramMap.put("effectiveDate",effectiveDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createPaymentAndApplication", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteBillingAccountTermAttr")
	public ResponseEntity<Object> deleteBillingAccountTermAttr(HttpSession session, @RequestParam(value="billingAccountTermId") String billingAccountTermId, @RequestParam(value="attrName") String attrName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("billingAccountTermId",billingAccountTermId);
		paramMap.put("attrName",attrName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteBillingAccountTermAttr", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/massChangePaymentStatus")
	public ResponseEntity<Object> massChangePaymentStatus(HttpSession session, @RequestParam(value="paymentIds") List paymentIds, @RequestParam(value="statusId") String statusId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("paymentIds",paymentIds);
		paramMap.put("statusId",statusId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("massChangePaymentStatus", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createFinAccoutnTransFromPayment")
	public ResponseEntity<Object> createFinAccoutnTransFromPayment(HttpSession session, @RequestParam(value="orderItemSeqId", required=false) String orderItemSeqId, @RequestParam(value="finAccountTransId", required=false) String finAccountTransId, @RequestParam(value="amount", required=false) BigDecimal amount, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="entryDate", required=false) Timestamp entryDate, @RequestParam(value="orderId", required=false) String orderId, @RequestParam(value="glReconciliationId", required=false) String glReconciliationId, @RequestParam(value="transactionDate", required=false) Timestamp transactionDate, @RequestParam(value="finAccountId", required=false) String finAccountId, @RequestParam(value="finAccountTransTypeId", required=false) String finAccountTransTypeId, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="paymentId", required=false) String paymentId, @RequestParam(value="performedByPartyId", required=false) String performedByPartyId, @RequestParam(value="reasonEnumId", required=false) String reasonEnumId, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="invoiceIds", required=false) List invoiceIds) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderItemSeqId",orderItemSeqId);
		paramMap.put("finAccountTransId",finAccountTransId);
		paramMap.put("amount",amount);
		paramMap.put("comments",comments);
		paramMap.put("entryDate",entryDate);
		paramMap.put("orderId",orderId);
		paramMap.put("glReconciliationId",glReconciliationId);
		paramMap.put("transactionDate",transactionDate);
		paramMap.put("finAccountId",finAccountId);
		paramMap.put("finAccountTransTypeId",finAccountTransTypeId);
		paramMap.put("statusId",statusId);
		paramMap.put("paymentId",paymentId);
		paramMap.put("performedByPartyId",performedByPartyId);
		paramMap.put("reasonEnumId",reasonEnumId);
		paramMap.put("partyId",partyId);
		paramMap.put("invoiceIds",invoiceIds);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createFinAccoutnTransFromPayment", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(value = (" * "))
	public ResponseEntity<Object> returnErrorPage(HttpSession session) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Requested service does not exist. JSESSIONID=" + session.getId());
	}

}
