package com.skytala.eCommerce.domain.paymentMethodType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.paymentMethodType.event.PaymentMethodTypeUpdated;
import com.skytala.eCommerce.domain.paymentMethodType.model.PaymentMethodType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdatePaymentMethodType extends Command {

private PaymentMethodType elementToBeUpdated;

public UpdatePaymentMethodType(PaymentMethodType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public PaymentMethodType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(PaymentMethodType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("PaymentMethodType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(PaymentMethodType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(PaymentMethodType.class);
}
success = false;
}
Event resultingEvent = new PaymentMethodTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
