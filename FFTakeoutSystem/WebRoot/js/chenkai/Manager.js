$(function() {
		
	// 查询所有用户
	$("#findalluser").click(function() {
		
		$.getJSON("shwkuser!getUserList.action?v=" + Math.random(), Userrollback)
	});

	// 查询所有商家
	$("#findallrest").click(
			function() {
				$.getJSON("shwkrest!getRestList.action?v=" + Math.random(),
						Restrollback)
			});
	
	$("#findsomerest").click(
			function(){
				$.getJSON("shwkrest!getSomeRestList.action?v=" + Math.random(),
						SomeRestrollback)
			}
			)

	// 查询所有商家评论
	$("#findaddMess").click(
			function() {
				$.getJSON("shwkmess!getMessList.action?v=" + Math.random(),
						Messrollback)
			});

	// 查询所有菜单
	$("#findallmenu").click(
			function() {
				$.getJSON("shwkmenu!getMenuList.action?v=" + Math.random(),
						Menurollback)
			});

	// 查询所有菜单评论
	$("#findallmenumsg").click(
			function() {
				$.getJSON("shwkmenumsg!getMenuMsgList.action?v="
						+ Math.random(), MenuMsgrollback)
			});

	// 查询所有订单
	$("#findallorder").click(
			function() {
				$.getJSON("shwkorder!getOrderList.action?v=" + Math.random(),
						Orderrollback)
			});

	// 查询所有订单支付方式
	$("#findallpay").click(function() {
		$.getJSON("shwkpay!getPayList.action?v=" + Math.random(), Payrollback)
	});

	// 查询所有礼品
	$("#findallgift").click(
			function() {
				$.getJSON("shwkugift!getGiftList.action?v=" + Math.random(),
						Giftrollback)
			});

	// 查询所有礼品兑换记录
	$("#findallgiftRec").click(
			function() {
				$.getJSON("shwkgiftrec!getGiftRecordList.action?v="
						+ Math.random(), GiftRecrollback)
			});

});

// 用户回调函数
function Userrollback(data) {
	var userid = $("#id").val()
	var page = data.pages;
	var sum = data.total;
	var getAllUser = data.rows;
	var s = "<table align='center' border='1px dotted #000' width='90%'>";
	s += "<tr id='title'><th>编号</th><th>姓名</th><th>密码</th><th>邮箱</th><th>地址</th><th>真实姓名</th><th>余额</th><th>积分</th><th>性别</th><th>注册日期</th><th>权限</th><th>图片</th><th>操作</th></tr>"
	for ( var i = 0; i < getAllUser.length; i++) {
		var k = getAllUser[i];
		var authority = "";
		if (k.authority == 1) {
			authority = "普通用户"
		} else if (k.authority == 2) {
			authority = "配送员"
		} else if (k.authority == 3) {
			authority = "店主"
		} else if (k.authority == 4) {
			authority = "普通管理员"
		} else if (k.authority == 5) {
			authority = "系统管理员"
		}
		var addr=""
		if (k.address.length>4) {
			addr=k.address.substring(0,4)+"..."	;
		}else{
			addr=k.address;
		}	
		s += "<tr align='center'>"
		s += "<td >" + k.userid + "</td>";
		s += "<td >" + k.username + "</td>";
		s += "<td >" + k.pwd + "</td>";
		s += "<td >" + k.email + "</td>";
		s += "<td  class='tdtype' title="+k.address+">" + addr+ "</td>";
		s += "<td >" + k.realname + "</td>";
		s += "<td >" + k.balance + "</td>";
		s += "<td >" + k.score + "</td>";
		s += "<td >" + k.gender + "</td>";
		s += "<td >" + k.regdate + "</td>";
		s += "<td >" + authority + "</td>";
		s += "<td > <img src=" + k.photo
				+ " width='50px' heigth='50px'/></td>";
		s += "<td width='100px'>";
		if (userid == k.userid) {
			s += "<font color='red'>当前用户</font>";
		} else {
			s += "<span onclick='findbyid(" + k.userid + ")'>修改</span>";
			s += "&nbsp;";
			s += "&nbsp;";
			s += "<span onclick='deleUser(" + k.userid + ")'>删除</span>";
		}
		s += "</td>";
		s += "</tr>";
	}
	s += "</table>";
	s += "<table align='center' width='90%'>"
	s += "<tr valign='bottom' align='center' id='last'>"
	s += "<td colspan='3' width='100%'>"
	if (page != 1) {
		s += "<a  onclick='getUserList(1)'><font color='blue'>首页</font></a>"

	} else {
		s += "<span>首页</span>"
	}
	s += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	if (page > 1) {
		s += "<a  onclick='getUserList(" + (page - 1)
				+ ")'><font color='blue'>上一页</font></a>"
	} else {
		s += "<span>上一页</span>"
	}
	s += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	if (page < sum) {
		s += "<a  onclick='getUserList(" + (page + 1)
				+ ")'><font color='blue'>下一页</font></a>"
	} else {
		s += "<span>下一页</span>"
	}
	s += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	if (page != sum) {
		s += "<a onclick='getUserList(" + (sum)
				+ ")'><font color='blue'>尾页</font></a>"
	} else {
		s += "<span>尾页</span>"
	}
	s += "</td>"
	s += "</tr>"
	s += "</table>"
	
		 
	$("#mydiv").html(s);
	TableModel();
}


// 商家回调函数
function Restrollback(data) {
	var page = data.pages;
	var sum = data.total;
	var getAllRest = data.rows;

	var s = "<table align='center' border='1' width='90%'>";
	s += "<tr id='title'><th>编号</th><th>店名</th><th>地址</th><th>店主名</th><th>图片</th><th>公告</th><th>开店时间</th><th>营业时间</th><th>开店认证</th><th>操作</th></tr>"
	for ( var i = 0; i < getAllRest.length; i++) {
		var k = getAllRest[i];
		var status = "";
		if (k.rtstatus == 0) {
			status = "已通过"
		}
		var addr=""
			if (k.rtaddr.length>4) {
				addr=k.rtaddr.substring(0,4)+"..."	;
			}else{
				addr=k.rtaddr;
			}	
		var content=""
			if (k.rtcontent.length>4) {
				content=k.rtcontent.substring(0,4)+"..."	;
			}else{
				content=k.rtcontent;
			}
		s += "<tr align='center'>"
		s += "<td >" + k.rtid + "</td>";
		s += "<td >" + k.rtname + "</td>";
		s += "<td class='tdtype' title="+k.rtaddr+">" + addr+ "</td>";
		s += "<td >" + k.owner + "</td>";
		s += "<td > <img src=" + k.rtpic
				+ " width='50px' heigth='50px'/></td>";
		s += "<td class='tdtype' title="+k.rtcontent+">" + content+ "</td>";
		s += "<td >" + k.rtdate + "</td>";
		s += "<td >" + k.rtonbuz + "</td>";
		s += "<td >" + status + "</td>";
		s += "<td>";
		s += "<span onclick='findrestbyid(" + k.rtid + ")'>修改</span>";
		s += "&nbsp;";
		s += "&nbsp;";
		s += "<span onclick='deleRest(" + k.rtid + ")'>删除</span>";
		s += "</td>";
		s += "</tr>";
	}
	s += "</table>";
	s += "<table align='center' width='90%'>"
	s += "<tr valign='bottom' align='center' id='last'>"
	s += "<td colspan='3' width='100%'>"
	if (page != 1) {
		s += "<a  onclick='getRestList(1)'><font color='blue'>首页</font></a>"

	} else {
		s += "<span>首页</span>"
	}
	s += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	if (page > 1) {
		s += "<a  onclick='getRestList(" + (page - 1)
				+ ")'><font color='blue'>上一页</font></a>"
	} else {
		s += "<span>上一页</span>"
	}
	s += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	if (page < sum) {
		s += "<a  onclick='getRestList(" + (page + 1)
				+ ")'><font color='blue'>下一页</font></a>"
	} else {
		s += "<span>下一页</span>"
	}
	s += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	if (page != sum) {
		s += "<a onclick='getRestList(" + (sum)
				+ ")'><font color='blue'>尾页</font></a>"
	} else {
		s += "<span>尾页</span>"
	}
	s += "</td>"
	s += "</tr>"
	s += "</table>"
	$("#mydiv").html(s);
	TableModel();
}

//申请开店回调函数
function SomeRestrollback(data) {
	var page = data.pages;
	var sum = data.total;
	var getAllRest = data.rows
	if (getAllRest.length!=0) {
		var s = "<table align='center' border='1' width='90%'>";
		s += "<tr id='title'><th>编号</th><th>店名</th><th>地址</th><th>店主名</th><th>图片</th><th>公告</th><th>开店时间</th><th>营业时间</th><th>开店认证</th><th>操作</th></tr>"
		for ( var i = 0; i < getAllRest.length; i++) {
			var k = getAllRest[i];
			var status = "";
			if (k.rtstatus == 1) {
				status = "<font color='red'>待审核</font>"
			}
			var addr=""
				if (k.rtaddr.length>4) {
					addr=k.rtaddr.substring(0,4)+"..."	;
				}else{
					addr=k.rtaddr;
				}	
			var content=""
				if (k.rtcontent.length>4) {
					content=k.rtcontent.substring(0,4)+"..."	;
				}else{
					content=k.rtcontent;
				}
			s += "<tr align='center'>"
			s += "<td >" + k.rtid + "</td>";
			s += "<td >" + k.rtname + "</td>";
			s += "<td class='tdtype' title="+k.rtaddr+">" + addr+ "</td>";
			s += "<td >" + k.owner + "</td>";
			s += "<td > <img src=" + k.rtpic
					+ " width='50px' heigth='50px'/></td>";
			s += "<td class='tdtype' title="+k.rtcontent+">" + content+ "</td>";
			s += "<td >" + k.rtdate + "</td>";
			s += "<td >" + k.rtonbuz + "</td>";
			s += "<td >" + status + "</td>";
			s += "<td>";
			s += "<span onclick='updateRest(" + k.rtid + ","+k.rtowner+")'>同意开店</span>";
			s += "</td>";
			s += "</tr>";
		}
		s += "</table>";
		s += "<table align='center' width='90%'>"
		s += "<tr valign='bottom' align='center' id='last'>"
		s += "<td colspan='3' width='100%'>"
		if (page != 1) {
			s += "<a  onclick='getSomeRestList(1)'><font color='blue'>首页</font></a>"

		} else {
			s += "<span>首页</span>"
		}
		s += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
		if (page > 1) {
			s += "<a  onclick='getSomeRestList(" + (page - 1)
					+ ")'><font color='blue'>上一页</font></a>"
		} else {
			s += "<span>上一页</span>"
		}
		s += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
		if (page < sum) {
			s += "<a  onclick='getSomeRestList(" + (page + 1)
					+ ")'><font color='blue'>下一页</font></a>"
		} else {
			s += "<span>下一页</span>"
		}
		s += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
		if (page != sum) {
			s += "<a onclick='getSomeRestList(" + (sum)
					+ ")'><font color='blue'>尾页</font></a>"
		} else {
			s += "<span>尾页</span>"
		}
		s += "</td>"
		s += "</tr>"
		s += "</table>"
		$("#mydiv").html(s);

		TableModel();
	}else{
		$("#mydiv").html("<span style='float:left; padding:10px 20px; font-size:26px;'>暂无用户申请成为店家!</span>");
	}
	
}

