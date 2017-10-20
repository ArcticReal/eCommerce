package com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.command.stage;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.event.stage.SalesOpportunityStageAdded;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.mapper.stage.SalesOpportunityStageMapper;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.model.stage.SalesOpportunityStage;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddSalesOpportunityStage extends Command {

private SalesOpportunityStage elementToBeAdded;
public AddSalesOpportunityStage(SalesOpportunityStage elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

SalesOpportunityStage addedElement = null;
boolean success = false;
try {
elementToBeAdded.setOpportunityStageId(delegator.getNextSeqId("SalesOpportunityStage"));
GenericValue newValue = delegator.makeValue("SalesOpportunityStage", elementToBeAdded.mapAttributeField());
addedElement = SalesOpportunityStageMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new SalesOpportunityStageAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
