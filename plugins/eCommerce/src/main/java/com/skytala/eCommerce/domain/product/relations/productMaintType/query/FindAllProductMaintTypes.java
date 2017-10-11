
package com.skytala.eCommerce.domain.product.relations.productMaintType.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.productMaintType.event.ProductMaintTypeFound;
import com.skytala.eCommerce.domain.product.relations.productMaintType.mapper.ProductMaintTypeMapper;
import com.skytala.eCommerce.domain.product.relations.productMaintType.model.ProductMaintType;


public class FindAllProductMaintTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductMaintType> returnVal = new ArrayList<ProductMaintType>();
try{
List<GenericValue> results = delegator.findAll("ProductMaintType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProductMaintTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProductMaintTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
