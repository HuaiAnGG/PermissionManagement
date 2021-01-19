$(function () {
    /**
     * 权限数据表格
     */
    $('#role_dg').datagrid({
        url: '/roleList',
        columns: [[
            {field: 'rnum', title: '角色编号', width: 100, align: 'center'},
            {field: 'rname', title: '角色名称', width: 100, align: 'center'},
        ]],
        fit: true,
        fitColumns: true,
        rownumbers: true,
        pagination: true,
        singleSelect: true,
        striped: true,
        toolbar: '#toolbar'
    });

    /**
     * 添加编辑对话框
     */
    $('#dialog').dialog({
        width: 600,
        height: 450,
        closed: true,
        buttons: [{
            text: '保存',
            handler: function () {
                var submitUrl = '/saveRole';
                var rid = $("[name='rid']").val();
                if (rid){
                    submitUrl = '/updateRole';
                }
                $('#myform').form('submit', {
                    url: submitUrl,
                    onSubmit: function (param) {         /*提交前的操作操作*/
                        var allRows = $('#role_data2').datagrid('getRows');
                        for (let i = 0; i < allRows.length; i++) {
                            // 判断该权限是否已存在
                            param['permissions[' + i + '].pid'] = allRows[i].pid;
                            // param['permissions[' + i + '].pname'] = allRows[i].pname;
                        }
                        // console.log(param)
                    },
                    success: function (data) {
                        data = $.parseJSON(data);
                        if (data.success) {
                            $.messager.alert('温馨提示', data.msg);
                            // 关闭对话框
                            $('#dialog').dialog('close');
                            // 刷新数据
                            $('#role_dg').datagrid('reload');
                        } else {
                            $.messager.alert('温馨提示', data.msg);
                        }
                    }
                })
            }
        }, {
            text: '关闭',
            handler: function () {
                $('#dialog').dialog('close');
            }
        }]

    });

    /**
     * 添加按钮事件监听
     */
    $('#add').click(function () {
        // 清空已有数据
        $('#myform').form('clear');
        // 打开对话框
        $('#dialog').dialog('open');
        // 设置title
        $('#dialog').dialog('setTitle', '添加角色');
        // 清空已有权限数据表
        $('#role_data2').datagrid('loadData', {rows: []});
    });

    /**
     * 权限列表
     */
    $('#role_data1').datagrid({
        title: '所有权限',
        width: 250,
        height: 250,
        url: '/permissionList',
        columns: [[
            {field: 'pname', title: '权限名称', width: 100, align: 'center'}
        ]],
        fitColumns: true,
        singleSelect: true,
        onClickRow: function (rowIndex, rowData) {  /*点击所有权限中的一行，进行的回调*/
            var allRows = $('#role_data2').datagrid('getRows');
            for (let i = 0; i < allRows.length; i++) {
                // 判断该权限是否已存在
                if (allRows[i].pid === rowData.pid) {
                    // 获取当前角标
                    var index = $('#role_data2').datagrid('getRowIndex', allRows[i]);
                    // 选中已有权限
                    $('#role_data2').datagrid('selectRow', index);
                    return;
                }
            }
            // 添加到已有权限
            $('#role_data2').datagrid('appendRow', rowData);
        }
    });

    /**
     * 选中权限列表
     */
    $('#role_data2').datagrid({
        title: '已有权限',
        width: 250,
        height: 250,
        columns: [[
            {field: 'pname', title: '权限名称', width: 100, align: 'center'}
        ]],
        fitColumns: true,
        singleSelect: true,
        onClickRow: function (rowIndex, rowData) {  /*点击所有权限中的一行，进行的回调*/
            // 删除选中
            $('#role_data2').datagrid('deleteRow', rowIndex);
        }
    });

    /**
     * 编辑按钮事件处理
     */
    $('#edit').click(function () {
        // 获取选中
        var rowData = $('#role_dg').datagrid('getSelected');
        if (!rowData) {
            $.messager.alert('提示', '请选择一行数据进行编辑!');
            return;
        }

        /**
         * 数据回显
         * rowData
         * {rid: 4, rnum: "manager2", rname: "经理2", permissions: Array(0)}
         */
        $("[name='rid']").val(rowData.rid);
        $("[name='rnum']").val(rowData.rnum);
        $("[name='rname']").val(rowData.rname);
        // 加载 role_data2 的数据
        var options = $('#role_data2').datagrid('options');
        // 填写 options 的 url
        options.url = '/getPermissionByRid?rid=' + rowData.rid;
        // 重新加载 role_data2 的数据
        $('#role_data2').datagrid('load');
        // 显示对话框
        $('#dialog').dialog('setTitle', '编辑角色');
        $('#dialog').dialog('open');
    });

    /**
     * 删除按钮事件处理
     */
    $('#delete').click(function () {
        // 获取选中
        var rowData = $('#role_dg').datagrid('getSelected');
        if (!rowData) {
            $.messager.alert('提示', '请选择一行数据进行编辑!');
            return;
        }
        /*提醒用户是否继续操作*/
        $.messager.confirm("确认", "是否删除该角色", function (res) {
            if (res) {
                // 继续离职操作
                $.get('/deleteRole?rid=' + rowData.rid, function (data) {
                    try {
                        if (data['success']) {
                            // 刷新数据
                            $('#role_dg').datagrid('reload');
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
});