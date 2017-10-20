package com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.keyword;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.keyword.WorkEffortKeywordAdded;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.keyword.WorkEffortKeywordMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.keyword.WorkEffortKeyword;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddWorkEffortKeyword extends Command {

private WorkEffortKeyword elementToBeAdded;
public AddWorkEffortKeyword(WorkEffortKeyword elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

WorkEffortKeyword addedElement = null;
boolean success = false;
try {
elementToBeAdded.setKeyword(delegator.getNextSeqId("WorkEffortKeyword"));
GenericValue newValue = delegator.makeValue("WorkEffortKeyword", elementToBeAdded.mapAttributeField());
addedElement = WorkEffortKeywordMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new WorkEffortKeywordAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
