package com.skytala.eCommerce.domain.order.relations.custRequest.command.attribute;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.custRequest.event.attribute.CustRequestAttributeAdded;
import com.skytala.eCommerce.domain.order.relations.custRequest.mapper.attribute.CustRequestAttributeMapper;
import com.skytala.eCommerce.domain.order.relations.custRequest.model.attribute.CustRequestAttribute;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddCustRequestAttribute extends Command {

private CustRequestAttribute elementToBeAdded;
public AddCustRequestAttribute(CustRequestAttribute elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

CustRequestAttribute addedElement = null;
boolean success = false;
try {
elementToBeAdded.setAttrName(delegator.getNextSeqId("CustRequestAttribute"));
GenericValue newValue = delegator.makeValue("CustRequestAttribute", elementToBeAdded.mapAttributeField());
addedElement = CustRequestAttributeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new CustRequestAttributeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
