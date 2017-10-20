package com.skytala.eCommerce.domain.order.relations.custRequest.command.resolution;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.custRequest.event.resolution.CustRequestResolutionAdded;
import com.skytala.eCommerce.domain.order.relations.custRequest.mapper.resolution.CustRequestResolutionMapper;
import com.skytala.eCommerce.domain.order.relations.custRequest.model.resolution.CustRequestResolution;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddCustRequestResolution extends Command {

private CustRequestResolution elementToBeAdded;
public AddCustRequestResolution(CustRequestResolution elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

CustRequestResolution addedElement = null;
boolean success = false;
try {
elementToBeAdded.setCustRequestResolutionId(delegator.getNextSeqId("CustRequestResolution"));
GenericValue newValue = delegator.makeValue("CustRequestResolution", elementToBeAdded.mapAttributeField());
addedElement = CustRequestResolutionMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new CustRequestResolutionAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
