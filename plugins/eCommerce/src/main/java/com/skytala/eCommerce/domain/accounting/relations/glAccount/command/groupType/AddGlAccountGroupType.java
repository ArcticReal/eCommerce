package com.skytala.eCommerce.domain.accounting.relations.glAccount.command.groupType;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.groupType.GlAccountGroupTypeAdded;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.mapper.groupType.GlAccountGroupTypeMapper;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.groupType.GlAccountGroupType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddGlAccountGroupType extends Command {

private GlAccountGroupType elementToBeAdded;
public AddGlAccountGroupType(GlAccountGroupType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

GlAccountGroupType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setGlAccountGroupTypeId(delegator.getNextSeqId("GlAccountGroupType"));
GenericValue newValue = delegator.makeValue("GlAccountGroupType", elementToBeAdded.mapAttributeField());
addedElement = GlAccountGroupTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new GlAccountGroupTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
