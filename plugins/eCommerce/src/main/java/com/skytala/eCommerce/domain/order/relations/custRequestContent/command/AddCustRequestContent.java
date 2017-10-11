package com.skytala.eCommerce.domain.order.relations.custRequestContent.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.custRequestContent.event.CustRequestContentAdded;
import com.skytala.eCommerce.domain.order.relations.custRequestContent.mapper.CustRequestContentMapper;
import com.skytala.eCommerce.domain.order.relations.custRequestContent.model.CustRequestContent;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddCustRequestContent extends Command {

private CustRequestContent elementToBeAdded;
public AddCustRequestContent(CustRequestContent elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

CustRequestContent addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("CustRequestContent", elementToBeAdded.mapAttributeField());
addedElement = CustRequestContentMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new CustRequestContentAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
