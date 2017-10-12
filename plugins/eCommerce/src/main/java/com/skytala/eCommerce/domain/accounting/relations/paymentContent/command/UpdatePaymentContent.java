package com.skytala.eCommerce.domain.accounting.relations.paymentContent.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accounting.relations.paymentContent.event.PaymentContentUpdated;
import com.skytala.eCommerce.domain.accounting.relations.paymentContent.model.PaymentContent;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdatePaymentContent extends Command {

private PaymentContent elementToBeUpdated;

public UpdatePaymentContent(PaymentContent elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public PaymentContent getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(PaymentContent elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("PaymentContent", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(PaymentContent.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(PaymentContent.class);
}
success = false;
}
Event resultingEvent = new PaymentContentUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
