
package com.skytala.eCommerce.domain.product.relations.product.query.storeFinActSetting;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.product.event.storeFinActSetting.ProductStoreFinActSettingFound;
import com.skytala.eCommerce.domain.product.relations.product.mapper.storeFinActSetting.ProductStoreFinActSettingMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.storeFinActSetting.ProductStoreFinActSetting;


public class FindAllProductStoreFinActSettings extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductStoreFinActSetting> returnVal = new ArrayList<ProductStoreFinActSetting>();
try{
List<GenericValue> results = delegator.findAll("ProductStoreFinActSetting", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProductStoreFinActSettingMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProductStoreFinActSettingFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
