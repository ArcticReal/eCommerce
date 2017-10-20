package com.skytala.eCommerce.domain.order.relations.custRequest.command.status;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.custRequest.event.status.CustRequestStatusAdded;
import com.skytala.eCommerce.domain.order.relations.custRequest.mapper.status.CustRequestStatusMapper;
import com.skytala.eCommerce.domain.order.relations.custRequest.model.status.CustRequestStatus;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddCustRequestStatus extends Command {

private CustRequestStatus elementToBeAdded;
public AddCustRequestStatus(CustRequestStatus elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

CustRequestStatus addedElement = null;
boolean success = false;
try {
elementToBeAdded.setCustRequestStatusId(delegator.getNextSeqId("CustRequestStatus"));
GenericValue newValue = delegator.makeValue("CustRequestStatus", elementToBeAdded.mapAttributeField());
addedElement = CustRequestStatusMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new CustRequestStatusAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
