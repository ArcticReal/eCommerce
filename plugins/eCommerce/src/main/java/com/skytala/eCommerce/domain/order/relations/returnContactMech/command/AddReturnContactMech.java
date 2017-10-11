package com.skytala.eCommerce.domain.order.relations.returnContactMech.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.returnContactMech.event.ReturnContactMechAdded;
import com.skytala.eCommerce.domain.order.relations.returnContactMech.mapper.ReturnContactMechMapper;
import com.skytala.eCommerce.domain.order.relations.returnContactMech.model.ReturnContactMech;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddReturnContactMech extends Command {

private ReturnContactMech elementToBeAdded;
public AddReturnContactMech(ReturnContactMech elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ReturnContactMech addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("ReturnContactMech", elementToBeAdded.mapAttributeField());
addedElement = ReturnContactMechMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ReturnContactMechAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
