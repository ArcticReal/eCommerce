package com.skytala.eCommerce.domain.content.relations.surveyQuestionType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.content.relations.surveyQuestionType.event.SurveyQuestionTypeAdded;
import com.skytala.eCommerce.domain.content.relations.surveyQuestionType.mapper.SurveyQuestionTypeMapper;
import com.skytala.eCommerce.domain.content.relations.surveyQuestionType.model.SurveyQuestionType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddSurveyQuestionType extends Command {

private SurveyQuestionType elementToBeAdded;
public AddSurveyQuestionType(SurveyQuestionType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

SurveyQuestionType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setSurveyQuestionTypeId(delegator.getNextSeqId("SurveyQuestionType"));
GenericValue newValue = delegator.makeValue("SurveyQuestionType", elementToBeAdded.mapAttributeField());
addedElement = SurveyQuestionTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new SurveyQuestionTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
