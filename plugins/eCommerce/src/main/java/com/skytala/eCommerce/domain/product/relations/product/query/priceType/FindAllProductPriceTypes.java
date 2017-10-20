
package com.skytala.eCommerce.domain.product.relations.product.query.priceType;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.product.event.priceType.ProductPriceTypeFound;
import com.skytala.eCommerce.domain.product.relations.product.mapper.priceType.ProductPriceTypeMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.priceType.ProductPriceType;


public class FindAllProductPriceTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductPriceType> returnVal = new ArrayList<ProductPriceType>();
try{
List<GenericValue> results = delegator.findAll("ProductPriceType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProductPriceTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProductPriceTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
