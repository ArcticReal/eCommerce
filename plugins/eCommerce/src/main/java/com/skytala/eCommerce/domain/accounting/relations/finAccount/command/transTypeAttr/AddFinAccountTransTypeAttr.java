package com.skytala.eCommerce.domain.accounting.relations.finAccount.command.transTypeAttr;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.event.transTypeAttr.FinAccountTransTypeAttrAdded;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.mapper.transTypeAttr.FinAccountTransTypeAttrMapper;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.model.transTypeAttr.FinAccountTransTypeAttr;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddFinAccountTransTypeAttr extends Command {

private FinAccountTransTypeAttr elementToBeAdded;
public AddFinAccountTransTypeAttr(FinAccountTransTypeAttr elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

FinAccountTransTypeAttr addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("FinAccountTransTypeAttr", elementToBeAdded.mapAttributeField());
addedElement = FinAccountTransTypeAttrMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new FinAccountTransTypeAttrAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
