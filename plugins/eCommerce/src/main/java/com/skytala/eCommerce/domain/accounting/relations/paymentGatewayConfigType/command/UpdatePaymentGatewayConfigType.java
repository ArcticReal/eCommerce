package com.skytala.eCommerce.domain.accounting.relations.paymentGatewayConfigType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayConfigType.event.PaymentGatewayConfigTypeUpdated;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayConfigType.model.PaymentGatewayConfigType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdatePaymentGatewayConfigType extends Command {

private PaymentGatewayConfigType elementToBeUpdated;

public UpdatePaymentGatewayConfigType(PaymentGatewayConfigType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public PaymentGatewayConfigType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(PaymentGatewayConfigType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("PaymentGatewayConfigType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(PaymentGatewayConfigType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(PaymentGatewayConfigType.class);
}
success = false;
}
Event resultingEvent = new PaymentGatewayConfigTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
