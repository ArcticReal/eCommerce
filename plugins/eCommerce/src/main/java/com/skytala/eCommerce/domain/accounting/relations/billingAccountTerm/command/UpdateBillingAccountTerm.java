package com.skytala.eCommerce.domain.accounting.relations.billingAccountTerm.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accounting.relations.billingAccountTerm.event.BillingAccountTermUpdated;
import com.skytala.eCommerce.domain.accounting.relations.billingAccountTerm.model.BillingAccountTerm;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateBillingAccountTerm extends Command {

private BillingAccountTerm elementToBeUpdated;

public UpdateBillingAccountTerm(BillingAccountTerm elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public BillingAccountTerm getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(BillingAccountTerm elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("BillingAccountTerm", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(BillingAccountTerm.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(BillingAccountTerm.class);
}
success = false;
}
Event resultingEvent = new BillingAccountTermUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
