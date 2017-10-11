package com.skytala.eCommerce.domain.product.relations.productStorePaymentSetting.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.productStorePaymentSetting.event.ProductStorePaymentSettingAdded;
import com.skytala.eCommerce.domain.product.relations.productStorePaymentSetting.mapper.ProductStorePaymentSettingMapper;
import com.skytala.eCommerce.domain.product.relations.productStorePaymentSetting.model.ProductStorePaymentSetting;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProductStorePaymentSetting extends Command {

private ProductStorePaymentSetting elementToBeAdded;
public AddProductStorePaymentSetting(ProductStorePaymentSetting elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProductStorePaymentSetting addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("ProductStorePaymentSetting", elementToBeAdded.mapAttributeField());
addedElement = ProductStorePaymentSettingMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProductStorePaymentSettingAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
