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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/service/AccountingAdmin")
public class AccountingAdmin{

	@RequestMapping(method = RequestMethod.POST, value = "/removeGlAccountTypeDefault")
	public ResponseEntity<Object> removeGlAccountTypeDefault(HttpSession session, @RequestParam(value="glAccountTypeId") String glAccountTypeId, @RequestParam(value="organizationPartyId") String organizationPartyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("glAccountTypeId",glAccountTypeId);
		paramMap.put("organizationPartyId",organizationPartyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeGlAccountTypeDefault", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createPartyAcctgPreference")
	public ResponseEntity<Object> createPartyAcctgPreference(HttpSession session, @RequestParam(value="partyId") String partyId, @RequestParam(value="cogsMethodId", required=false) String cogsMethodId, @RequestParam(value="oldInvoiceSequenceEnumId", required=false) String oldInvoiceSequenceEnumId, @RequestParam(value="baseCurrencyUomId", required=false) String baseCurrencyUomId, @RequestParam(value="lastInvoiceRestartDate", required=false) Timestamp lastInvoiceRestartDate, @RequestParam(value="quoteSeqCustMethId", required=false) String quoteSeqCustMethId, @RequestParam(value="lastQuoteNumber", required=false) Long lastQuoteNumber, @RequestParam(value="errorGlJournalId", required=false) String errorGlJournalId, @RequestParam(value="orderIdPrefix", required=false) String orderIdPrefix, @RequestParam(value="fiscalYearStartDay", required=false) Long fiscalYearStartDay, @RequestParam(value="oldQuoteSequenceEnumId", required=false) String oldQuoteSequenceEnumId, @RequestParam(value="lastInvoiceNumber", required=false) Long lastInvoiceNumber, @RequestParam(value="taxFormId", required=false) String taxFormId, @RequestParam(value="refundPaymentMethodId", required=false) String refundPaymentMethodId, @RequestParam(value="useInvoiceIdForReturns", required=false) String useInvoiceIdForReturns, @RequestParam(value="invoiceIdPrefix", required=false) String invoiceIdPrefix, @RequestParam(value="lastOrderNumber", required=false) Long lastOrderNumber, @RequestParam(value="orderSeqCustMethId", required=false) String orderSeqCustMethId, @RequestParam(value="fiscalYearStartMonth", required=false) Long fiscalYearStartMonth, @RequestParam(value="quoteIdPrefix", required=false) String quoteIdPrefix, @RequestParam(value="oldOrderSequenceEnumId", required=false) String oldOrderSequenceEnumId, @RequestParam(value="invoiceSeqCustMethId", required=false) String invoiceSeqCustMethId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("partyId",partyId);
		paramMap.put("cogsMethodId",cogsMethodId);
		paramMap.put("oldInvoiceSequenceEnumId",oldInvoiceSequenceEnumId);
		paramMap.put("baseCurrencyUomId",baseCurrencyUomId);
		paramMap.put("lastInvoiceRestartDate",lastInvoiceRestartDate);
		paramMap.put("quoteSeqCustMethId",quoteSeqCustMethId);
		paramMap.put("lastQuoteNumber",lastQuoteNumber);
		paramMap.put("errorGlJournalId",errorGlJournalId);
		paramMap.put("orderIdPrefix",orderIdPrefix);
		paramMap.put("fiscalYearStartDay",fiscalYearStartDay);
		paramMap.put("oldQuoteSequenceEnumId",oldQuoteSequenceEnumId);
		paramMap.put("lastInvoiceNumber",lastInvoiceNumber);
		paramMap.put("taxFormId",taxFormId);
		paramMap.put("refundPaymentMethodId",refundPaymentMethodId);
		paramMap.put("useInvoiceIdForReturns",useInvoiceIdForReturns);
		paramMap.put("invoiceIdPrefix",invoiceIdPrefix);
		paramMap.put("lastOrderNumber",lastOrderNumber);
		paramMap.put("orderSeqCustMethId",orderSeqCustMethId);
		paramMap.put("fiscalYearStartMonth",fiscalYearStartMonth);
		paramMap.put("quoteIdPrefix",quoteIdPrefix);
		paramMap.put("oldOrderSequenceEnumId",oldOrderSequenceEnumId);
		paramMap.put("invoiceSeqCustMethId",invoiceSeqCustMethId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createPartyAcctgPreference", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateFXConversion")
	public ResponseEntity<Object> updateFXConversion(HttpSession session, @RequestParam(value="conversionFactor") BigDecimal conversionFactor, @RequestParam(value="uomId") String uomId, @RequestParam(value="uomIdTo") String uomIdTo, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="purposeEnumId", required=false) String purposeEnumId, @RequestParam(value="asOfTimestamp", required=false) Timestamp asOfTimestamp, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("conversionFactor",conversionFactor);
		paramMap.put("uomId",uomId);
		paramMap.put("uomIdTo",uomIdTo);
		paramMap.put("fromDate",fromDate);
		paramMap.put("purposeEnumId",purposeEnumId);
		paramMap.put("asOfTimestamp",asOfTimestamp);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateFXConversion", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/getPartyAccountingPreferences")
	public ResponseEntity<Object> getPartyAccountingPreferences(HttpSession session, @RequestParam(value="organizationPartyId") String organizationPartyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("organizationPartyId",organizationPartyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getPartyAccountingPreferences", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/addInvoiceItemTypeGlAssignment")
	public ResponseEntity<Object> addInvoiceItemTypeGlAssignment(HttpSession session, @RequestParam(value="glAccountId") String glAccountId, @RequestParam(value="organizationPartyId") String organizationPartyId, @RequestParam(value="invoiceItemTypeId") String invoiceItemTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("glAccountId",glAccountId);
		paramMap.put("organizationPartyId",organizationPartyId);
		paramMap.put("invoiceItemTypeId",invoiceItemTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("addInvoiceItemTypeGlAssignment", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/addPaymentTypeGlAssignment")
	public ResponseEntity<Object> addPaymentTypeGlAssignment(HttpSession session, @RequestParam(value="paymentTypeId") String paymentTypeId, @RequestParam(value="glAccountTypeId") String glAccountTypeId, @RequestParam(value="organizationPartyId") String organizationPartyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("paymentTypeId",paymentTypeId);
		paramMap.put("glAccountTypeId",glAccountTypeId);
		paramMap.put("organizationPartyId",organizationPartyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("addPaymentTypeGlAssignment", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/getFXConversion")
	public ResponseEntity<Object> getFXConversion(HttpSession session, @RequestParam(value="uomId") String uomId, @RequestParam(value="uomIdTo") String uomIdTo, @RequestParam(value="asOfTimestamp", required=false) Timestamp asOfTimestamp) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("uomId",uomId);
		paramMap.put("uomIdTo",uomIdTo);
		paramMap.put("asOfTimestamp",asOfTimestamp);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getFXConversion", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/removePaymentMethodTypeGlAssignment")
	public ResponseEntity<Object> removePaymentMethodTypeGlAssignment(HttpSession session, @RequestParam(value="organizationPartyId") String organizationPartyId, @RequestParam(value="paymentMethodTypeId") String paymentMethodTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("organizationPartyId",organizationPartyId);
		paramMap.put("paymentMethodTypeId",paymentMethodTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removePaymentMethodTypeGlAssignment", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createGlAccountTypeDefault")
	public ResponseEntity<Object> createGlAccountTypeDefault(HttpSession session, @RequestParam(value="glAccountTypeId") String glAccountTypeId, @RequestParam(value="glAccountId") String glAccountId, @RequestParam(value="organizationPartyId") String organizationPartyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("glAccountTypeId",glAccountTypeId);
		paramMap.put("glAccountId",glAccountId);
		paramMap.put("organizationPartyId",organizationPartyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createGlAccountTypeDefault", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/setAcctgCompany")
	public ResponseEntity<Object> setAcctgCompany(HttpSession session, @RequestParam(value="organizationPartyId", required=false) String organizationPartyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("organizationPartyId",organizationPartyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("setAcctgCompany", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/removeInvoiceItemTypeGlAssignment")
	public ResponseEntity<Object> removeInvoiceItemTypeGlAssignment(HttpSession session, @RequestParam(value="organizationPartyId") String organizationPartyId, @RequestParam(value="invoiceItemTypeId") String invoiceItemTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("organizationPartyId",organizationPartyId);
		paramMap.put("invoiceItemTypeId",invoiceItemTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeInvoiceItemTypeGlAssignment", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/addPaymentMethodTypeGlAssignment")
	public ResponseEntity<Object> addPaymentMethodTypeGlAssignment(HttpSession session, @RequestParam(value="glAccountId") String glAccountId, @RequestParam(value="organizationPartyId") String organizationPartyId, @RequestParam(value="paymentMethodTypeId") String paymentMethodTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("glAccountId",glAccountId);
		paramMap.put("organizationPartyId",organizationPartyId);
		paramMap.put("paymentMethodTypeId",paymentMethodTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("addPaymentMethodTypeGlAssignment", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updatePartyAcctgPreference")
	public ResponseEntity<Object> updatePartyAcctgPreference(HttpSession session, @RequestParam(value="partyId") String partyId, @RequestParam(value="errorGlJournalId", required=false) String errorGlJournalId, @RequestParam(value="refundPaymentMethodId", required=false) String refundPaymentMethodId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("partyId",partyId);
		paramMap.put("errorGlJournalId",errorGlJournalId);
		paramMap.put("refundPaymentMethodId",refundPaymentMethodId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updatePartyAcctgPreference", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/removePaymentTypeGlAssignment")
	public ResponseEntity<Object> removePaymentTypeGlAssignment(HttpSession session, @RequestParam(value="paymentTypeId") String paymentTypeId, @RequestParam(value="organizationPartyId") String organizationPartyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("paymentTypeId",paymentTypeId);
		paramMap.put("organizationPartyId",organizationPartyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removePaymentTypeGlAssignment", paramMap);
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
