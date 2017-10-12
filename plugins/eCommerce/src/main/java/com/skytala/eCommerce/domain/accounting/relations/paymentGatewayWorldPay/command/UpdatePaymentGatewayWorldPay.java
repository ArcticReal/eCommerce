package com.skytala.eCommerce.domain.accounting.relations.paymentGatewayWorldPay.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayWorldPay.event.PaymentGatewayWorldPayUpdated;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayWorldPay.model.PaymentGatewayWorldPay;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdatePaymentGatewayWorldPay extends Command {

private PaymentGatewayWorldPay elementToBeUpdated;

public UpdatePaymentGatewayWorldPay(PaymentGatewayWorldPay elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public PaymentGatewayWorldPay getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(PaymentGatewayWorldPay elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("PaymentGatewayWorldPay", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(PaymentGatewayWorldPay.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(PaymentGatewayWorldPay.class);
}
success = false;
}
Event resultingEvent = new PaymentGatewayWorldPayUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
