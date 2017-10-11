
package com.skytala.eCommerce.domain.product.relations.supplierProductFeature.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.supplierProductFeature.event.SupplierProductFeatureFound;
import com.skytala.eCommerce.domain.product.relations.supplierProductFeature.mapper.SupplierProductFeatureMapper;
import com.skytala.eCommerce.domain.product.relations.supplierProductFeature.model.SupplierProductFeature;


public class FindAllSupplierProductFeatures extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<SupplierProductFeature> returnVal = new ArrayList<SupplierProductFeature>();
try{
List<GenericValue> results = delegator.findAll("SupplierProductFeature", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(SupplierProductFeatureMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new SupplierProductFeatureFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
