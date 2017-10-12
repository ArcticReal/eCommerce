package com.skytala.eCommerce.domain.manufacturing.relations.mrpEvent.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.manufacturing.relations.mrpEvent.event.MrpEventAdded;
import com.skytala.eCommerce.domain.manufacturing.relations.mrpEvent.mapper.MrpEventMapper;
import com.skytala.eCommerce.domain.manufacturing.relations.mrpEvent.model.MrpEvent;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddMrpEvent extends Command {

private MrpEvent elementToBeAdded;
public AddMrpEvent(MrpEvent elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

MrpEvent addedElement = null;
boolean success = false;
try {
elementToBeAdded.setMrpId(delegator.getNextSeqId("MrpEvent"));
GenericValue newValue = delegator.makeValue("MrpEvent", elementToBeAdded.mapAttributeField());
addedElement = MrpEventMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new MrpEventAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
