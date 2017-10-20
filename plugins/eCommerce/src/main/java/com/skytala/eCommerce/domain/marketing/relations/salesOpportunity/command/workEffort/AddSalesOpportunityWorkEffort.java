package com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.command.workEffort;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.event.workEffort.SalesOpportunityWorkEffortAdded;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.mapper.workEffort.SalesOpportunityWorkEffortMapper;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.model.workEffort.SalesOpportunityWorkEffort;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddSalesOpportunityWorkEffort extends Command {

private SalesOpportunityWorkEffort elementToBeAdded;
public AddSalesOpportunityWorkEffort(SalesOpportunityWorkEffort elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

SalesOpportunityWorkEffort addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("SalesOpportunityWorkEffort", elementToBeAdded.mapAttributeField());
addedElement = SalesOpportunityWorkEffortMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new SalesOpportunityWorkEffortAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
