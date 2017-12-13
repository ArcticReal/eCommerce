package com.skytala.eCommerce.domain.party.relations.party.command.typeAttr;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.party.event.typeAttr.PartyTypeAttrAdded;
import com.skytala.eCommerce.domain.party.relations.party.mapper.typeAttr.PartyTypeAttrMapper;
import com.skytala.eCommerce.domain.party.relations.party.model.typeAttr.PartyTypeAttr;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddPartyTypeAttr extends Command {

private PartyTypeAttr elementToBeAdded;
public AddPartyTypeAttr(PartyTypeAttr elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

PartyTypeAttr addedElement = null;
boolean success = false;
try {
elementToBeAdded.setAttrName(delegator.getNextSeqId("PartyTypeAttr"));
GenericValue newValue = delegator.makeValue("PartyTypeAttr", elementToBeAdded.mapAttributeField());
addedElement = PartyTypeAttrMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new PartyTypeAttrAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