function updateRest(rtid,uid){
	$.getJSON("shwkrest!UpdateRestStatus.action?v=" + Math.random()+"&rtid="+rtid+"&userid="+uid,
			function(){
		$.getJSON("shwkrest!getRestList.action?v=" + Math.random(),
				Restrollback)
	})
	
};

// 商家评论回调函数
function Messrollback(data) {
	var page = data.pages;
	var sum = data.total;
	var getAllMess = data.rows;

	var s = "<table align='center' border='1' width='90%'>";
	s += "<tr id='title'><th>编号</th><th>评论人</th><th>店铺名</th><th>评论内容</th><th>评论时间</th><th>操作</th></tr>"
	for ( var i = 0; i < getAllMess.length; i++) {
		var k = getAllMess[i];
			
		var content=""
			if (k.mcontent.length>4) {
				content=k.mcontent.substring(0,4)+"..."	;
			}else{
				content=k.mcontent;
			}
		s += "<tr align='center'>"
		s += "<td >" + k.mid + "</td>";
		s += "<td >" + k.musername + "</td>";
		s += "<td >" + k.mrtname + "</td>";
		s += "<td class='tdtype' title="+k.mcontent+">" + content+ "</td>";
		s += "<td >" + k.mdate + "</td>";
		s += "<td>";
		s += "<span onclick='findmessbyid(" + k.mid + ")'>修改</span>";
		s += "&nbsp;";
		s += "&nbsp;";
		s += "<span onclick='deleMess(" + k.mid + ")'>删除</span>";
		s += "</td>";
		s += "</tr>";
	}
	s += "</table>";
	s += "<table align='center' width='90%'>"
	s += "<tr valign='bottom' align='center' id='last'>"
	s += "<td colspan='3' width='100%'>"
	if (page != 1) {
		s += "<a  onclick='getMessList(1)'><font color='blue'>首页</font></a>"

	} else {
		s += "<span>首页</span>"
	}
	s += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	if (page > 1) {
		s += "<a  onclick='getMessList(" + (page - 1)
				+ ")'><font color='blue'>上一页</font></a>"
	} else {
		s += "<span>上一页</span>"
	}
	s += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	if (page < sum) {
		s += "<a  onclick='getMessList(" + (page + 1)
				+ ")'><font color='blue'>下一页</font></a>"
	} else {
		s += "<span>下一页</span>"
	}
	s += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	if (page != sum) {
		s += "<a onclick='getMessList(" + (sum)
				+ ")'><font color='blue'>尾页</font></a>"
	} else {
		s += "<span>尾页</span>"
	}
	s += "</td>"
	s += "</tr>"
	s += "</table>"
	$("#mydiv").html(s);
	TableModel();
}

// 菜单回调函数
function Menurollback(data) {
	var page = data.pages;
	var sum = data.total;
	var getAllMenu = data.rows;

	var s = "<table align='center' border='1' width='90%'>";
	s += "<tr id='title'><th>编号</th><th>店名</th><th>菜名</th><th>价格</th><th>图片</th><th>类型</th><th>描述</th><th>销量</th><th>是否售完</th><th>操作</th></tr>"
	for ( var i = 0; i < getAllMenu.length; i++) {
		var k = getAllMenu[i];
		var status = "";
		if (k.mustatus == 0) {
			status = "正在销售"
		} else if (k.mustatus == 1) {
			status = "已售完"
		}
		var content=""
			if (k.mudesc.length>4) {
				content=k.mudesc.substring(0,4)+"..."	;
			}else{
				content=k.mudesc;
			}
		s += "<tr align='center'>"
		s += "<td >" + k.muid + "</td>";
		s += "<td >" + k.mrtname + "</td>";
		s += "<td >" + k.muname + "</td>";
		s += "<td >" + k.muprice + "</td>";
		s += "<td > <img src=" + k.mupic
				+ " width='50px' heigth='50px'/></td>";
		s += "<td >" + k.mutype + "</td>";
		s += "<td class='tdtype' title="+k.mudesc+">" + content+ "</td>";
		s += "<td >" + k.musale + "</td>";
		s += "<td >" + status + "</td>";
		s += "<td>";
		s += "<span onclick='findmenubyid(" + k.muid + ")'>修改</span>";
		s += "&nbsp;";
		s += "&nbsp;";
		s += "<span onclick='deleMenu(" + k.muid + ")'>删除</span>";
		s += "</td>";
		s += "</tr>";
	}
	s += "</table>";
	s += "<table align='center' width='90%'>"
	s += "<tr valign='bottom' align='center' id='last'>"
	s += "<td colspan='3' width='100%'>"
	if (page != 1) {
		s += "<a  onclick='getMenuList(1)'><font color='blue'>首页</font></a>"

	} else {
		s += "<span>首页</span>"
	}
	s += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	if (page > 1) {
		s += "<a  onclick='getMenuList(" + (page - 1)
				+ ")'><font color='blue'>上一页</font></a>"
	} else {
		s += "<span>上一页</span>"
	}
	s += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	if (page < sum) {
		s += "<a  onclick='getMenuList(" + (page + 1)
				+ ")'><font color='blue'>下一页</font></a>"
	} else {
		s += "<span>下一页</span>"
	}
	s += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	if (page != sum) {
		s += "<a onclick='getMenuList(" + (sum)
				+ ")'><font color='blue'>尾页</font></a>"
	} else {
		s += "<span>尾页</span>"
	}
	s += "</td>"
	s += "</tr>"
	s += "</table>"
	$("#mydiv").html(s);
	TableModel();
}

// 菜单评论回调函数
function MenuMsgrollback(data) {
	var page = data.pages;
	var sum = data.total;
	var getAllMenuMsg = data.rows;

	var s = "<table align='center' border='1' width='90%'>";
	s += "<tr align='center' id='title'><th>编号</th><th>评论人</th><th>菜名</th><th>评论内容</th><th>评分</th><th>评论时间</th><th>操作</th></tr>"
	for ( var i = 0; i < getAllMenuMsg.length; i++) {
		var k = getAllMenuMsg[i];
		var str = ""
		if (k.mmscore == 1) {
			str = "★☆☆☆☆"
		} else if (k.mmscore == 2) {
			str = "★★☆☆☆"
		} else if (k.mmscore == 3) {
			str = "★★★☆☆"
		} else if (k.mmscore == 4) {
			str = "★★★★☆"
		} else if (k.mmscore == 5) {
			str = "★★★★★"
		}
		var content=""
			if (k.mmcontent.length>4) {
				content=k.mmcontent.substring(0,4)+"..."	;
			}else{
				content=k.mmcontent;
			}
		s += "<tr align='center'>"
		s += "<td >" + k.mmid + "</td>";
		s += "<td >" + k.mmusername + "</td>";
		s += "<td >" + k.mmmuname + "</td>";
		s += "<td class='tdtype' title="+k.mmcontent+">" + content+ "</td>";
		s += "<td >" + str + "</td>";
		s += "<td >" + k.mmdate + "</td>";
		s += "<td>";
		s += "<span onclick='findmenumsgbyid(" + k.mmid + ")'>修改</span>";
		s += "&nbsp;";
		s += "&nbsp;";
		s += "<span onclick='deleMenumsg(" + k.mmid + ")'>删除</span>";
		s += "</td>";
		s += "</tr>";
	}
	s += "</table>";
	s += "<table align='center' width='90%'>"
	s += "<tr valign='bottom' align='center' id='last'>"
	s += "<td colspan='3' width='100%'>"
	if (page != 1) {
		s += "<a  onclick='getMenuMsgList(1)'><font color='blue'>首页</font></a>"

	} else {
		s += "<span>首页</span>"
	}
	s += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	if (page > 1) {
		s += "<a  onclick='getMenuMsgList(" + (page - 1)
				+ ")'><font color='blue'>上一页</font></a>"
	} else {
		s += "<span>上一页</span>"
	}
	s += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	if (page < sum) {
		s += "<a  onclick='getMenuMsgList(" + (page + 1)
				+ ")'><font color='blue'>下一页</font></a>"
	} else {
		s += "<span>下一页</span>"
	}
	s += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	if (page != sum) {
		s += "<a onclick='getMenuMsgList(" + (sum)
				+ ")'><font color='blue'>尾页</font></a>"
	} else {
		s += "<span>尾页</span>"
	}
	s += "</td>"
	s += "</tr>"
	s += "</table>"
	$("#mydiv").html(s);
	TableModel();
}

// 订单回调函数
function Orderrollback(data) {
	var page = data.pages;
	var sum = data.total;
	var getAllOrder = data.rows;

	var s = "<table align='center' border='1' width='90%'>";
	s += "<tr id='title'><th>编号</th><th>下单用户</th><th>菜名</th><th>店名</th><th>数量</th><th>配送员</th><th>下单时间</th><th>UUID</th><th>订单状态</th><th>操作</th></tr>"
	for ( var i = 0; i < getAllOrder.length; i++) {
		var k = getAllOrder[i];

		var str = ""
		if (k.OSTATUS == 0) {
			str = "购物车中"
		} else if (k.OSTATUS == 1) {
			str = "已下单（未支付）"
		} else if (k.OSTATUS == 2) {
			str = "已支付"
		} else if (k.OSTATUS == 3) {
			str = "商家已接单"
		} else if (k.OSTATUS == 4) {
			str = "配送中"
		} else if (k.OSTATUS == 5) {
			str = "订单完成"
		}
		var content=""
			if (k.OUUID.length>8) {
				content=k.OUUID.substring(0,8)+"..."	;
			}else{
				content=k.OUUID;
			}
		
		var name=k.OSENDERNAME;
		if (name==undefined || name=="" || name==null) {
			name="暂无配送人员";
		}
		s += "<tr align='center'>"
		s += "<td >" + k.OID + "</td>";
		s += "<td >" + k.OUSERNAME + "</td>";
		s += "<td >" + k.OMUNAME + "</td>";
		s += "<td >" + k.ORTNAME + "</td>";
		s += "<td >" + k.OCOUNT + "</td>";
		s += "<td >" + name + "</td>";
		s += "<td >" + k.ODATE + "</td>";
		s += "<td class='tdtype' title="+k.OUUID+">" + content+ "</td>";
		
		s += "<td >" + str + "</td>";
		s += "<td>";
		s += "<span onclick='findorderbyid(" + k.OID + ")'>修改</span>";
		s += "&nbsp;";
		s += "&nbsp;";
		s += "<span onclick='deleOrder(" + k.OID + ")'>删除</span>";
		s += "</td>";
		s += "</tr>";
	}
	s += "</table>";
	s += "<table align='center' width='90%'>"
	s += "<tr valign='bottom' align='center' id='last'>"
	s += "<td colspan='3' width='100%'>"
	if (page != 1) {
		s += "<a  onclick='getOrderList(1)'><font color='blue'>首页</font></a>"

	} else {
		s += "<span>首页</span>"
	}
	s += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	if (page > 1) {
		s += "<a  onclick='getOrderList(" + (page - 1)
				+ ")'><font color='blue'>上一页</font></a>"
	} else {
		s += "<span>上一页</span>"
	}
	s += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	if (page < sum) {
		s += "<a  onclick='getOrderList(" + (page + 1)
				+ ")'><font color='blue'>下一页</font></a>"
	} else {
		s += "<span>下一页</span>"
	}
	s += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	if (page != sum) {
		s += "<a onclick='getOrderList(" + (sum)
				+ ")'><font color='blue'>尾页</font></a>"
	} else {
		s += "<span>尾页</span>"
	}
	s += "</td>"
	s += "</tr>"
	s += "</table>"
	$("#mydiv").html(s);
	TableModel();
}

