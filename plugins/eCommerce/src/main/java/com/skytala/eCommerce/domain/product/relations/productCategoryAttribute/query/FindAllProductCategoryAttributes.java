
package com.skytala.eCommerce.domain.product.relations.productCategoryAttribute.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.productCategoryAttribute.event.ProductCategoryAttributeFound;
import com.skytala.eCommerce.domain.product.relations.productCategoryAttribute.mapper.ProductCategoryAttributeMapper;
import com.skytala.eCommerce.domain.product.relations.productCategoryAttribute.model.ProductCategoryAttribute;


public class FindAllProductCategoryAttributes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductCategoryAttribute> returnVal = new ArrayList<ProductCategoryAttribute>();
try{
List<GenericValue> results = delegator.findAll("ProductCategoryAttribute", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProductCategoryAttributeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProductCategoryAttributeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
