/**
 * Created by Administrator on 2014/10/27.
 */
Ext.define('myjs.SupplierManagement',{
    extend: 'Ext.tab.Panel',
    requires:['myjs.Extjs.CommonColumns','myjs.Extjs.CommonWindow'],
    id:'Supptabpanl',
    tabPosition: 'left',
    closable: true,
    myStore: null,
    myColumns: {},
    comColumns:[],
    comItems: new Array(),
    initComponent:function(){
        var me = this;

        Ext.apply(this,{
        items:
            me.test('供应商管理','/queryField?tableName=tba_supplierinfo','/queryaut','gysglgrid')
//             {
//                    title: 'Bar',
//                    tabConfig: {
//                    title: 'Custom Title',
//                    tooltip: 'A button tooltip'
//                }

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
            if(item.text =='供应商名称') {
                item.width = 180;
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
                    handler:function(){me.myInsert(me,gridId,'/AddAut','gysgltianjiawin',1);}
                },
                {
                    text:"修改",
                    handler:function(){me.myInsert(me,gridId,'/updataAut','gysglxiugaiwin');}
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
                    root: 'autList',
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

    myInsert: function (myGlobal,gridId,suburl,windowId,cade) {
       var winITems=new Array();
        var defaultValue = new Array();
//        Ext.each(myGlobal.comItems, function(item, index){
//        if(itme.name=)
//           winITems[index] = {};
//           winITems[index].fieldLabel = item.fieldLabel;
//           winITems[index].name= 'aut.'+item.name;
//
//
//        });
        Ext.Ajax.request({
            url: '/addAutwin.json',
            reader: 'json',
            root:'comwinite',
            async: false,
            success: function (response) {
                winITems = Ext.JSON.decode(response.responseText);
            }
        });





        if(cade==1){
        Ext.Ajax.request({
            url:'/autdefaultVauel',
            reader:'json',
            async:false,
            success:function(response){
                defaultValue =Ext.JSON.decode(response.responseText);
            }
        });
        Ext.each(winITems.comwinite,function(item, index){
//            winITems.comwinite[index].value='';
            if(item.name=='aut.id'){
                item.value=defaultValue.aut.id;
            };
            if(item.name=='aut.supplierid'){
                item.value=defaultValue.aut.supplierid;
            };
        });
        }else{
            var record= Ext.getCmp(gridId).getSelectionModel().getSelection()[0];
            if(record){
                Ext.each(winITems.comwinite,function(item, index){
                    winITems.comwinite[index].value=record.get(item.name.replace('aut.',''));
                });
            }else{
                Ext.Msg.alert('系统提示', '请选择修改项');
                return;
            }
        }

       var wd= Ext.create('myjs.Extjs.CommonWindow',
           {
               id:windowId,
               comsuburl:suburl,
               comwinite:winITems.comwinite,
               comsubsuc: function (form, action) {
                    var msg = Ext.JSON.decode(action.response.responseText);
                        if(msg.success=='true'){
                            Ext.Msg.show({
                                title: '系统提示',
                                msg: msg.message,
                                buttons: Ext.MessageBox.YES,
                                fn: function(){

                                    Ext.getCmp(gridId).store.reload();
                                    Ext.getCmp(windowId).close();
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
    delete:function(gridId){
        var record= Ext.getCmp(gridId).getSelectionModel().getSelection()[0];
        if(record) {
            Ext.MessageBox.show({
                title: '删除提示',
                msg: '确实要删除数据么?',
                buttons: Ext.MessageBox.YESNO,
                icon: Ext.MessageBox.WARNING,
                fn: function (btn) {
                    if (btn === 'yes') {
                        Ext.Ajax.request({
                            url: '/delectAut?aut.supplierid=' + record.get('supplierid'),
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
        }else{
            Ext.Msg.alert('系统提示', '请选择删除项');
        }
    }

    });



