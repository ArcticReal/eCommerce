package com.skytala.eCommerce.domain.workeffort.relations.workEffortNote.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortNote.event.WorkEffortNoteUpdated;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortNote.model.WorkEffortNote;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateWorkEffortNote extends Command {

private WorkEffortNote elementToBeUpdated;

public UpdateWorkEffortNote(WorkEffortNote elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public WorkEffortNote getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(WorkEffortNote elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("WorkEffortNote", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(WorkEffortNote.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(WorkEffortNote.class);
}
success = false;
}
Event resultingEvent = new WorkEffortNoteUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
