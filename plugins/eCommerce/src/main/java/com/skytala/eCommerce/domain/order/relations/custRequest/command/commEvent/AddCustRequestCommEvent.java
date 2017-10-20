package com.skytala.eCommerce.domain.order.relations.custRequest.command.commEvent;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.custRequest.event.commEvent.CustRequestCommEventAdded;
import com.skytala.eCommerce.domain.order.relations.custRequest.mapper.commEvent.CustRequestCommEventMapper;
import com.skytala.eCommerce.domain.order.relations.custRequest.model.commEvent.CustRequestCommEvent;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddCustRequestCommEvent extends Command {

private CustRequestCommEvent elementToBeAdded;
public AddCustRequestCommEvent(CustRequestCommEvent elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

CustRequestCommEvent addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("CustRequestCommEvent", elementToBeAdded.mapAttributeField());
addedElement = CustRequestCommEventMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new CustRequestCommEventAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
