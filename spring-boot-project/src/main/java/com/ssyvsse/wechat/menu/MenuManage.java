package com.ssyvsse.wechat.menu;

import com.ssyvsse.wechat.utils.WechtPropUtil;

/**
 * @author llb
 *
 * @Date 2017年12月9日
 */
public class MenuManage {

	public static Menu getMenu() {
		ClickButton mainBtn1 = new ClickButton();
		mainBtn1.setName(WechtPropUtil.prop.getProperty("wechat.mainBtn1").trim());
		mainBtn1.setType("click");
		mainBtn1.setKey("21");
		
		ClickButton mainBtn2 = new ClickButton();
		mainBtn2.setName(WechtPropUtil.prop.getProperty("wechat.mainBtn2").trim());
		mainBtn2.setType("click");
		mainBtn2.setKey("11");
		
		/**
		 * 这是公众号xiaoqrobot目前的菜单结构，每个一级菜单都有二级菜单项 在某个一级菜单下没有二级菜单的情况，menu该如何定义呢？
		 * 比如，第三个一级菜单项不是“更多体验”，而直接是“幽默笑话”，那么menu应该这样定义： menu.setButton(new Button[] {
		 * mainBtn1, mainBtn2, btn33 });
		 */
		Menu menu = new Menu();  
		menu.setButton(new Button[] { mainBtn1,mainBtn2});
        return menu;  
	}
}
