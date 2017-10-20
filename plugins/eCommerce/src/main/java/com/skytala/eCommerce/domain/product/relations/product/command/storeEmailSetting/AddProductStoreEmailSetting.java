package com.skytala.eCommerce.domain.product.relations.product.command.storeEmailSetting;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.product.event.storeEmailSetting.ProductStoreEmailSettingAdded;
import com.skytala.eCommerce.domain.product.relations.product.mapper.storeEmailSetting.ProductStoreEmailSettingMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.storeEmailSetting.ProductStoreEmailSetting;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProductStoreEmailSetting extends Command {

private ProductStoreEmailSetting elementToBeAdded;
public AddProductStoreEmailSetting(ProductStoreEmailSetting elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProductStoreEmailSetting addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("ProductStoreEmailSetting", elementToBeAdded.mapAttributeField());
addedElement = ProductStoreEmailSettingMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProductStoreEmailSettingAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
