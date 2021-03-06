package com.skytala.eCommerce.domain.accounting.relations.glAccount.command.history;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.history.GlAccountHistoryAdded;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.mapper.history.GlAccountHistoryMapper;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.history.GlAccountHistory;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddGlAccountHistory extends Command {

private GlAccountHistory elementToBeAdded;
public AddGlAccountHistory(GlAccountHistory elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

GlAccountHistory addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("GlAccountHistory", elementToBeAdded.mapAttributeField());
addedElement = GlAccountHistoryMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new GlAccountHistoryAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
