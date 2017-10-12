package com.skytala.eCommerce.domain.accounting.relations.fixedAssetProductType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetProductType.event.FixedAssetProductTypeUpdated;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetProductType.model.FixedAssetProductType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateFixedAssetProductType extends Command {

private FixedAssetProductType elementToBeUpdated;

public UpdateFixedAssetProductType(FixedAssetProductType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public FixedAssetProductType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(FixedAssetProductType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("FixedAssetProductType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(FixedAssetProductType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(FixedAssetProductType.class);
}
success = false;
}
Event resultingEvent = new FixedAssetProductTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
