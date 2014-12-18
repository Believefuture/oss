/**
 * Created by Administrator on 2014/10/27.
 */
Ext.define('myjs.MarketManagement',{
    extend: 'Ext.tab.Panel',
    requires:['myjs.Extjs.CommonColumns','myjs.Extjs.CommonWindow'],
    id:'MaeketTabPanel',
    tabPosition: 'left',
    closable: true,
    myStore: null,
    myColumns: {},
    comColumns:[],
    comItems: new Array(),
    initComponent:function(){
        var me = this;
        Ext.apply(this,{
        items:[
            me.test('配送商管理','/queryField?tableName=tba_deliveryinfo','/qureydel','pssglgrid'),
            {
                title:'出货单',
                layout:'fit',
                id:'maaeketoutstockgrid',
                listeners: {
                    show: function () {
                        Ext.require('myjs.MarketdownOutstorkout', function () {
                            obj = Ext.create('myjs.MarketdownOutstorkout');
                            Ext.getCmp('maaeketoutstockgrid').add(obj);
                            Ext.getCmp('maaeketoutstockgrid').setActive(obj);
                        });
                    }
                }
            },
            {
                title:'出库历史',
                layout:'fit',
                id:'maaeketoutstockdetagrid',
                listeners: {
                    show: function () {
                        Ext.require('myjs.Marketdownoutstockdeta', function () {
                            obj = Ext.create('myjs.Marketdownoutstockdeta');
                            Ext.getCmp('maaeketoutstockdetagrid').add(obj);
                            Ext.getCmp('maaeketoutstockdetagrid').setActive(obj);
                        });
                    }
                }
            }
        ]

        });
        this.callParent();
    },
    test: function (titlname,turl,dataUrl,gridId) {
        var me=this;
        var mydata = {};
        var dajax = Ext.Ajax.request({
            url: turl,
            reader: 'json',
            root:'fElist',
            async: false,
            success: function (response) {
               mydata = Ext.JSON.decode(response.responseText);
            }
        });

        Ext.each(mydata.fElist, function(item, index){
            me.comColumns[index] = {};
            me.comColumns[index].dataIndex = item.columnName;
            me.comColumns[index].text = item.description;
            me.comColumns[index].type = me.getType(item.typeName);
            me.comColumns[index].width = 120;
        });
        Ext.each(me.comColumns,function(item){
            if(item){
                if(item.text =='递增的流水号') {
                    Ext.Array.remove(me.comColumns,item);
                }
            };
        });
        me.myColumns = Ext.create('myjs.Extjs.CommonColumns');
        Ext.each(me.myColumns.comColumns,function(item,index){
            if(item) {
                Ext.Array.remove(me.myColumns.comColumns, item);
            }
        });
        Ext.apply(me.myColumns.comColumns,  me.comColumns);
        me.comItems = me.myColumns.getItems();
        me.createStore(dataUrl);

        return me.t(titlname, gridId);
    },
    getType: function(typeName){
        switch (typeName)
        {
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
    t:function (titlname ,gridId) {
        var me = this;
        var titlename=titlname;
        var resu=Ext.create('Ext.grid.Panel',
        {
            id:gridId,
            title: titlename,
            columns: me.myColumns.getColumn(),
            multiSelect: true,
            store: me.myStore,
            filters: me.myColumns.getFilter(),
            dockedItems: [{
                xtype: 'pagingtoolbar',
                store:me.myStore,
                dock: 'bottom',
                displayInfo: true
            }],
            buttons:[
                {
                    text:"添加",
                    handler:function(){me.myInsert(me,gridId,'/Adddel','pssgltianjia');}
                },
                {
                    text:"修改",
                    handler:function(){me.myInsert(me,gridId,'/updatadel','pssglxiugaiwin',1);}
                },
                {
                    text:"删除",
                    handler:function(){me.delete(gridId);}
                }
            ]
        });
        return resu;
    },
    createStore: function (dataUrl) {
        var me = this;
        this.myStore = Ext.create('Ext.data.Store', {
            pageSize: 5,
            proxy: {
                type: 'ajax',
                url: dataUrl,
                reader: {
                    root: 'delList',
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

    myInsert: function (myGlobal,gridId,suburl,windowID,cade) {
       var winITems=new Array();
        var defaultValue = new Array();
        Ext.each(myGlobal.comItems, function(item, index){
            winITems[index] = {};
            winITems[index].fieldLabel = item.fieldLabel;
            winITems[index].name= 'del.'+item.name;
        });
        Ext.each( winITems, function(item, index){
            if(item){
                if(item.fieldLabel=='配送商编码'){
                    winITems[index].hidden=true;
                }
            }
        });
        Ext.each( winITems, function(item, index){
            if((item.fieldLabel=='配送商名称')||(item.fieldLabel=='地址')){
                winITems[index].allowBlank=false;
            }
        });
        if(cade==1){
            var record= Ext.getCmp(gridId).getSelectionModel().getSelection()[0];
            if(record){
                Ext.each(winITems,function(item, index){
                    winITems[index].value=record.get(item.name.replace('del.',''));
                });
            }else{
                Ext.Msg.alert('系统提示', '请选择修改项');
                return;
            }
        }

       var wd= Ext.create('myjs.Extjs.CommonWindow',
           {
               id:windowID,
               comsuburl:suburl,
               comwinite:winITems,
               comsubsuc: function (form, action) {
                    var msg = Ext.JSON.decode(action.response.responseText);
                        if(msg.success=='true'){
                            Ext.Msg.show({
                                title: '系统提示',
                                msg: msg.message,
                                buttons: Ext.MessageBox.YES,
                                fn: function(){

                                    Ext.getCmp(gridId).store.reload();
                                    Ext.getCmp(windowID).close();
                                }
                            });
                        }else{
                            Ext.Msg.alert('系统提示', msg.message);
                        }
               }

           }).show().center();

//        Ext.apply(wd, {
//            id: 'myInsertWindow'
//        });
//        Ext.apply(wd.comwinite,myGlobal.comItems).show().center();


    },
    delete:function(gridId) {
        var record = Ext.getCmp(gridId).getSelectionModel().getSelection()[0];
        if (record) {
            Ext.MessageBox.show({
                title: '删除提示',
                msg: '确实要删除数据么?',
                buttons: Ext.MessageBox.YESNO,
                icon: Ext.MessageBox.WARNING,
                fn: function (btn) {
                    if (btn === 'yes') {
                        Ext.Ajax.request({
                            url: '/delectdel?del.deliveryid=' + record.get('deliveryid'),
                            success: function (action) {
                                var result;
                                if (typeof (action.responseText) === "string") {
                                    result = Ext.JSON.decode(action.responseText);
                                }
                                else {
                                    result = action.responseText;
                                }
                                if (result.success) {
                                    Ext.getCmp(gridId).store.reload();
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



