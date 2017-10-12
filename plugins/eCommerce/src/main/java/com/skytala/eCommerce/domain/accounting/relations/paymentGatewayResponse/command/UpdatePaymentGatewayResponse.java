package com.skytala.eCommerce.domain.accounting.relations.paymentGatewayResponse.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayResponse.event.PaymentGatewayResponseUpdated;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayResponse.model.PaymentGatewayResponse;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdatePaymentGatewayResponse extends Command {

private PaymentGatewayResponse elementToBeUpdated;

public UpdatePaymentGatewayResponse(PaymentGatewayResponse elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public PaymentGatewayResponse getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(PaymentGatewayResponse elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("PaymentGatewayResponse", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(PaymentGatewayResponse.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(PaymentGatewayResponse.class);
}
success = false;
}
Event resultingEvent = new PaymentGatewayResponseUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
