package com.skytala.eCommerce.domain.product.relations.lot.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.lot.event.LotAdded;
import com.skytala.eCommerce.domain.product.relations.lot.mapper.LotMapper;
import com.skytala.eCommerce.domain.product.relations.lot.model.Lot;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddLot extends Command {

private Lot elementToBeAdded;
public AddLot(Lot elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

Lot addedElement = null;
boolean success = false;
try {
elementToBeAdded.setLotId(delegator.getNextSeqId("Lot"));
GenericValue newValue = delegator.makeValue("Lot", elementToBeAdded.mapAttributeField());
addedElement = LotMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new LotAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
