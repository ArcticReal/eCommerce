
package com.skytala.eCommerce.domain.product.relations.productStoreGroupType.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.productStoreGroupType.event.ProductStoreGroupTypeFound;
import com.skytala.eCommerce.domain.product.relations.productStoreGroupType.mapper.ProductStoreGroupTypeMapper;
import com.skytala.eCommerce.domain.product.relations.productStoreGroupType.model.ProductStoreGroupType;


public class FindAllProductStoreGroupTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductStoreGroupType> returnVal = new ArrayList<ProductStoreGroupType>();
try{
List<GenericValue> results = delegator.findAll("ProductStoreGroupType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProductStoreGroupTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProductStoreGroupTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
