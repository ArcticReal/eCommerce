package com.skytala.eCommerce.domain.product.relations.product.command.featureIactn;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.product.event.featureIactn.ProductFeatureIactnUpdated;
import com.skytala.eCommerce.domain.product.relations.product.model.featureIactn.ProductFeatureIactn;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateProductFeatureIactn extends Command {

private ProductFeatureIactn elementToBeUpdated;

public UpdateProductFeatureIactn(ProductFeatureIactn elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ProductFeatureIactn getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ProductFeatureIactn elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ProductFeatureIactn", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ProductFeatureIactn.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ProductFeatureIactn.class);
}
success = false;
}
Event resultingEvent = new ProductFeatureIactnUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
