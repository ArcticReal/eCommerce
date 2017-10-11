
package com.skytala.eCommerce.domain.product.relations.productPromoProduct.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.productPromoProduct.event.ProductPromoProductFound;
import com.skytala.eCommerce.domain.product.relations.productPromoProduct.mapper.ProductPromoProductMapper;
import com.skytala.eCommerce.domain.product.relations.productPromoProduct.model.ProductPromoProduct;


public class FindAllProductPromoProducts extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductPromoProduct> returnVal = new ArrayList<ProductPromoProduct>();
try{
List<GenericValue> results = delegator.findAll("ProductPromoProduct", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProductPromoProductMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProductPromoProductFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
