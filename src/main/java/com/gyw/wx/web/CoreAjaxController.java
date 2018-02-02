package com.gyw.wx.web;

import com.gyw.wx.utils.TulingApiUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CoreAjaxController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	private static final boolean Fail = false;
		
	/**查询按钮 */
	/*@RequestMapping("/ajax/work/supplierMng/getAll.hm")
	public Map<String, Object> getAll(SupplierMngCondition condition){
		return  supplierMngService.getAll(condition);
	}*/

	@RequestMapping("/tl/hello")
	public String hello(){
		System.out.println("问题：你叫什么?");
		System.out.println("回答："+TulingApiUtil.getTulingResult("你叫什么?"));
		return  TulingApiUtil.getTulingResult("你叫什么?");
	}


}
