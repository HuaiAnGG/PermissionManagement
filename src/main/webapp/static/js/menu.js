$(function () {

    $("#menu_datagrid").datagrid({
        url: "/menuList",
        columns: [[
            {field: 'text', title: '名称', width: 1, align: 'center'},
            {field: 'url', title: '跳转地址', width: 1, align: 'center'},
            {
                field: 'parent', title: '父菜单', width: 1, align: 'center', formatter: function (value, row, index) {
                    return value ? value.text : '';
                }
            }
        ]],
        fit: true,
        rownumbers: true,
        singleSelect: true,
        striped: true,
        pagination: true,
        fitColumns: true,
        toolbar: '#menu_toolbar'
    });

    /*
     * 初始化新增/编辑对话框
     */
    $("#menu_dialog").dialog({
        width: 300,
        height: 250,
        closed: true,
        buttons: '#menu_dialog_bt'
    });

    $("#add").click(function () {
        $("#menu_dialog").dialog("open");
    });

    $('#parentMenu').combobox({
        width: 150,
        panelHeight: 'auto',
        textField: 'text',     /*文本显示*/
        valueField: 'id',    /*提交到服务器的值*/
        editable: false,
        url: '/getMenuParentList',
        onLoadSuccess: function () {
            $("#parentMenu").each(function (i) {
                var span = $(this).siblings('span')[i];
                var targetInput = $(span).find("input:first");
                if (targetInput) {
                    $(targetInput).attr('placeholder', $(this).attr('placeholder'));
                }
            });
        }
    });


    /**
     * 点击按钮事件监听
     */
    $('#add').click(function () {
        // 清空上次提交的信息
        $('#employeeForm').form('clear');

        // 显示对话框
        $("#menu_dialog").dialog({'title': '添加菜单'});
        $('#menu_dialog').dialog({'closed': false});
    });

    /**
     * 点击编辑按钮事件监听
     */
    $('#edit').click(function () {
        // 获取选中
        var rowData = $('#menu_datagrid').datagrid('getSelected');
        if (!rowData) {
            $.messager.alert('提示', '请选择一行数据进行编辑!');
            return;
        }

        // 清空权限和角色之间关系
        $('#parentMenu').combobox('clear');
        // 显示对话框
        $("#menu_dialog").dialog({'title': '编辑菜单'});
        $('#menu_dialog').dialog("open");
        // 处理id、名称、父菜单、父类id等信息
        if (rowData.parent) {
            rowData['parent.id'] = rowData.parent.id;
        }else {
            // 没有数据回显 placeholder
            $("#parentMenu").each(function (i) {
                var span = $(this).siblings('span')[i];
                var targetInput = $(span).find("input:first");
                if (targetInput) {
                    $(targetInput).attr('placeholder', $(this).attr('placeholder'));
                }
            });
        }
        // 数据回显
        $('#menu_form').form('load', rowData);
    });

    /**
     * 离职按钮事件处理
     */
    $('#delete').click(function () {
        // 获取选中
        var rowData = $('#menu_datagrid').datagrid('getSelected');
        if (!rowData) {
            $.messager.alert('提示', '请选择一行数据进行删除!');
            return;
        }
        /*提醒用户是否继续操作*/
        $.messager.confirm("确认", "是否删除该菜单", function (res) {
            if (res) {
                // 继续离职操作
                $.get('/deleteMenu?id=' + rowData.id, function (data) {
                    try {
                        if (data['success']) {
                            // 刷新数据
                            $('#menu_datagrid').datagrid('reload');
                            $.messager.alert('温馨提示', data.msg);
                        } else {
                            $.messager.alert('温馨提示', data.msg);
                        }
                    } catch (err) {
                        console.log(err);
                        $.messager.alert('服务器发生异常，请联系管理员', err);
                    }
                }, 'json');
            } else {
                // 取消操作
            }
        });
    });

    /**
     * 新增、编辑按钮点击事件
     */
    $('#save').click(function () {
        var id = $("[name='id']").val();
        var menuFromUrl = "/saveMenu";
        if (id) {
            menuFromUrl = "/updateMenu";
        }
        // 提交表单
        $("#menu_form").form('submit', {
            url: menuFromUrl,
            success: function (data) {
                try {
                    data = $.parseJSON(data);
                    if (data['success']) {
                        $.messager.alert('温馨提示', data.msg);
                        // 关闭对话框
                        $('#menu_dialog').dialog('close');
                        // 刷新数据
                        $('#menu_datagrid').datagrid('reload');
                    } else {
                        $.messager.alert('温馨提示', data.msg);
                    }
                } catch (err) {
                    console.log(err);
                    $.messager.alert('服务器发生异常，请联系管理员', err);
                }
            },

        });
    });

    /**
     * 添加编辑窗口取消按钮
     */
    $('#cancel').click(function () {
        $('#menu_dialog').dialog('close');
        // alert("点击了关闭按钮");
    });

    /**
     * 刷新按钮事件监听
     */
    $('#reload').click(function () {
        // 重新加载所有数据
        $('#menu_datagrid').datagrid('load', {});
    });
});