
package com.skytala.eCommerce.domain.product.relations.product.query.storeKeywordOvrd;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.product.event.storeKeywordOvrd.ProductStoreKeywordOvrdFound;
import com.skytala.eCommerce.domain.product.relations.product.mapper.storeKeywordOvrd.ProductStoreKeywordOvrdMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.storeKeywordOvrd.ProductStoreKeywordOvrd;


public class FindAllProductStoreKeywordOvrds extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductStoreKeywordOvrd> returnVal = new ArrayList<ProductStoreKeywordOvrd>();
try{
List<GenericValue> results = delegator.findAll("ProductStoreKeywordOvrd", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProductStoreKeywordOvrdMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProductStoreKeywordOvrdFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
