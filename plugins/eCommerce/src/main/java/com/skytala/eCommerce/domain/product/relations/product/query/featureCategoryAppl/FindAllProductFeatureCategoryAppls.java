
package com.skytala.eCommerce.domain.product.relations.product.query.featureCategoryAppl;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.product.event.featureCategoryAppl.ProductFeatureCategoryApplFound;
import com.skytala.eCommerce.domain.product.relations.product.mapper.featureCategoryAppl.ProductFeatureCategoryApplMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.featureCategoryAppl.ProductFeatureCategoryAppl;


public class FindAllProductFeatureCategoryAppls extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductFeatureCategoryAppl> returnVal = new ArrayList<ProductFeatureCategoryAppl>();
try{
List<GenericValue> results = delegator.findAll("ProductFeatureCategoryAppl", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProductFeatureCategoryApplMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProductFeatureCategoryApplFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
