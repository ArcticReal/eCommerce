package com.skytala.eCommerce.domain.humanres.relations.skillType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.humanres.relations.skillType.event.SkillTypeUpdated;
import com.skytala.eCommerce.domain.humanres.relations.skillType.model.SkillType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateSkillType extends Command {

private SkillType elementToBeUpdated;

public UpdateSkillType(SkillType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public SkillType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(SkillType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("SkillType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(SkillType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(SkillType.class);
}
success = false;
}
Event resultingEvent = new SkillTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
