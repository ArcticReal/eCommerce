
package com.skytala.eCommerce.domain.product.relations.product.query.geo;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.product.event.geo.ProductGeoFound;
import com.skytala.eCommerce.domain.product.relations.product.mapper.geo.ProductGeoMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.geo.ProductGeo;


public class FindAllProductGeos extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductGeo> returnVal = new ArrayList<ProductGeo>();
try{
List<GenericValue> results = delegator.findAll("ProductGeo", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProductGeoMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProductGeoFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
