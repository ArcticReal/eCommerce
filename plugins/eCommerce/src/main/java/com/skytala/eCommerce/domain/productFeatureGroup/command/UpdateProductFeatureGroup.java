package com.skytala.eCommerce.domain.productFeatureGroup.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.productFeatureGroup.event.ProductFeatureGroupUpdated;
import com.skytala.eCommerce.domain.productFeatureGroup.model.ProductFeatureGroup;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateProductFeatureGroup extends Command {

private ProductFeatureGroup elementToBeUpdated;

public UpdateProductFeatureGroup(ProductFeatureGroup elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ProductFeatureGroup getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ProductFeatureGroup elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ProductFeatureGroup", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ProductFeatureGroup.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ProductFeatureGroup.class);
}
success = false;
}
Event resultingEvent = new ProductFeatureGroupUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
