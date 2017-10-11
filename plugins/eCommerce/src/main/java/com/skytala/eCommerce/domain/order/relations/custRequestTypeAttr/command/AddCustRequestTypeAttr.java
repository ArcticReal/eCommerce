package com.skytala.eCommerce.domain.order.relations.custRequestTypeAttr.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.custRequestTypeAttr.event.CustRequestTypeAttrAdded;
import com.skytala.eCommerce.domain.order.relations.custRequestTypeAttr.mapper.CustRequestTypeAttrMapper;
import com.skytala.eCommerce.domain.order.relations.custRequestTypeAttr.model.CustRequestTypeAttr;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddCustRequestTypeAttr extends Command {

private CustRequestTypeAttr elementToBeAdded;
public AddCustRequestTypeAttr(CustRequestTypeAttr elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

CustRequestTypeAttr addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("CustRequestTypeAttr", elementToBeAdded.mapAttributeField());
addedElement = CustRequestTypeAttrMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new CustRequestTypeAttrAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
