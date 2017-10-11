package com.skytala.eCommerce.domain.product.relations.productCategoryAttribute.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.productCategoryAttribute.event.ProductCategoryAttributeUpdated;
import com.skytala.eCommerce.domain.product.relations.productCategoryAttribute.model.ProductCategoryAttribute;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateProductCategoryAttribute extends Command {

private ProductCategoryAttribute elementToBeUpdated;

public UpdateProductCategoryAttribute(ProductCategoryAttribute elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ProductCategoryAttribute getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ProductCategoryAttribute elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ProductCategoryAttribute", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ProductCategoryAttribute.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ProductCategoryAttribute.class);
}
success = false;
}
Event resultingEvent = new ProductCategoryAttributeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
