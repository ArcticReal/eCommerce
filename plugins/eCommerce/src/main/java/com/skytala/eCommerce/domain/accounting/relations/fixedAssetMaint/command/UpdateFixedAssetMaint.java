package com.skytala.eCommerce.domain.accounting.relations.fixedAssetMaint.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetMaint.event.FixedAssetMaintUpdated;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetMaint.model.FixedAssetMaint;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateFixedAssetMaint extends Command {

private FixedAssetMaint elementToBeUpdated;

public UpdateFixedAssetMaint(FixedAssetMaint elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public FixedAssetMaint getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(FixedAssetMaint elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("FixedAssetMaint", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(FixedAssetMaint.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(FixedAssetMaint.class);
}
success = false;
}
Event resultingEvent = new FixedAssetMaintUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
