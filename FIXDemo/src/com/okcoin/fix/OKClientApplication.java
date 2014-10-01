package com.okcoin.fix;

import java.io.IOException;

import org.apache.log4j.Logger;


import quickfix.DoNotSend;
import quickfix.FieldNotFound;
import quickfix.IncorrectDataFormat;
import quickfix.IncorrectTagValue;
import quickfix.Message;
import quickfix.RejectLogon;
import quickfix.Session;
import quickfix.SessionID;
import quickfix.StringField;
import quickfix.UnsupportedMessageType;

/**
 * 客户端请求实现
 * @author OKCOIN
 */
public class OKClientApplication  implements quickfix.Application {
	
	
	
	private static final Logger log = Logger.getLogger(OKClientApplication.class);
	
	public void onMessage(Message message, SessionID sessionID)
			throws FieldNotFound, UnsupportedMessageType, IncorrectTagValue {
		System.out.println("-----------onMessage-----------");
	}

	public void fromAdmin(quickfix.Message msg, SessionID sessionID)
			throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue,
			RejectLogon {
		System.out.println("receivedType:" + msg.getHeader().getString(35));
		if ("0".equals(msg.getHeader().getString(35))) {
			System.out.println(sessionID
					+ "------client fromAdmin----收到一个心跳消息 ----"
					+ msg.toString());
		} else
			System.out.println(sessionID
					+ "------client fromAdmin----收到一个认证消息 ----"
					+ msg.toString());
	}

	public void fromApp(quickfix.Message msg, SessionID sessionID)
			throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue,
			UnsupportedMessageType {
		System.out.println("receivedType:" + msg.getHeader().getString(35));
		System.out.println(sessionID + "------client fromApp-----消息接收----"
				+ msg.toString());
	}

	public void onCreate(SessionID sessionID) {
		System.out.println(sessionID + "------client onCreate 创建Session-------"
				+ sessionID);
	}

	public void onLogon(final SessionID sessionID) {
		
		new Thread(new Runnable() {

			@Override
			public void run() {
				Session session = Session.lookupSession(sessionID);
				Message message = null;
				
				// 订单记录
				// message = OKMarketDataRequest.createOrderBookRequest();
				// 盘口数据
				 message = OKMarketDataRequest.create24HTickerRequest();
				// 历史交易数据
				//message = OKMarketDataRequest.createLiveTradesRequest();
				 
				// 用户数据请求
				// message = OKTradingRequest.createUserAccountRequest();
				 
                // 创建新订单
                //try {
				// message = OKTradingRequest.createOrderBookRequest();
				// } catch (IOException e) {
				//System.out.println(e.getMessage());
				//}
				
				 // 取消订单请求
			     // message = OKTradingRequest.createOrderCancelRequest();
				 // 订单状态请求
				 // message = OKTradingRequest.createOrderStatusRequest();
				 // 交易历史结果 – 交易资产报告
				 //message = OKTradingRequest.createTradeHistoryRequest();
				session.send(message);
			}
		}).start();


		System.out.println(sessionID + "------client onLogon----登录成功---"
				+ sessionID);
	}

	public void onLogout(SessionID sessionID) {
		System.out.println(sessionID + "------client onLogout 退出-------"
				+ sessionID);
		// System.exit(0);
	}

	public void toAdmin(quickfix.Message msg, SessionID sessionID) {
		//msg.setField(new StringField(553, MessageValidation.PARTNER));
     	msg.setField(new StringField(553, AccountUtil.apiKey));
		msg.setField(new StringField(554, AccountUtil.sercretKey));

		System.out.println("-------------toAdmin");
		try {
			if (msg.getHeader().getInt(35) == 0) {
				System.out.println(sessionID
						+ "------client toAdmin-----发送心跳----" + msg.toString());
			} else
				System.out.println(sessionID
						+ "------client toAdmin-----登陆认证----" + msg.toString());
		} catch (FieldNotFound e) {
			e.printStackTrace();
		}
	}

	public void toApp(quickfix.Message msg, SessionID sessionID)
			throws DoNotSend {
		System.out.println(sessionID + "------client toApp-------业务逻辑----"
				+ msg.toString());

	}


}
