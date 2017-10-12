package com.skytala.eCommerce.domain.workeffort.relations.workEffortNote.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortNote.event.WorkEffortNoteAdded;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortNote.mapper.WorkEffortNoteMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortNote.model.WorkEffortNote;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddWorkEffortNote extends Command {

private WorkEffortNote elementToBeAdded;
public AddWorkEffortNote(WorkEffortNote elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

WorkEffortNote addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("WorkEffortNote", elementToBeAdded.mapAttributeField());
addedElement = WorkEffortNoteMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new WorkEffortNoteAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
