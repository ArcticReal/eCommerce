package com.skytala.eCommerce.domain.characterSet.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.characterSet.event.CharacterSetUpdated;
import com.skytala.eCommerce.domain.characterSet.model.CharacterSet;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateCharacterSet extends Command {

private CharacterSet elementToBeUpdated;

public UpdateCharacterSet(CharacterSet elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public CharacterSet getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(CharacterSet elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("CharacterSet", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(CharacterSet.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(CharacterSet.class);
}
success = false;
}
Event resultingEvent = new CharacterSetUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
