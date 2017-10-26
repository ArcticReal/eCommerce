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
@RequestMapping("/service/accountingInvoice")
public class AccountingInvoiceServiceController{

	@RequestMapping(method = RequestMethod.POST, value = "/checkInvoicePaymentApplications")
	public ResponseEntity<Object> checkInvoicePaymentApplications(HttpSession session, @RequestParam(value="invoiceId") String invoiceId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("invoiceId",invoiceId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("checkInvoicePaymentApplications", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateInvoiceTypeAttr")
	public ResponseEntity<Object> updateInvoiceTypeAttr(HttpSession session, @RequestParam(value="invoiceTypeId") String invoiceTypeId, @RequestParam(value="attrName") String attrName, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("invoiceTypeId",invoiceTypeId);
		paramMap.put("attrName",attrName);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateInvoiceTypeAttr", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createInvoiceForOrder")
	public ResponseEntity<Object> createInvoiceForOrder(HttpSession session, @RequestParam(value="orderId") String orderId, @RequestParam(value="billItems") List billItems, @RequestParam(value="invoiceId", required=false) String invoiceId, @RequestParam(value="eventDate", required=false) Timestamp eventDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderId",orderId);
		paramMap.put("billItems",billItems);
		paramMap.put("invoiceId",invoiceId);
		paramMap.put("eventDate",eventDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createInvoiceForOrder", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateInvoiceAttribute")
	public ResponseEntity<Object> updateInvoiceAttribute(HttpSession session, @RequestParam(value="invoiceId") String invoiceId, @RequestParam(value="attrName") String attrName, @RequestParam(value="attrDescription", required=false) String attrDescription, @RequestParam(value="attrValue", required=false) Long attrValue) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("invoiceId",invoiceId);
		paramMap.put("attrName",attrName);
		paramMap.put("attrDescription",attrDescription);
		paramMap.put("attrValue",attrValue);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateInvoiceAttribute", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/setInvoiceStatus")
	public ResponseEntity<Object> setInvoiceStatus(HttpSession session, @RequestParam(value="statusId") String statusId, @RequestParam(value="invoiceId") String invoiceId, @RequestParam(value="statusDate", required=false) Timestamp statusDate, @RequestParam(value="paidDate", required=false) Timestamp paidDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("statusId",statusId);
		paramMap.put("invoiceId",invoiceId);
		paramMap.put("statusDate",statusDate);
		paramMap.put("paidDate",paidDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("setInvoiceStatus", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/calculateInvoicedAdjustmentTotal")
	public ResponseEntity<Object> calculateInvoicedAdjustmentTotal(HttpSession session, @RequestParam(value="orderAdjustment") org.apache.ofbiz.entity.GenericValue orderAdjustment) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderAdjustment",orderAdjustment);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("calculateInvoicedAdjustmentTotal", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createInvoiceItemAssoc")
	public ResponseEntity<Object> createInvoiceItemAssoc(HttpSession session, @RequestParam(value="invoiceItemSeqIdFrom") String invoiceItemSeqIdFrom, @RequestParam(value="invoiceIdFrom") String invoiceIdFrom, @RequestParam(value="invoiceItemSeqIdTo") String invoiceItemSeqIdTo, @RequestParam(value="invoiceItemAssocTypeId") String invoiceItemAssocTypeId, @RequestParam(value="invoiceIdTo") String invoiceIdTo, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="partyIdFrom", required=false) String partyIdFrom, @RequestParam(value="amount", required=false) BigDecimal amount, @RequestParam(value="quantity", required=false) BigDecimal quantity, @RequestParam(value="partyIdTo", required=false) String partyIdTo, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("invoiceItemSeqIdFrom",invoiceItemSeqIdFrom);
		paramMap.put("invoiceIdFrom",invoiceIdFrom);
		paramMap.put("invoiceItemSeqIdTo",invoiceItemSeqIdTo);
		paramMap.put("invoiceItemAssocTypeId",invoiceItemAssocTypeId);
		paramMap.put("invoiceIdTo",invoiceIdTo);
		paramMap.put("fromDate",fromDate);
		paramMap.put("partyIdFrom",partyIdFrom);
		paramMap.put("amount",amount);
		paramMap.put("quantity",quantity);
		paramMap.put("partyIdTo",partyIdTo);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createInvoiceItemAssoc", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateInvoice")
	public ResponseEntity<Object> updateInvoice(HttpSession session, @RequestParam(value="invoiceId") String invoiceId, @RequestParam(value="partyIdFrom", required=false) String partyIdFrom, @RequestParam(value="roleTypeId", required=false) String roleTypeId, @RequestParam(value="recurrenceInfoId", required=false) String recurrenceInfoId, @RequestParam(value="invoiceTypeId", required=false) String invoiceTypeId, @RequestParam(value="dueDate", required=false) Timestamp dueDate, @RequestParam(value="description", required=false) String description, @RequestParam(value="billingAccountId", required=false) String billingAccountId, @RequestParam(value="invoiceDate", required=false) Timestamp invoiceDate, @RequestParam(value="contactMechId", required=false) String contactMechId, @RequestParam(value="currencyUomId", required=false) String currencyUomId, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="paidDate", required=false) Timestamp paidDate, @RequestParam(value="referenceNumber", required=false) String referenceNumber, @RequestParam(value="invoiceMessage", required=false) String invoiceMessage, @RequestParam(value="partyId", required=false) String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("invoiceId",invoiceId);
		paramMap.put("partyIdFrom",partyIdFrom);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("recurrenceInfoId",recurrenceInfoId);
		paramMap.put("invoiceTypeId",invoiceTypeId);
		paramMap.put("dueDate",dueDate);
		paramMap.put("description",description);
		paramMap.put("billingAccountId",billingAccountId);
		paramMap.put("invoiceDate",invoiceDate);
		paramMap.put("contactMechId",contactMechId);
		paramMap.put("currencyUomId",currencyUomId);
		paramMap.put("statusId",statusId);
		paramMap.put("paidDate",paidDate);
		paramMap.put("referenceNumber",referenceNumber);
		paramMap.put("invoiceMessage",invoiceMessage);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateInvoice", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createInvoiceContactMech")
	public ResponseEntity<Object> createInvoiceContactMech(HttpSession session, @RequestParam(value="contactMechPurposeTypeId") String contactMechPurposeTypeId, @RequestParam(value="invoiceId") String invoiceId, @RequestParam(value="contactMechId") String contactMechId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contactMechPurposeTypeId",contactMechPurposeTypeId);
		paramMap.put("invoiceId",invoiceId);
		paramMap.put("contactMechId",contactMechId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createInvoiceContactMech", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteInvoiceContentType")
	public ResponseEntity<Object> deleteInvoiceContentType(HttpSession session, @RequestParam(value="invoiceContentTypeId") String invoiceContentTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("invoiceContentTypeId",invoiceContentTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteInvoiceContentType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createInvoiceNote")
	public ResponseEntity<Object> createInvoiceNote(HttpSession session, @RequestParam(value="invoiceId") String invoiceId, @RequestParam(value="noteId") String noteId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("invoiceId",invoiceId);
		paramMap.put("noteId",noteId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createInvoiceNote", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createInvoiceRole")
	public ResponseEntity<Object> createInvoiceRole(HttpSession session, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="invoiceId") String invoiceId, @RequestParam(value="partyId") String partyId, @RequestParam(value="datetimePerformed", required=false) Timestamp datetimePerformed, @RequestParam(value="percentage", required=false) BigDecimal percentage) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("invoiceId",invoiceId);
		paramMap.put("partyId",partyId);
		paramMap.put("datetimePerformed",datetimePerformed);
		paramMap.put("percentage",percentage);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createInvoiceRole", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateInvoiceTerm")
	public ResponseEntity<Object> updateInvoiceTerm(HttpSession session, @RequestParam(value="invoiceTermId") String invoiceTermId, @RequestParam(value="textValue", required=false) String textValue, @RequestParam(value="termDays", required=false) Long termDays, @RequestParam(value="termTypeId", required=false) String termTypeId, @RequestParam(value="description", required=false) String description, @RequestParam(value="invoiceId", required=false) String invoiceId, @RequestParam(value="invoiceItemSeqId", required=false) String invoiceItemSeqId, @RequestParam(value="uomId", required=false) String uomId, @RequestParam(value="termValue", required=false) BigDecimal termValue) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("invoiceTermId",invoiceTermId);
		paramMap.put("textValue",textValue);
		paramMap.put("termDays",termDays);
		paramMap.put("termTypeId",termTypeId);
		paramMap.put("description",description);
		paramMap.put("invoiceId",invoiceId);
		paramMap.put("invoiceItemSeqId",invoiceItemSeqId);
		paramMap.put("uomId",uomId);
		paramMap.put("termValue",termValue);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateInvoiceTerm", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/autoGenerateInvoiceFromExistingInvoice")
	public ResponseEntity<Object> autoGenerateInvoiceFromExistingInvoice(HttpSession session, @RequestParam(value="recurrenceInfoId") String recurrenceInfoId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("recurrenceInfoId",recurrenceInfoId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("autoGenerateInvoiceFromExistingInvoice", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/massChangeInvoiceStatus")
	public ResponseEntity<Object> massChangeInvoiceStatus(HttpSession session, @RequestParam(value="statusId") String statusId, @RequestParam(value="invoiceIds") List invoiceIds) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("statusId",statusId);
		paramMap.put("invoiceIds",invoiceIds);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("massChangeInvoiceStatus", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/importInvoice")
	public ResponseEntity<Object> importInvoice(HttpSession session, @RequestParam(value="organizationPartyId") String organizationPartyId, @RequestParam(value="uploadedFile", required=false) java.nio.ByteBuffer uploadedFile) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("organizationPartyId",organizationPartyId);
		paramMap.put("uploadedFile",uploadedFile);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("importInvoice", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateInvoiceContentType")
	public ResponseEntity<Object> updateInvoiceContentType(HttpSession session, @RequestParam(value="invoiceContentTypeId") String invoiceContentTypeId, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="hasTable", required=false) String hasTable, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("invoiceContentTypeId",invoiceContentTypeId);
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateInvoiceContentType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createInvoiceTerm")
	public ResponseEntity<Object> createInvoiceTerm(HttpSession session, @RequestParam(value="textValue", required=false) String textValue, @RequestParam(value="termDays", required=false) Long termDays, @RequestParam(value="termTypeId", required=false) String termTypeId, @RequestParam(value="description", required=false) String description, @RequestParam(value="invoiceId", required=false) String invoiceId, @RequestParam(value="invoiceItemSeqId", required=false) String invoiceItemSeqId, @RequestParam(value="uomId", required=false) String uomId, @RequestParam(value="termValue", required=false) BigDecimal termValue) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("textValue",textValue);
		paramMap.put("termDays",termDays);
		paramMap.put("termTypeId",termTypeId);
		paramMap.put("description",description);
		paramMap.put("invoiceId",invoiceId);
		paramMap.put("invoiceItemSeqId",invoiceItemSeqId);
		paramMap.put("uomId",uomId);
		paramMap.put("termValue",termValue);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createInvoiceTerm", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteInvoiceItemTypeAttr")
	public ResponseEntity<Object> deleteInvoiceItemTypeAttr(HttpSession session, @RequestParam(value="invoiceItemTypeId") String invoiceItemTypeId, @RequestParam(value="attrName") String attrName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("invoiceItemTypeId",invoiceItemTypeId);
		paramMap.put("attrName",attrName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteInvoiceItemTypeAttr", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/invoiceSequenceRestart")
	public ResponseEntity<Object> invoiceSequenceRestart(HttpSession session, @RequestParam(value="partyAcctgPreference") org.apache.ofbiz.entity.GenericValue partyAcctgPreference, @RequestParam(value="partyIdFrom", required=false) String partyIdFrom, @RequestParam(value="roleTypeId", required=false) String roleTypeId, @RequestParam(value="recurrenceInfoId", required=false) String recurrenceInfoId, @RequestParam(value="invoiceTypeId", required=false) String invoiceTypeId, @RequestParam(value="dueDate", required=false) Timestamp dueDate, @RequestParam(value="description", required=false) String description, @RequestParam(value="billingAccountId", required=false) String billingAccountId, @RequestParam(value="invoiceDate", required=false) Timestamp invoiceDate, @RequestParam(value="contactMechId", required=false) String contactMechId, @RequestParam(value="currencyUomId", required=false) String currencyUomId, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="paidDate", required=false) Timestamp paidDate, @RequestParam(value="referenceNumber", required=false) String referenceNumber, @RequestParam(value="invoiceId", required=false) String invoiceId, @RequestParam(value="invoiceMessage", required=false) String invoiceMessage, @RequestParam(value="partyId", required=false) String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("partyAcctgPreference",partyAcctgPreference);
		paramMap.put("partyIdFrom",partyIdFrom);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("recurrenceInfoId",recurrenceInfoId);
		paramMap.put("invoiceTypeId",invoiceTypeId);
		paramMap.put("dueDate",dueDate);
		paramMap.put("description",description);
		paramMap.put("billingAccountId",billingAccountId);
		paramMap.put("invoiceDate",invoiceDate);
		paramMap.put("contactMechId",contactMechId);
		paramMap.put("currencyUomId",currencyUomId);
		paramMap.put("statusId",statusId);
		paramMap.put("paidDate",paidDate);
		paramMap.put("referenceNumber",referenceNumber);
		paramMap.put("invoiceId",invoiceId);
		paramMap.put("invoiceMessage",invoiceMessage);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("invoiceSequenceRestart", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteInvoiceAttribute")
	public ResponseEntity<Object> deleteInvoiceAttribute(HttpSession session, @RequestParam(value="invoiceId") String invoiceId, @RequestParam(value="attrName") String attrName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("invoiceId",invoiceId);
		paramMap.put("attrName",attrName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteInvoiceAttribute", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateInvoiceItemType")
	public ResponseEntity<Object> updateInvoiceItemType(HttpSession session, @RequestParam(value="invoiceItemTypeId") String invoiceItemTypeId, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="hasTable", required=false) String hasTable, @RequestParam(value="defaultGlAccountId", required=false) String defaultGlAccountId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("invoiceItemTypeId",invoiceItemTypeId);
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("defaultGlAccountId",defaultGlAccountId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateInvoiceItemType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteInvoiceItemTypeMap")
	public ResponseEntity<Object> deleteInvoiceItemTypeMap(HttpSession session, @RequestParam(value="invoiceItemMapKey") String invoiceItemMapKey, @RequestParam(value="invoiceTypeId") String invoiceTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("invoiceItemMapKey",invoiceItemMapKey);
		paramMap.put("invoiceTypeId",invoiceTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteInvoiceItemTypeMap", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createInvoiceItemAssocType")
	public ResponseEntity<Object> createInvoiceItemAssocType(HttpSession session, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="hasTable", required=false) String hasTable, @RequestParam(value="invoiceItemAssocTypeId", required=false) String invoiceItemAssocTypeId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("invoiceItemAssocTypeId",invoiceItemAssocTypeId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createInvoiceItemAssocType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteInvoiceType")
	public ResponseEntity<Object> deleteInvoiceType(HttpSession session, @RequestParam(value="invoiceTypeId") String invoiceTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("invoiceTypeId",invoiceTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteInvoiceType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createInvoicesFromShipment")
	public ResponseEntity<Object> createInvoicesFromShipment(HttpSession session, @RequestParam(value="shipmentId") String shipmentId, @RequestParam(value="eventDate", required=false) Timestamp eventDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shipmentId",shipmentId);
		paramMap.put("eventDate",eventDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createInvoicesFromShipment", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createInvoice")
	public ResponseEntity<Object> createInvoice(HttpSession session, @RequestParam(value="partyIdFrom") String partyIdFrom, @RequestParam(value="invoiceTypeId") String invoiceTypeId, @RequestParam(value="partyId") String partyId, @RequestParam(value="roleTypeId", required=false) String roleTypeId, @RequestParam(value="recurrenceInfoId", required=false) String recurrenceInfoId, @RequestParam(value="dueDate", required=false) Timestamp dueDate, @RequestParam(value="description", required=false) String description, @RequestParam(value="billingAccountId", required=false) String billingAccountId, @RequestParam(value="invoiceDate", required=false) Timestamp invoiceDate, @RequestParam(value="contactMechId", required=false) String contactMechId, @RequestParam(value="currencyUomId", required=false) String currencyUomId, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="paidDate", required=false) Timestamp paidDate, @RequestParam(value="referenceNumber", required=false) String referenceNumber, @RequestParam(value="invoiceId", required=false) String invoiceId, @RequestParam(value="invoiceMessage", required=false) String invoiceMessage) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("partyIdFrom",partyIdFrom);
		paramMap.put("invoiceTypeId",invoiceTypeId);
		paramMap.put("partyId",partyId);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("recurrenceInfoId",recurrenceInfoId);
		paramMap.put("dueDate",dueDate);
		paramMap.put("description",description);
		paramMap.put("billingAccountId",billingAccountId);
		paramMap.put("invoiceDate",invoiceDate);
		paramMap.put("contactMechId",contactMechId);
		paramMap.put("currencyUomId",currencyUomId);
		paramMap.put("statusId",statusId);
		paramMap.put("paidDate",paidDate);
		paramMap.put("referenceNumber",referenceNumber);
		paramMap.put("invoiceId",invoiceId);
		paramMap.put("invoiceMessage",invoiceMessage);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createInvoice", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createInvoiceForOrderAllItems")
	public ResponseEntity<Object> createInvoiceForOrderAllItems(HttpSession session, @RequestParam(value="orderId") String orderId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderId",orderId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createInvoiceForOrderAllItems", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createInvoicesFromShipments")
	public ResponseEntity<Object> createInvoicesFromShipments(HttpSession session, @RequestParam(value="shipmentIds") List shipmentIds, @RequestParam(value="createSalesInvoicesForDropShipments", required=false) Boolean createSalesInvoicesForDropShipments, @RequestParam(value="eventDate", required=false) Timestamp eventDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shipmentIds",shipmentIds);
		paramMap.put("createSalesInvoicesForDropShipments",createSalesInvoicesForDropShipments);
		paramMap.put("eventDate",eventDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createInvoicesFromShipments", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createInvoicesFromReturnShipment")
	public ResponseEntity<Object> createInvoicesFromReturnShipment(HttpSession session, @RequestParam(value="shipmentId") String shipmentId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shipmentId",shipmentId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createInvoicesFromReturnShipment", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateInvoiceItemAssoc")
	public ResponseEntity<Object> updateInvoiceItemAssoc(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="invoiceItemSeqIdFrom") String invoiceItemSeqIdFrom, @RequestParam(value="invoiceIdFrom") String invoiceIdFrom, @RequestParam(value="invoiceItemSeqIdTo") String invoiceItemSeqIdTo, @RequestParam(value="invoiceItemAssocTypeId") String invoiceItemAssocTypeId, @RequestParam(value="invoiceIdTo") String invoiceIdTo, @RequestParam(value="partyIdFrom", required=false) String partyIdFrom, @RequestParam(value="amount", required=false) BigDecimal amount, @RequestParam(value="quantity", required=false) BigDecimal quantity, @RequestParam(value="partyIdTo", required=false) String partyIdTo, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("invoiceItemSeqIdFrom",invoiceItemSeqIdFrom);
		paramMap.put("invoiceIdFrom",invoiceIdFrom);
		paramMap.put("invoiceItemSeqIdTo",invoiceItemSeqIdTo);
		paramMap.put("invoiceItemAssocTypeId",invoiceItemAssocTypeId);
		paramMap.put("invoiceIdTo",invoiceIdTo);
		paramMap.put("partyIdFrom",partyIdFrom);
		paramMap.put("amount",amount);
		paramMap.put("quantity",quantity);
		paramMap.put("partyIdTo",partyIdTo);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateInvoiceItemAssoc", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/resetOrderItemBillingAndOrderAdjustmentBillingOnCancelInvoice")
	public ResponseEntity<Object> resetOrderItemBillingAndOrderAdjustmentBillingOnCancelInvoice(HttpSession session, @RequestParam(value="invoiceId") String invoiceId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("invoiceId",invoiceId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("resetOrderItemBillingAndOrderAdjustmentBillingOnCancelInvoice", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createInvoiceItemTypeMap")
	public ResponseEntity<Object> createInvoiceItemTypeMap(HttpSession session, @RequestParam(value="invoiceItemMapKey") String invoiceItemMapKey, @RequestParam(value="invoiceTypeId") String invoiceTypeId, @RequestParam(value="invoiceItemTypeId", required=false) String invoiceItemTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("invoiceItemMapKey",invoiceItemMapKey);
		paramMap.put("invoiceTypeId",invoiceTypeId);
		paramMap.put("invoiceItemTypeId",invoiceItemTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createInvoiceItemTypeMap", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/getNextInvoiceId")
	public ResponseEntity<Object> getNextInvoiceId(HttpSession session, @RequestParam(value="partyIdFrom", required=false) String partyIdFrom, @RequestParam(value="roleTypeId", required=false) String roleTypeId, @RequestParam(value="recurrenceInfoId", required=false) String recurrenceInfoId, @RequestParam(value="invoiceTypeId", required=false) String invoiceTypeId, @RequestParam(value="dueDate", required=false) Timestamp dueDate, @RequestParam(value="description", required=false) String description, @RequestParam(value="billingAccountId", required=false) String billingAccountId, @RequestParam(value="invoiceDate", required=false) Timestamp invoiceDate, @RequestParam(value="contactMechId", required=false) String contactMechId, @RequestParam(value="currencyUomId", required=false) String currencyUomId, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="paidDate", required=false) Timestamp paidDate, @RequestParam(value="referenceNumber", required=false) String referenceNumber, @RequestParam(value="invoiceId", required=false) String invoiceId, @RequestParam(value="invoiceMessage", required=false) String invoiceMessage, @RequestParam(value="partyId", required=false) String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("partyIdFrom",partyIdFrom);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("recurrenceInfoId",recurrenceInfoId);
		paramMap.put("invoiceTypeId",invoiceTypeId);
		paramMap.put("dueDate",dueDate);
		paramMap.put("description",description);
		paramMap.put("billingAccountId",billingAccountId);
		paramMap.put("invoiceDate",invoiceDate);
		paramMap.put("contactMechId",contactMechId);
		paramMap.put("currencyUomId",currencyUomId);
		paramMap.put("statusId",statusId);
		paramMap.put("paidDate",paidDate);
		paramMap.put("referenceNumber",referenceNumber);
		paramMap.put("invoiceId",invoiceId);
		paramMap.put("invoiceMessage",invoiceMessage);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getNextInvoiceId", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createInvoiceTermAttribute")
	public ResponseEntity<Object> createInvoiceTermAttribute(HttpSession session, @RequestParam(value="invoiceTermId") String invoiceTermId, @RequestParam(value="attrName") String attrName, @RequestParam(value="attrDescription", required=false) String attrDescription, @RequestParam(value="attrValue", required=false) Long attrValue) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("invoiceTermId",invoiceTermId);
		paramMap.put("attrName",attrName);
		paramMap.put("attrDescription",attrDescription);
		paramMap.put("attrValue",attrValue);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createInvoiceTermAttribute", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/invoiceSequenceEnforced")
	public ResponseEntity<Object> invoiceSequenceEnforced(HttpSession session, @RequestParam(value="partyAcctgPreference") org.apache.ofbiz.entity.GenericValue partyAcctgPreference, @RequestParam(value="partyIdFrom", required=false) String partyIdFrom, @RequestParam(value="roleTypeId", required=false) String roleTypeId, @RequestParam(value="recurrenceInfoId", required=false) String recurrenceInfoId, @RequestParam(value="invoiceTypeId", required=false) String invoiceTypeId, @RequestParam(value="dueDate", required=false) Timestamp dueDate, @RequestParam(value="description", required=false) String description, @RequestParam(value="billingAccountId", required=false) String billingAccountId, @RequestParam(value="invoiceDate", required=false) Timestamp invoiceDate, @RequestParam(value="contactMechId", required=false) String contactMechId, @RequestParam(value="currencyUomId", required=false) String currencyUomId, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="paidDate", required=false) Timestamp paidDate, @RequestParam(value="referenceNumber", required=false) String referenceNumber, @RequestParam(value="invoiceMessage", required=false) String invoiceMessage, @RequestParam(value="partyId", required=false) String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("partyAcctgPreference",partyAcctgPreference);
		paramMap.put("partyIdFrom",partyIdFrom);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("recurrenceInfoId",recurrenceInfoId);
		paramMap.put("invoiceTypeId",invoiceTypeId);
		paramMap.put("dueDate",dueDate);
		paramMap.put("description",description);
		paramMap.put("billingAccountId",billingAccountId);
		paramMap.put("invoiceDate",invoiceDate);
		paramMap.put("contactMechId",contactMechId);
		paramMap.put("currencyUomId",currencyUomId);
		paramMap.put("statusId",statusId);
		paramMap.put("paidDate",paidDate);
		paramMap.put("referenceNumber",referenceNumber);
		paramMap.put("invoiceMessage",invoiceMessage);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("invoiceSequenceEnforced", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createCommissionInvoices")
	public ResponseEntity<Object> createCommissionInvoices(HttpSession session, @RequestParam(value="invoiceIds") List invoiceIds, @RequestParam(value="partyIds", required=false) List partyIds) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("invoiceIds",invoiceIds);
		paramMap.put("partyIds",partyIds);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createCommissionInvoices", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createInvoiceTypeAttr")
	public ResponseEntity<Object> createInvoiceTypeAttr(HttpSession session, @RequestParam(value="invoiceTypeId") String invoiceTypeId, @RequestParam(value="attrName") String attrName, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("invoiceTypeId",invoiceTypeId);
		paramMap.put("attrName",attrName);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createInvoiceTypeAttr", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createInvoiceFromReturn")
	public ResponseEntity<Object> createInvoiceFromReturn(HttpSession session, @RequestParam(value="returnId") String returnId, @RequestParam(value="billItems", required=false) List billItems) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("returnId",returnId);
		paramMap.put("billItems",billItems);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createInvoiceFromReturn", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/setInvoicesToReadyFromShipment")
	public ResponseEntity<Object> setInvoicesToReadyFromShipment(HttpSession session, @RequestParam(value="shipmentId") String shipmentId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shipmentId",shipmentId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("setInvoicesToReadyFromShipment", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/isInvoiceInForeignCurrency")
	public ResponseEntity<Object> isInvoiceInForeignCurrency(HttpSession session, @RequestParam(value="invoiceId") String invoiceId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("invoiceId",invoiceId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("isInvoiceInForeignCurrency", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createInvoiceAttribute")
	public ResponseEntity<Object> createInvoiceAttribute(HttpSession session, @RequestParam(value="invoiceId") String invoiceId, @RequestParam(value="attrName") String attrName, @RequestParam(value="attrDescription", required=false) String attrDescription, @RequestParam(value="attrValue", required=false) Long attrValue) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("invoiceId",invoiceId);
		paramMap.put("attrName",attrName);
		paramMap.put("attrDescription",attrDescription);
		paramMap.put("attrValue",attrValue);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createInvoiceAttribute", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteInvoiceItemAttribute")
	public ResponseEntity<Object> deleteInvoiceItemAttribute(HttpSession session, @RequestParam(value="invoiceId") String invoiceId, @RequestParam(value="invoiceItemSeqId") String invoiceItemSeqId, @RequestParam(value="attrName") String attrName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("invoiceId",invoiceId);
		paramMap.put("invoiceItemSeqId",invoiceItemSeqId);
		paramMap.put("attrName",attrName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteInvoiceItemAttribute", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/readyInvoices")
	public ResponseEntity<Object> readyInvoices(HttpSession session, @RequestParam(value="invoicesCreated") List invoicesCreated) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("invoicesCreated",invoicesCreated);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("readyInvoices", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateInvoiceItemAssocType")
	public ResponseEntity<Object> updateInvoiceItemAssocType(HttpSession session, @RequestParam(value="invoiceItemAssocTypeId") String invoiceItemAssocTypeId, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="hasTable", required=false) String hasTable, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("invoiceItemAssocTypeId",invoiceItemAssocTypeId);
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateInvoiceItemAssocType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createSimpleTextContentForInvoice")
	public ResponseEntity<Object> createSimpleTextContentForInvoice(HttpSession session, @RequestParam(value="invoiceContentTypeId") String invoiceContentTypeId, @RequestParam(value="invoiceId") String invoiceId, @RequestParam(value="text") String text, @RequestParam(value="ownerContentId", required=false) String ownerContentId, @RequestParam(value="contentId", required=false) String contentId, @RequestParam(value="contentTypeId", required=false) String contentTypeId, @RequestParam(value="description", required=false) String description, @RequestParam(value="mimeTypeId", required=false) String mimeTypeId, @RequestParam(value="childLeafCount", required=false) Long childLeafCount, @RequestParam(value="privilegeEnumId", required=false) String privilegeEnumId, @RequestParam(value="dataSourceId", required=false) String dataSourceId, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="dataResourceId", required=false) String dataResourceId, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin, @RequestParam(value="templateDataResourceId", required=false) String templateDataResourceId, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="instanceOfContentId", required=false) String instanceOfContentId, @RequestParam(value="serviceName", required=false) String serviceName, @RequestParam(value="customMethodId", required=false) String customMethodId, @RequestParam(value="characterSetId", required=false) String characterSetId, @RequestParam(value="thruDate", required=false) Timestamp thruDate, @RequestParam(value="contentName", required=false) Long contentName, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="localeString", required=false) String localeString, @RequestParam(value="decoratorContentId", required=false) String decoratorContentId, @RequestParam(value="childBranchCount", required=false) Long childBranchCount) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("invoiceContentTypeId",invoiceContentTypeId);
		paramMap.put("invoiceId",invoiceId);
		paramMap.put("text",text);
		paramMap.put("ownerContentId",ownerContentId);
		paramMap.put("contentId",contentId);
		paramMap.put("contentTypeId",contentTypeId);
		paramMap.put("description",description);
		paramMap.put("mimeTypeId",mimeTypeId);
		paramMap.put("childLeafCount",childLeafCount);
		paramMap.put("privilegeEnumId",privilegeEnumId);
		paramMap.put("dataSourceId",dataSourceId);
		paramMap.put("lastModifiedByUserLogin",lastModifiedByUserLogin);
		paramMap.put("dataResourceId",dataResourceId);
		paramMap.put("createdByUserLogin",createdByUserLogin);
		paramMap.put("templateDataResourceId",templateDataResourceId);
		paramMap.put("lastModifiedDate",lastModifiedDate);
		paramMap.put("instanceOfContentId",instanceOfContentId);
		paramMap.put("serviceName",serviceName);
		paramMap.put("customMethodId",customMethodId);
		paramMap.put("characterSetId",characterSetId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("contentName",contentName);
		paramMap.put("fromDate",fromDate);
		paramMap.put("createdDate",createdDate);
		paramMap.put("statusId",statusId);
		paramMap.put("localeString",localeString);
		paramMap.put("decoratorContentId",decoratorContentId);
		paramMap.put("childBranchCount",childBranchCount);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createSimpleTextContentForInvoice", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/removeInvoiceItemAssocOnCancelInvoice")
	public ResponseEntity<Object> removeInvoiceItemAssocOnCancelInvoice(HttpSession session, @RequestParam(value="invoiceId") String invoiceId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("invoiceId",invoiceId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeInvoiceItemAssocOnCancelInvoice", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateInvoiceContent")
	public ResponseEntity<Object> updateInvoiceContent(HttpSession session, @RequestParam(value="invoiceContentTypeId") String invoiceContentTypeId, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="contentId") String contentId, @RequestParam(value="invoiceId") String invoiceId, @RequestParam(value="ownerContentId", required=false) String ownerContentId, @RequestParam(value="templateDataResourceId", required=false) String templateDataResourceId, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="instanceOfContentId", required=false) String instanceOfContentId, @RequestParam(value="contentTypeId", required=false) String contentTypeId, @RequestParam(value="description", required=false) String description, @RequestParam(value="mimeTypeId", required=false) String mimeTypeId, @RequestParam(value="childLeafCount", required=false) Long childLeafCount, @RequestParam(value="serviceName", required=false) String serviceName, @RequestParam(value="customMethodId", required=false) String customMethodId, @RequestParam(value="characterSetId", required=false) String characterSetId, @RequestParam(value="thruDate", required=false) Timestamp thruDate, @RequestParam(value="contentName", required=false) Long contentName, @RequestParam(value="privilegeEnumId", required=false) String privilegeEnumId, @RequestParam(value="dataSourceId", required=false) String dataSourceId, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="localeString", required=false) String localeString, @RequestParam(value="dataResourceId", required=false) String dataResourceId, @RequestParam(value="decoratorContentId", required=false) String decoratorContentId, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin, @RequestParam(value="childBranchCount", required=false) Long childBranchCount) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("invoiceContentTypeId",invoiceContentTypeId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("contentId",contentId);
		paramMap.put("invoiceId",invoiceId);
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
			result = dispatcher.runSync("updateInvoiceContent", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/copyInvoice")
	public ResponseEntity<Object> copyInvoice(HttpSession session, @RequestParam(value="invoiceIdToCopyFrom") String invoiceIdToCopyFrom, @RequestParam(value="invoiceId", required=false) String invoiceId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("invoiceIdToCopyFrom",invoiceIdToCopyFrom);
		paramMap.put("invoiceId",invoiceId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("copyInvoice", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateInvoiceType")
	public ResponseEntity<Object> updateInvoiceType(HttpSession session, @RequestParam(value="invoiceTypeId") String invoiceTypeId, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="hasTable", required=false) String hasTable, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("invoiceTypeId",invoiceTypeId);
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateInvoiceType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createInvoiceStatus")
	public ResponseEntity<Object> createInvoiceStatus(HttpSession session, @RequestParam(value="statusId") String statusId, @RequestParam(value="invoiceId") String invoiceId, @RequestParam(value="statusDate", required=false) Timestamp statusDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("statusId",statusId);
		paramMap.put("invoiceId",invoiceId);
		paramMap.put("statusDate",statusDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createInvoiceStatus", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/getInvoice")
	public ResponseEntity<Object> getInvoice(HttpSession session, @RequestParam(value="invoiceId") String invoiceId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("invoiceId",invoiceId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getInvoice", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/sampleInvoiceAffiliateCommission")
	public ResponseEntity<Object> sampleInvoiceAffiliateCommission(HttpSession session, @RequestParam(value="paymentId") String paymentId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("paymentId",paymentId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("sampleInvoiceAffiliateCommission", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createInvoiceItemAttribute")
	public ResponseEntity<Object> createInvoiceItemAttribute(HttpSession session, @RequestParam(value="invoiceId") String invoiceId, @RequestParam(value="invoiceItemSeqId") String invoiceItemSeqId, @RequestParam(value="attrName") String attrName, @RequestParam(value="attrDescription", required=false) String attrDescription, @RequestParam(value="attrValue", required=false) Long attrValue) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("invoiceId",invoiceId);
		paramMap.put("invoiceItemSeqId",invoiceItemSeqId);
		paramMap.put("attrName",attrName);
		paramMap.put("attrDescription",attrDescription);
		paramMap.put("attrValue",attrValue);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createInvoiceItemAttribute", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/removeInvoiceRole")
	public ResponseEntity<Object> removeInvoiceRole(HttpSession session, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="invoiceId") String invoiceId, @RequestParam(value="partyId") String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("invoiceId",invoiceId);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeInvoiceRole", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createInvoiceContent")
	public ResponseEntity<Object> createInvoiceContent(HttpSession session, @RequestParam(value="invoiceContentTypeId") String invoiceContentTypeId, @RequestParam(value="invoiceId") String invoiceId, @RequestParam(value="ownerContentId", required=false) String ownerContentId, @RequestParam(value="contentId", required=false) String contentId, @RequestParam(value="contentTypeId", required=false) String contentTypeId, @RequestParam(value="description", required=false) String description, @RequestParam(value="mimeTypeId", required=false) String mimeTypeId, @RequestParam(value="childLeafCount", required=false) Long childLeafCount, @RequestParam(value="privilegeEnumId", required=false) String privilegeEnumId, @RequestParam(value="dataSourceId", required=false) String dataSourceId, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="dataResourceId", required=false) String dataResourceId, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin, @RequestParam(value="templateDataResourceId", required=false) String templateDataResourceId, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="instanceOfContentId", required=false) String instanceOfContentId, @RequestParam(value="serviceName", required=false) String serviceName, @RequestParam(value="customMethodId", required=false) String customMethodId, @RequestParam(value="characterSetId", required=false) String characterSetId, @RequestParam(value="thruDate", required=false) Timestamp thruDate, @RequestParam(value="contentName", required=false) Long contentName, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="localeString", required=false) String localeString, @RequestParam(value="decoratorContentId", required=false) String decoratorContentId, @RequestParam(value="childBranchCount", required=false) Long childBranchCount) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("invoiceContentTypeId",invoiceContentTypeId);
		paramMap.put("invoiceId",invoiceId);
		paramMap.put("ownerContentId",ownerContentId);
		paramMap.put("contentId",contentId);
		paramMap.put("contentTypeId",contentTypeId);
		paramMap.put("description",description);
		paramMap.put("mimeTypeId",mimeTypeId);
		paramMap.put("childLeafCount",childLeafCount);
		paramMap.put("privilegeEnumId",privilegeEnumId);
		paramMap.put("dataSourceId",dataSourceId);
		paramMap.put("lastModifiedByUserLogin",lastModifiedByUserLogin);
		paramMap.put("dataResourceId",dataResourceId);
		paramMap.put("createdByUserLogin",createdByUserLogin);
		paramMap.put("templateDataResourceId",templateDataResourceId);
		paramMap.put("lastModifiedDate",lastModifiedDate);
		paramMap.put("instanceOfContentId",instanceOfContentId);
		paramMap.put("serviceName",serviceName);
		paramMap.put("customMethodId",customMethodId);
		paramMap.put("characterSetId",characterSetId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("contentName",contentName);
		paramMap.put("fromDate",fromDate);
		paramMap.put("createdDate",createdDate);
		paramMap.put("statusId",statusId);
		paramMap.put("localeString",localeString);
		paramMap.put("decoratorContentId",decoratorContentId);
		paramMap.put("childBranchCount",childBranchCount);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createInvoiceContent", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createInvoiceType")
	public ResponseEntity<Object> createInvoiceType(HttpSession session, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="hasTable", required=false) String hasTable, @RequestParam(value="invoiceTypeId", required=false) String invoiceTypeId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("invoiceTypeId",invoiceTypeId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createInvoiceType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateInvoiceItemAttribute")
	public ResponseEntity<Object> updateInvoiceItemAttribute(HttpSession session, @RequestParam(value="invoiceId") String invoiceId, @RequestParam(value="invoiceItemSeqId") String invoiceItemSeqId, @RequestParam(value="attrName") String attrName, @RequestParam(value="attrDescription", required=false) String attrDescription, @RequestParam(value="attrValue", required=false) Long attrValue) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("invoiceId",invoiceId);
		paramMap.put("invoiceItemSeqId",invoiceItemSeqId);
		paramMap.put("attrName",attrName);
		paramMap.put("attrDescription",attrDescription);
		paramMap.put("attrValue",attrValue);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateInvoiceItemAttribute", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteInvoiceTermAttribute")
	public ResponseEntity<Object> deleteInvoiceTermAttribute(HttpSession session, @RequestParam(value="invoiceTermId") String invoiceTermId, @RequestParam(value="attrName") String attrName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("invoiceTermId",invoiceTermId);
		paramMap.put("attrName",attrName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteInvoiceTermAttribute", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/getInvoiceRunningTotal")
	public ResponseEntity<Object> getInvoiceRunningTotal(HttpSession session, @RequestParam(value="invoiceIds") List invoiceIds, @RequestParam(value="organizationPartyId", required=false) String organizationPartyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("invoiceIds",invoiceIds);
		paramMap.put("organizationPartyId",organizationPartyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getInvoiceRunningTotal", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteInvoiceItemAssocType")
	public ResponseEntity<Object> deleteInvoiceItemAssocType(HttpSession session, @RequestParam(value="invoiceItemAssocTypeId") String invoiceItemAssocTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("invoiceItemAssocTypeId",invoiceItemAssocTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteInvoiceItemAssocType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/addtax")
	public ResponseEntity<Object> addtax(HttpSession session, @RequestParam(value="invoiceId") String invoiceId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("invoiceId",invoiceId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("addtax", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createInvoiceItemTypeAttr")
	public ResponseEntity<Object> createInvoiceItemTypeAttr(HttpSession session, @RequestParam(value="invoiceItemTypeId") String invoiceItemTypeId, @RequestParam(value="attrName") String attrName, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("invoiceItemTypeId",invoiceItemTypeId);
		paramMap.put("attrName",attrName);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createInvoiceItemTypeAttr", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createInvoiceFromOrder")
	public ResponseEntity<Object> createInvoiceFromOrder(HttpSession session, @RequestParam(value="orderId") String orderId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderId",orderId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createInvoiceFromOrder", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteInvoiceContactMech")
	public ResponseEntity<Object> deleteInvoiceContactMech(HttpSession session, @RequestParam(value="contactMechPurposeTypeId") String contactMechPurposeTypeId, @RequestParam(value="invoiceId") String invoiceId, @RequestParam(value="contactMechId") String contactMechId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contactMechPurposeTypeId",contactMechPurposeTypeId);
		paramMap.put("invoiceId",invoiceId);
		paramMap.put("contactMechId",contactMechId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteInvoiceContactMech", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createInvoiceContentType")
	public ResponseEntity<Object> createInvoiceContentType(HttpSession session, @RequestParam(value="invoiceContentTypeId", required=false) String invoiceContentTypeId, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="hasTable", required=false) String hasTable, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("invoiceContentTypeId",invoiceContentTypeId);
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createInvoiceContentType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteInvoiceTypeAttr")
	public ResponseEntity<Object> deleteInvoiceTypeAttr(HttpSession session, @RequestParam(value="invoiceTypeId") String invoiceTypeId, @RequestParam(value="attrName") String attrName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("invoiceTypeId",invoiceTypeId);
		paramMap.put("attrName",attrName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteInvoiceTypeAttr", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateInvoiceItemTypeAttr")
	public ResponseEntity<Object> updateInvoiceItemTypeAttr(HttpSession session, @RequestParam(value="invoiceItemTypeId") String invoiceItemTypeId, @RequestParam(value="attrName") String attrName, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("invoiceItemTypeId",invoiceItemTypeId);
		paramMap.put("attrName",attrName);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateInvoiceItemTypeAttr", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateInvoiceTermAttribute")
	public ResponseEntity<Object> updateInvoiceTermAttribute(HttpSession session, @RequestParam(value="invoiceTermId") String invoiceTermId, @RequestParam(value="attrName") String attrName, @RequestParam(value="attrDescription", required=false) String attrDescription, @RequestParam(value="attrValue", required=false) Long attrValue) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("invoiceTermId",invoiceTermId);
		paramMap.put("attrName",attrName);
		paramMap.put("attrDescription",attrDescription);
		paramMap.put("attrValue",attrValue);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateInvoiceTermAttribute", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateSimpleTextContentForInvoice")
	public ResponseEntity<Object> updateSimpleTextContentForInvoice(HttpSession session, @RequestParam(value="invoiceContentTypeId") String invoiceContentTypeId, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="contentId") String contentId, @RequestParam(value="invoiceId") String invoiceId, @RequestParam(value="ownerContentId", required=false) String ownerContentId, @RequestParam(value="contentTypeId", required=false) String contentTypeId, @RequestParam(value="description", required=false) String description, @RequestParam(value="mimeTypeId", required=false) String mimeTypeId, @RequestParam(value="childLeafCount", required=false) Long childLeafCount, @RequestParam(value="privilegeEnumId", required=false) String privilegeEnumId, @RequestParam(value="dataSourceId", required=false) String dataSourceId, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="dataResourceId", required=false) String dataResourceId, @RequestParam(value="text", required=false) String text, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin, @RequestParam(value="textDataResourceId", required=false) String textDataResourceId, @RequestParam(value="templateDataResourceId", required=false) String templateDataResourceId, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="instanceOfContentId", required=false) String instanceOfContentId, @RequestParam(value="serviceName", required=false) String serviceName, @RequestParam(value="customMethodId", required=false) String customMethodId, @RequestParam(value="characterSetId", required=false) String characterSetId, @RequestParam(value="thruDate", required=false) Timestamp thruDate, @RequestParam(value="contentName", required=false) Long contentName, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="localeString", required=false) String localeString, @RequestParam(value="decoratorContentId", required=false) String decoratorContentId, @RequestParam(value="childBranchCount", required=false) Long childBranchCount) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("invoiceContentTypeId",invoiceContentTypeId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("contentId",contentId);
		paramMap.put("invoiceId",invoiceId);
		paramMap.put("ownerContentId",ownerContentId);
		paramMap.put("contentTypeId",contentTypeId);
		paramMap.put("description",description);
		paramMap.put("mimeTypeId",mimeTypeId);
		paramMap.put("childLeafCount",childLeafCount);
		paramMap.put("privilegeEnumId",privilegeEnumId);
		paramMap.put("dataSourceId",dataSourceId);
		paramMap.put("lastModifiedByUserLogin",lastModifiedByUserLogin);
		paramMap.put("dataResourceId",dataResourceId);
		paramMap.put("text",text);
		paramMap.put("createdByUserLogin",createdByUserLogin);
		paramMap.put("textDataResourceId",textDataResourceId);
		paramMap.put("templateDataResourceId",templateDataResourceId);
		paramMap.put("lastModifiedDate",lastModifiedDate);
		paramMap.put("instanceOfContentId",instanceOfContentId);
		paramMap.put("serviceName",serviceName);
		paramMap.put("customMethodId",customMethodId);
		paramMap.put("characterSetId",characterSetId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("contentName",contentName);
		paramMap.put("createdDate",createdDate);
		paramMap.put("statusId",statusId);
		paramMap.put("localeString",localeString);
		paramMap.put("decoratorContentId",decoratorContentId);
		paramMap.put("childBranchCount",childBranchCount);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateSimpleTextContentForInvoice", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/cancelInvoice")
	public ResponseEntity<Object> cancelInvoice(HttpSession session, @RequestParam(value="invoiceId") String invoiceId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("invoiceId",invoiceId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("cancelInvoice", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteInvoiceNote")
	public ResponseEntity<Object> deleteInvoiceNote(HttpSession session, @RequestParam(value="invoiceId") String invoiceId, @RequestParam(value="noteId") String noteId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("invoiceId",invoiceId);
		paramMap.put("noteId",noteId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteInvoiceNote", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/sendInvoicePerEmail")
	public ResponseEntity<Object> sendInvoicePerEmail(HttpSession session, @RequestParam(value="sendTo") String sendTo, @RequestParam(value="sendFrom") String sendFrom, @RequestParam(value="invoiceId") String invoiceId, @RequestParam(value="other", required=false) String other, @RequestParam(value="bodyText", required=false) String bodyText, @RequestParam(value="subject", required=false) String subject, @RequestParam(value="sendCc", required=false) String sendCc) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("sendTo",sendTo);
		paramMap.put("sendFrom",sendFrom);
		paramMap.put("invoiceId",invoiceId);
		paramMap.put("other",other);
		paramMap.put("bodyText",bodyText);
		paramMap.put("subject",subject);
		paramMap.put("sendCc",sendCc);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("sendInvoicePerEmail", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/copyInvoiceToTemplate")
	public ResponseEntity<Object> copyInvoiceToTemplate(HttpSession session, @RequestParam(value="invoiceTypeId") String invoiceTypeId, @RequestParam(value="invoiceId") String invoiceId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("invoiceTypeId",invoiceTypeId);
		paramMap.put("invoiceId",invoiceId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("copyInvoiceToTemplate", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteInvoiceItemAssoc")
	public ResponseEntity<Object> deleteInvoiceItemAssoc(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="invoiceItemSeqIdFrom") String invoiceItemSeqIdFrom, @RequestParam(value="invoiceIdFrom") String invoiceIdFrom, @RequestParam(value="invoiceItemSeqIdTo") String invoiceItemSeqIdTo, @RequestParam(value="invoiceItemAssocTypeId") String invoiceItemAssocTypeId, @RequestParam(value="invoiceIdTo") String invoiceIdTo) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("invoiceItemSeqIdFrom",invoiceItemSeqIdFrom);
		paramMap.put("invoiceIdFrom",invoiceIdFrom);
		paramMap.put("invoiceItemSeqIdTo",invoiceItemSeqIdTo);
		paramMap.put("invoiceItemAssocTypeId",invoiceItemAssocTypeId);
		paramMap.put("invoiceIdTo",invoiceIdTo);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteInvoiceItemAssoc", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/getInvoicesFilterByAssocType")
	public ResponseEntity<Object> getInvoicesFilterByAssocType(HttpSession session, @RequestParam(value="invoiceList") List invoiceList, @RequestParam(value="invoiceItemAssocTypeId") String invoiceItemAssocTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("invoiceList",invoiceList);
		paramMap.put("invoiceItemAssocTypeId",invoiceItemAssocTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getInvoicesFilterByAssocType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createSalesInvoicesFromDropShipment")
	public ResponseEntity<Object> createSalesInvoicesFromDropShipment(HttpSession session, @RequestParam(value="shipmentId") String shipmentId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shipmentId",shipmentId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createSalesInvoicesFromDropShipment", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteInvoiceTerm")
	public ResponseEntity<Object> deleteInvoiceTerm(HttpSession session, @RequestParam(value="invoiceTermId") String invoiceTermId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("invoiceTermId",invoiceTermId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteInvoiceTerm", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/removeInvoiceItem")
	public ResponseEntity<Object> removeInvoiceItem(HttpSession session, @RequestParam(value="invoiceId") String invoiceId, @RequestParam(value="invoiceItemSeqId") String invoiceItemSeqId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("invoiceId",invoiceId);
		paramMap.put("invoiceItemSeqId",invoiceItemSeqId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeInvoiceItem", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createInvoiceItem")
	public ResponseEntity<Object> createInvoiceItem(HttpSession session, @RequestParam(value="invoiceId") String invoiceId, @RequestParam(value="amount", required=false) BigDecimal amount, @RequestParam(value="quantity", required=false) BigDecimal quantity, @RequestParam(value="productId", required=false) String productId, @RequestParam(value="overrideOrgPartyId", required=false) String overrideOrgPartyId, @RequestParam(value="productFeatureId", required=false) String productFeatureId, @RequestParam(value="taxableFlag", required=false) String taxableFlag, @RequestParam(value="taxAuthorityRateSeqId", required=false) String taxAuthorityRateSeqId, @RequestParam(value="overrideGlAccountId", required=false) String overrideGlAccountId, @RequestParam(value="description", required=false) String description, @RequestParam(value="invoiceItemSeqId", required=false) String invoiceItemSeqId, @RequestParam(value="uomId", required=false) String uomId, @RequestParam(value="invoiceItemTypeId", required=false) String invoiceItemTypeId, @RequestParam(value="inventoryItemId", required=false) String inventoryItemId, @RequestParam(value="taxAuthPartyId", required=false) String taxAuthPartyId, @RequestParam(value="parentInvoiceId", required=false) String parentInvoiceId, @RequestParam(value="parentInvoiceItemSeqId", required=false) String parentInvoiceItemSeqId, @RequestParam(value="taxAuthGeoId", required=false) String taxAuthGeoId, @RequestParam(value="salesOpportunityId", required=false) String salesOpportunityId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("invoiceId",invoiceId);
		paramMap.put("amount",amount);
		paramMap.put("quantity",quantity);
		paramMap.put("productId",productId);
		paramMap.put("overrideOrgPartyId",overrideOrgPartyId);
		paramMap.put("productFeatureId",productFeatureId);
		paramMap.put("taxableFlag",taxableFlag);
		paramMap.put("taxAuthorityRateSeqId",taxAuthorityRateSeqId);
		paramMap.put("overrideGlAccountId",overrideGlAccountId);
		paramMap.put("description",description);
		paramMap.put("invoiceItemSeqId",invoiceItemSeqId);
		paramMap.put("uomId",uomId);
		paramMap.put("invoiceItemTypeId",invoiceItemTypeId);
		paramMap.put("inventoryItemId",inventoryItemId);
		paramMap.put("taxAuthPartyId",taxAuthPartyId);
		paramMap.put("parentInvoiceId",parentInvoiceId);
		paramMap.put("parentInvoiceItemSeqId",parentInvoiceItemSeqId);
		paramMap.put("taxAuthGeoId",taxAuthGeoId);
		paramMap.put("salesOpportunityId",salesOpportunityId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createInvoiceItem", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateInvoiceItem")
	public ResponseEntity<Object> updateInvoiceItem(HttpSession session, @RequestParam(value="invoiceId") String invoiceId, @RequestParam(value="invoiceItemSeqId") String invoiceItemSeqId, @RequestParam(value="amount", required=false) BigDecimal amount, @RequestParam(value="quantity", required=false) BigDecimal quantity, @RequestParam(value="productId", required=false) String productId, @RequestParam(value="overrideOrgPartyId", required=false) String overrideOrgPartyId, @RequestParam(value="productFeatureId", required=false) String productFeatureId, @RequestParam(value="taxableFlag", required=false) String taxableFlag, @RequestParam(value="taxAuthorityRateSeqId", required=false) String taxAuthorityRateSeqId, @RequestParam(value="overrideGlAccountId", required=false) String overrideGlAccountId, @RequestParam(value="description", required=false) String description, @RequestParam(value="uomId", required=false) String uomId, @RequestParam(value="invoiceItemTypeId", required=false) String invoiceItemTypeId, @RequestParam(value="inventoryItemId", required=false) String inventoryItemId, @RequestParam(value="taxAuthPartyId", required=false) String taxAuthPartyId, @RequestParam(value="parentInvoiceId", required=false) String parentInvoiceId, @RequestParam(value="parentInvoiceItemSeqId", required=false) String parentInvoiceItemSeqId, @RequestParam(value="taxAuthGeoId", required=false) String taxAuthGeoId, @RequestParam(value="salesOpportunityId", required=false) String salesOpportunityId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("invoiceId",invoiceId);
		paramMap.put("invoiceItemSeqId",invoiceItemSeqId);
		paramMap.put("amount",amount);
		paramMap.put("quantity",quantity);
		paramMap.put("productId",productId);
		paramMap.put("overrideOrgPartyId",overrideOrgPartyId);
		paramMap.put("productFeatureId",productFeatureId);
		paramMap.put("taxableFlag",taxableFlag);
		paramMap.put("taxAuthorityRateSeqId",taxAuthorityRateSeqId);
		paramMap.put("overrideGlAccountId",overrideGlAccountId);
		paramMap.put("description",description);
		paramMap.put("uomId",uomId);
		paramMap.put("invoiceItemTypeId",invoiceItemTypeId);
		paramMap.put("inventoryItemId",inventoryItemId);
		paramMap.put("taxAuthPartyId",taxAuthPartyId);
		paramMap.put("parentInvoiceId",parentInvoiceId);
		paramMap.put("parentInvoiceItemSeqId",parentInvoiceItemSeqId);
		paramMap.put("taxAuthGeoId",taxAuthGeoId);
		paramMap.put("salesOpportunityId",salesOpportunityId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateInvoiceItem", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateInvoiceItemTypeMap")
	public ResponseEntity<Object> updateInvoiceItemTypeMap(HttpSession session, @RequestParam(value="invoiceItemMapKey") String invoiceItemMapKey, @RequestParam(value="invoiceTypeId") String invoiceTypeId, @RequestParam(value="invoiceItemTypeId", required=false) String invoiceItemTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("invoiceItemMapKey",invoiceItemMapKey);
		paramMap.put("invoiceTypeId",invoiceTypeId);
		paramMap.put("invoiceItemTypeId",invoiceItemTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateInvoiceItemTypeMap", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/removeInvoiceContent")
	public ResponseEntity<Object> removeInvoiceContent(HttpSession session, @RequestParam(value="invoiceContentTypeId") String invoiceContentTypeId, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="contentId") String contentId, @RequestParam(value="invoiceId") String invoiceId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("invoiceContentTypeId",invoiceContentTypeId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("contentId",contentId);
		paramMap.put("invoiceId",invoiceId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeInvoiceContent", paramMap);
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
