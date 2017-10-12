package com.skytala.eCommerce.domain.accounting.relations.paymentGroup.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accounting.relations.paymentGroup.event.PaymentGroupUpdated;
import com.skytala.eCommerce.domain.accounting.relations.paymentGroup.model.PaymentGroup;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdatePaymentGroup extends Command {

private PaymentGroup elementToBeUpdated;

public UpdatePaymentGroup(PaymentGroup elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public PaymentGroup getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(PaymentGroup elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("PaymentGroup", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(PaymentGroup.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(PaymentGroup.class);
}
success = false;
}
Event resultingEvent = new PaymentGroupUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