// 订单支付方式回调函数
function Payrollback(data) {

	var page = data.pages;
	var sum = data.total;
	var getAllPay = data.rows;
	var s = "<table align='center' border='1' width='90%'>";
	s += "<tr id='title'><th>编号</th><th>支付方式</th><th>订单编号</th><th>操作</th></tr>"
	for ( var i = 0; i < getAllPay.length; i++) {
		var k = getAllPay[i];
		s += "<tr align='center'>"
		s += "<td >" + k.pid + "</td>";
		s += "<td >" + k.ptype + "</td>";
		s += "<td >" + k.poid + "</td>";
		s += "<td>";
		s += "<span onclick='findpaybyid(" + k.pid + ")'>修改</span>";
		s += "&nbsp;";
		s += "&nbsp;";
		s += "<span onclick='delepay(" + k.pid + ")'>删除</span>";
		s += "</td>";
		s += "</tr>";
	}
	s += "</table>";
	s += "<table align='center' width='90%'>"
	s += "<tr valign='bottom' align='center' id='last'>"
	s += "<td colspan='3' width='100%'>"
	if (page != 1) {
		s += "<a  onclick='getPayList(1)'><font color='blue'>首页</font></a>"

	} else {
		s += "<span>首页</span>"
	}
	s += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	if (page > 1) {
		s += "<a  onclick='getPayList(" + (page - 1)
				+ ")'><font color='blue'>上一页</font></a>"
	} else {
		s += "<span>上一页</span>"
	}
	s += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	if (page < sum) {
		s += "<a  onclick='getPayList(" + (page + 1)
				+ ")'><font color='blue'>下一页</font></a>"
	} else {
		s += "<span>下一页</span>"
	}
	s += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	if (page != sum) {
		s += "<a onclick='getPayList(" + (sum)
				+ ")'><font color='blue'>尾页</font></a>"
	} else {
		s += "<span>尾页</span>"
	}
	s += "</td>"
	s += "</tr>"
	s += "</table>"
	$("#mydiv").html(s);
	TableModel();
}

// 礼物回调函数
function Giftrollback(data) {
	var page = data.pages;
	var sum = data.total;
	var getAllGift = data.rows;

	var s = "<table align='center' border='1' width='90%'>";
	s += "<tr id='title'><th>编号</th><th>礼物名</th><th>图片</th><th>所需积分</th><th>已换数量</th><th>总共库存</th><th>描述</th><th>操作</th></tr>"
	for ( var i = 0; i < getAllGift.length; i++) {
		var k = getAllGift[i];
		var content=""
			if (k.gdesc.length>4) {
				content=k.gdesc.substring(0,4)+"..."	;
			}else{
				content=k.gdesc;
			}
		s += "<tr align='center'>"
		s += "<td >" + k.gid + "</td>";
		s += "<td >" + k.gname + "</td>";
		s += "<td > <img src=" + k.gpic
				+ " width='50px' heigth='50px'/></td>";
		s += "<td >" + k.greqscore + "</td>";
		s += "<td >" + k.gcount + "</td>";
		s += "<td >" + k.gsum + "</td>";
		s += "<td class='tdtype' title="+k.gdesc+">" + content+ "</td>";
		
		s += "<td>";
		s += "<span onclick='findgiftbyid(" + k.gid + ")'>修改</span>";
		s += "&nbsp;";
		s += "&nbsp;";
		s += "<span onclick='deleGift(" + k.gid + ")'>删除</span>";
		s += "</td>";
		s += "</tr>";
	}
	s += "</table>";
	s += "<table align='center' width='90%'>"
	s += "<tr valign='bottom' align='center' id='last'>"
	s += "<td colspan='3' width='100%'>"
	if (page != 1) {
		s += "<a  onclick='getGiftList(1)'><font color='blue'>首页</font></a>"

	} else {
		s += "<span>首页</span>"
	}
	s += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	if (page > 1) {
		s += "<a  onclick='getGiftList(" + (page - 1)
				+ ")'><font color='blue'>上一页</font></a>"
	} else {
		s += "<span>上一页</span>"
	}
	s += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	if (page < sum) {
		s += "<a  onclick='getGiftList(" + (page + 1)
				+ ")'><font color='blue'>下一页</font></a>"
	} else {
		s += "<span>下一页</span>"
	}
	s += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	if (page != sum) {
		s += "<a onclick='getGiftList(" + (sum)
				+ ")'><font color='blue'>尾页</font></a>"
	} else {
		s += "<span>尾页</span>"
	}
	s += "</td>"
	s += "</tr>"
	s += "</table>"
	$("#mydiv").html(s);
	TableModel();
}

// 礼物兑换记录
function GiftRecrollback(data) {
	var page = data.pages;
	var sum = data.total;
	var getAllGiftRec = data.rows;

	var s = "<table align='center' border='1' width='90%'>";
	s += "<tr id='title'><th>编号</th><th>礼物名</th><th>兑换人</th><th>兑换数量</th><th>兑换时间</th><th>是否费送</th><th>操作</th></tr>"
	for ( var i = 0; i < getAllGiftRec.length; i++) {
		var k = getAllGiftRec[i];
		var str = ""
		if (k.grstatus == 0) {
			str = "未配送"
		} else if (k.grstatus == 1) {
			str = "已配送"
		}
		s += "<tr align='center'>"
		s += "<td >" + k.grid + "</td>";
		s += "<td >" + k.grgname + "</td>";
		s += "<td >" + k.grusername + "</td>";
		s += "<td >" + k.grnum + "</td>";
		s += "<td >" + k.grdate + "</td>";
		s += "<td >" + str + "</td>";
		s += "<td>";
		s += "<span onclick='findGiftRecbyid(" + k.grid + ")'>修改</span>";
		s += "&nbsp;";
		s += "&nbsp;";
		s += "<span onclick='deleGiftRec(" + k.grid + ")'>删除</span>";
		s += "</td>";
		s += "</tr>";
	}
	s += "</table>";
	s += "<table align='center' width='90%'>"
	s += "<tr valign='bottom' align='center' id='last'>"
	s += "<td colspan='3' width='100%'>"
	if (page != 1) {
		s += "<a  onclick='getGiftRecList(1)'><font color='blue'>首页</font></a>"

	} else {
		s += "<span>首页</span>"
	}
	s += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	if (page > 1) {
		s += "<a  onclick='getGiftRecList(" + (page - 1)
				+ ")'><font color='blue'>上一页</font></a>"
	} else {
		s += "<span>上一页</span>"
	}
	s += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	if (page < sum) {
		s += "<a  onclick='getGiftRecList(" + (page + 1)
				+ ")'><font color='blue'>下一页</font></a>"
	} else {
		s += "<span>下一页</span>"
	}
	s += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	if (page != sum) {
		s += "<a onclick='getGiftRecList(" + (sum)
				+ ")'><font color='blue'>尾页</font></a>"
	} else {
		s += "<span>尾页</span>"
	}
	s += "</td>"
	s += "</tr>"
	s += "</table>"
	$("#mydiv").html(s);
	TableModel();
}


// 分页查询用户
function getUserList(pages) {
	$.ajax( {
		url : "shwkuser!getUserList.action?v=" + Math.random(),
		type : "post",
		data : {
			"page" : pages
		},
		dataType : "json",
		success : Userrollback
	})
}

// 分页查询商家
function getRestList(pages) {
	$.ajax( {
		url : "shwkrest!getRestList.action?v=" + Math.random(),
		type : "post",
		data : {
			"page" : pages
		},
		dataType : "json",
		success : Restrollback
	})
}

//分页查询申请开店商家
function getSomeRestList(pages){
	$.ajax( {
		url : "shwkrest!getSomeRestList.action?v=" + Math.random(),
		type : "post",
		data : {
			"page" : pages
		},
		dataType : "json",
		success : SomeRestrollback
	})
}

// 分页查询商家评论
function getMessList(pages) {
	$.ajax( {
		url : "shwkmess!getMessList.action?v=" + Math.random(),
		type : "post",
		data : {
			"page" : pages
		},
		dataType : "json",
		success : Messrollback
	})
}

// 分页查询菜单
function getMenuList(pages) {
	$.ajax( {
		url : "shwkmenu!getMenuList.action?v=" + Math.random(),
		type : "post",
		data : {
			"page" : pages
		},
		dataType : "json",
		success : Menurollback
	})
}

// 分页查询菜单评论
function getMenuMsgList(pages) {
	$.ajax( {
		url : "shwkmenumsg!getMenuMsgList.action?v=" + Math.random(),
		type : "post",
		data : {
			"page" : pages
		},
		dataType : "json",
		success : MenuMsgrollback
	})
}

// 分页查询订单
function getOrderList(pages) {
	$.ajax( {
		url : "shwkorder!getOrderList.action?v=" + Math.random(),
		type : "post",
		data : {
			"page" : pages
		},
		dataType : "json",
		success : Orderrollback
	})
}

// 分页查询订单支付方式
function getPayList(pages) {
	$.ajax( {
		url : "shwkpay!getPayList.action?v=" + Math.random(),
		type : "post",
		data : {
			"page" : pages
		},
		dataType : "json",
		success : Payrollback
	})
}

