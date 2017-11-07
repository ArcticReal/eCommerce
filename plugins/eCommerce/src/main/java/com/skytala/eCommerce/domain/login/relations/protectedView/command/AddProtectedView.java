package com.skytala.eCommerce.domain.login.relations.protectedView.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.login.relations.protectedView.event.ProtectedViewAdded;
import com.skytala.eCommerce.domain.login.relations.protectedView.mapper.ProtectedViewMapper;
import com.skytala.eCommerce.domain.login.relations.protectedView.model.ProtectedView;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProtectedView extends Command {

private ProtectedView elementToBeAdded;
public AddProtectedView(ProtectedView elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProtectedView addedElement = null;
boolean success = false;
try {
elementToBeAdded.setViewNameId(delegator.getNextSeqId("ProtectedView"));
GenericValue newValue = delegator.makeValue("ProtectedView", elementToBeAdded.mapAttributeField());
addedElement = ProtectedViewMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProtectedViewAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
