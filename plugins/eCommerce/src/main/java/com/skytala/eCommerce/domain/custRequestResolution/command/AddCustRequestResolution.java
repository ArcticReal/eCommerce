package com.skytala.eCommerce.domain.custRequestResolution.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.custRequestResolution.event.CustRequestResolutionAdded;
import com.skytala.eCommerce.domain.custRequestResolution.mapper.CustRequestResolutionMapper;
import com.skytala.eCommerce.domain.custRequestResolution.model.CustRequestResolution;
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
