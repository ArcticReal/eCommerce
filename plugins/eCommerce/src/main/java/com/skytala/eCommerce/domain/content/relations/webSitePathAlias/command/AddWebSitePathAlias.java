package com.skytala.eCommerce.domain.content.relations.webSitePathAlias.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.content.relations.webSitePathAlias.event.WebSitePathAliasAdded;
import com.skytala.eCommerce.domain.content.relations.webSitePathAlias.mapper.WebSitePathAliasMapper;
import com.skytala.eCommerce.domain.content.relations.webSitePathAlias.model.WebSitePathAlias;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddWebSitePathAlias extends Command {

private WebSitePathAlias elementToBeAdded;
public AddWebSitePathAlias(WebSitePathAlias elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

WebSitePathAlias addedElement = null;
boolean success = false;
try {
elementToBeAdded.setPathAlias(delegator.getNextSeqId("WebSitePathAlias"));
GenericValue newValue = delegator.makeValue("WebSitePathAlias", elementToBeAdded.mapAttributeField());
addedElement = WebSitePathAliasMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new WebSitePathAliasAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
