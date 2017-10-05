package com.skytala.eCommerce.domain.productFeatureIactnType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.productFeatureIactnType.event.ProductFeatureIactnTypeUpdated;
import com.skytala.eCommerce.domain.productFeatureIactnType.model.ProductFeatureIactnType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateProductFeatureIactnType extends Command {

private ProductFeatureIactnType elementToBeUpdated;

public UpdateProductFeatureIactnType(ProductFeatureIactnType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ProductFeatureIactnType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ProductFeatureIactnType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ProductFeatureIactnType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ProductFeatureIactnType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ProductFeatureIactnType.class);
}
success = false;
}
Event resultingEvent = new ProductFeatureIactnTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
