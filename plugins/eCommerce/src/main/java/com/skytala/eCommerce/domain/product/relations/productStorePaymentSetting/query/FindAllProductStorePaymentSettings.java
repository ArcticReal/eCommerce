
package com.skytala.eCommerce.domain.product.relations.productStorePaymentSetting.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.productStorePaymentSetting.event.ProductStorePaymentSettingFound;
import com.skytala.eCommerce.domain.product.relations.productStorePaymentSetting.mapper.ProductStorePaymentSettingMapper;
import com.skytala.eCommerce.domain.product.relations.productStorePaymentSetting.model.ProductStorePaymentSetting;


public class FindAllProductStorePaymentSettings extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductStorePaymentSetting> returnVal = new ArrayList<ProductStorePaymentSetting>();
try{
List<GenericValue> results = delegator.findAll("ProductStorePaymentSetting", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProductStorePaymentSettingMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProductStorePaymentSettingFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
