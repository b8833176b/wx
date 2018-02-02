package com.gyw.wx.service.model.message.common;

//文本消息，Request和Response格式一样
public class TextMessage extends BaseMessage {
	// 消息内容
	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
}
