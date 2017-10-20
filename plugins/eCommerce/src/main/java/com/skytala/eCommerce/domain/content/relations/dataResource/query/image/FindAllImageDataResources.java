
package com.skytala.eCommerce.domain.content.relations.dataResource.query.image;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.content.relations.dataResource.event.image.ImageDataResourceFound;
import com.skytala.eCommerce.domain.content.relations.dataResource.mapper.image.ImageDataResourceMapper;
import com.skytala.eCommerce.domain.content.relations.dataResource.model.image.ImageDataResource;


public class FindAllImageDataResources extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ImageDataResource> returnVal = new ArrayList<ImageDataResource>();
try{
List<GenericValue> results = delegator.findAll("ImageDataResource", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ImageDataResourceMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ImageDataResourceFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
