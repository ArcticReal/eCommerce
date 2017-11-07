package com.skytala.eCommerce.domain.login.relations.tarpittedLoginView.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.login.relations.tarpittedLoginView.event.TarpittedLoginViewAdded;
import com.skytala.eCommerce.domain.login.relations.tarpittedLoginView.mapper.TarpittedLoginViewMapper;
import com.skytala.eCommerce.domain.login.relations.tarpittedLoginView.model.TarpittedLoginView;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddTarpittedLoginView extends Command {

private TarpittedLoginView elementToBeAdded;
public AddTarpittedLoginView(TarpittedLoginView elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

TarpittedLoginView addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("TarpittedLoginView", elementToBeAdded.mapAttributeField());
addedElement = TarpittedLoginViewMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new TarpittedLoginViewAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
