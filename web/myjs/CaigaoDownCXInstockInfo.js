/**
 * Created by Administrator on 2014/11/20.
 */
Ext.define('myjs.CaigaoDownCXInstockInfo', {
    extend: 'Ext.grid.Panel',
    requires: ['myjs.Extjs.CommonColumns', 'myjs.Extjs.CommonWindow'],
    tabPosition: 'left',
    title: '入库记录',
    myStore: null,
    myColumns: {},
    comColumns: [],
    comItems: new Array(),
    initComponent: function () {
        var me = this;
        var mydata = {};
        var dajax = Ext.Ajax.request({
            url: '/queryField?tableName=tme_instockinfo',
            reader: 'json',
            root: 'fElist',
            async: false,
            success: function (response) {
                mydata = Ext.JSON.decode(response.responseText);
            }
        });
        Ext.each(mydata.fElist, function (item, index) {
            me.comColumns[index] = {};
            me.comColumns[index].dataIndex = item.columnName;
            me.comColumns[index].text = item.description;
            me.comColumns[index].type = me.getType(item.typeName);
            me.comColumns[index].width = 120;
        });
        Ext.each(me.comColumns, function (item) {
            if (item) {
                if (item.text == '递增的流水号') {
                    Ext.Array.remove(me.comColumns, item);
                }
            }
        });
        Ext.each(me.comColumns, function (item) {
            if (item.text == '操作员编码') {
                item.text='操作员名称';
                item.dataIndex='operid.opername';
            }
            if(item.text=='供应商编码'){
                item.text='供应商名称';
                item.dataIndex='supplierid.suppliername';
            }
        });
        me.myColumns = Ext.create('myjs.Extjs.CommonColumns');
        Ext.each(me.myColumns.comColumns, function (item, index) {
            if (item) {
                Ext.Array.remove(me.myColumns.comColumns, item);
            }
        });
        Ext.apply(me.myColumns.comColumns, me.comColumns);
        me.comItems = me.myColumns.getItems();
        me.createStore();

        Ext.apply(this, {
            id: 'CXINstockgrid',
            columns: me.myColumns.getColumn(),
            multiSelect: true,
            store: me.myStore,
            filters: me.myColumns.getFilter(),
            dockedItems: [
                {
                    xtype: 'pagingtoolbar',
                    store: me.myStore,
                    dock: 'bottom',
                    displayInfo: true
                }
            ],
            tbar: [
                {
                    text: "查看详情",
                    handler: function () {
                        me.myLook();
                    }
                },
                {
                    text: "删除",
                    handler: function () {
                        me.delete();
                    }
                }
            ]

        });
        this.callParent();
    },

    getType: function (typeName) {
        switch (typeName) {
            case 'smallint':
            case 'tinyint':
            case 'int':
                return 'int';
            case 'numeric':
            case 'money':
                return 'float';
            case 'varchar':
                return 'string';
            case 'bit':
                return 'bool';
        }
    },
    createStore: function () {
        var me = this;
        this.myStore = Ext.create('Ext.data.Store', {
            pageSize: 5,
            proxy: {
                type: 'ajax',
                url: '/queryStockInfo',
                reader: {
                    root: 'instockinfolist',
                    type: 'json',
                    totalProperty: 'countNumed'
                }
            },
            fields: me.myColumns.getRecord(),
            autoLoad: false
        });
        this.myStore.load({

            params:{
                start:0,
                limit:5
            }
        });
    },

    myLook: function () {
        var record = Ext.getCmp('CXINstockgrid').getSelectionModel().getSelection()[0];
        if(record){
        var defaultvalue=record.raw;
        if(!Ext.getCmp('mainwindow')){
            Ext.require('myjs.CDCXdownwindow',function(){
                 Ext.create('Ext.window.Window',{
                     title:  '入库详细信息',
                     id:"mainwindow",
                     layout:'fit',
                     height: 800,
                     width: 1200,
                    items:[Ext.create('myjs.CDCXdownwindow',{defaultvalue:defaultvalue})]
                }).show().center()
            });
        }
        }else{
            Ext.Msg.alert('系统提示', '请选择入库单');
        }
    },


    delete:function() {
        var record = Ext.getCmp('CXINstockgrid').getSelectionModel().getSelection()[0];
        if (record) {
            Ext.MessageBox.show({
                title: '删除提示',
                msg: '确实要删除数据么?',
                buttons: Ext.MessageBox.YESNO,
                icon: Ext.MessageBox.WARNING,
                fn: function (btn) {
                    if (btn === 'yes') {
                        Ext.Ajax.request({
                            url: '/instockinfodelect?instockinfo.billcode=' + record.get('billcode'),
                            success: function (action) {
                                var result;
                                if (typeof (action.responseText) === "string") {
                                    result = Ext.JSON.decode(action.responseText);
                                }
                                else {
                                    result = action.responseText;
                                }
                                if (result.success) {
                                    Ext.Msg.show({
                                        title : "系统提示",
                                        msg : msg.message,
                                        icon : Ext.Msg.WARNING,
                                        buttons : Ext.Msg.YES
                                    });
                                    Ext.getCmp('CXINstockgrid').store.reload();
                                }
                            }
                        });
                    }
                }
            });
        } else {
            Ext.Msg.alert('系统提示', '请选择删除项');
        }
    }

});