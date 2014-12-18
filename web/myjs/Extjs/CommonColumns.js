/*
    comColumns不能为空集合。示例如下：
    comColumns:[
        ///////一对大括号内的为一个列的配置项。text，type，dataIndex为必填项，其他可以不填
        {
            text: '',               --显示的文本
            type: '',               --数据类型（int,string,bool,date,datetime,float）
            dataIndex: '',          --对应的数据表中的列名称
            width: 100,             --列宽度
            hidden: false,          --是否隐藏
            hideable: false,        --是否能够显示出来，true可以通过列菜单显示出来，false不能通过列菜单显示
            menuDisabled: false,    --是否有列菜单
            resizable: true,        --是否可以调整宽度
            sortable: true,         --是否可以排序
            flex: false,            --是否填充剩下的所有空位
            align: 'center',         --列中的文本位置 left right center
            renderer: function(val) {   --单元格渲染函数
                return '<font color="red">' + val + '</font>';
            },
            isPrimaryKey: false
        }
    ]
*/
Ext.define('myjs.Extjs.CommonColumns', {
    alternateClassName: 'myComCol',
    comColumns: new Array(),
    comWidth: 100,
    comHidden: false,
    comHideable: false,
    comMenu: false,
    comResiz: true,
    comSotr: true,
    comFlex: false,
    comFilter: true,
    comAlign: 'center',
    //返回grid所需的列
    getColumn: function () {
        var me = this;
        if (me.comColumns.length < 1) {
            return [];
        }
        var result = [];
        Ext.each(me.comColumns, function (item, index, allitems) {
            result[index] = {
                dataIndex: item.dataIndex,
                header: item.text,
                width: item.width ? item.width : me.comWidth,
                hidden: item.hidden ? item.hidden : me.comHidden,
                hideable: item.hideable ? item.hideable : me.comHideable,
                menuDisabled: item.menuDisabled ? item.menuDisabled : me.comMenu,
                resizable: item.resizable ? item.resizable : me.comResiz,
                sortable: item.sortable ? item.sortable : me.comSotr,
                flex: item.flex ? item.flex : me.comFlex,
                align: item.align ? item.align : me.comAlign,
                filter: item.filter ? item.filter : me.comFilter,
                renderer: item.renderer ? item.renderer : null
            };
        });
        return result;
    },
    //返回列头所需要的过滤
    getFilter: function () {
        //string,numeric,boolean,date,datetime
        var me = this;
        if (me.comColumns.length < 1) {
            return [];
        }
        var result = [];
        Ext.each(me.comColumns, function (item, index, allitems) {
            result[index] = {
                dataIndex: item.dataIndex,
                type: me.getType(item.type, "filter")
            };
        });
        return result;
    },
    //返回store所需要的匹配器
    getRecord: function () {
        //string,int,float,boolean,date
        var me = this;
        if (me.comColumns.length < 1) {
            return [];
        }
        var result = [];
        Ext.each(me.comColumns, function (item, index, allitems) {
            result[index] = {
                name: item.dataIndex,
                type: me.getType(item.type, "record")
            };
        });
        return result;
    },
    getItems: function () {
        var me = this, result = new Array();
        Ext.each(me.comColumns, function (item, index) {
            if (item.isPrimaryKey) {
                result.push({
                    xtype: 'hidden',
                    name: item.dataIndex,
                    fieldLabel: item.text
                });
            }
            else {
                result.push({
                    name: item.dataIndex,
                    fieldLabel: item.text
                });
            }
        });
        return result;
    },
    getType: function (dataType, componentType) {
        if (dataType === "int" || dataType === "float") {
            if (componentType === "filter") {
                return "numeric";
            }
        }
        if (dataType === "datetime") {
            if (componentType === "record") {
                return "date";
            }
        }
        return dataType;
    }
});