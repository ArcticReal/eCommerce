package com.skytala.eCommerce.domain.product.relations.productCostComponentCalc.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.productCostComponentCalc.event.ProductCostComponentCalcUpdated;
import com.skytala.eCommerce.domain.product.relations.productCostComponentCalc.model.ProductCostComponentCalc;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateProductCostComponentCalc extends Command {

private ProductCostComponentCalc elementToBeUpdated;

public UpdateProductCostComponentCalc(ProductCostComponentCalc elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ProductCostComponentCalc getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ProductCostComponentCalc elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ProductCostComponentCalc", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ProductCostComponentCalc.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ProductCostComponentCalc.class);
}
success = false;
}
Event resultingEvent = new ProductCostComponentCalcUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
