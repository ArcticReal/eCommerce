package com.skytala.eCommerce.domain.accounting.relations.payment.command.method;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.method.PaymentMethodUpdated;
import com.skytala.eCommerce.domain.accounting.relations.payment.model.method.PaymentMethod;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdatePaymentMethod extends Command {

private PaymentMethod elementToBeUpdated;

public UpdatePaymentMethod(PaymentMethod elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public PaymentMethod getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(PaymentMethod elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("PaymentMethod", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(PaymentMethod.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(PaymentMethod.class);
}
success = false;
}
Event resultingEvent = new PaymentMethodUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
