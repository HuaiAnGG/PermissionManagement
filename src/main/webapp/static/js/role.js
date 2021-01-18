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
        height: 480,
        closed: true,
        buttons: [{
            text: '保存',
            handler: function () {
                $('#myform').form('submit', {
                    url: '/saveRole',
                    onSubmit: function (param) {         /*提交前的操作操作*/
                        var allRows = $('#role_data2').datagrid('getRows');
                        for (let i = 0; i < allRows.length; i++) {
                            // 判断该权限是否已存在
                            param['permissions[' + i + '].pid'] = allRows[i].pid;
                            // param['permissions[' + i + '].pname'] = allRows[i].pname;
                        }
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
        
    });
});