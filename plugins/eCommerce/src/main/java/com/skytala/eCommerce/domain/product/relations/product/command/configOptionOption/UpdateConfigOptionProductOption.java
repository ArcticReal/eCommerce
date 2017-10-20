package com.skytala.eCommerce.domain.product.relations.product.command.configOptionOption;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.product.event.configOptionOption.ConfigOptionProductOptionUpdated;
import com.skytala.eCommerce.domain.product.relations.product.model.configOptionOption.ConfigOptionProductOption;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateConfigOptionProductOption extends Command {

private ConfigOptionProductOption elementToBeUpdated;

public UpdateConfigOptionProductOption(ConfigOptionProductOption elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ConfigOptionProductOption getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ConfigOptionProductOption elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ConfigOptionProductOption", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ConfigOptionProductOption.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ConfigOptionProductOption.class);
}
success = false;
}
Event resultingEvent = new ConfigOptionProductOptionUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
