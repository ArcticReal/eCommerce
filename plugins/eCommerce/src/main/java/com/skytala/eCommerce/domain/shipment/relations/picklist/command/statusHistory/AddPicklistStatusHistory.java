package com.skytala.eCommerce.domain.shipment.relations.picklist.command.statusHistory;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.shipment.relations.picklist.event.statusHistory.PicklistStatusHistoryAdded;
import com.skytala.eCommerce.domain.shipment.relations.picklist.mapper.statusHistory.PicklistStatusHistoryMapper;
import com.skytala.eCommerce.domain.shipment.relations.picklist.model.statusHistory.PicklistStatusHistory;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddPicklistStatusHistory extends Command {

private PicklistStatusHistory elementToBeAdded;
public AddPicklistStatusHistory(PicklistStatusHistory elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

PicklistStatusHistory addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("PicklistStatusHistory", elementToBeAdded.mapAttributeField());
addedElement = PicklistStatusHistoryMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new PicklistStatusHistoryAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
