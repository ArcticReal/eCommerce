
package com.skytala.eCommerce.domain.product.relations.prodConfItemContent.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.prodConfItemContent.event.ProdConfItemContentFound;
import com.skytala.eCommerce.domain.product.relations.prodConfItemContent.mapper.ProdConfItemContentMapper;
import com.skytala.eCommerce.domain.product.relations.prodConfItemContent.model.ProdConfItemContent;


public class FindAllProdConfItemContents extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProdConfItemContent> returnVal = new ArrayList<ProdConfItemContent>();
try{
List<GenericValue> results = delegator.findAll("ProdConfItemContent", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProdConfItemContentMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProdConfItemContentFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
