package com.skytala.eCommerce.domain.marketing.relations.salesOpportunityQuote.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunityQuote.event.SalesOpportunityQuoteAdded;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunityQuote.mapper.SalesOpportunityQuoteMapper;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunityQuote.model.SalesOpportunityQuote;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddSalesOpportunityQuote extends Command {

private SalesOpportunityQuote elementToBeAdded;
public AddSalesOpportunityQuote(SalesOpportunityQuote elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

SalesOpportunityQuote addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("SalesOpportunityQuote", elementToBeAdded.mapAttributeField());
addedElement = SalesOpportunityQuoteMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new SalesOpportunityQuoteAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
