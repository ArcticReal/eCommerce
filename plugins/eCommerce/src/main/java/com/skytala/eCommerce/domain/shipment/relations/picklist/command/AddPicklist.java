package com.skytala.eCommerce.domain.shipment.relations.picklist.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.shipment.relations.picklist.event.PicklistAdded;
import com.skytala.eCommerce.domain.shipment.relations.picklist.mapper.PicklistMapper;
import com.skytala.eCommerce.domain.shipment.relations.picklist.model.Picklist;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddPicklist extends Command {

private Picklist elementToBeAdded;
public AddPicklist(Picklist elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

Picklist addedElement = null;
boolean success = false;
try {
elementToBeAdded.setPicklistId(delegator.getNextSeqId("Picklist"));
GenericValue newValue = delegator.makeValue("Picklist", elementToBeAdded.mapAttributeField());
addedElement = PicklistMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new PicklistAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
