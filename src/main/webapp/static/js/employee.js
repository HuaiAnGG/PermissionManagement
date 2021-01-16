$(function () {
    $('#dg').datagrid({
        url: '/employeeList',
        columns: [[
            {field: 'username', title: '姓名', width: 100, align: 'center'},
            {field: 'inputtime', title: '入职时间', width: 100, align: 'center'},
            {field: 'tel', title: '电话', width: 100, align: 'center'},
            {field: 'email', title: '邮箱', width: 100, align: 'center'},
            {field: 'department', title: '部门', width: 100, align: 'center', formatter: function (value, row, index) {
                    return value.name;
                }},
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
        pagination: true
    });
});