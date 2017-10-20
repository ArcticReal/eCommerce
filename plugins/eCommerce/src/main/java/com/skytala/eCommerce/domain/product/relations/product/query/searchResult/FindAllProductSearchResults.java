
package com.skytala.eCommerce.domain.product.relations.product.query.searchResult;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.product.event.searchResult.ProductSearchResultFound;
import com.skytala.eCommerce.domain.product.relations.product.mapper.searchResult.ProductSearchResultMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.searchResult.ProductSearchResult;


public class FindAllProductSearchResults extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductSearchResult> returnVal = new ArrayList<ProductSearchResult>();
try{
List<GenericValue> results = delegator.findAll("ProductSearchResult", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProductSearchResultMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProductSearchResultFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
