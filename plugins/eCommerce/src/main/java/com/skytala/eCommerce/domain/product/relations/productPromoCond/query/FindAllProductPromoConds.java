
package com.skytala.eCommerce.domain.product.relations.productPromoCond.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.productPromoCond.event.ProductPromoCondFound;
import com.skytala.eCommerce.domain.product.relations.productPromoCond.mapper.ProductPromoCondMapper;
import com.skytala.eCommerce.domain.product.relations.productPromoCond.model.ProductPromoCond;


public class FindAllProductPromoConds extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductPromoCond> returnVal = new ArrayList<ProductPromoCond>();
try{
List<GenericValue> results = delegator.findAll("ProductPromoCond", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProductPromoCondMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProductPromoCondFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
