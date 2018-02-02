package com.gyw.wx.service.model.message.request;


import com.gyw.wx.service.model.message.common.BaseMessage;

public class ImageMessage extends BaseMessage {
	// 图片链接
	private String PicUrl;

	public String getPicUrl() {
		return PicUrl;
	}

	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}
}
