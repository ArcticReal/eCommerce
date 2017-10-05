package com.skytala.eCommerce.domain.skillType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.skillType.event.SkillTypeAdded;
import com.skytala.eCommerce.domain.skillType.mapper.SkillTypeMapper;
import com.skytala.eCommerce.domain.skillType.model.SkillType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddSkillType extends Command {

private SkillType elementToBeAdded;
public AddSkillType(SkillType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

SkillType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setSkillTypeId(delegator.getNextSeqId("SkillType"));
GenericValue newValue = delegator.makeValue("SkillType", elementToBeAdded.mapAttributeField());
addedElement = SkillTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new SkillTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
