package com.skytala.eCommerce.domain.accounting.relations.glFiscalType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.glFiscalType.event.GlFiscalTypeAdded;
import com.skytala.eCommerce.domain.accounting.relations.glFiscalType.mapper.GlFiscalTypeMapper;
import com.skytala.eCommerce.domain.accounting.relations.glFiscalType.model.GlFiscalType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddGlFiscalType extends Command {

private GlFiscalType elementToBeAdded;
public AddGlFiscalType(GlFiscalType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

GlFiscalType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setGlFiscalTypeId(delegator.getNextSeqId("GlFiscalType"));
GenericValue newValue = delegator.makeValue("GlFiscalType", elementToBeAdded.mapAttributeField());
addedElement = GlFiscalTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new GlFiscalTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
