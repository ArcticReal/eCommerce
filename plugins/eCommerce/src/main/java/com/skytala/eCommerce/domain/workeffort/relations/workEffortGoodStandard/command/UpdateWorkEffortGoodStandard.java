package com.skytala.eCommerce.domain.workeffort.relations.workEffortGoodStandard.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortGoodStandard.event.WorkEffortGoodStandardUpdated;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortGoodStandard.model.WorkEffortGoodStandard;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateWorkEffortGoodStandard extends Command {

private WorkEffortGoodStandard elementToBeUpdated;

public UpdateWorkEffortGoodStandard(WorkEffortGoodStandard elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public WorkEffortGoodStandard getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(WorkEffortGoodStandard elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("WorkEffortGoodStandard", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(WorkEffortGoodStandard.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(WorkEffortGoodStandard.class);
}
success = false;
}
Event resultingEvent = new WorkEffortGoodStandardUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
