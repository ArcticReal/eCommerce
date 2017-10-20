package com.skytala.eCommerce.domain.accounting.relations.glAccount.command.paymentMethodType;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.paymentMethodType.PaymentMethodTypeGlAccountUpdated;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.paymentMethodType.PaymentMethodTypeGlAccount;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdatePaymentMethodTypeGlAccount extends Command {

private PaymentMethodTypeGlAccount elementToBeUpdated;

public UpdatePaymentMethodTypeGlAccount(PaymentMethodTypeGlAccount elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public PaymentMethodTypeGlAccount getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(PaymentMethodTypeGlAccount elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("PaymentMethodTypeGlAccount", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(PaymentMethodTypeGlAccount.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(PaymentMethodTypeGlAccount.class);
}
success = false;
}
Event resultingEvent = new PaymentMethodTypeGlAccountUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
