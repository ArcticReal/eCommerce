package com.skytala.eCommerce.domain.accounting.relations.glAccountCategoryType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.glAccountCategoryType.event.GlAccountCategoryTypeAdded;
import com.skytala.eCommerce.domain.accounting.relations.glAccountCategoryType.mapper.GlAccountCategoryTypeMapper;
import com.skytala.eCommerce.domain.accounting.relations.glAccountCategoryType.model.GlAccountCategoryType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddGlAccountCategoryType extends Command {

private GlAccountCategoryType elementToBeAdded;
public AddGlAccountCategoryType(GlAccountCategoryType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

GlAccountCategoryType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setGlAccountCategoryTypeId(delegator.getNextSeqId("GlAccountCategoryType"));
GenericValue newValue = delegator.makeValue("GlAccountCategoryType", elementToBeAdded.mapAttributeField());
addedElement = GlAccountCategoryTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new GlAccountCategoryTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
