$(function() {

    // 站点数据统计
    $("#tab_zdsjtj label").click(function () {
        $(this).addClass("active").siblings().removeClass("active");
        var txt = $(this).context.innerText;
        var type;
        if ('今日' == txt) {
            type = "1";
        } else if ('本周' == txt) {
            type = "2"
        } else if ('本月' == txt) {
            type = "3";
        } else {
            type = "4";
        }
        var url = "getZdsjtj.ht";
        var data = {type: type};
        $.ajax({
            type: "post",
            async: false,  //同步请求
            url: url,
            data: data,
            dataType: "json",
            success: function (result) {
                //console.log(result);
                var html = "<ul>";
                html += "<li>";
                html += "<a href=\"#\">";
                html += "<div class=\"col-md-4\"><img src=\"" + result.ctx + "/bstj/images/data-icon01.png\"></div>";
                html += "<div class=\"col-md-8\">";
                html += "<p class=\"num\">" + result.allservice + "</p>";
                html += "<p class=\"text\">接入的服务数</p>";
                html += "</div>";
                html += "</a>";
                html += "</li>";
                html += "<li>";
                html += "<a href=\"#\">";
                html += "<div class=\"col-md-4\"><img src=\"" + result.ctx + "/bstj/images/data-icon02.png\"></div>";
                html += "<div class=\"col-md-8\">";
                html += "<p class=\"num\">" + result.apply + "</p>";
                html += "<p class=\"text\">发起的流程总数</p>";
                html += "</div>";
                html += "</a>";
                html += "</li>";
                html += "<li>";
                html += "<a href=\"#\">";
                html += "<div class=\"col-md-4\"><img src=\"" + result.ctx + "/bstj/images/data-icon03.png\"></div>";
                html += "<div class=\"col-md-8\">";
                html += "<p class=\"num\">" + result.done + "</p>";
                html += "<p class=\"text\">流程办结总数</p>";
                html += "</div>";
                html += "</a>";
                html += "</li>";
                html += "<li>";
                html += "<a href=\"#\">";
                html += "<div class=\"col-md-4\"><img src=\"" + result.ctx + "/bstj/images/data-icon04.png\"></div>";
                html += "<div class=\"col-md-8\">";
                html += "<p class=\"num\">" + result.timeout + "</p>";
                html += "<p class=\"text\">超时未办结总数</p>";
                html += "</div>";
                html += "</a>";
                html += "</li>";
                html += "<li>";
                html += "<a href=\"#\">";
                html += "<div class=\"col-md-4\"><img src=\"" + result.ctx + "/bstj/images/data-icon05.png\"></div>";
                html += "<div class=\"col-md-8\">";
                html += "<p class=\"num\">" + result.todo + "</p>";
                html += "<p class=\"text\">待办流程总数</p>";
                html += "</div>";
                html += "</a>";
                html += "</li>";
                html += "</ul>";
                $("#zdsjtj").html(html);
            },
            error: function () {
                alert("失败，请稍后再试！");
            }
        });
    });

    // 查看事项办理统计1
    $("#tab01 label").click(function() {
        $(this).addClass("active").siblings().removeClass("active");
        var txt=$(this).context.innerText;
        var type;
        if('今日' == txt){
            type = "1";
        }else if('本周' == txt){
            type = "2"
        }else if('本月' == txt){
            type = "3";
        }else{
            type = "4";
        }
        $.ajax({
            type: "post",
            url: "getSxbltj.ht",
            data: {
                type : type
            },
            dataType:"json",
            success : function(json) {
                $('.bstj-content .statistics-div #infoTable').bootstrapTable('load', json);
                var defId="";
                var lh ="";
                $("#infoTable tbody tr").hover(function(){
                    lh =  $(this).index();
                    defId= json[lh].defId;
                    $("[data-toggle='popover']").popover({
                        html : true,
                        delay:{show:500, hide:1000},
                        content: function() {
                            return showNodeAvgTime(defId);
                        }
                    });

                });
            }
        });


    });

    // 查看事项办理统计3
    $("#tab03 label").click(function() {
        $(this).addClass("active").siblings().removeClass("active");
        var txt=$(this).context.innerText;
        var type;
        if('今日' == txt){
            type = "1";
        }else if('本周' == txt){
            type = "2"
        }else if('本月' == txt){
            type = "3";
        }else{
            type = "4";
        }
        $.ajax({
            type: "post",
            url: "getRycstj.ht",
            data: {

                type : type
            },
            dataType:"json",
            success : function(json) {
                $('.bstj-content .statistics-div #infoTabletwo').bootstrapTable('load', json);
            }
        });
    });

    // 内容Top10事项统计
    $('#tab_ten label').click(function() {
        $(this).addClass("active").siblings().removeClass("active");
        var txt=$(this).context.innerText;
        var type;
        if('今日' == txt){
            type = "1";
        }else if('本周' == txt){
            type = "2"
        }else if('本月' == txt){
            type = "3";
        }else{
            type = "4";
        }
        $.ajax({
            type: "post",
            url: "getSxtjforten.ht",
            data: {
                type : type
            },
            dataType:"json",
            success : function(json) {
                $('.bstj-content .statistics-div #rankingTable').bootstrapTable('load', json);
            }
        });
    });

    // 查看事项办理统计1
    $('.bstj-content .statistics-div #infoTable').bootstrapTable({
        url: 'getSxbltj.ht',			//请求后台的URL（*）
        method: 'get',                      //请求方式（*）
        pageNumber: 1,                      //初始化加载第一页，默认第一页
        pageSize: 10,                       //每页的记录行数（*）
        pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
        showRefresh: true,                  //是否显示刷新按钮
        showColumns: true,                  //是否显示所有的列
        pagination: true,
        paginationPreText:'<',
        paginationNextText:'>',
        locale:'zh-CN',                      //中文支持
        columns: [
            {
                field: 'id', // 返回json数据中的name
                title: '序号', // 表格表头显示文字
                align: 'center', // 左右居中
                valign: 'middle', // 上下居中
                width: '5%',
                formatter : function(value, row, index) {
                    return index+1;
                }
            }, {
                field: 'name',
                title: '服务名称',
                align: 'center',
                valign: 'middle',
                width: '30%',
                formatter : function(value, row, index) {
                    return '<a href="getSxbltjxq.ht?defId='+row.defId+'">'+value+'</a>';
                }
            }, {
                field: 'versionNo',
                title: '版本号',
                align: 'center',
                valign: 'middle',
                width: '5%',
            }, {
                field: 'time',
                title: '平均耗时',
                align: 'center',
                valign: 'middle',
                width: '15%',
                formatter:function(value,row,index){
return '<a  onclick="showNodeAvgTime('+row.defId+','+row.name+')" id="'+row.defId+'" class="bind_hover_card" title="'+row.name+'" data-container="body" data-trigger="hover" data-toggle="popover" data-placement="right">'+value+' </a>';
                }
            }, {
                field: 'sponsor',
                title: '发起流量总数',
                align: 'center',
                valign: 'middle',
                width: '5%',
            }, {
                field: 'finished',
                title: '已办结数',
                align: 'center',
                valign: 'middle',
                width: '5%',
            }, {
                field: 'timeout',
                title: '超时未办结数',
                align: 'center',
                valign: 'middle',
                width: '5%',
            }, {
                field: 'rate',
                title: '办结率',
                align: 'center',
                valign: 'middle',
                width: '15%',
                formatter : function(value, row, index) {
                    return '<div class="progress progress-md"><div style="width: '+value+';" class="progress-bar progress-bar-mint">'+value+'</div></div>';
                }
            }],
        onLoadSuccess: function(data){  //加载成功时执行
            console.info("加载成功");
            //清空搜索框和时间框
            $('#sxbltjfw').val('');
            $('[name="dateClean"]').val('');
            searchModel.Start = null;
            searchModel.End = null;

            var defId="";
            var lh ="";
            $("#infoTable tbody tr").hover(function(){
                lh =  $(this).index();
                defId= data[lh].defId;
                $("[data-toggle='popover']").popover({
                    html : true,
                    delay:{show:500, hide:1000},
                    content: function() {
                        return showNodeAvgTime(defId);
                    }
                });

            });

        },
        onLoadError: function(){  //加载失败时执行
            console.info("加载数据失败");
        },
        onPageChange:function(){
            //更改页码，重新初始化popover窗口
            var defid="";
            var lh ="";
            $("#infoTable td").hover(function(){
                    lh =  $(this).parent().parent().find("tr").index($(this).parent()[0]);
                    if(lh!=null){
                        $("#infoTable td .bind_hover_card").hover(function(){
                            defid = $("#infoTable td .bind_hover_card")[lh].id;
                        });
                        console.log("defid:"+defid);
                        $("[data-toggle='popover']").popover({
                            html : true,
                            delay:{show:500, hide:1000},
                            content: function() {
                                return showNodeAvgTime(defid);
                            }
                        });
                    }

            });
        },
        onColumnSwitch:function(){
            //隐藏列，重新初始化popover窗口
            var defid="";
            var lh ="";
            $("#infoTable td").hover(function(){
                lh =  $(this).parent().parent().find("tr").index($(this).parent()[0]);

                    $("#infoTable td .bind_hover_card").hover(function(){
                        defid = $("#infoTable td .bind_hover_card")[lh].id;
                    });
                    $("[data-toggle='popover']").popover({
                        html : true,
                        delay:{show:500, hide:1000},
                        content: function() {
                            return showNodeAvgTime(defid);
                        }
                    });

            });
        }
    });

    // 查看事项办理统计3
    $('.bstj-content .statistics-div #infoTabletwo').bootstrapTable({
        url: 'getRycstj.ht',			//请求后台的URL（*）
        method: 'get',                      //请求方式（*）
        pageNumber: 1,                      //初始化加载第一页，默认第一页
        pageSize: 10,                       //每页的记录行数（*）
        pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
        showRefresh: true,                  //是否显示刷新按钮
        showColumns: true,                  //是否显示所有的列
        pagination: true,
        paginationPreText:'<',
        paginationNextText:'>',
        locale:'zh-CN',                      //中文支持
        columns: [
            {
                field: 'id', // 返回json数据中的name
                title: '序号', // 表格表头显示文字
                align: 'center', // 左右居中
                valign: 'middle', // 上下居中
                width: '5%',
                formatter : function(value, row, index) {
                    return index+1;
                }
            }, {
                field: 'name',
                title: '审批人',
                align: 'center',
                valign: 'middle',
                width: '15%',
            }, {
                field: 'ssyx',
                title: '所属院系',
                align: 'center',
                valign: 'middle',
                width: '15%',
                formatter : function(value, row, index) {
                    if(value == null){
                        return '<span class="red">未设置院系</span>';
                    }else{
                        return value;
                    }
                }
            }, {
                field: 'pjhs',
                title: '平均耗时',
                align: 'center',
                valign: 'middle',
                width: '30%',
            }, {
                field: 'splczs',
                title: '审批流程总数',
                align: 'center',
                valign: 'middle',
                width: '5%',
            }, {
                field: 'timeout',
                title: '超时审批数',
                align: 'center',
                valign: 'middle',
                width: '5%',
            }, {
                field: 'csspl',
                title: '超时审批率',
                align: 'center',
                valign: 'middle',
                width: '15%',
                formatter : function(value, row, index) {
                    return '<div class="progress progress-md"><div style="width: '+value+';" class="progress-bar progress-bar-mint">'+value+'</div></div>';
                }
            }],
        onLoadSuccess: function(){  //加载成功时执行
            console.info("加载成功");

            //清空搜索框和时间框
            $("#ssyx_rycs").val("");
            $('[name="dateClean"]').val('');
            searchModel.Start = null;
            searchModel.End = null;

        },
        onLoadError: function(){  //加载失败时执行
            console.info("加载数据失败");
        }
    });


    //内容Top10事项统计
    $('.bstj-content .statistics-div #rankingTable').bootstrapTable({
        url: 'getSxtjforten.ht',			//请求后台的URL（*）
        method: 'get',                      //请求方式（*）
        columns: [
            {
                field: 'order', // 返回json数据中的name
                title: '排行榜', // 表格表头显示文字
                align: 'center', // 左右居中
                valign: 'middle', // 上下居中
                width: '5%',
            }, {
                field: 'name',
                title: '服务名称',
                align: 'center',
                valign: 'middle',
                width: '85%',
            }, {
                field: 'versionNo',
                title: '版本号',
                align: 'center',
                valign: 'middle',
                width: '5%',
            }, {
                field: 'amount',
                title: '使用量',
                align: 'center',
                valign: 'middle',
                width: '10%',
            }],
        onLoadSuccess: function(){  //加载成功时执行
            console.info("加载成功");
        },
        onLoadError: function(){  //加载失败时执行
            console.info("加载数据失败");
        }
    });

    //双日历插件
    var searchModel = {
        Start: null,
        End: null
    };

    $("#date01,#date02,#date03,#date04").daterangepicker({
            datePicker: true,
            showDropdowns: true,
            showWeekNumbers: false,
            autoUpdateInput: false,
            ranges: {
                '今日': [moment().startOf('day'), moment()],
                '昨天': [moment().subtract('days', 1).startOf('day'), moment().subtract('days', 1).endOf('day')],
                '最近7日': [moment().subtract('days', 6), moment()]
            },
            alwaysShowCalendars: true,
            opens: 'right',
            buttonClasses: ['btn btn-default'],
            applyClass: 'btn-small btn-primary blue',
            cancelClass: 'btn-small',
            "locale": {
                format: 'YYYY-MM-DD',
                customRangeLabel: "自定义",
                applyLabel: "确定",
                cancelLabel: "清除",
                daysOfWeek: ["日", "一", "二", "三", "四", "五", "六"],
                monthNames: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"]
            }
        },
        function(start, end) {
            searchModel.Start = start.format('YYYY-MM-DD 00:00:00');
            searchModel.End = end.format('YYYY-MM-DD 23:59:59');
        }
    );

    //清空日期
    $('[name="dateClean"]').on('cancel.daterangepicker', function(ev, picker) {
        $('[name="dateClean"]').val('');
        searchModel.Start = null;
        searchModel.End = null;
    });
    //tab切换
    $(".bstj-content .statistics-div .left-div .nav-pills>li>a").click(function(){
        var index=$(".bstj-content .statistics-div .left-div .nav-pills>li>a").index(this);
        $("#sxtjIndex").val(index);
    });

});

