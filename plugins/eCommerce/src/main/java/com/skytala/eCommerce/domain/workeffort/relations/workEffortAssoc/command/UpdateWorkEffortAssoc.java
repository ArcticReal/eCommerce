package com.skytala.eCommerce.domain.workeffort.relations.workEffortAssoc.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortAssoc.event.WorkEffortAssocUpdated;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortAssoc.model.WorkEffortAssoc;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateWorkEffortAssoc extends Command {

private WorkEffortAssoc elementToBeUpdated;

public UpdateWorkEffortAssoc(WorkEffortAssoc elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public WorkEffortAssoc getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(WorkEffortAssoc elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("WorkEffortAssoc", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(WorkEffortAssoc.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(WorkEffortAssoc.class);
}
success = false;
}
Event resultingEvent = new WorkEffortAssocUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
