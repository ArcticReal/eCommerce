
package com.skytala.eCommerce.domain.product.relations.productStoreEmailSetting.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.productStoreEmailSetting.event.ProductStoreEmailSettingFound;
import com.skytala.eCommerce.domain.product.relations.productStoreEmailSetting.mapper.ProductStoreEmailSettingMapper;
import com.skytala.eCommerce.domain.product.relations.productStoreEmailSetting.model.ProductStoreEmailSetting;


public class FindAllProductStoreEmailSettings extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductStoreEmailSetting> returnVal = new ArrayList<ProductStoreEmailSetting>();
try{
List<GenericValue> results = delegator.findAll("ProductStoreEmailSetting", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProductStoreEmailSettingMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProductStoreEmailSettingFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