// 分页查询礼品
function getGiftList(pages) {
	$.ajax( {
		url : "shwkugift!getGiftList.action?v=" + Math.random(),
		type : "post",
		data : {
			"page" : pages
		},
		dataType : "json",
		success : Giftrollback
	})
}

// 分页查询礼品兑换记录
function getGiftRecList(pages) {
	$.ajax( {
		url : "shwkgiftrec!getGiftRecordList.action?v=" + Math.random(),
		type : "post",
		data : {
			"page" : pages
		},
		dataType : "json",
		success : GiftRecrollback
	})
}

// 通过id查询用户
function findbyid(id) {
	$.ajax( {
				url : "shwkuser!FindbyId.action?v=" + Math.random(),
				type : "post",
				data : {
					"id" : id
				},
				dataType : "json",
				success : function(data) {
					var us = data.user;
					var s = "<form  action='shwkuser!UpdaUser.action'  method='post' enctype='multipart/form-data'>";
					s += "<table align='center' style='margin-top: 30px'>";
					s += "<tr><td><input type='hidden' id='userid' name='userid' value="
							+ us.userid + " /></td></tr>"
					s += "<tr><td>用户名:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type='text' id='username' name='username' value="
							+ us.username + " onblur='inputname()'/>&nbsp;&nbsp;<span id='uname'></span></td></tr>"
					s += "<tr><td>密码：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type='text' id='pwd' name='pwd' value="
							+ us.pwd + " onblur='userpwd()'/>&nbsp;&nbsp;<span id='upwd'></span></td></tr>"
					s += "<tr><td>电话：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type='text' id='tel' name='tel' value="
							+ us.tel + " onblur='usertel()'/>&nbsp;&nbsp;<span id='utel'></span></td></tr>"
					s += "<tr><td>邮件：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type='text' id='email' name='email' value="
							+ us.email + " onblur='useremail()'/>&nbsp;&nbsp;<span id='uemail'></span></td></tr>"
					s += "<tr><td>地址：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type='text' id='address' name='address'value="
							+ us.address + " onblur='useraddress()'/>&nbsp;&nbsp;<span id='uaddress'></span></td></tr>"
					s += "<tr><td>真实姓名：<input type='text' id='realname' name='realname' value="
							+ us.realname + " onblur='userrealname()'/>&nbsp;&nbsp;<span id='urealname'></span></td></tr>"
					s += "<tr><td>余额：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type='text' id='balance' name='balance' value="
							+ us.balance + " onblur='userbalance()'/>&nbsp;&nbsp;<span id='ubalance'></span></td></tr>"
					s += "<tr><td>积分：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type='text' id='score' name='score' value="
							+ us.score + " onblur='userscore()'/>&nbsp;&nbsp;<span id='uscore'></span></td></tr>"
					s += "<tr><td>注册日期：<input type='date' id='regdate' name='regdate' value="
							+ us.regdate + " /></td></tr>"
					if (us.gender == '男') {
						s += "<tr><td>性别:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type='radio' id='gender' name='gender' value='男' checked='checked'>男<input type='radio' id='gender' name='gender' value='女'> 女</td></tr>"
					} else {
						s += "<tr><td>性别:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type='radio' id='gender' name='gender' value='男' >男<input type='radio' id='gender' name='gender' value='女' checked='checked'> 女</td></tr>"
					}
					if (us.authority == 1) {
						s += "<tr><td>权限:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<select id='authority' name='authority'><option value='1' selected='selected'>普通用户</option><option value='2'>配送员</option><option value='3'>店主</option><option value='4'>普通管理员</option><option value='5'>系统管理员</option> </select></td></tr>"
					} else if (us.authority == 2) {
						s += "<tr><td>权限:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<select id='authority' name='authority'><option value='1' >普通用户</option><option value='2' selected='selected'>配送员</option><option value='3'>店主</option><option value='4'>普通管理员</option><option value='5'>系统管理员</option> </select></td></tr>"
					} else if (us.authority == 3) {
						s += "<tr><td>权限:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<select id='authority' name='authority'><option value='1' >普通用户</option><option value='2'>配送员</option><option value='3' selected='selected'>店主</option><option value='4'>普通管理员</option><option value='5'>系统管理员</option> </select></td></tr>"
					} else if (us.authority == 4) {
						s += "<tr><td>权限:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<select id='authority' name='authority'><option value='1' >普通用户</option><option value='2'>配送员</option><option value='3'>店主</option><option value='4' selected='selected'>普通管理员</option><option value='5'>系统管理员</option> </select></td></tr>"
					} else {
						s += "<tr><td>权限:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<select id='authority' name='authority'><option value='1' >普通用户</option><option value='2'>配送员</option><option value='3'>店主</option><option value='4'>普通管理员</option><option value='5' selected='selected'>系统管理员</option> </select></td></tr>"
					}
					s += "<tr><td><input type='hidden' id='pic' name='pic' value="
							+ us.photo + " /></td></tr>"
					s += "<tr><td>图片：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="
							+ us.photo
							+ " width=50px height:50px/>&nbsp;&nbsp;&nbsp;&nbsp;<input type='file' id='photo' name='photo' accept='image/gif, image/jpeg, image/png'/></td></tr>"
					s += "<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;<input type='submit' value='提交'/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type='button' id='btn' value='取消'/></td></tr>"
					s += "</table>"
					s += "</form>"
					$("#mydiv").html(s);
					$("#btn").click(
							function() {
								$.getJSON("shwkuser!getUserList.action?v="
										+ Math.random(), Userrollback)
							})
				}
			})
}

// 通过id查询商家
function findrestbyid(id) {
	$.ajax( {url : "shwkrest!FindRestbyId.action?v=" + Math.random(),
				type : "post",
				data : {
					"id" : id
				},
				dataType : "json",
				success : function(data) {
					var ts = data.rest;
					$
							.getJSON(
									"shwkuser!GetAllOwner.action?v=" + Math.random(),
									function(data) {
										var getAllUser = data.rows;
										var str = "";
										for ( var i = 0; i < getAllUser.length; i++) {
											var k = getAllUser[i]
											if (ts.rtowner == k.userid) {
												str += "<option value="
														+ k.userid
														+ "  selected='selected'>"
														+ k.username
														+ "</option>"
											} else {
												str += "<option value="
														+ k.userid + ">"
														+ k.username
														+ "</option>"
											}

										}
										var s = "<form  action='shwkrest!UpdaRest.action'  method='post' enctype='multipart/form-data'>";
										s += "<table align='center' style='margin-top: 30px'>";
										s += "<tr><td><input type='hidden' id='rtid' name='rtid' value="
												+ ts.rtid + " /></td></tr>"
										s += "<tr><td>店名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type='text' id='rtname' name='rtname' value="
												+ ts.rtname + " onblur='myrtname()'/>&nbsp;&nbsp;<span id='mrtname'></span></td></tr>"
										s += "<tr><td>地址：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type='text' id='rtaddr' name='rtaddr' value="
												+ ts.rtaddr + " onblur='myrtaddr()'/>&nbsp;&nbsp;<span id='mrtaddr'></span></td></tr>"
										s += "<tr><td>店主：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<select id='rtowner' name='rtowner'>"
												+ str + "<select></td></tr>"
										s += "<tr><td><input type='hidden' id='pic' name='pic' value="
												+ ts.rtstatus + " /></td></tr>"
										s += "<tr><td>图片：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="
												+ ts.rtpic
												+ " width=50px height:50px/>&nbsp;&nbsp;<input type='file' id='photo' name='photo' accept='image/gif, image/jpeg, image/png'/></td></tr>"
										s += "<tr><td>公告:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<textarea rows='3' cols='20' id='rtcontent' name='rtcontent'>"
												+ ts.rtcontent
												+ "</textarea></td></tr>"
										s += "<tr><td>开店时间：<input type='date' id='rtdate' name='rtdate' value="
												+ ts.rtdate + " /></td></tr>"
										s += "<tr><td>营业时间：<input type='text' id='rtonbuz' name='rtonbuz'value="
												+ ts.rtonbuz + " onblur='myrtonbuz()'/>&nbsp;&nbsp;<span id='mrtonbuz'></span></td></tr>"
										if (ts.rtstatus == 0) {
											s += "<tr><td>开店认证:&nbsp;&nbsp;<select id='rtstatus' name='rtstatus'><option value='0' selected='selected'>已通过</option><option value='1'>待审核</option> </select></td></tr>"
										} else if (ts.rtstatus == 1) {
											s += "<tr><td>开店认证:&nbsp;&nbsp;<select id='rtstatus' name='rtstatus'><option value='0' >已通过</option><option value='1' selected='selected'>待审核</option> </select></td></tr>"
										}
										s += "<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;<input type='submit' value='提交'/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type='button' id='btn' value='取消'/></td></tr>"
										s += "</table>"
										s += "</form>"
										$("#mydiv").html(s);
										$("#btn")
												.click(
														function() {
															$
																	.getJSON(
																			"shwkrest!getRestList.action?v="
																					+ Math
																							.random(),
																			Restrollback)
														})
									})
				}
			})
}

// 通过id查询商家评论
function findmessbyid(id) {
	$
			.ajax( {
				url : "shwkmess!FindMessbyId.action?v=" + Math.random(),
				type : "post",
				data : {
					"id" : id
				},
				dataType : "json",
				success : function(data) {
					var ts = data.mess;
					var str = "";
					var str2 = "";
					$
							.getJSON(
									"shwkuser!getAllUser.action?v=" + Math.random(),
									function(data) {
										var getAllUser = data.rows;
										for ( var i = 0; i < getAllUser.length; i++) {
											var k = getAllUser[i]
											if (ts.muserid == k.userid) {
												str += "<option value="
														+ k.userid
														+ "  selected='selected'>"
														+ k.username
														+ "</option>"
											} else {
												str += "<option value="
														+ k.userid + ">"
														+ k.username
														+ "</option>"
											}

										}
										$
												.getJSON(
														"shwkrest!getAllRest.action?v="
																+ Math.random(),
														function(data) {
															var getAllRest = data.rows;
															for ( var i = 0; i < getAllRest.length; i++) {
																var k = getAllRest[i]
																if (ts.mrtid == k.rtid) {
																	str2 += "<option value="
																			+ k.rtid
																			+ "  checked='checked'>"
																			+ k.rtname
																			+ "</option>"
																} else {
																	str2 += "<option value="
																			+ k.rtid
																			+ ">"
																			+ k.rtname
																			+ "</option>"
																}
															}

															var s = "<form  action='shwkmess!UpdaMess.action'  method='post' enctype='multipart/form-data'>";
															s += "<table align='center' style='margin-top: 30px'>";
															s += "<tr><td><input type='hidden' id='mid' name='mid' value="
																	+ ts.mid
																	+ " /></td></tr>"
															s += "<tr><td>用户：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<select id='muserid' name='muserid'>"
																	+ str
																	+ "<select></td></tr>"
															s += "<tr><td>店铺名：&nbsp;&nbsp;&nbsp;&nbsp;<select id='mrtid' name='mrtid'>"
																	+ str2
																	+ "<select></td></tr>"
															s += "<tr><td>评论：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<textarea rows='3' cols='20' id='mcontent' name='mcontent'>"
																	+ ts.mcontent
																	+ "</textarea></td></tr>"
															s += "<tr><td>开店时间：<input type='date' id='mdate' name='mdate' value="
																	+ ts.mdate
																	+ " /></td></tr>"
															s += "<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;<input type='submit' value='提交'/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type='button' id='btn' value='取消'/></td></tr>"
															s += "</table>"
															s += "</form>"
															$("#mydiv").html(s);
															$("#btn")
																	.click(
																			function() {
																				$
																						.getJSON(
																								"shwkmess!getMessList.action?v="
																										+ Math
																												.random(),
																								Messrollback)
																			})
														})
									})
				}
			})
}

