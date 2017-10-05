package com.skytala.eCommerce.domain.salesOpportunityHistory.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.salesOpportunityHistory.event.SalesOpportunityHistoryAdded;
import com.skytala.eCommerce.domain.salesOpportunityHistory.mapper.SalesOpportunityHistoryMapper;
import com.skytala.eCommerce.domain.salesOpportunityHistory.model.SalesOpportunityHistory;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddSalesOpportunityHistory extends Command {

private SalesOpportunityHistory elementToBeAdded;
public AddSalesOpportunityHistory(SalesOpportunityHistory elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

SalesOpportunityHistory addedElement = null;
boolean success = false;
try {
elementToBeAdded.setSalesOpportunityHistoryId(delegator.getNextSeqId("SalesOpportunityHistory"));
GenericValue newValue = delegator.makeValue("SalesOpportunityHistory", elementToBeAdded.mapAttributeField());
addedElement = SalesOpportunityHistoryMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new SalesOpportunityHistoryAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
