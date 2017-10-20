package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.command.attribute;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.attribute.FixedAssetAttributeUpdated;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.attribute.FixedAssetAttribute;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateFixedAssetAttribute extends Command {

private FixedAssetAttribute elementToBeUpdated;

public UpdateFixedAssetAttribute(FixedAssetAttribute elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public FixedAssetAttribute getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(FixedAssetAttribute elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("FixedAssetAttribute", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(FixedAssetAttribute.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(FixedAssetAttribute.class);
}
success = false;
}
Event resultingEvent = new FixedAssetAttributeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
