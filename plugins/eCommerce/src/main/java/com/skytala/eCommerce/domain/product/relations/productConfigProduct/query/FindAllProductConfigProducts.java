
package com.skytala.eCommerce.domain.product.relations.productConfigProduct.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.productConfigProduct.event.ProductConfigProductFound;
import com.skytala.eCommerce.domain.product.relations.productConfigProduct.mapper.ProductConfigProductMapper;
import com.skytala.eCommerce.domain.product.relations.productConfigProduct.model.ProductConfigProduct;


public class FindAllProductConfigProducts extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductConfigProduct> returnVal = new ArrayList<ProductConfigProduct>();
try{
List<GenericValue> results = delegator.findAll("ProductConfigProduct", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProductConfigProductMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProductConfigProductFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
