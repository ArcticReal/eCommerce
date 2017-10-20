package com.skytala.eCommerce.domain.party.relations.party.command.oldTaxInfo;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.party.event.oldTaxInfo.OldPartyTaxInfoAdded;
import com.skytala.eCommerce.domain.party.relations.party.mapper.oldTaxInfo.OldPartyTaxInfoMapper;
import com.skytala.eCommerce.domain.party.relations.party.model.oldTaxInfo.OldPartyTaxInfo;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddOldPartyTaxInfo extends Command {

private OldPartyTaxInfo elementToBeAdded;
public AddOldPartyTaxInfo(OldPartyTaxInfo elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

OldPartyTaxInfo addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("OldPartyTaxInfo", elementToBeAdded.mapAttributeField());
addedElement = OldPartyTaxInfoMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new OldPartyTaxInfoAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
