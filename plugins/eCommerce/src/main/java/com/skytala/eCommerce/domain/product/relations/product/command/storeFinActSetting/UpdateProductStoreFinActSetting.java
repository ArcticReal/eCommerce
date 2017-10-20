package com.skytala.eCommerce.domain.product.relations.product.command.storeFinActSetting;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.product.event.storeFinActSetting.ProductStoreFinActSettingUpdated;
import com.skytala.eCommerce.domain.product.relations.product.model.storeFinActSetting.ProductStoreFinActSetting;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateProductStoreFinActSetting extends Command {

private ProductStoreFinActSetting elementToBeUpdated;

public UpdateProductStoreFinActSetting(ProductStoreFinActSetting elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ProductStoreFinActSetting getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ProductStoreFinActSetting elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ProductStoreFinActSetting", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ProductStoreFinActSetting.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ProductStoreFinActSetting.class);
}
success = false;
}
Event resultingEvent = new ProductStoreFinActSettingUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
