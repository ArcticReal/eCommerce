
package com.skytala.eCommerce.domain.product.relations.productMeter.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.productMeter.event.ProductMeterFound;
import com.skytala.eCommerce.domain.product.relations.productMeter.mapper.ProductMeterMapper;
import com.skytala.eCommerce.domain.product.relations.productMeter.model.ProductMeter;


public class FindAllProductMeters extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductMeter> returnVal = new ArrayList<ProductMeter>();
try{
List<GenericValue> results = delegator.findAll("ProductMeter", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProductMeterMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProductMeterFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
