package com.skytala.eCommerce.domain.accounting.relations.paymentGatewayEway.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayEway.event.PaymentGatewayEwayUpdated;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayEway.model.PaymentGatewayEway;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdatePaymentGatewayEway extends Command {

private PaymentGatewayEway elementToBeUpdated;

public UpdatePaymentGatewayEway(PaymentGatewayEway elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public PaymentGatewayEway getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(PaymentGatewayEway elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("PaymentGatewayEway", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(PaymentGatewayEway.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(PaymentGatewayEway.class);
}
success = false;
}
Event resultingEvent = new PaymentGatewayEwayUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
