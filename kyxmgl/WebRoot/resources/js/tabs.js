// JavaScript Document
	
$(function(){
	
	//导航内容切换
	$('.unusual_tab_menu li a').onclick(function(){
		$(this).addClass("current").siblings().removeClass();//removeClass就是删除当前其他类；只有当前对象有addClass("selected")；siblings()意思就是当前对象的同级元素，removeClass就是删除.
	});

	 
	//内容切换tab
	$('.unusual_tab_menu li a').onclick(function(){
		$(".unusual_tab_body > div.unusual_tab_body_item").hide().eq($('.unusual_tab_menu li').index(this)).show();		
	}); 
});