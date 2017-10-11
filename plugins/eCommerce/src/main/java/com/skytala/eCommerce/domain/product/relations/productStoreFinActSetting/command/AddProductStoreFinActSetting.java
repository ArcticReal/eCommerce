package com.skytala.eCommerce.domain.product.relations.productStoreFinActSetting.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.productStoreFinActSetting.event.ProductStoreFinActSettingAdded;
import com.skytala.eCommerce.domain.product.relations.productStoreFinActSetting.mapper.ProductStoreFinActSettingMapper;
import com.skytala.eCommerce.domain.product.relations.productStoreFinActSetting.model.ProductStoreFinActSetting;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProductStoreFinActSetting extends Command {

private ProductStoreFinActSetting elementToBeAdded;
public AddProductStoreFinActSetting(ProductStoreFinActSetting elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProductStoreFinActSetting addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("ProductStoreFinActSetting", elementToBeAdded.mapAttributeField());
addedElement = ProductStoreFinActSettingMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProductStoreFinActSettingAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
