package com.skytala.eCommerce.domain.accounting.relations.glAccountClass.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.glAccountClass.event.GlAccountClassAdded;
import com.skytala.eCommerce.domain.accounting.relations.glAccountClass.mapper.GlAccountClassMapper;
import com.skytala.eCommerce.domain.accounting.relations.glAccountClass.model.GlAccountClass;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddGlAccountClass extends Command {

private GlAccountClass elementToBeAdded;
public AddGlAccountClass(GlAccountClass elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

GlAccountClass addedElement = null;
boolean success = false;
try {
elementToBeAdded.setGlAccountClassId(delegator.getNextSeqId("GlAccountClass"));
GenericValue newValue = delegator.makeValue("GlAccountClass", elementToBeAdded.mapAttributeField());
addedElement = GlAccountClassMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new GlAccountClassAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
