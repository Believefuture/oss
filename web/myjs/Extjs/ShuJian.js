Ext.define('myjs.Extjs.ShuJian', {
    extend: 'Ext.grid.Panel',
    title: '书剑',
    requires: [
        'Ext.ux.grid.filter.BooleanFilter',
        'Ext.ux.grid.filter.DateFilter',
        'Ext.ux.grid.filter.DateTimeFilter',
        'Ext.ux.grid.filter.ListFilter',
        'Ext.ux.grid.filter.NumericFilter',
        'Ext.ux.grid.filter.StringFilter',
        'Ext.ux.grid.FiltersFeature',
        'myjs.Extjs.CommonColumns',
        'myjs.Extjs.CommonWindow'
    ],
    id: 'ShuJian',
    closable: true,
    myStore: null,
    myColumns: {},
    comItems: new Array(),
    initComponent: function () {
        var me = this, myfilters;
        me.myColumns = Ext.create('myjs.Extjs.CommonColumns');
        Ext.apply(me.myColumns, {
            comColumns: [
                {
                    dataIndex: 'id',
                    type: 'int',
                    text: '编号',
                    hidden: true,
                    hideable: false,
                    isPrimaryKey: true
                },
                {
                    dataIndex: 'areaID',
                    type: 'string',
                    text: '区县编号'
                },
                {
                    dataIndex: 'area',
                    type: 'string',
                    text: '区县'
                },
                {
                    dataIndex: 'cityID',
                    type: 'string',
                    text: '市编号'
                },
                {
                    dataIndex: 'city',
                    type: 'string',
                    text: '市'
                },
                {
                    dataIndex: 'provinceID',
                    type: 'string',
                    text: '省编号'
                },
                {
                    dataIndex: 'province',
                    type: 'string',
                    text: '省',
                    flex: true
                }
            ]
        });
        me.comItems = me.myColumns.getItems();
        me.createStore();

        Ext.apply(this, {
            tbar: [
                {
                    xtype: 'panel',
                    border: false,
                    layout: 'hbox',
                    items: [
                        { xtype: 'button', text: '添加', handler: function () { me.myInsert(me); } },
                        { xtype: 'button', text: '修改' },
                        { xtype: 'button', text: '删除' }
                    ]
                }
            ],
            columns: me.myColumns.getColumn(),
            multiSelect: true,
            store: me.myStore,
            features: [{
                ftype: 'filters',
                encode: false,
                menuFilterText: '条件',
                filters: me.myColumns.getFilter()
            }],
            dockedItems: [
                {
                    xtype: 'pagingtoolbar',
                    store: me.myStore,
                    dock: 'bottom',
                    displayInfo: true
                }
            ]
        });

        this.callParent();
    },
    createStore: function () {
        var me = this;
        this.myStore = Ext.create('Ext.data.Store', {
            pageSize: 10,
            proxy: {
                type: 'ajax',
                url: 'GetTableData.aspx',
                reader: {
                    root: 'rows',
                    type: 'json',
                    totalProperty: 'rowCount'
                }
            },
            fields: me.myColumns.getRecord(),
            autoLoad: true
        });
    },
    myInsert: function (myGlobal) {
        Ext.create('myComWin', {
            id: 'myInsertWindow',
            //comColumns: myGlobal.myColumns,
            comwinite: myGlobal.comItems,
            comsuburl: 'xxx.aspx',
            comsubsuc: function (form, action) {
                Ext.getCmp('girdID').store.reLoad();
                Ext.getCmp('myInsertWindow').close();
            }
        }).show().center();
    },
    myEdit: function () {
        //
        Ext.create('myComWin', {
            id: 'myInsertWindow',
            comwinite: myGlobal.comItems,
            comsuburl: 'xxx.aspx',
            comsubpar: {
                id: Ext.getCmp('girdID').getSelectionModel().getSelection()[0].get('pid')
            },
            comsubsuc: function (form, action) {
                Ext.getCmp('girdID').store.reLoad();
                Ext.getCmp('myInsertWindow').close();
            }
        }).show().center();
    },
    myDel: function () {
        var record = Ext.getCmp('girdID').getSelectionModel().getSelection(), idList, me = this;
        for (var i = 0, len = Ext.getCmp('girdID').getSelectionModel().getSelection().length; i < len; i++) {
            idList += record[i].get('id');
            if (i != len - 1) {
                idList += ',';
            }
        }
        Ext.MessageBox.show({
            title: '删除提示',
            message: '确实要删除数据【' + Ext.getCmp('girdID').getSelectionModel().getSelection().length + '条】么?',
            buttons: Ext.MessageBox.YESNO,
            icon: Ext.MessageBox.WARNING,
            fn: function (btn) {
                if (btn === 'yes') {
                    Ext.Ajax.request({
                        url: 'xxx?id=' + me.idList,
                        success: function (action) {
                            var result;
                            if (typeof (action) === "string") {
                                result = Ext.JSON.decode(action);
                            }
                            else {
                                result = action;
                            }
                            if (result.isDel) {
                                Ext.getCmp('girdID').store.reLoad();
                            }
                        }
                    });
                }
            }
        });
    }
});