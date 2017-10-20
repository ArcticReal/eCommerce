package com.skytala.eCommerce.domain.accounting.relations.payment.command.attribute;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.attribute.PaymentAttributeUpdated;
import com.skytala.eCommerce.domain.accounting.relations.payment.model.attribute.PaymentAttribute;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdatePaymentAttribute extends Command {

private PaymentAttribute elementToBeUpdated;

public UpdatePaymentAttribute(PaymentAttribute elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public PaymentAttribute getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(PaymentAttribute elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("PaymentAttribute", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(PaymentAttribute.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(PaymentAttribute.class);
}
success = false;
}
Event resultingEvent = new PaymentAttributeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
