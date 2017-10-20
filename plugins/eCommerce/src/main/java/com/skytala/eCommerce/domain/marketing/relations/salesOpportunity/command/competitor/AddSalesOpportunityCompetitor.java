package com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.command.competitor;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.event.competitor.SalesOpportunityCompetitorAdded;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.mapper.competitor.SalesOpportunityCompetitorMapper;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.model.competitor.SalesOpportunityCompetitor;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddSalesOpportunityCompetitor extends Command {

private SalesOpportunityCompetitor elementToBeAdded;
public AddSalesOpportunityCompetitor(SalesOpportunityCompetitor elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

SalesOpportunityCompetitor addedElement = null;
boolean success = false;
try {
elementToBeAdded.setCompetitorPartyId(delegator.getNextSeqId("SalesOpportunityCompetitor"));
GenericValue newValue = delegator.makeValue("SalesOpportunityCompetitor", elementToBeAdded.mapAttributeField());
addedElement = SalesOpportunityCompetitorMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new SalesOpportunityCompetitorAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
