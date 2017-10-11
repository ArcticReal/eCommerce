
package com.skytala.eCommerce.domain.product.relations.productAttribute.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.productAttribute.event.ProductAttributeFound;
import com.skytala.eCommerce.domain.product.relations.productAttribute.mapper.ProductAttributeMapper;
import com.skytala.eCommerce.domain.product.relations.productAttribute.model.ProductAttribute;


public class FindAllProductAttributes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductAttribute> returnVal = new ArrayList<ProductAttribute>();
try{
List<GenericValue> results = delegator.findAll("ProductAttribute", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProductAttributeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProductAttributeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
