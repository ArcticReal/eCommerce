
package com.skytala.eCommerce.domain.content.relations.content.query.typeAttr;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.content.relations.content.event.typeAttr.ContentTypeAttrFound;
import com.skytala.eCommerce.domain.content.relations.content.mapper.typeAttr.ContentTypeAttrMapper;
import com.skytala.eCommerce.domain.content.relations.content.model.typeAttr.ContentTypeAttr;


public class FindAllContentTypeAttrs extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ContentTypeAttr> returnVal = new ArrayList<ContentTypeAttr>();
try{
List<GenericValue> results = delegator.findAll("ContentTypeAttr", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ContentTypeAttrMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ContentTypeAttrFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
