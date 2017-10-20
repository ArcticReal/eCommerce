package com.skytala.eCommerce.domain.product.relations.product.command.paymentMethodType;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.product.event.paymentMethodType.ProductPaymentMethodTypeUpdated;
import com.skytala.eCommerce.domain.product.relations.product.model.paymentMethodType.ProductPaymentMethodType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateProductPaymentMethodType extends Command {

private ProductPaymentMethodType elementToBeUpdated;

public UpdateProductPaymentMethodType(ProductPaymentMethodType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ProductPaymentMethodType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ProductPaymentMethodType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ProductPaymentMethodType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ProductPaymentMethodType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ProductPaymentMethodType.class);
}
success = false;
}
Event resultingEvent = new ProductPaymentMethodTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
