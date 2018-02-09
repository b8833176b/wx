package com.gyw.wx.event;


import com.gyw.wx.common.ConstStr;
import com.gyw.wx.resume.service.ResumeService;
import com.gyw.wx.service.IMessageMgrService;
import com.gyw.wx.service.model.message.common.TextMessage;
import com.gyw.wx.utils.MsgUtil;
import com.gyw.wx.utils.TulingApiUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * 响应菜单的点击
 * 
 */
//@Component("wx.event.ButtonEvent")
@Service
public class ButtonEvent implements IButtonEvent {
	@Autowired
	private IMessageMgrService msgService;

	@Autowired
	private ResumeService resumeService;

	/**
	 * 处理微信发来的请求
	 * 
	 * @param request
	 * @return
	 */
	@Override
	public String processRequest(HttpServletRequest request ) {

		String respMessage = null;
		// 默认返回的文本消息内容
		String respContent = "请求处理异常，请稍候尝试！";
		// xml请求解析
		Map<String, String> requestMap = null;
		try {
			requestMap = MsgUtil.parseXml(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 公众帐号
		String fromUserName = requestMap.get("FromUserName");

		// 发送方帐号（open_id）
		String toUserName = requestMap.get("ToUserName");
		// 消息类型
		String msgType = requestMap.get("MsgType");
		// 消息内容
		String msgContent = requestMap.get("Content");
		// 回复文本消息
		TextMessage textMessage = new TextMessage();
		textMessage.setToUserName(fromUserName);
		textMessage.setFromUserName(toUserName);
		textMessage.setCreateTime(new Date().getTime());
		textMessage.setMsgType(ConstStr.RESP_MESSAGE_TYPE_TEXT);
		textMessage.setFuncFlag(0);

		// 文本消息
		if (msgType.equals(ConstStr.REQ_MESSAGE_TYPE_TEXT)) {
            //如果以./。号开头 特殊处理;
			if(msgContent.startsWith("。")||msgContent.startsWith(".")){
				String msg = msgContent.substring(1);
				msg = msg.trim();
				if("郭有为".equals(msg)){
					respContent = "回复以下关键字：\n"
					+"。基本信息\n"
					+"。项目经验\n"
					+"。工作经历\n"
					+"。职业技能\n"
					+"。自我评价";
				}else{
					respContent = resumeService.getContentByType(msg);
					if(respContent==null|| "".equals(respContent))
						respContent = "暂不提供该查询："+msg;
				}
			}else{
				respContent=TulingApiUtil.getTulingResult(msgContent);//使用图灵机器人api
			}
		}
		// 图片消息
		else if (msgType.equals(ConstStr.REQ_MESSAGE_TYPE_IMAGE)) {
			respContent = "您发送的是图片消息！";
		}
		// 地理位置消息
		else if (msgType.equals(ConstStr.REQ_MESSAGE_TYPE_LOCATION)) {
			respContent = "您发送的是地理位置消息！";
		}
		// 链接消息
		else if (msgType.equals(ConstStr.REQ_MESSAGE_TYPE_LINK)) {
			respContent = "您发送的是链接消息！";
		}
		// 音频消息
		else if (msgType.equals(ConstStr.REQ_MESSAGE_TYPE_VOICE)) {
			respContent = "您发送的是音频消息！";
		}
		// 事件推送
		else if (msgType.equals(ConstStr.REQ_MESSAGE_TYPE_EVENT)) {
			// 事件类型
			String eventType = requestMap.get("Event");
			// 订阅
			if (eventType.equals(ConstStr.EVENT_TYPE_SUBSCRIBE)) {
				respContent = "谢谢您的关注！";
			}
			// 取消订阅
			else if (eventType.equals(ConstStr.EVENT_TYPE_UNSUBSCRIBE)) {
				// TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
			}
			// 自定义菜单点击事件
			else if (eventType.equals(ConstStr.EVENT_TYPE_CLICK)) {
				// 事件KEY值，与创建自定义菜单时指定的KEY值对应
				String eventKey = requestMap.get("EventKey");
				if (eventKey.equals("11")) {
						respMessage = msgService.createCreditsQueryNewsMessage(fromUserName, toUserName);	
				} else if (eventKey.equals("12")) {
					respMessage = msgService.createCreditsExchangeNewsMessage(fromUserName, toUserName);
					return respMessage;
				} else if (eventKey.equals("14")) {
					respMessage = msgService.createAboutUsNewsMessage(fromUserName, toUserName);
					return respMessage;
				} else if (eventKey.equals("21")) { 
					respMessage=msgService.createBusinessNewsMessage(fromUserName, toUserName);
					return respMessage;
				} else if (eventKey.equals("22")) {
					respMessage=msgService.createNetWorkNewsMessage(fromUserName, toUserName);
					return respMessage;
				} else if (eventKey.equals("24")) {
					respMessage=msgService.createFinancialProductsNewsMessage(fromUserName, toUserName);
					return respMessage;
				} else if (eventKey.equals("31")) {
					respMessage = msgService.createActivityNewsMessage(fromUserName, toUserName);
					return respMessage;
				}else{
					textMessage.setContent(respContent);
					respMessage = MsgUtil.textMessageToXml(textMessage);
					return respMessage;
				}
			}
		}
		textMessage.setContent(respContent);
		respMessage = MsgUtil.textMessageToXml(textMessage);
		return respMessage;
	}


}
