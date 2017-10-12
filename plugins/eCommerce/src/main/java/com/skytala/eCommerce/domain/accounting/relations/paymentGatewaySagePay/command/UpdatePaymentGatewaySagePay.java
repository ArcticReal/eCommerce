package com.skytala.eCommerce.domain.accounting.relations.paymentGatewaySagePay.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewaySagePay.event.PaymentGatewaySagePayUpdated;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewaySagePay.model.PaymentGatewaySagePay;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdatePaymentGatewaySagePay extends Command {

private PaymentGatewaySagePay elementToBeUpdated;

public UpdatePaymentGatewaySagePay(PaymentGatewaySagePay elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public PaymentGatewaySagePay getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(PaymentGatewaySagePay elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("PaymentGatewaySagePay", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(PaymentGatewaySagePay.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(PaymentGatewaySagePay.class);
}
success = false;
}
Event resultingEvent = new PaymentGatewaySagePayUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
