package com.skytala.eCommerce.domain.workEffortSearchResult.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.workEffortSearchResult.event.WorkEffortSearchResultAdded;
import com.skytala.eCommerce.domain.workEffortSearchResult.mapper.WorkEffortSearchResultMapper;
import com.skytala.eCommerce.domain.workEffortSearchResult.model.WorkEffortSearchResult;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddWorkEffortSearchResult extends Command {

private WorkEffortSearchResult elementToBeAdded;
public AddWorkEffortSearchResult(WorkEffortSearchResult elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

WorkEffortSearchResult addedElement = null;
boolean success = false;
try {
elementToBeAdded.setWorkEffortSearchResultId(delegator.getNextSeqId("WorkEffortSearchResult"));
GenericValue newValue = delegator.makeValue("WorkEffortSearchResult", elementToBeAdded.mapAttributeField());
addedElement = WorkEffortSearchResultMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new WorkEffortSearchResultAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
