package com.skytala.eCommerce.domain.product.relations.productFeatureCategoryAppl.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.productFeatureCategoryAppl.event.ProductFeatureCategoryApplUpdated;
import com.skytala.eCommerce.domain.product.relations.productFeatureCategoryAppl.model.ProductFeatureCategoryAppl;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateProductFeatureCategoryAppl extends Command {

private ProductFeatureCategoryAppl elementToBeUpdated;

public UpdateProductFeatureCategoryAppl(ProductFeatureCategoryAppl elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ProductFeatureCategoryAppl getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ProductFeatureCategoryAppl elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ProductFeatureCategoryAppl", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ProductFeatureCategoryAppl.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ProductFeatureCategoryAppl.class);
}
success = false;
}
Event resultingEvent = new ProductFeatureCategoryApplUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
