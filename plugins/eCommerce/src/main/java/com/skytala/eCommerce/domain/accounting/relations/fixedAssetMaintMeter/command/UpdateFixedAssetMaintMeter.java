package com.skytala.eCommerce.domain.accounting.relations.fixedAssetMaintMeter.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetMaintMeter.event.FixedAssetMaintMeterUpdated;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetMaintMeter.model.FixedAssetMaintMeter;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateFixedAssetMaintMeter extends Command {

private FixedAssetMaintMeter elementToBeUpdated;

public UpdateFixedAssetMaintMeter(FixedAssetMaintMeter elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public FixedAssetMaintMeter getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(FixedAssetMaintMeter elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("FixedAssetMaintMeter", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(FixedAssetMaintMeter.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(FixedAssetMaintMeter.class);
}
success = false;
}
Event resultingEvent = new FixedAssetMaintMeterUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
