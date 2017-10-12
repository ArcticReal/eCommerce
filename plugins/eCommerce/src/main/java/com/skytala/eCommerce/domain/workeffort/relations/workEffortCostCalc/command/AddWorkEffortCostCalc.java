package com.skytala.eCommerce.domain.workeffort.relations.workEffortCostCalc.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortCostCalc.event.WorkEffortCostCalcAdded;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortCostCalc.mapper.WorkEffortCostCalcMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortCostCalc.model.WorkEffortCostCalc;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddWorkEffortCostCalc extends Command {

private WorkEffortCostCalc elementToBeAdded;
public AddWorkEffortCostCalc(WorkEffortCostCalc elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

WorkEffortCostCalc addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("WorkEffortCostCalc", elementToBeAdded.mapAttributeField());
addedElement = WorkEffortCostCalcMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new WorkEffortCostCalcAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
