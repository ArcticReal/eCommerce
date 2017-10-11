
package com.skytala.eCommerce.domain.product.relations.productFeatureCatGrpAppl.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.productFeatureCatGrpAppl.event.ProductFeatureCatGrpApplFound;
import com.skytala.eCommerce.domain.product.relations.productFeatureCatGrpAppl.mapper.ProductFeatureCatGrpApplMapper;
import com.skytala.eCommerce.domain.product.relations.productFeatureCatGrpAppl.model.ProductFeatureCatGrpAppl;


public class FindAllProductFeatureCatGrpAppls extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductFeatureCatGrpAppl> returnVal = new ArrayList<ProductFeatureCatGrpAppl>();
try{
List<GenericValue> results = delegator.findAll("ProductFeatureCatGrpAppl", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProductFeatureCatGrpApplMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProductFeatureCatGrpApplFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