function sxbltjcx(){
    var fwmc = $("#sxbltjfw").val();
    var dataTmie = $("#date01").val();
    var starttime = dataTmie.substring(0,10);
    var endtime = dataTmie.substring(13,23);

    if(starttime!="undefined"&&starttime!=null&&starttime!=""&&starttime.length>0){
        starttime = starttime + " 00:00:00";
    }
    if(endtime!="undefined"&&endtime!=null&&endtime!=""&&endtime.length>0){
        endtime = endtime + " 23:59:59";
    }

    $.ajax({
        type: "post",
        url: "getSxbltj.ht",
        data: {
            //sxtjIndex: sxtjIndex,
            subject: fwmc,
            starttime: starttime,
            endtime: endtime
        },
        dataType:"json",
        success : function(json) {
            $('.bstj-content .statistics-div #infoTable').bootstrapTable('load', json);

            var defId="";
            var lh ="";
            $("#infoTable tbody tr").hover(function(){
                lh =  $(this).index();
                defId= json[lh].defId;
                $("[data-toggle='popover']").popover({
                    html : true,
                    delay:{show:500, hide:1000},
                    content: function() {
                        return showNodeAvgTime(defId);
                    }
                });

            });

        }
    });
}

function rycstjcx(){
    var ssyx = $("#ssyx_rycs").val();
    var dataTmie = $("#date03").val();
    var starttime = dataTmie.substring(0,10);
    var endtime = dataTmie.substring(13,23);

    if(starttime!="undefined"&&starttime!=null&&starttime!=""&&starttime.length>0){
        starttime = starttime + " 00:00:00";
    }
    if(endtime!="undefined"&&endtime!=null&&endtime!=""&&endtime.length>0){
        endtime = endtime + " 23:59:59";
    }
    //console.log('fwmc='+fwmc+';dataTmie='+dataTmie+';starttime='+starttime+';endtime='+endtime);

    $.ajax({
        type: "post",
        url: "getRycstj.ht",
        data: {
            ssyx: ssyx,
            starttime: starttime,
            endtime: endtime
        },
        dataType:"json",
        success : function(json) {
            $('.bstj-content .statistics-div #infoTabletwo').bootstrapTable('load', json);
        }
    });
}

