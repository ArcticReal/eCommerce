
package com.skytala.eCommerce.domain.productPriceChange.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
	import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.productPriceChange.event.ProductPriceChangeFound;
import com.skytala.eCommerce.domain.productPriceChange.mapper.ProductPriceChangeMapper;
import com.skytala.eCommerce.domain.productPriceChange.model.ProductPriceChange;


public class FindAllProductPriceChanges extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductPriceChange> returnVal = new ArrayList<ProductPriceChange>();
try{
List<GenericValue> results = delegator.findAll("ProductPriceChange", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProductPriceChangeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProductPriceChangeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
