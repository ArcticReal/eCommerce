
package com.skytala.eCommerce.domain.product.relations.productFeatureIactnType.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.productFeatureIactnType.event.ProductFeatureIactnTypeFound;
import com.skytala.eCommerce.domain.product.relations.productFeatureIactnType.mapper.ProductFeatureIactnTypeMapper;
import com.skytala.eCommerce.domain.product.relations.productFeatureIactnType.model.ProductFeatureIactnType;


public class FindAllProductFeatureIactnTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductFeatureIactnType> returnVal = new ArrayList<ProductFeatureIactnType>();
try{
List<GenericValue> results = delegator.findAll("ProductFeatureIactnType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProductFeatureIactnTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProductFeatureIactnTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
