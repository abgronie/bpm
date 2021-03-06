/**
 * jquery.scrollFollow.js
 * Copyright (c) 2008 Net Perspective (http://kitchen.net-perspective.com/)
 * Licensed under the MIT License (http://www.opensource.org/licenses/mit-license.php)
 * 
 * @author R.A. Ray
 *
 * @projectDescription	jQuery plugin for allowing an element to animate down as the user scrolls the page.
 * 
 * @version 0.4.0
 * 
 * @requires jquery.js (tested with 1.2.6)
 * @requires ui.core.js (tested with 1.5.2)
 * 
 * @optional jquery.cookie.js (http://www.stilbuero.de/2006/09/17/cookie-plugin-for-jquery/)
 * @optional jquery.easing.js (http://gsgd.co.uk/sandbox/jquery/easing/ - tested with 1.3)
 * 
 * @param speed		int - Duration of animation (in milliseconds)
 * 								default: 500
 * @param offset			int - Number of pixels box should remain from top of viewport
 * 								default: 0
 * @param easing		string - Any one of the easing options from the easing plugin - Requires jQuery Easing Plugin < http://gsgd.co.uk/sandbox/jquery/easing/ >
 * 								default: 'linear'
 * @param container	string - ID of the containing div
 * 								default: box's immediate parent
 * @param killSwitch	string - ID of the On/Off toggle element
 * 								default: 'killSwitch'
 * @param onText		string - killSwitch text to be displayed if sliding is enabled
 * 								default: 'Turn Slide Off'
 * @param offText		string - killSwitch text to be displayed if sliding is disabled
 * 								default: 'Turn Slide On'
 * @param relativeTo	string - Scroll animation can be relative to either the 'top' or 'bottom' of the viewport
 * 								default: 'top'
 * @param delay			int - Time between the end of the scroll and the beginning of the animation in milliseconds
 * 								default: 0
 */

( function( $ ) {
	$.foldBox = function ( box, options ){ 		
		   var $content;
		   var $button=	$('.drop',$(box));
			if($(box).hasClass(options.searchBox)){ //搜索框
				$content=$('#searchForm',$(box));
				var insertHtml='<div class="title">查询条件</div><div class="drop"><a>高级搜索</a></div>'
				// 浮层
				var supernatantHtml = '<div class="searchbutton">';
				if($(".panel").attr("ajax") == undefined){
					supernatantHtml +='<a class="link search btn" id="btnSearch">查询</a>';
				}else{
					supernatantHtml +='<a class="link ajaxSearch btn" href="javascript:;" onclick="handlerSearchAjax(this)">查询</a>';
				}
				supernatantHtml += '<a href="javascript:;" class="link reset btn" onclick="$.clearQueryForm();">重置</a><a href="javascript:;" class="cancel btn">取消</a></div><div class="bgc"></div>';
				$content.append(supernatantHtml);
				$(insertHtml).insertBefore( $content ) ;
				var afterShowfn=options.afterShow;
				options.afterShow=function(){afterShowfn();
				$('.title',$(box)).show();
				$('.drop',$(box)).find('a').text('收起');
				$('.drop',$(box)).find('a').addClass('activi')}
				var afterHidefn=options.afterHide;
				options.afterHide=function(){afterHidefn();
				$('.title',$(box)).hide();
				$('.drop',$(box)).find('a').text('高级搜索');
				$('.drop',$(box)).find('a').removeClass('activi')}
				$button =$('.drop,.cancel',$(box));
				
				if($.isFunction($.fn.select2)){
					//初始化复选框查询
					$(".js-example-basic-multiple").select2({
						width:"auto"
					});
					$(".js-example-basic-multiple").each(function(){
						var obj = $(this);
						var initData = obj.attr("initData");
						if(initData && initData != ""){
							var valueArray = initData.split(",");
							obj.val(valueArray).trigger('change');
						}
					});
				}
			}
			
			$button.live('click',function(){
				 if( $content.is(':hidden')){
					 options.beforeShow($(box));
					 $content.show();
					 options.afterShow($(box));
					 $.setCookie("isLocked", true);
					 //changeScrollHeight($.getCookie("noNeedFoldHeight"));
				 }else{
					options.beforeHide($(box));
					$content.hide();
					options.afterHide($(box));
					$.setCookie("isLocked", false);
					//changeScrollHeight($.getCookie("noNeedFoldHeight"));
				 }
			});
			if($.getCookie("isLocked")=="true"){
				 $('.title',$(box)).show();
				 $('.drop',$(box)).find('a').text('收起');
				 $('.drop',$(box)).find('a').addClass('activi');
				 $content.show();	
			}else{
				$content.hide();
			}
			
	};
	
	$.fn.foldBox = function ( options ){
		options = options || {};
		options.searchBox = options.searchBox || 'panel-search';
		options.beforeShow = options.beforeShow || function($box){};
		options.afterShow = options.afterShow || function($box){};
		options.beforeHide = options.beforeHide || function($box){};
		options.afterHide = options.afterHide || function($box){};
		this.each( function() 
			{
				new $.foldBox( this, options );
			}
		);
		
		return this;
	};
})( jQuery );

$(function(){
	$.extend({initFoldBox:function(){
		/**
		 * 初始化更新页面
		 */
		try{
			var updateHeight = function(){$("div.hide-panel").height($("div.panel-top").height());};
			$('.panel-search').foldBox({afterShow:updateHeight,afterHide:updateHeight});
			$('.foldBox').foldBox();
			changeScrollHeight(($('.panel-search')[0]&&$('.panel-search').attr("hasScroll")!="true"||$('.foldBox')[0]&&$('.foldBox').attr("hasScroll")!="true"||$('.panel-page')[0])?"":true);
		}catch(e){}
	}});
	$.initFoldBox();
});
