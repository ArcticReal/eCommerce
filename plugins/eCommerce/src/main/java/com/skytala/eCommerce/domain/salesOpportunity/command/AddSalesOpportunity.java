package com.skytala.eCommerce.domain.salesOpportunity.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.salesOpportunity.event.SalesOpportunityAdded;
import com.skytala.eCommerce.domain.salesOpportunity.mapper.SalesOpportunityMapper;
import com.skytala.eCommerce.domain.salesOpportunity.model.SalesOpportunity;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddSalesOpportunity extends Command {

private SalesOpportunity elementToBeAdded;
public AddSalesOpportunity(SalesOpportunity elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

SalesOpportunity addedElement = null;
boolean success = false;
try {
elementToBeAdded.setSalesOpportunityId(delegator.getNextSeqId("SalesOpportunity"));
GenericValue newValue = delegator.makeValue("SalesOpportunity", elementToBeAdded.mapAttributeField());
addedElement = SalesOpportunityMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new SalesOpportunityAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
