package com.skytala.eCommerce.domain.product.relations.quantityBreak.command.type;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.quantityBreak.event.type.QuantityBreakTypeAdded;
import com.skytala.eCommerce.domain.product.relations.quantityBreak.mapper.type.QuantityBreakTypeMapper;
import com.skytala.eCommerce.domain.product.relations.quantityBreak.model.type.QuantityBreakType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddQuantityBreakType extends Command {

private QuantityBreakType elementToBeAdded;
public AddQuantityBreakType(QuantityBreakType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

QuantityBreakType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setQuantityBreakTypeId(delegator.getNextSeqId("QuantityBreakType"));
GenericValue newValue = delegator.makeValue("QuantityBreakType", elementToBeAdded.mapAttributeField());
addedElement = QuantityBreakTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new QuantityBreakTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
