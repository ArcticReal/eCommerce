
package com.skytala.eCommerce.domain.product.relations.product.query.featureGroupAppl;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.product.event.featureGroupAppl.ProductFeatureGroupApplFound;
import com.skytala.eCommerce.domain.product.relations.product.mapper.featureGroupAppl.ProductFeatureGroupApplMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.featureGroupAppl.ProductFeatureGroupAppl;


public class FindAllProductFeatureGroupAppls extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductFeatureGroupAppl> returnVal = new ArrayList<ProductFeatureGroupAppl>();
try{
List<GenericValue> results = delegator.findAll("ProductFeatureGroupAppl", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProductFeatureGroupApplMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProductFeatureGroupApplFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
