package com.skytala.eCommerce.domain.accounting.relations.fixedAssetTypeAttr.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetTypeAttr.event.FixedAssetTypeAttrUpdated;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetTypeAttr.model.FixedAssetTypeAttr;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateFixedAssetTypeAttr extends Command {

private FixedAssetTypeAttr elementToBeUpdated;

public UpdateFixedAssetTypeAttr(FixedAssetTypeAttr elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public FixedAssetTypeAttr getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(FixedAssetTypeAttr elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("FixedAssetTypeAttr", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(FixedAssetTypeAttr.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(FixedAssetTypeAttr.class);
}
success = false;
}
Event resultingEvent = new FixedAssetTypeAttrUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
