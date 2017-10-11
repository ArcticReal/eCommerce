package com.skytala.eCommerce.domain.product.relations.productSearchConstraint.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.productSearchConstraint.event.ProductSearchConstraintUpdated;
import com.skytala.eCommerce.domain.product.relations.productSearchConstraint.model.ProductSearchConstraint;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateProductSearchConstraint extends Command {

private ProductSearchConstraint elementToBeUpdated;

public UpdateProductSearchConstraint(ProductSearchConstraint elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ProductSearchConstraint getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ProductSearchConstraint elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ProductSearchConstraint", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ProductSearchConstraint.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ProductSearchConstraint.class);
}
success = false;
}
Event resultingEvent = new ProductSearchConstraintUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
