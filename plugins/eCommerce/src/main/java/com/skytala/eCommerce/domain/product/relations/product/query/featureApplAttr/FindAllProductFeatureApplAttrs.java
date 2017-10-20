
package com.skytala.eCommerce.domain.product.relations.product.query.featureApplAttr;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.product.event.featureApplAttr.ProductFeatureApplAttrFound;
import com.skytala.eCommerce.domain.product.relations.product.mapper.featureApplAttr.ProductFeatureApplAttrMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.featureApplAttr.ProductFeatureApplAttr;


public class FindAllProductFeatureApplAttrs extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductFeatureApplAttr> returnVal = new ArrayList<ProductFeatureApplAttr>();
try{
List<GenericValue> results = delegator.findAll("ProductFeatureApplAttr", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProductFeatureApplAttrMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProductFeatureApplAttrFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
