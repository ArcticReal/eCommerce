package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.command.depMethod;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.depMethod.FixedAssetDepMethodUpdated;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.depMethod.FixedAssetDepMethod;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateFixedAssetDepMethod extends Command {

private FixedAssetDepMethod elementToBeUpdated;

public UpdateFixedAssetDepMethod(FixedAssetDepMethod elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public FixedAssetDepMethod getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(FixedAssetDepMethod elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("FixedAssetDepMethod", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(FixedAssetDepMethod.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(FixedAssetDepMethod.class);
}
success = false;
}
Event resultingEvent = new FixedAssetDepMethodUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
