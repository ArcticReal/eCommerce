package com.skytala.eCommerce.domain.shipment.relations.picklistBin.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.shipment.relations.picklistBin.event.PicklistBinAdded;
import com.skytala.eCommerce.domain.shipment.relations.picklistBin.mapper.PicklistBinMapper;
import com.skytala.eCommerce.domain.shipment.relations.picklistBin.model.PicklistBin;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddPicklistBin extends Command {

private PicklistBin elementToBeAdded;
public AddPicklistBin(PicklistBin elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

PicklistBin addedElement = null;
boolean success = false;
try {
elementToBeAdded.setPicklistBinId(delegator.getNextSeqId("PicklistBin"));
GenericValue newValue = delegator.makeValue("PicklistBin", elementToBeAdded.mapAttributeField());
addedElement = PicklistBinMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new PicklistBinAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
