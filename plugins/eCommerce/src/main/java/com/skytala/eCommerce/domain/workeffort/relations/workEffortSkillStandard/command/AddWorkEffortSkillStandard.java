package com.skytala.eCommerce.domain.workeffort.relations.workEffortSkillStandard.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortSkillStandard.event.WorkEffortSkillStandardAdded;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortSkillStandard.mapper.WorkEffortSkillStandardMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortSkillStandard.model.WorkEffortSkillStandard;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddWorkEffortSkillStandard extends Command {

private WorkEffortSkillStandard elementToBeAdded;
public AddWorkEffortSkillStandard(WorkEffortSkillStandard elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

WorkEffortSkillStandard addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("WorkEffortSkillStandard", elementToBeAdded.mapAttributeField());
addedElement = WorkEffortSkillStandardMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new WorkEffortSkillStandardAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