function showNodeAvgTime(defId){
    var con ="";
    var fwmc = $("#sxbltjfw").val();
    var dataTmie = $("#date01").val();
    var starttime = dataTmie.substring(0,10);
    var endtime = dataTmie.substring(13,23);

    if(starttime!="undefined"&&starttime!=null&&starttime!=""&&starttime.length>0){
        starttime = starttime + " 00:00:00";
    }
    if(endtime!="undefined"&&endtime!=null&&endtime!=""&&endtime.length>0){
        endtime = endtime + " 23:59:59";
    }

    $.ajax({
        type: "post",
        url: "getNodeAvgTime.ht",
        data: {
            defId:defId,
            subject: fwmc,
            starttime: starttime,
            endtime: endtime
        },
        dataType:"json",
        async : false,
        success : function(result) {
            var dataObj = result;
            if (dataObj.length > 0) {
                con += '<table class="table-task" cellpadding="0" cellspacing="0" border="0">';
                con += '<tr><th>节点名称</th><th>节点平均耗时</th></tr>';
            }
            $.each(dataObj, function(index, item){
                con += "<tr>";
                con += "<td>"+item.taskName+"</td>";
                con += "<td>"+item.time+"</td>";
                con += "</tr>";
            });
            if (dataObj.length > 0) {
                con += '</table>';
            }
        }
    });
    if(con!=null||con!=""){
        return con;
    }
}