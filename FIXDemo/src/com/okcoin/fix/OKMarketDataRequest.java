package com.okcoin.fix;

import quickfix.Message;
import quickfix.field.MDEntryType;
import quickfix.field.MDReqID;
import quickfix.field.MarketDepth;
import quickfix.field.SubscriptionRequestType;
import quickfix.field.Symbol;

/**
 * MarkertData Request
 * @author OKCOIN
 */
public class OKMarketDataRequest {
	/**
	 * 订单数据
	 * @return
	 */
	public static Message createOrderBookRequest() {
		quickfix.fix44.MarketDataRequest orderBookRequest = new quickfix.fix44.MarketDataRequest();
		quickfix.fix44.MarketDataRequest.NoRelatedSym noRelatedSym = new quickfix.fix44.MarketDataRequest.NoRelatedSym();
		noRelatedSym.set(new Symbol("BTC/CNY"));
		orderBookRequest.addGroup(noRelatedSym);
		
		orderBookRequest.set(new MDReqID("123"));
		orderBookRequest.set(new SubscriptionRequestType('0'));
		orderBookRequest.set(new MarketDepth(0));
		
	    quickfix.fix44.MarketDataRequest.NoMDEntryTypes group1 = new quickfix.fix44.MarketDataRequest.NoMDEntryTypes();
	    group1.set(new MDEntryType('0'));
	    orderBookRequest.addGroup(group1);
	    
	    quickfix.fix44.MarketDataRequest.NoMDEntryTypes group2 = new quickfix.fix44.MarketDataRequest.NoMDEntryTypes();
	    group2.set(new MDEntryType('1'));
	    orderBookRequest.addGroup(group2);
	    
	    return orderBookRequest;
    }
	
	/**
	 * 24h Data
	 * @return
	 */
	public static Message create24HTickerRequest() {
		quickfix.fix44.MarketDataRequest tickerRequest = new quickfix.fix44.MarketDataRequest();
		quickfix.fix44.MarketDataRequest.NoRelatedSym noRelatedSym = new quickfix.fix44.MarketDataRequest.NoRelatedSym();
		noRelatedSym.set(new Symbol("BTC/CNY"));
		tickerRequest.addGroup(noRelatedSym);
		
		tickerRequest.set(new MDReqID("123"));
		tickerRequest.set(new SubscriptionRequestType('0'));
		tickerRequest.set(new MarketDepth(0));
		
	    quickfix.fix44.MarketDataRequest.NoMDEntryTypes group1 = new quickfix.fix44.MarketDataRequest.NoMDEntryTypes();
	    group1.set(new MDEntryType('4'));
	    tickerRequest.addGroup(group1);
	    
	    quickfix.fix44.MarketDataRequest.NoMDEntryTypes group2 = new quickfix.fix44.MarketDataRequest.NoMDEntryTypes();
	    group2.set(new MDEntryType('5'));
	    tickerRequest.addGroup(group2);
	    
	    quickfix.fix44.MarketDataRequest.NoMDEntryTypes group3 = new quickfix.fix44.MarketDataRequest.NoMDEntryTypes();
	    group3.set(new MDEntryType('7'));
	    tickerRequest.addGroup(group3);
	    
	    quickfix.fix44.MarketDataRequest.NoMDEntryTypes group4 = new quickfix.fix44.MarketDataRequest.NoMDEntryTypes();
	    group4.set(new MDEntryType('8'));
	    tickerRequest.addGroup(group4);
	    
	    quickfix.fix44.MarketDataRequest.NoMDEntryTypes group5 = new quickfix.fix44.MarketDataRequest.NoMDEntryTypes();
	    group5.set(new MDEntryType('9'));
	    tickerRequest.addGroup(group5);
	    
	    quickfix.fix44.MarketDataRequest.NoMDEntryTypes group6 = new quickfix.fix44.MarketDataRequest.NoMDEntryTypes();
	    group6.set(new MDEntryType('B'));
	    tickerRequest.addGroup(group6);
	    
	    return tickerRequest;
	}
	/**
	 * 现场交易请求
	 * @return
	 */
	public static Message createLiveTradesRequest() {
		quickfix.fix44.MarketDataRequest liveTradesRequest = new quickfix.fix44.MarketDataRequest();
		quickfix.fix44.MarketDataRequest.NoRelatedSym noRelatedSym = new quickfix.fix44.MarketDataRequest.NoRelatedSym();
		
		noRelatedSym.set(new Symbol("BTC/CNY"));
		liveTradesRequest.addGroup(noRelatedSym);
		liveTradesRequest.set(new MDReqID("123"));
		liveTradesRequest.set(new SubscriptionRequestType('0'));
		liveTradesRequest.set(new MarketDepth(0));
		
		
	    quickfix.fix44.MarketDataRequest.NoMDEntryTypes group = new quickfix.fix44.MarketDataRequest.NoMDEntryTypes();
	    group.set(new MDEntryType('2'));
	    liveTradesRequest.addGroup(group);
	    
	    return liveTradesRequest;
	}
}
