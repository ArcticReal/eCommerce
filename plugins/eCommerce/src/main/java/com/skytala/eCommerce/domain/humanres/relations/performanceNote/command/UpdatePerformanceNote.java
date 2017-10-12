package com.skytala.eCommerce.domain.humanres.relations.performanceNote.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.humanres.relations.performanceNote.event.PerformanceNoteUpdated;
import com.skytala.eCommerce.domain.humanres.relations.performanceNote.model.PerformanceNote;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdatePerformanceNote extends Command {

private PerformanceNote elementToBeUpdated;

public UpdatePerformanceNote(PerformanceNote elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public PerformanceNote getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(PerformanceNote elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("PerformanceNote", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(PerformanceNote.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(PerformanceNote.class);
}
success = false;
}
Event resultingEvent = new PerformanceNoteUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
