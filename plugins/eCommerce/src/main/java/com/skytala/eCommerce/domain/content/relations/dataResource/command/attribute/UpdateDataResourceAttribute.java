package com.skytala.eCommerce.domain.content.relations.dataResource.command.attribute;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.content.relations.dataResource.event.attribute.DataResourceAttributeUpdated;
import com.skytala.eCommerce.domain.content.relations.dataResource.model.attribute.DataResourceAttribute;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateDataResourceAttribute extends Command {

private DataResourceAttribute elementToBeUpdated;

public UpdateDataResourceAttribute(DataResourceAttribute elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public DataResourceAttribute getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(DataResourceAttribute elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("DataResourceAttribute", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(DataResourceAttribute.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(DataResourceAttribute.class);
}
success = false;
}
Event resultingEvent = new DataResourceAttributeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
