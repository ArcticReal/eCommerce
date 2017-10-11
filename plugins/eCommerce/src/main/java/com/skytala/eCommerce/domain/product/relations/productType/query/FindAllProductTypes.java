
package com.skytala.eCommerce.domain.product.relations.productType.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.productType.event.ProductTypeFound;
import com.skytala.eCommerce.domain.product.relations.productType.mapper.ProductTypeMapper;
import com.skytala.eCommerce.domain.product.relations.productType.model.ProductType;


public class FindAllProductTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductType> returnVal = new ArrayList<ProductType>();
try{
List<GenericValue> results = delegator.findAll("ProductType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProductTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProductTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
