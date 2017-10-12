package com.skytala.eCommerce.domain.workeffort.relations.workEffortBilling.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortBilling.event.WorkEffortBillingUpdated;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortBilling.model.WorkEffortBilling;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateWorkEffortBilling extends Command {

private WorkEffortBilling elementToBeUpdated;

public UpdateWorkEffortBilling(WorkEffortBilling elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public WorkEffortBilling getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(WorkEffortBilling elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("WorkEffortBilling", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(WorkEffortBilling.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(WorkEffortBilling.class);
}
success = false;
}
Event resultingEvent = new WorkEffortBillingUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
