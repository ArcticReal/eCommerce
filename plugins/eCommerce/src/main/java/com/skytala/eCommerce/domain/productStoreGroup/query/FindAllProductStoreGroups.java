
package com.skytala.eCommerce.domain.productStoreGroup.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
	import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.productStoreGroup.event.ProductStoreGroupFound;
import com.skytala.eCommerce.domain.productStoreGroup.mapper.ProductStoreGroupMapper;
import com.skytala.eCommerce.domain.productStoreGroup.model.ProductStoreGroup;


public class FindAllProductStoreGroups extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductStoreGroup> returnVal = new ArrayList<ProductStoreGroup>();
try{
List<GenericValue> results = delegator.findAll("ProductStoreGroup", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProductStoreGroupMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProductStoreGroupFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
