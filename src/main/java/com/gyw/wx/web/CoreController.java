package com.gyw.wx.web;


import com.gyw.wx.common.ConstStr;
import com.gyw.wx.event.IButtonEvent;
import com.gyw.wx.utils.HttpXmlUtil;
import com.gyw.wx.utils.SignUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@Controller
public class CoreController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IButtonEvent btnEvent;
	
	/**
	 * Function name : index
	 * 功能 : 
	 * 创建(整修)日期 : 2016年12月27日
	 * 制订者 : wangyiran
	 */
	@RequestMapping(value = "/gyw/wx")
	public String index(HttpServletRequest request, HttpServletResponse response)throws Exception {
		/**
		 * 功能：验证开发者 微信服务器Get方式发送请求,开发者通过微信服务器发过来的参数来处理后与signature对比，一样则通过验证
		 */
		String method = request.getMethod().toUpperCase().trim();
		if (method.equals(ConstStr.MTD_GET)) {
			// Get获取微信加密签名
			String signature = request.getParameter("signature");
			// 获取时间戳
			String timestamp = request.getParameter("timestamp");
			// 获取随机数
			String nonce = request.getParameter("nonce");
			// 获取随机字符串
			String echostr = request.getParameter("echostr");
			// 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
			if (SignUtil.checkSignature(signature, timestamp, nonce)) {
				HttpXmlUtil.writeOutStr(response, echostr, false,"utf-8");
			}
		} else if (method.equals(ConstStr.MTD_POST)) {
			// 调用核心业务类接收消息、处理消息
			String respMessage = btnEvent.processRequest(request);
			// 响应消息,微信服务器收到响应的消息后，经过处理直接显示在开发者的公众号上
			HttpXmlUtil.writeOutStr(response, respMessage, true,"utf-8");
		}
		return null;
	} 			
	
	
}
