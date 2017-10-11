package com.skytala.eCommerce.domain.party.relations.addressMatchMap.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.addressMatchMap.event.AddressMatchMapAdded;
import com.skytala.eCommerce.domain.party.relations.addressMatchMap.mapper.AddressMatchMapMapper;
import com.skytala.eCommerce.domain.party.relations.addressMatchMap.model.AddressMatchMap;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddAddressMatchMap extends Command {

private AddressMatchMap elementToBeAdded;
public AddAddressMatchMap(AddressMatchMap elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

AddressMatchMap addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("AddressMatchMap", elementToBeAdded.mapAttributeField());
addedElement = AddressMatchMapMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new AddressMatchMapAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
