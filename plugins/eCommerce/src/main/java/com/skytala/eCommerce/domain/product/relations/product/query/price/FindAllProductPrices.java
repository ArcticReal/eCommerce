
package com.skytala.eCommerce.domain.product.relations.product.query.price;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.product.event.price.ProductPriceFound;
import com.skytala.eCommerce.domain.product.relations.product.mapper.price.ProductPriceMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.price.ProductPrice;


public class FindAllProductPrices extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductPrice> returnVal = new ArrayList<ProductPrice>();
try{
List<GenericValue> results = delegator.findAll("ProductPrice", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProductPriceMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProductPriceFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
