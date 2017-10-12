package com.skytala.eCommerce.domain.workeffort.relations.workEffortContent.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortContent.event.WorkEffortContentUpdated;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortContent.model.WorkEffortContent;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateWorkEffortContent extends Command {

private WorkEffortContent elementToBeUpdated;

public UpdateWorkEffortContent(WorkEffortContent elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public WorkEffortContent getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(WorkEffortContent elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("WorkEffortContent", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(WorkEffortContent.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(WorkEffortContent.class);
}
success = false;
}
Event resultingEvent = new WorkEffortContentUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
