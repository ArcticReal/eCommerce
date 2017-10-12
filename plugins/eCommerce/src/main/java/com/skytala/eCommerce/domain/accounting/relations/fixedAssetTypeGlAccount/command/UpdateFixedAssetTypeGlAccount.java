package com.skytala.eCommerce.domain.accounting.relations.fixedAssetTypeGlAccount.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetTypeGlAccount.event.FixedAssetTypeGlAccountUpdated;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetTypeGlAccount.model.FixedAssetTypeGlAccount;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateFixedAssetTypeGlAccount extends Command {

private FixedAssetTypeGlAccount elementToBeUpdated;

public UpdateFixedAssetTypeGlAccount(FixedAssetTypeGlAccount elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public FixedAssetTypeGlAccount getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(FixedAssetTypeGlAccount elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("FixedAssetTypeGlAccount", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(FixedAssetTypeGlAccount.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(FixedAssetTypeGlAccount.class);
}
success = false;
}
Event resultingEvent = new FixedAssetTypeGlAccountUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
