package com.skytala.eCommerce.domain.product.relations.product.command.storePaymentSetting;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.product.event.storePaymentSetting.ProductStorePaymentSettingUpdated;
import com.skytala.eCommerce.domain.product.relations.product.model.storePaymentSetting.ProductStorePaymentSetting;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateProductStorePaymentSetting extends Command {

private ProductStorePaymentSetting elementToBeUpdated;

public UpdateProductStorePaymentSetting(ProductStorePaymentSetting elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ProductStorePaymentSetting getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ProductStorePaymentSetting elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ProductStorePaymentSetting", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ProductStorePaymentSetting.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ProductStorePaymentSetting.class);
}
success = false;
}
Event resultingEvent = new ProductStorePaymentSettingUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
