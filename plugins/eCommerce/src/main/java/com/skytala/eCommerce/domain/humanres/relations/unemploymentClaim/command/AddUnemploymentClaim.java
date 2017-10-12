package com.skytala.eCommerce.domain.humanres.relations.unemploymentClaim.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.humanres.relations.unemploymentClaim.event.UnemploymentClaimAdded;
import com.skytala.eCommerce.domain.humanres.relations.unemploymentClaim.mapper.UnemploymentClaimMapper;
import com.skytala.eCommerce.domain.humanres.relations.unemploymentClaim.model.UnemploymentClaim;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddUnemploymentClaim extends Command {

private UnemploymentClaim elementToBeAdded;
public AddUnemploymentClaim(UnemploymentClaim elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

UnemploymentClaim addedElement = null;
boolean success = false;
try {
elementToBeAdded.setUnemploymentClaimId(delegator.getNextSeqId("UnemploymentClaim"));
GenericValue newValue = delegator.makeValue("UnemploymentClaim", elementToBeAdded.mapAttributeField());
addedElement = UnemploymentClaimMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new UnemploymentClaimAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
