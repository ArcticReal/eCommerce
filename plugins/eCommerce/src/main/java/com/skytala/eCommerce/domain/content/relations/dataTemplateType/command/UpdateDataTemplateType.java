package com.skytala.eCommerce.domain.content.relations.dataTemplateType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.content.relations.dataTemplateType.event.DataTemplateTypeUpdated;
import com.skytala.eCommerce.domain.content.relations.dataTemplateType.model.DataTemplateType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateDataTemplateType extends Command {

private DataTemplateType elementToBeUpdated;

public UpdateDataTemplateType(DataTemplateType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public DataTemplateType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(DataTemplateType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("DataTemplateType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(DataTemplateType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(DataTemplateType.class);
}
success = false;
}
Event resultingEvent = new DataTemplateTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
