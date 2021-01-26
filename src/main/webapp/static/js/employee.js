$(function () {
    /**
     * 员工数据列表
     */
    $('#dg').datagrid({
        url: '/employeeList',
        columns: [[
            {field: 'username', title: '姓名', width: 100, align: 'center'},
            {field: 'inputtime', title: '入职时间', width: 100, align: 'center'},
            {field: 'tel', title: '电话', width: 100, align: 'center'},
            {field: 'email', title: '邮箱', width: 100, align: 'center'},
            {
                field: 'department', title: '部门', width: 100, align: 'center', formatter: function (value, row, index) {
                    if (value) {
                        return value.name;
                    }
                }
            },
            {
                field: 'state', title: '状态', width: 100, align: 'center', formatter: function (value, row, index) {
                    if (row.state !== null) {
                        return row.state ? '<font color="#006400">在职</font>' : '<font color="red">已离职</font>';
                    }
                }
            },
            {
                field: 'admin', title: '管理员', width: 100, align: 'center', formatter: function (value, row, index) {
                    return row.admin ? '是' : '否';
                }
            }
        ]],
        fit: true,
        fitColumns: true,
        rownumbers: true,
        pagination: true,
        singleSelect: true,
        striped: true,
        toolbar: '#tb',
        onClickRow: function (rowIndex, rowData) {
            /*判断是否已经离职，离职则不能继续点击离职按钮*/
            if (rowData.state) {
                $('#delete').linkbutton('enable');
            } else {
                $('#delete').linkbutton('disable');
            }
        }
    });

    /**
     * 对话框
     */
    $('#dialog').dialog({
        width: 350,
        height: 400,
        closed: true,
        buttons: [{
            text: '保存',
            handler: function () {
                // 属性选择器取出隐藏域的id 值
                var id = $("[name='id']").val();
                var formSubmitUrl = '/saveEmployee';
                /*编辑操作*/
                if (id) {
                    formSubmitUrl = '/updateEmployee';
                }
                // 提交表单
                $("#employeeForm").form('submit', {
                    url: formSubmitUrl,
                    onSubmit: function (param) {
                        /*获取选中的角色*/
                        var values = $("#role").combobox("getValues");
                        // console.log('选中角色 values', values);
                        for (var i = 0; i < values.length; i++) {
                            var rid = values[i];
                            param["roles[" + i + "].rid"] = rid;
                        }
                        console.log(param);
                        return param;
                    },
                    success: function (data) {
                        try {
                            data = $.parseJSON(data);
                            if (data['success']) {
                                $.messager.alert('温馨提示', data.msg);
                                // 关闭对话框
                                $('#dialog').dialog('close');
                                // 刷新数据
                                $('#dg').datagrid('reload');
                            } else {
                                $.messager.alert('温馨提示', data.msg);
                            }
                        } catch (err) {
                            console.log(err);
                            $.messager.alert('服务器发生异常，请联系管理员', err);
                        }
                    },

                });
            }
        }, {
            text: '关闭',
            handler: function () {
                $('#dialog').dialog('close');
            }
        }]

    });

    /**
     * 点击按钮事件监听
     */
    $('#add').click(function () {
        // 清空上次提交的信息
        $('#employeeForm').form('clear');
        // 显示密码框
        $('#password').show();
        /*取消密码验证*/
        $("[name='password']").validatebox({required: true});
        // 显示对话框
        $('#dialog').dialog({'title': '添加员工'});
        $('#dialog').dialog({'closed': false});
    });

    /**
     * 点击编辑按钮事件监听
     */
    $('#edit').click(function () {
        // 获取选中
        var rowData = $('#dg').datagrid('getSelected');
        if (!rowData) {
            $.messager.alert('提示', '请选择一行数据进行编辑!');
            return;
        }
        $('#dialog').dialog("open");
        /*取消密码验证*/
        $("[name='password']").validatebox({required: false});
        // 清空员工和角色之间关系
        $('#role').combobox('clear');
        // 显示对话框
        $("#dialog").dialog({'title': '编辑员工'});
        $('#dialog').dialog("open");
        // 处理部门信息、管理员，回显
        if (rowData.department) {
            rowData['department.id'] = rowData.department.id;
        }
        // 回显管理员
        var admin = rowData['admin'];
        if (admin === null) {
            rowData['admin'] = false;
        } else {
            rowData['admin'] = rowData.admin + '';
        }
        //{id: 35, username: "老衲", password: "123", inputtime: "2021-01-19", tel: "1123123123"}
        // 回显角色(根据当前用户id进行回显)
        $.get('/getEmployeeRoleByEid?id=' + rowData.id, function (data) {
            // 设置下拉列表数据回显
            $('#role').combobox('setValues', data);
        });

        // 隐藏密码框
        $('#password').hide();
        // 数据回显
        $('#employeeForm').form('load', rowData);
    });

    /**
     * 刷新按钮事件监听
     */
    $('#reload').click(function () {
        // 清空搜索栏
        $("[name='keyword']").val('');
        // 重新加载所有数据
        $('#dg').datagrid('load', {});
    });

    /**
     * 部门选择 下拉列表
     */
    $('#department').combobox({
        width: 150,
        panelHeight: 'auto',
        textField: 'name',     /*文本显示*/
        valueField: 'id',    /*提交到服务器的值*/
        editable: false,
        url: '/getDepartmentList',
        onLoadSuccess: function () {
            $("#department").each(function (i) {
                var span = $(this).siblings('span')[i];
                var targetInput = $(span).find("input:first");
                if (targetInput) {
                    $(targetInput).attr('placeholder', $(this).attr('placeholder'));
                }
            });
        }
    });

    /**
     * 是否为管理员 下拉列表
     */
    $('#state').combobox({
        width: 150,
        panelHeight: 'auto',
        textField: 'label',     /*文本显示*/
        valueField: 'value',    /*提交到服务器的值*/
        editable: false,
        data: [{
            label: '是',
            value: 'true'
        }, {
            label: '否',
            value: 'false'
        }],
        /**
         * 数据加载完成后的回调
         */
        onLoadSuccess: function () {
            $("#state").each(function (i) {
                var span = $(this).siblings('span')[i];
                var targetInput = $(span).find("input:first");
                if (targetInput) {
                    $(targetInput).attr('placeholder', $(this).attr('placeholder'));
                }
            });
        }
    })
    /**
     * 选择用户角色 下拉列表
     */
    $('#role').combobox({
        width: 150,
        url: '/allRole',
        panelHeight: 'auto',
        textField: 'rname',     /*文本显示*/
        valueField: 'rid',    /*提交到服务器的值*/
        multiple: true,         /*是否支持多选*/
        editable: false,
        /**
         * 数据加载完成后的回调
         */
        onLoadSuccess: function () {
            $("#role").each(function (i) {
                var span = $(this).siblings('span')[i];
                var targetInput = $(span).find("input:first");
                if (targetInput) {
                    $(targetInput).attr('placeholder', $(this).attr('placeholder'));
                }
            });
        }
    })

    /**
     * 离职按钮事件处理
     */
    $('#delete').click(function () {
        // 获取选中
        var rowData = $('#dg').datagrid('getSelected');
        if (!rowData) {
            $.messager.alert('提示', '请选择一行数据进行编辑!');
            return;
        }
        /*提醒用户是否继续操作*/
        $.messager.confirm("确认", "是否进行离职操作", function (res) {
            if (res) {
                // 继续离职操作
                $.get('/updateEmployeeState?id=' + rowData.id, function (data) {
                    console.log(data);
                    try {
                        if (data['success']) {
                            console.log(data);
                            // 刷新数据
                            $('#dg').datagrid('reload');
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

    /*搜索按钮事件处理*/
    $('#searchbtn').click(function () {
        /*获取搜索关键字*/
        var keyword = $("[name='keyword']").val();
        /**
         * 把搜索关键字传到对应 url
         */
        $('#dg').datagrid('load', {keyword: keyword});
    });

    /**
     * 导出 Excel
     */
    $('#excelOut').click(function () {
        window.open('/downloadExcel');
    });

    /**
     * 导入 Excel
     */
    $('#excelIn').click(function () {
        $("#excelUpload").dialog('open');
    });

    /**
     * excel 弹出框
     */
    $("#excelUpload").dialog({
        width: 260,
        height: 180,
        title: "导入Excel",
        buttons: [{
            text: '保存',
            handler: function () {
                $('#uploadExcelForm').form('submit', {
                    url: '/uploadExcelFile',
                    success: function (data) {
                        try {
                            data = $.parseJSON(data);
                            if (data['success']) {
                                $.messager.alert('温馨提示', data.msg);
                                // 关闭对话框
                                $('#excelUpload').dialog('close');
                                // 刷新数据
                                $('#dg').datagrid('reload');
                            } else {
                                $.messager.alert('温馨提示', data.msg);
                            }
                        } catch (err) {
                            console.log(err);
                            $.messager.alert('服务器发生异常，请联系管理员', err);
                        }
                    }
                })
            }
        }, {
            text: '关闭',
            handler: function () {
                $("#excelUpload").dialog("close");
            }
        }],
        closed: true
    })

    /**
     * 下载 Excel 模板
     */
    $('#downloadTml').click(function () {
        window.open('/downloadExcelTemplate');
    });
});