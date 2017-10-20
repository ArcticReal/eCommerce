
package com.skytala.eCommerce.domain.product.relations.prodConfItemContent.query.type;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.prodConfItemContent.event.type.ProdConfItemContentTypeFound;
import com.skytala.eCommerce.domain.product.relations.prodConfItemContent.mapper.type.ProdConfItemContentTypeMapper;
import com.skytala.eCommerce.domain.product.relations.prodConfItemContent.model.type.ProdConfItemContentType;


public class FindAllProdConfItemContentTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProdConfItemContentType> returnVal = new ArrayList<ProdConfItemContentType>();
try{
List<GenericValue> results = delegator.findAll("ProdConfItemContentType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProdConfItemContentTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProdConfItemContentTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
