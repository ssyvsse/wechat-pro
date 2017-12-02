package com.ssyvsse.wechat.controller.menu;

import com.ssyvsse.wechat.utils.WechatPropUtil;

public class MenuManage {
	public static Menu getMenu() {
		/*
		 * ViewButton btn21 = new ViewButton();
		 * btn21.setName(WechtPropUtil.prop.getProperty("wechat.btn21Name").trim
		 * ()); btn21.setType("view");
		 * btn21.setUrl(WechtPropUtil.prop.getProperty("wechat.btn21Url").trim()
		 * );
		 * 
		 * ViewButton btn22 = new ViewButton();
		 * btn22.setName(WechtPropUtil.prop.getProperty("wechat.btn22Name").trim
		 * ()); btn22.setType("view");
		 * btn22.setUrl(WechtPropUtil.prop.getProperty("wechat.btn22Url").trim()
		 * );
		 */

		ClickButton mainBtn1 = new ClickButton();
		mainBtn1.setName(WechatPropUtil.prop.getProperty("wechat.mainBtn1").trim());
		mainBtn1.setType("click");
		mainBtn1.setKey("21");
		ViewButton mainBtn2 = new ViewButton();
		mainBtn2.setName(WechatPropUtil.prop.getProperty("wechat.mainBtn2").trim());
		mainBtn2.setType("view");
		mainBtn2.setUrl(
				"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx18311d7365923e28&redirect_uri=http%3A%2F%2Fssyvsse.s1.natapp.cc&response_type=code&scope=snsapi_userinfo&state=123#wechat_redirect");

		/*
		 * ComplexButton mainBtn2 = new ComplexButton();
		 * mainBtn2.setName(WechtPropUtil.prop.getProperty("wechat.mainBtn2").
		 * trim()); mainBtn2.setSub_button(new Button[] { btn21, btn22});
		 */
		/**
		 * 这是公众号xiaoqrobot目前的菜单结构，每个一级菜单都有二级菜单项 在某个一级菜单下没有二级菜单的情况，menu该如何定义呢？
		 * 比如，第三个一级菜单项不是“更多体验”，而直接是“幽默笑话”，那么menu应该这样定义： menu.setButton(new
		 * Button[] { mainBtn1, mainBtn2, btn33 });
		 */
		Menu menu = new Menu();
		/* menu.setButton(new Button[] { mainBtn1, mainBtn2}); */
		menu.setButton(new Button[] { mainBtn1, mainBtn2 });
		return menu;
	}
}
