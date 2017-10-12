package com.skytala.eCommerce.domain.accounting.relations.paymentGlAccountTypeMap.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accounting.relations.paymentGlAccountTypeMap.event.PaymentGlAccountTypeMapUpdated;
import com.skytala.eCommerce.domain.accounting.relations.paymentGlAccountTypeMap.model.PaymentGlAccountTypeMap;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdatePaymentGlAccountTypeMap extends Command {

private PaymentGlAccountTypeMap elementToBeUpdated;

public UpdatePaymentGlAccountTypeMap(PaymentGlAccountTypeMap elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public PaymentGlAccountTypeMap getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(PaymentGlAccountTypeMap elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("PaymentGlAccountTypeMap", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(PaymentGlAccountTypeMap.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(PaymentGlAccountTypeMap.class);
}
success = false;
}
Event resultingEvent = new PaymentGlAccountTypeMapUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
