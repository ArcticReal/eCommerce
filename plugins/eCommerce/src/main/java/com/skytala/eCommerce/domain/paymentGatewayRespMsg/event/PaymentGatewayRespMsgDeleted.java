package com.skytala.eCommerce.domain.paymentGatewayRespMsg.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.paymentGatewayRespMsg.model.PaymentGatewayRespMsg;
public class PaymentGatewayRespMsgDeleted implements Event{

	private boolean success;

	public PaymentGatewayRespMsgDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
