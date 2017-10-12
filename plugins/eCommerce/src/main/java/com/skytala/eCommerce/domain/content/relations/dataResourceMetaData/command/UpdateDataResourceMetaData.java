package com.skytala.eCommerce.domain.content.relations.dataResourceMetaData.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.content.relations.dataResourceMetaData.event.DataResourceMetaDataUpdated;
import com.skytala.eCommerce.domain.content.relations.dataResourceMetaData.model.DataResourceMetaData;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateDataResourceMetaData extends Command {

private DataResourceMetaData elementToBeUpdated;

public UpdateDataResourceMetaData(DataResourceMetaData elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public DataResourceMetaData getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(DataResourceMetaData elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("DataResourceMetaData", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(DataResourceMetaData.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(DataResourceMetaData.class);
}
success = false;
}
Event resultingEvent = new DataResourceMetaDataUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
