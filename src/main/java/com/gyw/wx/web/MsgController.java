package com.gyw.wx.web;


import com.gyw.wx.event.IButtonEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class MsgController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IButtonEvent btnEvent;
	
	/**
	 * Function name : 跳转群发页面 index
	 * 功能 : 
	 * 创建(整修)日期 : 2017年1月3日
	 * 制订者 : guoyouwei
	 */
	@RequestMapping(value = "/work/wx/sendMsg")
	public String index(){
		return "work.wx/sendMsg";
	} 		
	
	@RequestMapping(value = "/work/wx/sendText")
	public String send(){
		return null;
	} 			
	
}
