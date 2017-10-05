
package com.skytala.eCommerce.domain.productPricePurpose.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
	import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.productPricePurpose.event.ProductPricePurposeFound;
import com.skytala.eCommerce.domain.productPricePurpose.mapper.ProductPricePurposeMapper;
import com.skytala.eCommerce.domain.productPricePurpose.model.ProductPricePurpose;


public class FindAllProductPricePurposes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductPricePurpose> returnVal = new ArrayList<ProductPricePurpose>();
try{
List<GenericValue> results = delegator.findAll("ProductPricePurpose", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProductPricePurposeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProductPricePurposeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
