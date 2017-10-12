package com.skytala.eCommerce.domain.accounting.relations.billingAccount.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accounting.relations.billingAccount.event.BillingAccountUpdated;
import com.skytala.eCommerce.domain.accounting.relations.billingAccount.model.BillingAccount;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateBillingAccount extends Command {

private BillingAccount elementToBeUpdated;

public UpdateBillingAccount(BillingAccount elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public BillingAccount getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(BillingAccount elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("BillingAccount", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(BillingAccount.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(BillingAccount.class);
}
success = false;
}
Event resultingEvent = new BillingAccountUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
