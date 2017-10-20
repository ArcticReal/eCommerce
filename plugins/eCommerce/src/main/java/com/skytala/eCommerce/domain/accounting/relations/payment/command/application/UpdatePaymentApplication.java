package com.skytala.eCommerce.domain.accounting.relations.payment.command.application;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.application.PaymentApplicationUpdated;
import com.skytala.eCommerce.domain.accounting.relations.payment.model.application.PaymentApplication;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdatePaymentApplication extends Command {

private PaymentApplication elementToBeUpdated;

public UpdatePaymentApplication(PaymentApplication elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public PaymentApplication getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(PaymentApplication elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("PaymentApplication", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(PaymentApplication.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(PaymentApplication.class);
}
success = false;
}
Event resultingEvent = new PaymentApplicationUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
