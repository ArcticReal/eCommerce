package com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.command.trckCode;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.event.trckCode.SalesOpportunityTrckCodeAdded;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.mapper.trckCode.SalesOpportunityTrckCodeMapper;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.model.trckCode.SalesOpportunityTrckCode;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddSalesOpportunityTrckCode extends Command {

private SalesOpportunityTrckCode elementToBeAdded;
public AddSalesOpportunityTrckCode(SalesOpportunityTrckCode elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

SalesOpportunityTrckCode addedElement = null;
boolean success = false;
try {
elementToBeAdded.setTrackingCodeId(delegator.getNextSeqId("SalesOpportunityTrckCode"));
GenericValue newValue = delegator.makeValue("SalesOpportunityTrckCode", elementToBeAdded.mapAttributeField());
addedElement = SalesOpportunityTrckCodeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new SalesOpportunityTrckCodeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
