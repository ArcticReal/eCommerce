package com.skytala.eCommerce.domain.accounting.relations.billingAccount.command.role;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accounting.relations.billingAccount.event.role.BillingAccountRoleUpdated;
import com.skytala.eCommerce.domain.accounting.relations.billingAccount.model.role.BillingAccountRole;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateBillingAccountRole extends Command {

private BillingAccountRole elementToBeUpdated;

public UpdateBillingAccountRole(BillingAccountRole elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public BillingAccountRole getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(BillingAccountRole elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("BillingAccountRole", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(BillingAccountRole.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(BillingAccountRole.class);
}
success = false;
}
Event resultingEvent = new BillingAccountRoleUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
