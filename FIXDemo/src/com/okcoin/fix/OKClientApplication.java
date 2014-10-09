package com.okcoin.fix;

import java.io.IOException;

import org.apache.log4j.Logger;
import quickfix.DoNotSend;
import quickfix.FieldNotFound;
import quickfix.IncorrectDataFormat;
import quickfix.IncorrectTagValue;
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
	//if you request marketdata you can set PARTNER and SECRET_KEY as "1"
	public static final String PARTNER = "your partner ";
	public static final String SECRET_KEY = "your securityKey";
	
	private static final Logger log = Logger.getLogger(OKClientApplication.class);
	
	public void fromAdmin(quickfix.Message msg, SessionID sessionID)
			throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue,
			RejectLogon {
		log.info("receivedType:"+msg.getHeader().getString(35));
		log.info(sessionID+"------ fromAdmin--------"+msg.toString());
	}

	public void fromApp(quickfix.Message msg, SessionID sessionID)
			throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue,
			UnsupportedMessageType {
		
		log.info("receivedType:"+msg.getHeader().getString(35));
		log.info(sessionID+"------ fromApp---------"+msg.toString());
	}

	public void onCreate(SessionID sessionID) {
		try {
			//there should invoke reset()
			Session.lookupSession(sessionID).reset();
		} catch (IOException e) {
			e.printStackTrace();
		}
		log.info(sessionID+"------ onCreate Session-------"+sessionID);
	}

	
	
	public void onLogon(final SessionID sessionID) {
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				quickfix.Message message = OKMarketDataRequest.create24HTickerRequest();
				Session.lookupSession(sessionID).send(message);
			}
		}).start();
		
		log.info(sessionID+"------ onLogon-------"+sessionID);
	}

	public void onLogout(SessionID sessionID) {
		log.info(sessionID+"------ onLogout -------"+sessionID);
	}

	public void toAdmin(quickfix.Message msg, SessionID sessionID) {
		msg.setField(new StringField(553, PARTNER));
		msg.setField(new StringField(554, SECRET_KEY));
		log.info(sessionID+"------ toAdmin---------"+msg.toString());
	}

	public void toApp(quickfix.Message msg, SessionID sessionID) throws DoNotSend {
		log.info(sessionID+"------ toApp-----------"+msg.toString());
	}

}
