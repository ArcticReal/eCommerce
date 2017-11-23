package com.skytala.eCommerce.domain.party.relations.contactMech.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;

import com.skytala.eCommerce.domain.order.relations.orderHeader.model.OrderHeader;
import com.skytala.eCommerce.domain.party.model.Party;
import com.skytala.eCommerce.domain.party.relations.contactMech.mapper.ContactMechMapper;
import com.skytala.eCommerce.domain.party.relations.contactMech.util.Email;
import org.apache.ofbiz.base.util.Debug;
import org.apache.ofbiz.service.GenericServiceException;
import org.apache.ofbiz.service.LocalDispatcher;
import org.apache.ofbiz.service.ServiceAuthException;
import org.apache.ofbiz.service.ServiceValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;

public class ContactMech implements Serializable{

    private static final long serialVersionUID = 1L;
    private String contactMechId;
    private String contactMechTypeId;
    private String infoString;

    public String getContactMechId() {
        return contactMechId;
    }

    public void setContactMechId(String  contactMechId) {
        this.contactMechId = contactMechId;
    }

    public String getContactMechTypeId() {
        return contactMechTypeId;
    }

    public void setContactMechTypeId(String  contactMechTypeId) {
        this.contactMechTypeId = contactMechTypeId;
    }

    public String getInfoString() {
        return infoString;
    }

    public void setInfoString(String  infoString) {
        this.infoString = infoString;
    }


    public Map<String, Object> mapAttributeField() {
        return ContactMechMapper.map(this);
    }

    public Boolean sendEmail(HttpSession session, @NotNull Email email, Party party, OrderHeader orderHeader) throws Exception {

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("subject",email.getSubject());
        paramMap.put("sendFrom",email.getSendFrom());
        if(infoString!=null)
            paramMap.put("sendTo",infoString);
        else
            paramMap.put("sendTo",email.getSendTo());
        if(paramMap.get("sendTo")==null)
            throw new Exception("sendTo may not be null");
        paramMap.put("sendBcc",email.getSendBcc());
        paramMap.put("sendCc",email.getSendCc());
        paramMap.put("contentType",email.getContentType());
        paramMap.put("body",email.getBody());
        paramMap.put("port",email.getPort());
        if(orderHeader != null)
            paramMap.put("orderId", orderHeader.getOrderId());
        if(party != null)
            paramMap.put("partyId", party.getPartyId());
        //paramMap.put("communicationEventId", "9000");

        Map<String, Object> result = new HashMap<>();
        LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
        try {
            result = dispatcher.runSync("sendMail", paramMap);
        } catch (ServiceAuthException e) {

            e.printStackTrace();
            System.err.println("this should really not happen! And with really I mean reaaaaallllllyyyy");
            return false;

        } catch (ServiceValidationException e) {
            e.printStackTrace();
            return false;
        } catch (GenericServiceException e) {
            e.printStackTrace();
            return false;
        }
        if(result.get("responseMessage").equals("error")) {
            List<String> errors = (List)result.get("errorMessageList");
            if(errors!=null)
                for(int i = 0; i < errors.size(); i++){
                    Debug.log("Error: "+errors.get(i), Debug.ERROR);
                }
            return false;
        }

        return true;
    }

}
