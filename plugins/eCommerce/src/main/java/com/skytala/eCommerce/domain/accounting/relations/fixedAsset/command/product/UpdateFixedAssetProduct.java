package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.command.product;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.product.FixedAssetProductUpdated;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.product.FixedAssetProduct;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateFixedAssetProduct extends Command {

private FixedAssetProduct elementToBeUpdated;

public UpdateFixedAssetProduct(FixedAssetProduct elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public FixedAssetProduct getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(FixedAssetProduct elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("FixedAssetProduct", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(FixedAssetProduct.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(FixedAssetProduct.class);
}
success = false;
}
Event resultingEvent = new FixedAssetProductUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
