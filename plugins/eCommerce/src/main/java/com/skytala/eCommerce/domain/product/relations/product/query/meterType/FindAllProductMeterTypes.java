
package com.skytala.eCommerce.domain.product.relations.product.query.meterType;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.product.event.meterType.ProductMeterTypeFound;
import com.skytala.eCommerce.domain.product.relations.product.mapper.meterType.ProductMeterTypeMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.meterType.ProductMeterType;


public class FindAllProductMeterTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductMeterType> returnVal = new ArrayList<ProductMeterType>();
try{
List<GenericValue> results = delegator.findAll("ProductMeterType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProductMeterTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProductMeterTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
