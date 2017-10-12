package com.skytala.eCommerce.domain.accounting.relations.partyTaxAuthInfo.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.partyTaxAuthInfo.event.PartyTaxAuthInfoAdded;
import com.skytala.eCommerce.domain.accounting.relations.partyTaxAuthInfo.mapper.PartyTaxAuthInfoMapper;
import com.skytala.eCommerce.domain.accounting.relations.partyTaxAuthInfo.model.PartyTaxAuthInfo;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddPartyTaxAuthInfo extends Command {

private PartyTaxAuthInfo elementToBeAdded;
public AddPartyTaxAuthInfo(PartyTaxAuthInfo elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

PartyTaxAuthInfo addedElement = null;
boolean success = false;
try {
elementToBeAdded.setTaxAuthPartyId(delegator.getNextSeqId("PartyTaxAuthInfo"));
GenericValue newValue = delegator.makeValue("PartyTaxAuthInfo", elementToBeAdded.mapAttributeField());
addedElement = PartyTaxAuthInfoMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new PartyTaxAuthInfoAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
