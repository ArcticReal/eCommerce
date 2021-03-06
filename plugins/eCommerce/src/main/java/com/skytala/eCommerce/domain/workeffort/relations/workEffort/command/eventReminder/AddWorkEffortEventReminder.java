package com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.eventReminder;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.eventReminder.WorkEffortEventReminderAdded;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.eventReminder.WorkEffortEventReminderMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.eventReminder.WorkEffortEventReminder;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddWorkEffortEventReminder extends Command {

private WorkEffortEventReminder elementToBeAdded;
public AddWorkEffortEventReminder(WorkEffortEventReminder elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

WorkEffortEventReminder addedElement = null;
boolean success = false;
try {
elementToBeAdded.setSequenceId(delegator.getNextSeqId("WorkEffortEventReminder"));
GenericValue newValue = delegator.makeValue("WorkEffortEventReminder", elementToBeAdded.mapAttributeField());
addedElement = WorkEffortEventReminderMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new WorkEffortEventReminderAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
