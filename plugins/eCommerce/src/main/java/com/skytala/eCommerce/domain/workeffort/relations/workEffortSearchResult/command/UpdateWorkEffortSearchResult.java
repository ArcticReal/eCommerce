package com.skytala.eCommerce.domain.workeffort.relations.workEffortSearchResult.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortSearchResult.event.WorkEffortSearchResultUpdated;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortSearchResult.model.WorkEffortSearchResult;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateWorkEffortSearchResult extends Command {

private WorkEffortSearchResult elementToBeUpdated;

public UpdateWorkEffortSearchResult(WorkEffortSearchResult elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public WorkEffortSearchResult getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(WorkEffortSearchResult elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("WorkEffortSearchResult", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(WorkEffortSearchResult.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(WorkEffortSearchResult.class);
}
success = false;
}
Event resultingEvent = new WorkEffortSearchResultUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
