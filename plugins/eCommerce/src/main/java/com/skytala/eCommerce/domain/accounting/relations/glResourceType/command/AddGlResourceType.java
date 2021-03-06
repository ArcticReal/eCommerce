package com.skytala.eCommerce.domain.accounting.relations.glResourceType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.glResourceType.event.GlResourceTypeAdded;
import com.skytala.eCommerce.domain.accounting.relations.glResourceType.mapper.GlResourceTypeMapper;
import com.skytala.eCommerce.domain.accounting.relations.glResourceType.model.GlResourceType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddGlResourceType extends Command {

private GlResourceType elementToBeAdded;
public AddGlResourceType(GlResourceType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

GlResourceType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setGlResourceTypeId(delegator.getNextSeqId("GlResourceType"));
GenericValue newValue = delegator.makeValue("GlResourceType", elementToBeAdded.mapAttributeField());
addedElement = GlResourceTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new GlResourceTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
