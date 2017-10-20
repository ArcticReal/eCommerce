package com.skytala.eCommerce.domain.manufacturing.relations.mrpEvent.command.type;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.manufacturing.relations.mrpEvent.event.type.MrpEventTypeAdded;
import com.skytala.eCommerce.domain.manufacturing.relations.mrpEvent.mapper.type.MrpEventTypeMapper;
import com.skytala.eCommerce.domain.manufacturing.relations.mrpEvent.model.type.MrpEventType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddMrpEventType extends Command {

private MrpEventType elementToBeAdded;
public AddMrpEventType(MrpEventType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

MrpEventType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setMrpEventTypeId(delegator.getNextSeqId("MrpEventType"));
GenericValue newValue = delegator.makeValue("MrpEventType", elementToBeAdded.mapAttributeField());
addedElement = MrpEventTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new MrpEventTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
