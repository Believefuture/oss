/**
 * Created by Administrator on 2014/10/27.
 */
Ext.define('myjs.operinfopanle',{
    extend: 'Ext.panel.Panel',
    requires:['myjs.Extjs.CommonColumns','myjs.Extjs.CommonWindow'],
    id:'operpanlmei',
    tabPosition: 'left',
    myStore: null,
    myColumns: {},
    comColumns:[],
    comItems: new Array(),
    initComponent:function(){
        var me = this;
        Ext.apply(this,{
            closable:true,
            layout:'border',
            items:[
                {
                    id:'oper',
                    region:'center',
                    xtype:'grid',
                    width:900,
                    columns: me.test(),
                    store: me.myStore,
                    dockedItems: [
                        {
                            xtype: 'pagingtoolbar',
                            store: me.myStore,
                            dock: 'bottom',
                            displayInfo: true
                        }
                    ],
//
                    tbar: [
                        { xtype: 'button', text: '添加', handler: function () {
                            me.insert(me,'oper','/Addoper','Addoper',1);
                        }},
                        {xtype: 'button', text: '删除', handler: function () {
                            me.delete(me)
                        }},
                        {xtype: 'button', text: '修改', handler: function () {
                            me.insert(me,'oper','/updateoper','updateoper');
                        }}//,
//                        {fieldLabel: '供应商名称', xtype: 'textfield', name: 'supplierName', labelAlign: "right", id: "opename"},
//                        {xtype: 'button', text: '查询',icon:"images/1-4.png", handler: me.find}
                    ]

                }
            ]
        });
        this.callParent();
    },
    test: function () {
        var me=this;
        var mydata = {};
        Ext.Ajax.request({
            url: '/queryField?tableName=tau_operinfo',
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
                if(item.text=="递增的流水号"){
                    Ext.Array.remove(me.comColumns,item);
                }
            }
            if(item) {
                if (item.text == '密码') {
                    Ext.Array.remove(me.comColumns, item);
                }
            }
        });
        Ext.each(me.comColumns,function(item){
            if(item.text=='操作员编码'){
                item.hidden=true;
            }
            if(item.text=='角色编码'){
                item.text='角色名称';
                item.dataIndex='roleid.rolename';
            }
            if(item.text=='Email'){

                item.width=180;
            }
        });
        me.myColumns = Ext.create('myjs.Extjs.CommonColumns');
        Ext.each(me.myColumns.comColumns,function(item,index){
            if(item) {
                Ext.Array.remove(me.myColumns.comColumns, item);
            }
        });
        Ext.apply(me.myColumns.comColumns,  me.comColumns);
        me.comItems = me.myColumns.getItems();
        me.createStore();

        return me.myColumns.getColumn();
        //me.t(titlname, gridId);
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
    createStore: function () {
        var me = this;
        this.myStore = Ext.create('Ext.data.Store', {
            pageSize: 5,
            proxy: {
                type: 'ajax',
                url: '/queryoperlist',
                reader: {
                    root: 'operlist',
                    type: 'json',
                    totalProperty: 'countNumed'
                }
            },
            fields: me.myColumns.getRecord(),
            autoLoad:false
        });
        this.myStore.load({
            params:{
                start:0,
                limit:5
            }
        });
    },

    insert: function (myGlobal,gridId,suburl,windowId,cade) {
       var winITems=new Array();
        var defaultValue = new Array();
        Ext.each(myGlobal.comItems, function(item,index){
//        if(itme.name='roleid')
           winITems[index] = {};
           winITems[index].fieldLabel = item.fieldLabel;
           winITems[index].name= 'oper.'+item.name;

        });

//        Ext.Ajax.request({
//            url: '',
//            reader: 'json',
//            root:'',
//            async: false,
//            success: function (response) {
//                winITems = Ext.JSON.decode(response.responseText);
//            }
//        });

        var mygyscmobobm = Ext.create("Ext.data.Store",{
            proxy : {
                url : "/queryrolederault",
                type : "ajax",
                reader : {
                    type : "json",
                    root : "rolelist"
                }
            },
            fields : [
                "roleid",'rolename'
            ],
            autoLoad : false
        });//供应商编码combox
        var state = Ext.create('Ext.data.Store', {
            fields: ['value', 'key'],
            data: [
                {"value":true, "key": "在岗"},
                {"value":false, "key": "离职"}

            ]
        });
        Ext.each(winITems,function(item,index){
            if(item){
                if(item.fieldLabel=='操作员编码'){
                    Ext.Array.remove(winITems,item);
                }
            }
        });
        Ext.each(winITems,function(item,index){
            if(item.fieldLabel=='角色名称'){
                winITems[index].name='oper.roleid.roleid';
                winITems[index].editable = false;
                winITems[index].xtype='combo';
                winITems[index].store=mygyscmobobm;
                winITems[index].displayField='rolename';
                winITems[index].valueField='roleid';
            }
        });
        Ext.each(winITems,function(item,index){
            if(item.fieldLabel=='状态'){
                winITems[index].editable = false;
                winITems[index].xtype='combo';
                winITems[index].store=state;
                winITems[index].displayField='key';
                winITems[index].valueField='value';
            }
        });

        if(cade==1){

        }else{
            var record= Ext.getCmp(gridId).getSelectionModel().getSelection()[0];
            if(record){
                Ext.each(winITems,function(item, index){
                    if(item.fieldLabel=='状态'){
                        item.value='';
                    }
                    winITems[index].value=record.get(item.name.replace('oper.',''));
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
                                    Ext.getCmp(windowId).close();
                                }
                            });
                        }else{
                            Ext.Msg.alert('系统提示', msg.message);
                        }
               }

           }).show().center();

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



