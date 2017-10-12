package com.skytala.eCommerce.domain.accounting.relations.paymentGatewayPayPal.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayPayPal.event.PaymentGatewayPayPalUpdated;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayPayPal.model.PaymentGatewayPayPal;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdatePaymentGatewayPayPal extends Command {

private PaymentGatewayPayPal elementToBeUpdated;

public UpdatePaymentGatewayPayPal(PaymentGatewayPayPal elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public PaymentGatewayPayPal getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(PaymentGatewayPayPal elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("PaymentGatewayPayPal", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(PaymentGatewayPayPal.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(PaymentGatewayPayPal.class);
}
success = false;
}
Event resultingEvent = new PaymentGatewayPayPalUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
