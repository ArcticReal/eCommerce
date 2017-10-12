package com.skytala.eCommerce.domain.content.relations.dataCategory.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.content.relations.dataCategory.event.DataCategoryUpdated;
import com.skytala.eCommerce.domain.content.relations.dataCategory.model.DataCategory;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateDataCategory extends Command {

private DataCategory elementToBeUpdated;

public UpdateDataCategory(DataCategory elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public DataCategory getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(DataCategory elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("DataCategory", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(DataCategory.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(DataCategory.class);
}
success = false;
}
Event resultingEvent = new DataCategoryUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
