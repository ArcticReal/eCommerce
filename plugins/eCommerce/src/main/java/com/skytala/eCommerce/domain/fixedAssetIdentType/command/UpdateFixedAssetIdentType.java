package com.skytala.eCommerce.domain.fixedAssetIdentType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.fixedAssetIdentType.event.FixedAssetIdentTypeUpdated;
import com.skytala.eCommerce.domain.fixedAssetIdentType.model.FixedAssetIdentType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateFixedAssetIdentType extends Command {

private FixedAssetIdentType elementToBeUpdated;

public UpdateFixedAssetIdentType(FixedAssetIdentType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public FixedAssetIdentType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(FixedAssetIdentType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("FixedAssetIdentType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(FixedAssetIdentType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(FixedAssetIdentType.class);
}
success = false;
}
Event resultingEvent = new FixedAssetIdentTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
