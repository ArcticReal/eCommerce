package com.skytala.eCommerce.domain.party.relations.affiliate.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.affiliate.event.AffiliateAdded;
import com.skytala.eCommerce.domain.party.relations.affiliate.mapper.AffiliateMapper;
import com.skytala.eCommerce.domain.party.relations.affiliate.model.Affiliate;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddAffiliate extends Command {

private Affiliate elementToBeAdded;
public AddAffiliate(Affiliate elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

Affiliate addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("Affiliate", elementToBeAdded.mapAttributeField());
addedElement = AffiliateMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new AffiliateAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
