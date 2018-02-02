package com.gyw.wx.service.model.button;
/**
 * 按钮的基类
 * 
 * 定义：所有菜单都有一个共同属性name
 *
 */
public class Button {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
