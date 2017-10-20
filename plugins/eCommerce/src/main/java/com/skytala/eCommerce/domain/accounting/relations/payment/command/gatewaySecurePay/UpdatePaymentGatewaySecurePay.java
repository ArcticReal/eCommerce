package com.skytala.eCommerce.domain.accounting.relations.payment.command.gatewaySecurePay;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewaySecurePay.PaymentGatewaySecurePayUpdated;
import com.skytala.eCommerce.domain.accounting.relations.payment.model.gatewaySecurePay.PaymentGatewaySecurePay;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdatePaymentGatewaySecurePay extends Command {

private PaymentGatewaySecurePay elementToBeUpdated;

public UpdatePaymentGatewaySecurePay(PaymentGatewaySecurePay elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public PaymentGatewaySecurePay getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(PaymentGatewaySecurePay elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("PaymentGatewaySecurePay", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(PaymentGatewaySecurePay.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(PaymentGatewaySecurePay.class);
}
success = false;
}
Event resultingEvent = new PaymentGatewaySecurePayUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
