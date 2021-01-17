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
                    return value.name;
                }
            },
            {
                field: 'state', title: '状态', width: 100, align: 'center', formatter: function (value, row, index) {
                    return row.state ? '<font color="#006400">在职</font>' : '<font color="red">已离职</font>';
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
        toolbar: '#tb'
    });

    /**
     * 对话框
     */
    $('#dialog').dialog({
        width: 350,
        height: 350,
        closed: true,
        buttons:[{
            text:'保存',
            handler:function(){
                // 提交表单
                $('#employeeForm').form('submit', {
                    url: '/saveEmployee',
                    success: function (data) {
                        // console.log(data);
                        data = $.parseJSON(data);
                        if (data['success']) {
                            $.messager.alert('温馨提示', data.msg);
                            // 关闭对话框
                            $('#dialog').dialog('close');
                            // 刷新数据
                            $('#dg').datagrid('reload');
                        }else {
                            $.messager.alert('温馨提示', data.msg);
                        }
                    }
                });
            }
        },{
            text:'关闭',
            handler:function(){
                $('#dialog').dialog('close');
            }
        }]

    });

    /**
     * 点击按钮事件监听
     */
    $('#add').click(function () {
        $('#dialog').dialog({'closed': false});
    });

    /**
     * 点击按钮事件监听
     */
    $('#edit').click(function () {
        // 清空上次提交的信息
        $('#employeeForm').form('clear');
        // 显示对话框
        $('#dialog').dialog({'closed': false});
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
});