$(function(){
    $('.xy-btn .a1').click(function () {
		$('.xy-show').hide();
	})
	$('.xy-show-con .title em').click(function () {
		$('.xy-show').hide();
	})

	//注册通用
	$('.hylb-box1 h6').click(function () {
		if ($('.hylb-1c1').css('display') == 'none') {
			$('.hylb-1c1').slideDown();
		} else {
			$('.hylb-1c1').hide();
		}
	})

	$('.hylb-box2 h6').click(function () {
		if ($('.hylb-1c2').css('display') == 'none') {
			$('.hylb-1c2').slideDown();
		} else {
			$('.hylb-1c2').hide();
		}
	})

	$('.hylb-1c1 span').each(function () {
		$(this).click(function () {
			$('.hylb-box1 h6 span').text($(this).text());
			$('.hylb-1c1').hide();
		})
	})

	$('.hylb-1c2 span').each(function () {
		$(this).click(function () {
			debugger;
			$('.hylb-box2 h6 span').text($(this).text());
			$('.hylb-box2 h6 span').attr("value",$(this).attr("value"));
			$('.hylb-1c2').hide();
		})
	})

	
	$('.hylb-1c4 span').each(function () {
		$(this).click(function () {
			debugger;
			$('.hylb-box4 h6 span').text($(this).text());
			$('.hylb-box4 h6 span').attr("value",$(this).attr("value"));
			$('.hylb-1c4').hide();
		})
	})
	$('.hylb-1c3 span').each(function () {
		$(this).click(function () {
			debugger;
			$('.hylb-box3 h6 span').text($(this).text());
			$('.hylb-box3 h6 span').attr("value",$(this).attr("value"));
			$('.hylb-1c3').hide();
		})
	})
   //性别
	$('.hylb-box4 h6').click(function () {
		if ($('.hylb-1c4').css('display') == 'none') {
			$('.hylb-1c4').slideDown();
		} else {
			$('.hylb-1c4').hide();
		}
	})
	$('.hylb-1c4 span').each(function () {
		$(this).click(function () {
			$('.hylb-box4 h6 span').text($(this).text());
			$('.hylb-1c4').hide();
		})
	})
    //姓名输入框失焦事件
	$("#userName").blur(function () {
		check_userchs();
	})
})