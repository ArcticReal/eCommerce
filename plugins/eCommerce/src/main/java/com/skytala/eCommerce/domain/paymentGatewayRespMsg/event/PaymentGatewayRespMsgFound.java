package com.skytala.eCommerce.domain.paymentGatewayRespMsg.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.paymentGatewayRespMsg.model.PaymentGatewayRespMsg;
public class PaymentGatewayRespMsgFound implements Event{

	private List<PaymentGatewayRespMsg> paymentGatewayRespMsgs;

	public PaymentGatewayRespMsgFound(List<PaymentGatewayRespMsg> paymentGatewayRespMsgs) {
		this.paymentGatewayRespMsgs = paymentGatewayRespMsgs;
	}

	public List<PaymentGatewayRespMsg> getPaymentGatewayRespMsgs()	{
		return paymentGatewayRespMsgs;
	}

}
