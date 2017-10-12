package com.skytala.eCommerce.domain.accounting.relations.glXbrlClass.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.glXbrlClass.event.GlXbrlClassAdded;
import com.skytala.eCommerce.domain.accounting.relations.glXbrlClass.mapper.GlXbrlClassMapper;
import com.skytala.eCommerce.domain.accounting.relations.glXbrlClass.model.GlXbrlClass;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddGlXbrlClass extends Command {

private GlXbrlClass elementToBeAdded;
public AddGlXbrlClass(GlXbrlClass elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

GlXbrlClass addedElement = null;
boolean success = false;
try {
elementToBeAdded.setGlXbrlClassId(delegator.getNextSeqId("GlXbrlClass"));
GenericValue newValue = delegator.makeValue("GlXbrlClass", elementToBeAdded.mapAttributeField());
addedElement = GlXbrlClassMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new GlXbrlClassAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
