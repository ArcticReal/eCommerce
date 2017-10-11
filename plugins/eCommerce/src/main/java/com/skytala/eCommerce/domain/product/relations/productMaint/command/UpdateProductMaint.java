package com.skytala.eCommerce.domain.product.relations.productMaint.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.productMaint.event.ProductMaintUpdated;
import com.skytala.eCommerce.domain.product.relations.productMaint.model.ProductMaint;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateProductMaint extends Command {

private ProductMaint elementToBeUpdated;

public UpdateProductMaint(ProductMaint elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ProductMaint getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ProductMaint elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ProductMaint", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ProductMaint.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ProductMaint.class);
}
success = false;
}
Event resultingEvent = new ProductMaintUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
