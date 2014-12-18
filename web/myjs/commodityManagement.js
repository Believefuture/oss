/**
 * Created by Administrator on 2014/10/27.
 */
Ext.define('myjs.commodityManagement',{
    extend: 'Ext.tab.Panel',
    requires:['myjs.Extjs.CommonColumns','myjs.Extjs.CommonWindow','myjs.ciniiditydownMerchandiseInfo'],
    id:'Managementpanl',
    tabPosition: 'left',
    closable: true,
    lb:{
        name:'lb',
        myStore: null,
        myColumns: {},
        comColumns:[],
        comItems: new Array()

    },
    cx: {
        name:'cx',
        myStore: null,
        myColumns: {},
        comColumns: [],
        comItems: new Array()

    },
    dw: {
        name:'dw',
        myStore: null,
        myColumns: {},
        comColumns: [],
        comItems: new Array()
    },
    initComponent:function(){
        var me = this;

        Ext.apply(this,{
        items:[
            {title:'test',
                layout:'fit',
                id:'mytabone',
                show:function(){
                    Ext.require('',function(){
                        obj = Ext.
                        Ext.getCmp('mytabone').add(obj);
                    });
                }
            },
            me.test(me.lb,'商品类别信息管理','/queryField?tableName=tme_merchandisecinfo','/qureymccI','mccIList','spfengleiglgrid','/AddmccI','/updatamccI','/delectmccI?mccI.merchandisecid','merchandisecid','mccI','spfengleigltianjiawin','spfengleiglxiugaiwin'),
            me.test(me.cx,'商品促销管理','/queryField?tableName=tme_prostatusinfo','/qureypro','proList','spchuxiaoglgrid','/Addpro','/updatapro','/delectpro?pro.prostatusid','prostatusid','pro','spchuxiaogltianjiawin','spchuxiaoglxiugaiwin'),
            me.test(me.dw,'商品单位管理','/queryField?tableName=tme_unitinfo','/qureyuni','uniList','spdanweiglgrid','/Adduni','/updatauni','/delectuni?uni.unitid','unitid','uni','spdanweigltianjiawin','spdanweiglxiugaiwin'),
            Ext.create('myjs.ciniiditydownMerchandiseInfo')
        ]});
        this.callParent();
    },

   //who:谁,titlname：选项卡名字 ,turl:菜单的来源,dataUrl:数据源,dataroot:数据源的root，gridId:gridId,addurl:添加Action,updateurl:更新Action,delecturl:删除Action,xcdc:删除的参数,prefix:前缀,tdwindowID添加表单Id,xgwindowID修改表单Id

    test: function (who,titlname,turl,dataUrl,dataroot,gridId,addurl,updateurl,delecturl,xcdc,prefix,tdwindowID,xgwindowID) {
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
            who.comColumns[index] = {};
            who.comColumns[index].dataIndex = item.columnName;
            who.comColumns[index].text = item.description;
            who.comColumns[index].type = me.getType(item.typeName);
            who.comColumns[index].width = 120;
        });
        Ext.each(who.comColumns,function(item){
            if(item){
                if(item.text =='递增的流水号') {
                    Ext.Array.remove(who.comColumns,item);
                }
            };
        });
        who.myColumns = Ext.create('myjs.Extjs.CommonColumns');
        Ext.each(who.myColumns.comColumns,function(item){
            if(item) {
                Ext.Array.remove(who.myColumns.comColumns, item);
            }
        });
        Ext.apply(who.myColumns.comColumns,  who.comColumns);
        who.comItems = who.myColumns.getItems();
        me.createStore(who,dataUrl,dataroot);


        return me.t(who,titlname, gridId,addurl,updateurl,delecturl,xcdc,prefix,tdwindowID,xgwindowID);
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
    t:function (who,titlname ,gridId,addurl,updateurl,delecturl,xcdc,prefix,tdwindowID,xgwindowID) {
        var me = this;
        var titlename=titlname;
        var resu=Ext.create('Ext.grid.Panel',
        {
            id:gridId,
            title: titlename,
            columns: who.myColumns.getColumn(),
            multiSelect: true,
            store: who.myStore,
            filters: who.myColumns.getFilter(),
            dockedItems: [{
                xtype: 'pagingtoolbar',
                store:who.myStore,
                dock: 'bottom',
                displayInfo: true
            }],
            buttons:[
                {
                    text:"添加",
                    handler:function(){me.myInsert(who,gridId,addurl,prefix,tdwindowID);}
                },
                {
                    text:"修改",
                    handler:function(){me.myInsert(who,gridId,updateurl,prefix,xgwindowID,1);}
                },
                {
                    text:"删除",
                    handler:function(){me.delete(gridId,delecturl,xcdc);}
                }
            ]
        });
        return resu;
    },



    createStore: function (who,dataUrl,dataroot) {
        var me = this;
        who.myStore = Ext.create('Ext.data.Store', {
            pageSize: 5,
            proxy: {
                type: 'ajax',
                url: dataUrl,
                reader: {
                    root: dataroot,
                    type: 'json',
                    totalProperty: 'countNumed'
                }
            },
            fields: who.myColumns.getRecord(),
            autoLoad: false
        });
        who.myStore.load({

            params:{
                start:0,
                limit:5
            }
        });
    },

    myInsert: function (who,gridId,suburl,prefix,windowID,cade) {
       var winITems=new Array();
        var defaultValue = new Array();
        Ext.each(who.comItems, function(item, index){
            winITems[index] = {};
            winITems[index].fieldLabel = item.fieldLabel;
            winITems[index].name= prefix+'.'+item.name;
        });
        if(who.name=='lb'){
            Ext.each( winITems, function(item, index){
                if(item){
                    if(item.fieldLabel=='商品类别编码'){
                        winITems[index].hidden=true;
                    }
                }
            });
            Ext.each( winITems, function(item, index){
                if(item.fieldLabel=='商品类别名称'){
                    winITems[index].allowBlank=false;
                }
            });
        }
        if(who.name=='cx'){
            Ext.each( winITems, function(item, index){
                if(item.fieldLabel=='促销状态编码'){
                    winITems[index].hidden=true;
                }
            });
            Ext.each( winITems, function(item, index){
                if(item.fieldLabel=='促销状态名称'){
                    winITems[index].allowBlank=false;
                }
            });
        }
        if(who.name=='dw'){
            Ext.each( winITems, function(item, index){
                if(item.fieldLabel=='单位编码'){
                    winITems[index].hidden=true;
                }
            });
            Ext.each( winITems, function(item, index){
                if(item.fieldLabel=='名称'){
                    winITems[index].allowBlank=false;
                }
            });
        }
        if(cade==1){
            var record= Ext.getCmp(gridId).getSelectionModel().getSelection()[0];
            if(record){
                Ext.each(winITems,function(item, index){
                    winITems[index].value=record.get(item.name.replace(prefix+'.',''));
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

    },
    delete:function(gridId,delecturl,xcdc){
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
                            url: delecturl+'=' + record.get(xcdc),
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



