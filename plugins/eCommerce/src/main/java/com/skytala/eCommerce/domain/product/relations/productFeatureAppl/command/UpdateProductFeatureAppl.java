package com.skytala.eCommerce.domain.product.relations.productFeatureAppl.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.productFeatureAppl.event.ProductFeatureApplUpdated;
import com.skytala.eCommerce.domain.product.relations.productFeatureAppl.model.ProductFeatureAppl;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateProductFeatureAppl extends Command {

private ProductFeatureAppl elementToBeUpdated;

public UpdateProductFeatureAppl(ProductFeatureAppl elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ProductFeatureAppl getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ProductFeatureAppl elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ProductFeatureAppl", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ProductFeatureAppl.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ProductFeatureAppl.class);
}
success = false;
}
Event resultingEvent = new ProductFeatureApplUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
