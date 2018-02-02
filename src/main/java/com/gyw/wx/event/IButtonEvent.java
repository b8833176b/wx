package com.gyw.wx.event;

import javax.servlet.http.HttpServletRequest;

public interface IButtonEvent {
	
	public  String processRequest(HttpServletRequest request);

}