// 通过id查询菜单
function findmenubyid(id) {
	$
			.ajax( {
				url : "shwkmenu!FindMenubyId.action?v=" + Math.random(),
				type : "post",
				data : {
					"id" : id
				},
				dataType : "json",
				success : function(data) {
					var ts = data.menu;
					var str = "";
					$
							.getJSON(
									"shwkrest!getAllRest.action?v="
											+ Math.random(),
									function(data) {
										var getAllRest = data.rows;
										for ( var i = 0; i < getAllRest.length; i++) {
											var k = getAllRest[i]
											if (ts.murtid == k.rtid) {
												str += "<option value="
														+ k.rtid
														+ "  selected='selected'>"
														+ k.rtname
														+ "</option>"
											} else {
												str += "<option value="
														+ k.rtid + ">"
														+ k.rtname
														+ "</option>"
											}
										}
										var s = "<form  action='shwkmenu!UpdaMenu.action'  method='post' enctype='multipart/form-data'>";
										s += "<table align='center' style='margin-top: 30px'>";
										s += "<tr><td><input type='hidden' id='muid' name='muid' value="
												+ ts.muid + " /></td></tr>"
										s += "<tr><td>店铺名：&nbsp;&nbsp;<select id='murtid' name='murtid'>"
												+ str + "<select></td></tr>"
										s += "<tr><td>菜名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type='text' id='muname' name='muname' value="
												+ ts.muname + " onblur='mymuname()'/>&nbsp;&nbsp;<span id='mmuname'></span></td></tr>"
										s += "<tr><td>价格：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type='text' id='muprice' name='muprice' value="
												+ ts.muprice + " onblur='mymuprice()'/>&nbsp;&nbsp;<span id='mmuprice'></span></td></tr>"
										s += "<tr><td><input type='hidden' id='pic' name='pic' value="
												+ ts.mupic + " /></td></tr>"
										s += "<tr><td>图片：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="
												+ ts.mupic
												+ " width=50px height:50px/>"
												+ "<input type='file' id='photo' name='photo' accept='image/gif, image/jpeg, image/png'/></td></tr>"
										s += "<tr><td>类型：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type='text' id='mutype' name='mutype' value="
												+ ts.mutype + " onblur='mymutype()'/>&nbsp;&nbsp;<span id='mmutype'></span></td></tr>"
										s += "<tr><td>描述：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<textarea rows='3' cols='20' id='mudesc' name='mudesc'>"+ ts.mudesc + " </textarea>"
										s += "<tr><td>销量：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type='text' id='musale' name='musale' value="
												+ ts.musale + " onblur='mymusale()'/>&nbsp;&nbsp;<span id='mmusale'></span></td></tr>"
										if (ts.mustatus == 0) {
											s += "<tr><td>是否售完:<select id='mustatus' name='mustatus'><option value='0' selected='selected'>正在销售</option><option value='1'>已售完</option></td></tr>"
										} else if (ts.mustatus == 1) {
											s += "<tr><td>是否售完:&nbsp;<select id='mustatus' name='mustatus'><option value='0' >正在销售</option><option value='1' selected='selected'>已售完</option></select></td></tr>"
										}
										s += "<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type='submit' value='提交'/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type='button' id='btn' value='取消'/></td></tr>"
										s += "</table>"
										s += "</form>"
										$("#mydiv").html(s);
										$("#btn")
												.click(
														function() {
															$
																	.getJSON(
																			"shwkmenu!getMenuList.action?v="
																					+ Math
																							.random(),
																			Userrollback)
														})
									})
				}
			})
}

// 通过id查询菜单评论
function findmenumsgbyid(id) {
	$
			.ajax( {
				url : "shwkmenumsg!FindMenuMsgbyId.action?v=" + Math.random(),
				type : "post",
				data : {
					"id" : id
				},
				dataType : "json",
				success : function(data) {
					var ts = data.menumsg;
					var str = "";
					var str2 = "";
					$
							.getJSON(
									"shwkuser!getAllUser.action?v=" + Math.random(),
									function(data) {
										var getAllUser = data.rows;
										for ( var i = 0; i < getAllUser.length; i++) {
											var k = getAllUser[i]
											if (ts.mmuserid == k.userid) {
												str += "<option value="
														+ k.userid
														+ "  selected='selected'>"
														+ k.username
														+ "</option>"
											} else {
												str += "<option value="
														+ k.userid + ">"
														+ k.username
														+ "</option>"
											}
										}
										$
												.getJSON(
														"shwkmenu!getAllMenu.action?v="
																+ Math.random(),
														function(data) {
															var getAllMenu = data.rows;
															for ( var i = 0; i < getAllMenu.length; i++) {
																var k = getAllMenu[i]
																if (ts.mmmuid == k.muid) {
																	str2 += "<option value="
																			+ k.muid
																			+ "  selected='selected'>"
																			+ k.muname
																			+ "</option>"
																} else {
																	str2 += "<option value="
																			+ k.muid
																			+ ">"
																			+ k.muname
																			+ "</option>"
																}
															}

															var s = "<form  action='shwkmenumsg!UpdaMenuMsg.action'  method='post' enctype='multipart/form-data'>";
															s += "<table align='center' style='margin-top: 30px'>";
															s += "<tr><td><input type='hidden' id='mmid' name='mmid' value="
																	+ ts.mmid
																	+ " /></td></tr>"
															s += "<tr><td>用户：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<select id='mmuserid' name='mmuserid'>"
																	+ str
																	+ "<select></td></tr>"
															s += "<tr><td>菜名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<select id='mmmuid' name='mmmuid'>"
																	+ str2
																	+ "<select></td></tr>"
															s += "<tr><td>评论：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<textarea rows='3' cols='20' id='mmcontent' name='mmcontent'>"
																	+ ts.mmcontent
																	+ "</textarea></td></tr>"
															if (ts.mmscore == 1) {
																s += "<tr><td>评分:<input type='radio' id='mmscore' name='mmscore' value='1' checked='checked'>★☆☆☆☆"
																		+ "<input type='radio' id='mmscore' name='mmscore' value='2'>★★☆☆☆"
																		+ "<input type='radio' id='mmscore' name='mmscore' value='3'>★★★☆☆"
																		+ "<input type='radio' id='mmscore' name='mmscore' value='4'>★★★★☆"
																		+ "<input type='radio' id='mmscore' name='mmscore' value='5'>★★★★★</td></tr>"
															} else if (ts.mmscore == 2) {
																s += "<tr><td>评分:<input type='radio' id='mmscore' name='mmscore' value='1' >★☆☆☆☆"
																		+ "<input type='radio' id='mmscore' name='mmscore' value='2' checked='checked'>★★☆☆☆"
																		+ "<input type='radio' id='mmscore' name='mmscore' value='3'>★★★☆☆"
																		+ "<input type='radio' id='mmscore' name='mmscore' value='4'>★★★★☆"
																		+ "<input type='radio' id='mmscore' name='mmscore' value='5'>★★★★★</td></tr>"
															} else if (ts.mmscore == 3) {
																s += "<tr><td>评分:<input type='radio' id='mmscore' name='mmscore' value='1' >★☆☆☆☆"
																		+ "<input type='radio' id='mmscore' name='mmscore' value='2'>★★☆☆☆"
																		+ "<input type='radio' id='mmscore' name='mmscore' value='3' checked='checked'>★★★☆☆"
																		+ "<input type='radio' id='mmscore' name='mmscore' value='4'>★★★★☆"
																		+ "<input type='radio' id='mmscore' name='mmscore' value='5'>★★★★★</td></tr>"
															} else if (ts.mmscore == 4) {
																s += "<tr><td>评分:<input type='radio' id='mmscore' name='mmscore' value='1' >"
																		+ "★☆☆☆☆<input type='radio' id='mmscore' name='mmscore' value='2'>★★☆☆☆"
																		+ "<input type='radio' id='mmscore' name='mmscore' value='3'>★★★☆☆"
																		+ "<input type='radio' id='mmscore' name='mmscore' value='4' checked='checked'>★★★★☆"
																		+ "<input type='radio' id='mmscore' name='mmscore' value='5'>★★★★★</td></tr>"
															} else if (ts.mmscore == 5) {
																s += "<tr><td>评分:<input type='radio' id='mmscore' name='mmscore' value='1' >★☆☆☆☆"
																		+ "<input type='radio' id='mmscore' name='mmscore' value='2'>★★☆☆☆"
																		+ "<input type='radio' id='mmscore' name='mmscore' value='3'>★★★☆☆"
																		+ "<input type='radio' id='mmscore' name='mmscore' value='4'>★★★★☆"
																		+ "<input type='radio' id='mmscore' name='mmscore' value='5' checked='checked'>★★★★★</td></tr>"
															}
															s += "<tr><td>评论时间：<input type='date' id='mmdate' name='mmdate' value="
																	+ ts.mmdate
																	+ " /></td></tr>"
															s += "<tr><td><input type='submit' value='提交'/><input type='button' id='btn' value='取消'/></td></tr>"
															s += "</table>"
															s += "</form>"
															$("#mydiv").html(s);
															$("#btn")
																	.click(
																			function() {
																				$
																						.getJSON(
																								"shwkmenumsg!getMenuMsgList.action?v="
																										+ Math
																												.random(),
																								MenuMsgrollback)
																			})
														})
									})
				}
			})
}

