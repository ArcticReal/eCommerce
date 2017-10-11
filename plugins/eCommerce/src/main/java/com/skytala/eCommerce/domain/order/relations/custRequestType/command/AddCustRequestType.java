package com.skytala.eCommerce.domain.order.relations.custRequestType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.custRequestType.event.CustRequestTypeAdded;
import com.skytala.eCommerce.domain.order.relations.custRequestType.mapper.CustRequestTypeMapper;
import com.skytala.eCommerce.domain.order.relations.custRequestType.model.CustRequestType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddCustRequestType extends Command {

private CustRequestType elementToBeAdded;
public AddCustRequestType(CustRequestType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

CustRequestType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setCustRequestTypeId(delegator.getNextSeqId("CustRequestType"));
GenericValue newValue = delegator.makeValue("CustRequestType", elementToBeAdded.mapAttributeField());
addedElement = CustRequestTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new CustRequestTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
