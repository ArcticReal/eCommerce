package com.skytala.eCommerce.domain.humanres.relations.partyBenefit.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.humanres.relations.partyBenefit.event.PartyBenefitAdded;
import com.skytala.eCommerce.domain.humanres.relations.partyBenefit.mapper.PartyBenefitMapper;
import com.skytala.eCommerce.domain.humanres.relations.partyBenefit.model.PartyBenefit;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddPartyBenefit extends Command {

private PartyBenefit elementToBeAdded;
public AddPartyBenefit(PartyBenefit elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

PartyBenefit addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("PartyBenefit", elementToBeAdded.mapAttributeField());
addedElement = PartyBenefitMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new PartyBenefitAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
