package com.skytala.eCommerce.domain.accounting.relations.finAccountTypeAttr.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.finAccountTypeAttr.event.FinAccountTypeAttrAdded;
import com.skytala.eCommerce.domain.accounting.relations.finAccountTypeAttr.mapper.FinAccountTypeAttrMapper;
import com.skytala.eCommerce.domain.accounting.relations.finAccountTypeAttr.model.FinAccountTypeAttr;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddFinAccountTypeAttr extends Command {

private FinAccountTypeAttr elementToBeAdded;
public AddFinAccountTypeAttr(FinAccountTypeAttr elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

FinAccountTypeAttr addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("FinAccountTypeAttr", elementToBeAdded.mapAttributeField());
addedElement = FinAccountTypeAttrMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new FinAccountTypeAttrAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