// 通过id查询订单
function findorderbyid(id) {
	$
			.ajax( {
				url : "shwkorder!FindOrderbyId.action?v=" + Math.random(),
				type : "post",
				data : {
					"id" : id
				},
				dataType : "json",
				success : function(data) {
					var ts = data.order;
					var str = "";
					var str2 = "";
					var str3 = "";
					var str4 = "";
					$.getJSON("shwkuser!getAllUser.action?v=" + Math.random(),
							function(data) {
									var getAllUser = data.rows;
									for ( var i = 0; i < getAllUser.length; i++) {
										var k = getAllUser[i]
										if (ts.ouserid == k.userid) {
											str += "<option value="+ k.userid+ "  selected='selected'>"+ k.username+ "</option>"
										} else {
											str += "<option value="+ k.userid + ">"+ k.username+ "</option>"
										}
									}
					$.getJSON("shwkuser!GetAllSender.action?v=" + Math.random(),
						   function(data) {
									var getAllUser = data.rows;
									for ( var i = 0; i < getAllUser.length; i++) {
										var k = getAllUser[i]
										if (ts.osender == k.userid) {
												str2 += "<option value="+ k.userid+ "  selected='selected'>"+ k.username+ "</option>"
										} else {
												str2 += "<option value="+ k.userid + ">"+ k.username+ "</option>"
										}

									}
					$.getJSON("shwkrest!getAllRest.action?v="+ Math.random(),
							function(data) {
									var getAllRest = data.rows;
									for ( var i = 0; i < getAllRest.length; i++) {
										var k = getAllRest[i]
										if (ts.ortid == k.rtid) {
												str3 += "<option value="+ k.rtid+ "  selected='selected'>"+ k.rtname+ "</option>"
										} else {
												str3 += "<option value="+ k.rtid+ ">"+ k.rtname+ "</option>"
																}
															}
															$.getJSON("shwkmenu!getAllMenuByRest.action?v="+ Math.random()+ "&murtid="+ ts.ortid,
																			function(
																					data) {
																				var getAllMenu = data.rows;

																				for ( var i = 0; i < getAllMenu.length; i++) {
																					var k = getAllMenu[i]
																					if (ts.omuid == k.muid) {
																						str4 += "<option value="
																								+ k.muid
																								+ "  selected='selected'>"
																								+ k.muname
																								+ "</option>"
																					} else {
																						str4 += "<option value="
																								+ k.muid
																								+ ">"
																								+ k.muname
																								+ "</option>"
																					}

																				}
																				var s = "<form  action='shwkorder!UpdaOrder.action'  method='post' enctype='multipart/form-data'>";
																				s += "<table align='center' style='margin-top: 30px'>";
																				s += "<tr><td><input type='hidden' id='oid' name='oid' value="
																						+ ts.oid
																						+ " /></td></tr>"
																				s += "<tr><td>下单用户：<select id='ouserid' name='ouserid'>"
																						+ str
																						+ "<select></td></tr>"
																				s += "<tr><td>店家：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<select id='ortid' onchange='ortidchange("
																						+ ts.omuid
																						+ ")' name='ortid'>"
																						+ str3
																						+ "<select></td></tr>"
																				s += "<tr><td>菜名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<select id='omuid' name='omuid'>"
																						+ str4
																						+ "<select></td></tr>"
																				s += "<tr><td>数量：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type='text' style='width:80px' id='ocount' name='ocount' value="
																						+ ts.ocount
																						+ " onblur='myocount()'/>&nbsp;&nbsp;<span id='mocount'></span></td></tr>"
																				s += "<tr><td>配送员：&nbsp;&nbsp;&nbsp;&nbsp;<select id='osender' name='osender'>"
																						+ str2
																						+ "<select></td></tr>"
																				s += "<tr><td>下单日期：<input type='date' id='odate' name='odate' value="
																						+ ts.odate
																						+ " /></td></tr>"
																				s += "<tr><td>OUUID："+ts.ouuid+"</td></tr>"
																				if (ts.osattus == 0) {
																					s += "<tr><td>订单状态：<select id='ostatus' name='ostatus'><option value='0' selected='selected'>购物车中</option><option value='1'>已下单（未支付）</option><option value='2'>已支付</option><option value='3'>商家已接单</option><option value='4'>配送中</option> <option value='5'>订单完成</option></select></td></tr>"
																				} else if (ts.osattus == 1) {
																					s += "<tr><td>订单状态：<select id='ostatus' name='ostatus'><option value='0' >购物车中</option><option value='1' selected='selected'>已下单（未支付）</option><option value='2'>已支付</option><option value='3'>商家已接单</option><option value='4'>配送中</option> <option value='5'>订单完成</option></select></td></tr>"
																				} else if (ts.osattus == 2) {
																					s += "<tr><td>订单状态：<select id='ostatus' name='ostatus'><option value='0' >购物车中</option><option value='1'>已下单（未支付）</option><option value='2' selected='selected'>已支付</option><option value='3'>商家已接单</option><option value='4'>配送中</option><option value='5'>订单完成</option> </select></td></tr>"
																				} else if (ts.osattus == 3) {
																					s += "<tr><td>订单状态：<select id='ostatus' name='ostatus'><option value='0' >购物车中</option><option value='1'>已下单（未支付）</option><option value='2'>已支付</option><option value='3' selected='selected'>商家已接单</option><option value='4'>配送中</option><option value='5'>订单完成</option> </select></td></tr>"
																				} else if (ts.osattus == 4) {
																					s += "<tr><td>订单状态：<select id='ostatus' name='ostatus'><option value='0' >购物车中</option><option value='1'>已下单（未支付）</option><option value='2'>已支付</option><option value='3'>商家已接单</option><option value='4' selected='selected'>配送中</option><option value='5'>订单完成</option> </select></td></tr>"
																				} else {
																					s += "<tr><td>订单状态：<select id='ostatus' name='ostatus'><option value='0' >购物车中</option><option value='1'>已下单（未支付）</option><option value='2'>已支付</option><option value='3'>商家已接单</option><option value='4'>配送中</option> <option value='5' selected='selected'>订单完成</option></select></td></tr>"
																				}

																				s += "<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;<input type='submit' value='提交'/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type='button' id='btn' value='取消'/></td></tr>"
																				s += "</table>"
																				s += "</form>"
																				$(
																						"#mydiv")
																						.html(
																								s);
																				$(
																						"#btn")
																						.click(
																								function() {
																									$
																											.getJSON(
																													"shwkorder!getOrderList.action?v="
																															+ Math
																																	.random(),
																													Orderrollback)
																								})
																			});
														})
										})
									})
				}
			})
}

// 通过id查询订单支付方式
function findpaybyid(id) {
	$
			.ajax( {
				url : "shwkpay!FindPaybyId.action?v=" + Math.random(),
				type : "post",
				data : {
					"id" : id
				},
				dataType : "json",
				success : function(data) {
					var ts = data.pay;
					var s = "<form  action='shwkpay!UpdaPay.action'  method='post' enctype='multipart/form-data'>";
					s += "<table align='center' style='margin-top: 30px'>";
					s += "<tr><td><input type='hidden' id='pid' name='pid' value="
							+ ts.pid + " /></td></tr>"
					s += "<tr><td>订单编号：" + ts.poid + "</td></tr>"
					if (ts.ptype == '余额支付') {
						s += "<tr><td>支付方式:<input type='radio' id='ptype' name='ptype' value='余额支付' checked='checked'>余额支付"
								+ "<input type='radio' id='ptype' name='ptype' value='货到付款'>货到付款</td></tr>"
					} else if (ts.ptype == '货到付款') {
						s += "<tr><td>评分:<input type='radio' id='ptype' name='ptype' value='余额支付' >余额支付"
								+ "<input type='radio' id='ptype' name='ptype' value='货到付款' checked='checked'>货到付款</td></tr>"
					}
					s += "<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type='submit' value='提交'/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type='button' id='btn' value='取消'/></td></tr>"
					s += "</table>"
					s += "</form>"
					$("#mydiv").html(s);
					$("#btn").click(
							function() {
								$.getJSON("shwkpay!getPayList.action?v="
										+ Math.random(), Payrollback)
							})
				}
			})
}

// 通过id查询礼品
function findgiftbyid(id) {
	$
			.ajax( {
				url : "shwkugift!findGiftById.action?v=" + Math.random(),
				type : "post",
				data : {
					"id" : id
				},
				dataType : "json",
				success : function(data) {
					var ts = data.gift;
					var s = "<form  action='shwkugift!UpdaGift.action'  method='post' enctype='multipart/form-data'>";
					s += "<table align='center' style='margin-top: 30px'>";
					s += "<tr><td><input type='hidden' id='gid' name='gid' value="
							+ ts.gid + " /></td></tr>"
					s += "<tr><td>礼品名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type='text' id='gname' name='gname' value="
							+ ts.gname + " onblur='mygname()'/>&nbsp;&nbsp;<span id='mgname'></span></td></tr>"
					s += "<tr><td><input type='hidden' id='pic' name='pic' value="
							+ ts.gpic + " /></td></tr>"
					s += "<tr><td>图片：&nbsp;&nbsp;&nbsp;&nbsp;<img src="
							+ ts.gpic
							+ " width=50px height:50px/>&nbsp;&nbsp;&nbsp;<input type='file' id='photo' name='photo' accept='image/gif, image/jpeg, image/png'/></td></tr>"
					s += "<tr><td>所需积分：&nbsp;&nbsp;&nbsp;<input type='text' id='greqscore' name='greqscore' value="
							+ ts.greqscore + " onblur='mygreqscore()'/>&nbsp;&nbsp;<span id='mgreqscore'></span></td></tr>"
					s += "<tr><td>已兑换数量：<input type='text' id='gcount' name='gcount' value="
							+ ts.gcount + " onblur='mygcount()'/>&nbsp;&nbsp;<span id='mgcount'></span></td></tr>"
					s += "<tr><td>总库存数量：<input type='text' id='gsum' name='gsum'value="
							+ ts.gsum + " onblur='mygsum()'/>&nbsp;&nbsp;<span id='mgsum'></span></td></tr>"
					s += "<tr><td>公告：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<textarea rows='3' cols='20' id='gdesc' name='gdesc'>"
							+ ts.gdesc + "</textarea></td></tr>"
					s += "<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type='submit' value='提交'/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type='button' id='btn' value='取消'/></td></tr>"
					s += "</table>"
					s += "</form>"
					$("#mydiv").html(s);
					$("#btn").click(
							function() {
								$.getJSON("shwkugift!getGiftList.action?v="
										+ Math.random(), Giftrollback)
							})
				}
			})
}

