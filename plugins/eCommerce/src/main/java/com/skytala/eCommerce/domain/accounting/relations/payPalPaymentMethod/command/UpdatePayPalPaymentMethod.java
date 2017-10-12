package com.skytala.eCommerce.domain.accounting.relations.payPalPaymentMethod.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accounting.relations.payPalPaymentMethod.event.PayPalPaymentMethodUpdated;
import com.skytala.eCommerce.domain.accounting.relations.payPalPaymentMethod.model.PayPalPaymentMethod;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdatePayPalPaymentMethod extends Command {

private PayPalPaymentMethod elementToBeUpdated;

public UpdatePayPalPaymentMethod(PayPalPaymentMethod elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public PayPalPaymentMethod getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(PayPalPaymentMethod elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("PayPalPaymentMethod", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(PayPalPaymentMethod.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(PayPalPaymentMethod.class);
}
success = false;
}
Event resultingEvent = new PayPalPaymentMethodUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
