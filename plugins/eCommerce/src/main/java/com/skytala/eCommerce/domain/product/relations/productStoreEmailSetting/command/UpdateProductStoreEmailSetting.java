package com.skytala.eCommerce.domain.product.relations.productStoreEmailSetting.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.productStoreEmailSetting.event.ProductStoreEmailSettingUpdated;
import com.skytala.eCommerce.domain.product.relations.productStoreEmailSetting.model.ProductStoreEmailSetting;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateProductStoreEmailSetting extends Command {

private ProductStoreEmailSetting elementToBeUpdated;

public UpdateProductStoreEmailSetting(ProductStoreEmailSetting elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ProductStoreEmailSetting getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ProductStoreEmailSetting elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ProductStoreEmailSetting", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ProductStoreEmailSetting.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ProductStoreEmailSetting.class);
}
success = false;
}
Event resultingEvent = new ProductStoreEmailSettingUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
