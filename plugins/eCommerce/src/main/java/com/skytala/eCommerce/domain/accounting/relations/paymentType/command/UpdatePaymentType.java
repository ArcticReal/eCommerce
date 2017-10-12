package com.skytala.eCommerce.domain.accounting.relations.paymentType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accounting.relations.paymentType.event.PaymentTypeUpdated;
import com.skytala.eCommerce.domain.accounting.relations.paymentType.model.PaymentType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdatePaymentType extends Command {

private PaymentType elementToBeUpdated;

public UpdatePaymentType(PaymentType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public PaymentType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(PaymentType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("PaymentType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(PaymentType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(PaymentType.class);
}
success = false;
}
Event resultingEvent = new PaymentTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