// 通过id查询礼品兑换记录
function findGiftRecbyid(id) {
	$
			.ajax( {
				url : "shwkgiftrec!FindGiftRecordbyId.action?v="
						+ Math.random(),
				type : "post",
				data : {
					"id" : id
				},
				dataType : "json",
				success : function(data) {
					var ts = data.grec;
					var str = "";
					var str2 = "";
					$.getJSON("shwkuser!getAllUser.action?v=" + Math.random(),
									function(data) {
										var getAllUser = data.rows;
										for ( var i = 0; i < getAllUser.length; i++) {
											var k = getAllUser[i]
											if (ts.gruserid == k.userid) {
												str += "<option value="
														+ k.userid
														+ "  selected='selected'>"
														+ k.username
														+ "</option>"
											}else{
											str += "<option value=" + k.userid
													+ ">" + k.username
													+ "</option>"
											}
										}
										$.getJSON("shwkugift!getAllGift.action?v="+ Math.random(),
														function(data) {
															var getAllGift = data.rows;
															for ( var i = 0; i < getAllGift.length; i++) {
																var k = getAllGift[i]
																if (ts.grgid == k.gid) {
																	str2 += "<option value="
																			+ k.gid
																			+ "  selected='selected'>"
																			+ k.gname
																			+ "</option>"
																}else{
																str2 += "<option value="
																		+ k.gid
																		+ ">"
																		+ k.gname
																		+ "</option>"
																}
															}

															var s = "<form  action='shwkgiftrec!UpdaGiftRecord.action'  method='post' enctype='multipart/form-data'>";
															s += "<table align='center' style='margin-top: 30px'>";
															s += "<tr><td><input type='hidden' id='grid' name='grid' value="
																	+ ts.grid
																	+ " /></td></tr>"
															s += "<tr><td>兑换人：&nbsp;&nbsp;&nbsp;<select id='gruserid' name='gruserid'>"
																	+ str
																	+ "<select></td></tr>"
															s += "<tr><td>礼物名：&nbsp;&nbsp;&nbsp;<select id='grgid' name='grgid'>"
																	+ str2
																	+ "<select></td></tr>"
															s += "<tr><td>兑换数量：<input type='text' id='grnum' name='grnum' value="
																	+ ts.grnum
																	+ " onblur='mygrnum()'/>&nbsp;&nbsp;<span id='mgrnum'></span></td></tr>"
															s += "<tr><td>兑换日期：<input type='date' id='grdate' name='grdate' value="
																	+ ts.grdate
																	+ " /></td></tr>"
															if (ts.grstatus == 0) {
																s += "<tr><td>是否配送:&nbsp;&nbsp;&nbsp;<select id='grstatus' name='grstatus'><option value='0' selected='selected'>未配送</option><option value='1'>已配送</option></select></td></tr>"
															} else if (ts.grstatus == 1) {
																s += "<tr><td>是否配送:&nbsp;&nbsp;&nbsp;<select id='grstatus' name='grstatus'><option value='0' >未配送</option><option value='1' selected='selected'>已配送</option></select></td></tr>"
															}
															s += "<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type='submit' value='提交'/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type='button' id='btn' value='取消'/></td></tr>"
															s += "</table>"
															s += "</form>"
															$("#mydiv").html(s);
															$("#btn")
																	.click(
																			function() {
																				$.getJSON("shwkgiftrec!getGiftRecordList.action?v="
																						+ Math.random(), GiftRecrollback)
																			});
														})
									})
				}
			})
}

// 删除用户
function deleUser(id) {
	var ret=confirm("确认删除这个用户吗？")
	if (ret==true) {
		$.ajax( {
			url : "shwkuser!deleUser.action?v=" + Math.random(),
			type : "post",
			data : {
				"id" : id
			},
			dataType : "json",
			success : function() {
				$.getJSON("shwkuser!getUserList.action?v=" + Math.random(),
						Userrollback);
			}
		})
	}else{
		$.getJSON("shwkuser!getUserList.action?v=" + Math.random(),
				Userrollback);
	}

}

// 删除商家
function deleRest(id) {
	var ret=confirm("确认删除这个商家吗？")
	if (ret==true) {
		$.ajax( {
			url : "shwkrest!deleRest.action?v=" + Math.random(),
			type : "post",
			data : {
				"id" : id
			},
			dataType : "json",
			success : function() {
				$.getJSON("shwkrest!getRestList.action?v=" + Math.random(),
						Restrollback);
			}
		})
	}else{
		$.getJSON("shwkrest!getRestList.action?v=" + Math.random(),
				Restrollback);
	}
	
}

// 删除商家评论
function deleMess(id) {
	var ret=confirm("确认删除这条商家评论吗？")
	if (ret==true) {
		$.ajax( {
			url : "shwkmess!deleMess.action?v=" + Math.random(),
			type : "post",
			data : {
				"id" : id
			},
			dataType : "json",
			success : function() {
				$.getJSON("shwkmess!getMessList.action?v=" + Math.random(),
						Messrollback)
			}
		})
	}else{
		$.getJSON("shwkmess!getMessList.action?v=" + Math.random(),
				Messrollback)
	}
	
}

// 删除菜单
function deleMenu(id) {
	var ret=confirm("确认删除这个菜吗？")
	if (ret==true) {
		$.ajax( {
			url : "shwkmenu!deleMenu.action?v=" + Math.random(),
			type : "post",
			data : {
				"id" : id
			},
			dataType : "json",
			success : function() {
				$.getJSON("shwkmenu!getMenuList.action?v=" + Math.random(),
						Menurollback)
			}
		})
	}else{
		$.getJSON("shwkmenu!getMenuList.action?v=" + Math.random(),
				Menurollback)
	}
	
}

// 删除菜单评论
function deleMenumsg(id) {
	var ret=confirm("确认删除这条菜单评论吗？")
	if (ret==true) {
		$.ajax( {
			url : "shwkmenumsg!deleMenuMsg.action?v=" + Math.random(),
			type : "post",
			data : {
				"id" : id
			},
			dataType : "json",
			success : function() {
				$.getJSON("shwkmenumsg!getMenuMsgList.action?v=" + Math.random(),
						MenuMsgrollback)
			}
		})
	}else{
		$.getJSON("shwkmenumsg!getMenuMsgList.action?v=" + Math.random(),
				MenuMsgrollback)
	}
	
}

// 删除订单
function deleOrder(id) {
	var ret=confirm("确认删除这个订单吗？")
	if (ret==true) {
		$.ajax( {
			url : "shwkorder!deleOrder.action?v=" + Math.random(),
			type : "post",
			data : {
				"id" : id
			},
			dataType : "json",
			success : function() {
				$.getJSON("shwkorder!getOrderList.action?v=" + Math.random(),
						Orderrollback)
			}
		})
	}else{
		$.getJSON("shwkorder!getOrderList.action?v=" + Math.random(),
				Orderrollback)
	}
	
}

// 删除订单支付
function delepay(id) {
	var ret=confirm("确认删除这个支付信息吗？")
	if (ret==true) {
		$.ajax( {
			url : "shwkpay!delePay.action?v=" + Math.random(),
			type : "post",
			data : {
				"id" : id
			},
			dataType : "json",
			success : function() {
				$.getJSON("shwkpay!getPayList.action?v=" + Math.random(),
						Payrollback)
			}
		})
	}else{
		$.getJSON("shwkpay!getPayList.action?v=" + Math.random(),
				Payrollback)
	}

}

// 删除礼品
function deleGift(id) {
	var ret=confirm("确认删除这件礼品吗？")
	if (ret==true) {
		$.ajax( {
			url : "shwkugift!deleGift.action?v=" + Math.random(),
			type : "post",
			data : {
				"id" : id
			},
			dataType : "json",
			success : function() {
				$.getJSON("shwkugift!getGiftList.action?v=" + Math.random(),
						Giftrollback)
			}
		})
	}else{
		$.getJSON("shwkugift!getGiftList.action?v=" + Math.random(),
				Giftrollback)
	}
	
}

// 删除礼品兑换记录
function deleGiftRec(id) {
	var ret=confirm("确认删除这条记录吗？")
	if (ret==true) {
		$.ajax( {
			url : "shwkgiftrec!deleGiftRecord.action?v=" + Math.random(),
			type : "post",
			data : {
				"id" : id
			},
			dataType : "json",
			success : function() {
				$.getJSON(
						"shwkgiftrec!getGiftRecordList.action?v=" + Math.random(),
						GiftRecrollback)
			}
		})
	}else{
		$.getJSON(
				"shwkgiftrec!getGiftRecordList.action?v=" + Math.random(),
				GiftRecrollback)
	}
	
}

// 通过店的id查询菜名
function ortidchange(omuid) {
	$("#omuid").empty();
	var str = "";
	$.getJSON("shwkmenu!getAllMenuByRest.action?v=" + Math.random()
			+ "&murtid=" + $("#ortid").val(), function(data) {
		var getAllMenu = data.rows;

		for ( var i = 0; i < getAllMenu.length; i++) {
			var k = getAllMenu[i]
			if (omuid == k.muid) {
				str += "<option value=" + k.muid + "  selected='selected'>"
						+ k.muname + "</option>"
			} else {
				str += "<option value=" + k.muid + ">" + k.muname + "</option>"
			}
		}
		$("#omuid").append(str)
	});
}









// 验证用户名
function inputname() {
	$("#uname").html("");
	var username = $("#username").val()
	if (username == "") {
		var s = "<font color='red'>※：</font>用户名不能为空"
		$("#uname").html(s);
		$("#username").val("");
		this.$("#username").focus()
	} else if (username.length < 4 || username.length > 12) {
		var s = "<font color='red'>※：</font>用户名长度需在4-12个字符之间"
		$("#uname").html(s);
		$("#username").val("")
		this.$("#username").focus()
	}

};

//验证密码
function userpwd() {
	$("#upwd").html("");
	var pwd = $("#pwd").val()
	var reg = /^\w{6,20}$/;
	if (pwd == "") {
		var s = "<font color='red'>※：</font>密码不能为空"
		$("#upwd").html(s);
		$("#pwd").val("")
		this.$("#pwd").focus()
	} else if (pwd.length < 6 || pwd.length > 16) {
		var s = "<font color='red'>※：</font>密码长度需在6-16个字符之间"
		$("#upwd").html(s);
		$("#pwd").val("")
		this.$("#pwd").focus()
	} else if (!reg.test(pwd)) {
		var s = "<font color='red'>※：</font>密码必须由字母或数字组成"
		$("#upwd").html(s);
		$("#pwd").val("")
		this.$("#pwd").focus()
	}
}

//验证电话号码
function usertel(){
	$("#utel").html("");
	var tel=$("#tel").val();
	var reg = /^1(3|5|8)\d{9}$|^\d{3}-\d{8}$|^\d{4}-\d{7,8}$/;
	if (tel == "") {
		var s = "<font color='red'>※：</font>电话号码不能为空"
		$("#utel").html(s);
		$("#tel").val("")
		this.$("#tel").focus()
	} else if (!reg.test(tel)) {
		var s = "<font color='red'>※：</font>电话号码格式不正确"
		$("#utel").html(s);
		$("#tel").val("")
		this.$("#tel").focus()
	} 
}

