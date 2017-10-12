package com.skytala.eCommerce.domain.workeffort.relations.workEffortGoodStandardType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortGoodStandardType.event.WorkEffortGoodStandardTypeUpdated;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortGoodStandardType.model.WorkEffortGoodStandardType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateWorkEffortGoodStandardType extends Command {

private WorkEffortGoodStandardType elementToBeUpdated;

public UpdateWorkEffortGoodStandardType(WorkEffortGoodStandardType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public WorkEffortGoodStandardType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(WorkEffortGoodStandardType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("WorkEffortGoodStandardType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(WorkEffortGoodStandardType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(WorkEffortGoodStandardType.class);
}
success = false;
}
Event resultingEvent = new WorkEffortGoodStandardTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
