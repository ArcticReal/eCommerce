package com.skytala.eCommerce.domain.workeffort.relations.workEffortSurveyAppl.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortSurveyAppl.event.WorkEffortSurveyApplAdded;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortSurveyAppl.mapper.WorkEffortSurveyApplMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortSurveyAppl.model.WorkEffortSurveyAppl;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddWorkEffortSurveyAppl extends Command {

private WorkEffortSurveyAppl elementToBeAdded;
public AddWorkEffortSurveyAppl(WorkEffortSurveyAppl elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

WorkEffortSurveyAppl addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("WorkEffortSurveyAppl", elementToBeAdded.mapAttributeField());
addedElement = WorkEffortSurveyApplMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new WorkEffortSurveyApplAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