//验证email
function useremail(){
	$("#uemail").html("");
	var email=$("#email").val();
	var reg =/^\w+@\w{2,5}\.(com|cn)$/;
	if (email == "") {
		var s = "<font color='red'>※：</font>邮件不能为空"
		$("#uemail").html(s);
		$("#email").val("")
		this.$("#email").focus()
	} else if (!reg.test(email)) {
		var s = "<font color='red'>※：</font>邮件格式不正确"
		$("#uemail").html(s);
		$("#email").val("")
		this.$("#email").focus()
	} 
}

//验证用户地址
function useraddress(){
	$("#uaddress").html("");
	var address=$("#address").val();
	if (address == "") {
		var s = "<font color='red'>※：</font>地址不能为空"
		$("#uaddress").html(s);
		$("#address").val("")
		this.$("#address").focus()
	}
}

//验证真实姓名
function userrealname(){
	$("#urealname").html("");
	var realname=$("#realname").val();
	if (realname == "") {
		var s = "<font color='red'>※：</font>真实姓名不能为空"
		$("#urealname").html(s);
		$("#realname").val("")
		this.$("#realname").focus()
	}else if (realname.length < 4 || realname.length > 12) {
		var s = "<font color='red'>※：</font>真实姓名长度需在4-12个字符之间"
			$("#urealname").html(s);
			$("#realname").val("")
			this.$("#realname").focus()
		}
}

//验证余额输入的是否有误
function userbalance(){
	$("#ubalance").html("");
	var reg =/^(0|[1-9][0-9]*)$|^[0-9]+(.[0-9]{1,2})?$/;
	var balance=$("#balance").val();
	if (balance == "") {
		var s = "<font color='red'>※：</font>余额不能为空"
		$("#ubalance").html(s);
		$("#balance").val("")
		this.$("#balance").focus()
	}else if (!reg.test(balance)) {
		var s = "<font color='red'>※：</font>余额必须由数字或数字加小数点组成，例如：1,1.0,1.00"
			$("#ubalance").html(s);
			$("#balance").val("")
			this.$("#balance").focus()
		}
}

//验证积分输入的是否有误
function userscore(){
	$("#uscore").html("");
	var reg =/^(0|[1-9][0-9]*)$/;
	var balance=$("#score").val();
	if (balance == "") {
		var s = "<font color='red'>※：</font>积分不能为空"
		$("#uscore").html(s);
		$("#score").val("")
		this.$("#score").focus()
	}else if (!reg.test(balance)) {
		var s = "<font color='red'>※：</font>积分必须是正整数或者0"
			$("#uscore").html(s);
			$("#score").val("")
			this.$("#score").focus()
		}
}

//验证店名
function myrtname(){
	$("#mrtname").html("");
	var rtname=$("#rtname").val();
	if (rtname == "") {
		var s = "<font color='red'>※：</font>店名不能为空"
		$("#mrtname").html(s);
		$("#rtname").val("")
		this.$("#rtname").focus()
	}else if (rtname.length < 4 || rtname.length > 12) {
		var s = "<font color='red'>※：</font>店名长度需在4-12个字符之间"
			$("#mrtname").html(s);
			$("#rtname").val("")
			this.$("#rtname").focus()
		}
}

//验证商店地址
function myrtaddr(){
	$("#mrtaddr").html("");
	var address=$("#rtaddr").val();
	if (address == "") {
		var s = "<font color='red'>※：</font>地址不能为空"
		$("#mrtaddr").html(s);
		$("#rtaddr").val("")
		this.$("#rtaddr").focus()
	}
}

//验证上的营业时间
function myrtonbuz(){
	$("#mrtonbuz").html("");
	var rtonbuz=$("#rtonbuz").val();
	var reg = /^(\d{1,2}):(\d{1,2})-(\d{1,2}):(\d{1,2})$/;
	if (rtonbuz == "") {
		var s = "<font color='red'>※：</font>营业时间不能为空"
		$("#mrtonbuz").html(s);
		$("#rtonbuz").val("")
		this.$("#rtonbuz").focus()
	} else if (!reg.test(rtonbuz)) {
		var s = "<font color='red'>※：</font>营业时间格式不正确"
		$("#mrtonbuz").html(s);
		$("#rtonbuz").val("")
		this.$("#rtonbuz").focus()
	} 
}

//验证菜名
function mymuname(){
	$("#mmuname").html("");
	var muname=$("#muname").val();
	if (muname == "") {
		var s = "<font color='red'>※：</font>菜名不能为空"
		$("#mmuname").html(s);
		$("#muname").val("")
		this.$("#muname").focus()
	}else if (muname.length < 4 || muname.length > 12) {
		var s = "<font color='red'>※：</font>菜名长度需在4-12个字符之间"
			$("#mmuname").html(s);
			$("#muname").val("")
			this.$("#muname").focus()
		}
}

//验证菜的价格
function mymuprice(){
	$("#mmuprice").html("");
	var reg =/^(0|[1-9][0-9]*)$|^[0-9]+(.[0-9]{1,2})?$/;
	var muprice=$("#muprice").val();
	if (muprice == "") {
		var s = "<font color='red'>※：</font>菜的单价不能为空"
		$("#mmuprice").html(s);
		$("#muprice").val("")
		this.$("#muprice").focus()
	}else if (!reg.test(muprice)) {
		var s = "<font color='red'>※：</font>菜的单价必须由数字或数字加小数点组成，例如：1,1.0,1.00"
			$("#mmuprice").html(s);
			$("#muprice").val("")
			this.$("#muprice").focus()
		}
}

//验证菜的类型
function mymutype(){
	$("#mmutype").html("");
	var mutype=$("#mutype").val();
	if (mutype == "") {
		var s = "<font color='red'>※：</font>菜的类型不能为空"
		$("#mmutype").html(s);
		$("#mutype").val("")
		this.$("#mutype").focus()
	}else if (mutype.length < 4 || mutype.length > 12) {
		var s = "<font color='red'>※：</font>菜的类型长度需在4-12个字符之间"
			$("#mmutype").html(s);
			$("#mutype").val("")
			this.$("#mutype").focus()
		}
}

//验证菜的销量
function mymusale(){
	$("#mmusale").html("");
	var reg =/^(0|[1-9][0-9]*)$/;
	var musale=$("#musale").val();
	if (musale == "") {
		var s = "<font color='red'>※：</font>销量不能为空"
		$("#mmusale").html(s);
		$("#musale").val("")
		this.$("#musale").focus()
	}else if (!reg.test(musale)) {
		var s = "<font color='red'>※：</font>销量必须是正整数或者0"
			$("#mmusale").html(s);
			$("#musale").val("")
			this.$("#musale").focus()
		}
}

//验证某个菜的订单数量
function myocount(){
	$("#mocount").html("");
	var reg =/^(0|[1-9][0-9]*)$/;
	var ocount=$("#ocount").val();
	if (ocount == "") {
		var s = "<font color='red'>※：</font>订单数量不能为空"
		$("#mocount").html(s);
		$("#ocount").val("")
		this.$("#ocount").focus()
	}else if (!reg.test(ocount)) {
		var s = "<font color='red'>※：</font>订单数量必须是正整数或者0"
			$("#mocount").html(s);
			$("#ocount").val("")
			this.$("#ocount").focus()
		}
}

//验证礼品名
function mygname(){
	$("#mgname").html("");
	var gname=$("#gname").val();
	if (gname == "") {
		var s = "<font color='red'>※：</font>礼品名不能为空"
		$("#mgname").html(s);
		$("#gname").val("")
		this.$("#gname").focus()
	}else if (gname.length < 4 || gname.length > 12) {
		var s = "<font color='red'>※：</font>礼品名长度需在4-12个字符之间"
			$("#mgname").html(s);
			$("#gname").val("")
			this.$("#gname").focus()
		}
}

//验证礼品所需积分
function mygreqscore(){
	$("#mgreqscore").html("");
	var reg =/^([1-9][0-9]*)$/;
	var greqscore=$("#greqscore").val();
	if (greqscore == "") {
		var s = "<font color='red'>※：</font>礼品所需积分不能为空"
		$("#mgreqscore").html(s);
		$("#greqscore").val("")
		this.$("#greqscore").focus()
	}else if (!reg.test(greqscore)) {
		var s = "<font color='red'>※：</font>礼品所需积分必须是正整数"
			$("#mgreqscore").html(s);
			$("#greqscore").val("")
			this.$("#greqscore").focus()
		}
}

//验证已兑换数量
function mygcount(){
	$("#mgcount").html("");
	var reg =/^(0|[1-9][0-9]*)$/;
	var gcount=$("#gcount").val();
	if (gcount == "") {
		var s = "<font color='red'>※：</font>礼品已兑换数不能为空"
		$("#mgcount").html(s);
		$("#gcount").val("")
		this.$("#gcount").focus()
	}else if (!reg.test(gcount)) {
		var s = "<font color='red'>※：</font>礼品已兑换数必须是正整数"
			$("#mgcount").html(s);
			$("#gcount").val("")
			this.$("#gcount").focus()
	}
}

//验证总库存数
function mygsum(){
	$("#mgsum").html("");
	var reg =/^(0|[1-9][0-9]*)$/;
	var gcount=$("#gcount").val();
	var gsum=$("#gsum").val();
	if (gsum == "") {
		var s = "<font color='red'>※：</font>礼品总库存数不能为空"
		$("#mgsum").html(s);
		$("#gsum").val("")
		this.$("#gsum").focus()
	}else if (!reg.test(gsum)) {
		var s = "<font color='red'>※：</font>礼品总库存数必须是正整数"
			$("#mgsum").html(s);
			$("#gsum").val("")
			this.$("#gsum").focus()
	}else if(gsum<gcount){
		var s = "<font color='red'>※：</font>礼品总库存数必须大于礼品已兑换数"
			$("#mgsum").html(s);
			$("#gsum").val("")
			this.$("#gsum").focus()
	}
}

//验证礼品兑换数量
function mygrnum(){
	$("#mgrnum").html("");
	var reg =/^([1-9][0-9]*)$/;
	var grnum=$("#grnum").val();
	if (grnum == "") {
		var s = "<font color='red'>※：</font>礼品已兑换数不能为空"
		$("#mgrnum").html(s);
		$("#grnum").val("")
		this.$("#grnum").focus()
	}else if (!reg.test(grnum)) {
		var s = "<font color='red'>※：</font>礼品已兑换数必须是正整数"
			$("#mgrnum").html(s);
			$("#grnum").val("")
			this.$("#grnum").focus()
	}
}

//表格样式
function TableModel(){
	$("table").css("margin-top","20px");
	$("th").css("height","15px");
	$("td").css("height","55px");
	   
	$("tr:first").css("background-color","#00a5a5").css("color","#fbfbfb");						   
	$("tr:not(#title,#last)").hover(
			function(){
				$(this).css("background-color","#666666");
				$(this).css("color","#fbfbfb");
			},function(){
				$(this).css("background-color","#f6f6f6");
				$(this).css("color","#5e5e5e");
			}
		);
}

