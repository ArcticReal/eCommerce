package com.skytala.eCommerce.domain.order.relations.custRequest.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.custRequest.event.CustRequestAdded;
import com.skytala.eCommerce.domain.order.relations.custRequest.mapper.CustRequestMapper;
import com.skytala.eCommerce.domain.order.relations.custRequest.model.CustRequest;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddCustRequest extends Command {

private CustRequest elementToBeAdded;
public AddCustRequest(CustRequest elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

CustRequest addedElement = null;
boolean success = false;
try {
elementToBeAdded.setCustRequestId(delegator.getNextSeqId("CustRequest"));
GenericValue newValue = delegator.makeValue("CustRequest", elementToBeAdded.mapAttributeField());
addedElement = CustRequestMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new CustRequestAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
