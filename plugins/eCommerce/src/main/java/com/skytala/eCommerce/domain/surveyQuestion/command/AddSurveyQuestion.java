package com.skytala.eCommerce.domain.surveyQuestion.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.surveyQuestion.event.SurveyQuestionAdded;
import com.skytala.eCommerce.domain.surveyQuestion.mapper.SurveyQuestionMapper;
import com.skytala.eCommerce.domain.surveyQuestion.model.SurveyQuestion;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddSurveyQuestion extends Command {

private SurveyQuestion elementToBeAdded;
public AddSurveyQuestion(SurveyQuestion elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

SurveyQuestion addedElement = null;
boolean success = false;
try {
elementToBeAdded.setSurveyQuestionId(delegator.getNextSeqId("SurveyQuestion"));
GenericValue newValue = delegator.makeValue("SurveyQuestion", elementToBeAdded.mapAttributeField());
addedElement = SurveyQuestionMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new SurveyQuestionAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
