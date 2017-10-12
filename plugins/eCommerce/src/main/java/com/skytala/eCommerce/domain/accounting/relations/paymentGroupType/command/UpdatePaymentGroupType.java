package com.skytala.eCommerce.domain.accounting.relations.paymentGroupType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accounting.relations.paymentGroupType.event.PaymentGroupTypeUpdated;
import com.skytala.eCommerce.domain.accounting.relations.paymentGroupType.model.PaymentGroupType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdatePaymentGroupType extends Command {

private PaymentGroupType elementToBeUpdated;

public UpdatePaymentGroupType(PaymentGroupType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public PaymentGroupType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(PaymentGroupType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("PaymentGroupType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(PaymentGroupType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(PaymentGroupType.class);
}
success = false;
}
Event resultingEvent = new PaymentGroupTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
