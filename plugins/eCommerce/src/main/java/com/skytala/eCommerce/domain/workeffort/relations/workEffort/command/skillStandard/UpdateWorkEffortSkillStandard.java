package com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.skillStandard;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.skillStandard.WorkEffortSkillStandardUpdated;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.skillStandard.WorkEffortSkillStandard;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateWorkEffortSkillStandard extends Command {

private WorkEffortSkillStandard elementToBeUpdated;

public UpdateWorkEffortSkillStandard(WorkEffortSkillStandard elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public WorkEffortSkillStandard getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(WorkEffortSkillStandard elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("WorkEffortSkillStandard", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(WorkEffortSkillStandard.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(WorkEffortSkillStandard.class);
}
success = false;
}
Event resultingEvent = new WorkEffortSkillStandardUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
