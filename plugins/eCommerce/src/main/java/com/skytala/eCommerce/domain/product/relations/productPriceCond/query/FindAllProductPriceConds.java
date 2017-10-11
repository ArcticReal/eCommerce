
package com.skytala.eCommerce.domain.product.relations.productPriceCond.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.productPriceCond.event.ProductPriceCondFound;
import com.skytala.eCommerce.domain.product.relations.productPriceCond.mapper.ProductPriceCondMapper;
import com.skytala.eCommerce.domain.product.relations.productPriceCond.model.ProductPriceCond;


public class FindAllProductPriceConds extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductPriceCond> returnVal = new ArrayList<ProductPriceCond>();
try{
List<GenericValue> results = delegator.findAll("ProductPriceCond", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProductPriceCondMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProductPriceCondFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
