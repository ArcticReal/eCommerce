package com.skytala.eCommerce.domain.accounting.relations.payment.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.PaymentUpdated;
import com.skytala.eCommerce.domain.accounting.relations.payment.model.Payment;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdatePayment extends Command {

private Payment elementToBeUpdated;

public UpdatePayment(Payment elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public Payment getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(Payment elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("Payment", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(Payment.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(Payment.class);
}
success = false;
}
Event resultingEvent = new PaymentUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
