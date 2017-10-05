
package com.skytala.eCommerce.domain.productPromoCode.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
	import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.productPromoCode.event.ProductPromoCodeFound;
import com.skytala.eCommerce.domain.productPromoCode.mapper.ProductPromoCodeMapper;
import com.skytala.eCommerce.domain.productPromoCode.model.ProductPromoCode;


public class FindAllProductPromoCodes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductPromoCode> returnVal = new ArrayList<ProductPromoCode>();
try{
List<GenericValue> results = delegator.findAll("ProductPromoCode", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProductPromoCodeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProductPromoCodeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
