/**
 * Created by Administrator on 2014/11/14.
 */
Ext.define('myjs.Extjs.test',{
    extend: 'Ext.panel.Panel',
    requires:'myjs.Extjs.CommonColumns',
    tabPosition: 'left',
    title:  '添加入库信息',
    closable: true,
    myStore: null,
    myColumns: {},
    comColumns:[],
    comItems: new Array(),
    myColumns2: {},
    comColumns2:[],
    comItems2: new Array(),
    initComponent:function(){
        var me = this;
        Ext.apply(this,{
            layout:'border',
            items: [
                {
                    xtype:'form',
                    id:"rukuform",
                    region: 'north',
                    defaults: {
                        xtype: 'textfield',
                        labelAlign: 'right',
                        labelWidth: 100
                    },
                    layout: {
                        type: 'table',
                        columns: 3
                    },
                    items:me.test('入库单','/queryField?tableName=tme_instockinfo'),
                    buttons:[
                        {
                            text:"保存",
                            handler:function(){me.myInsert(me);}
                        },
                        {
                            text:"重置",
                            handler:function(){me.delete();}
                        }
                    ]
                },
                {
                    region: 'center',
                    layout:'fit',
                    xtype:'panel',
//                    height:816,
                    items:me.t('标题','/queryField?tableName=tme_instockdetailsinfo','rukuxianxiguanlipanle')
                }
            ]
        });
        this.callParent();
    },
    test: function (titlname,turl) {
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
        var mygyscmobobm = Ext.create("Ext.data.Store",{
            proxy : {
                url : "/queryaut",
                type : "ajax",
                reader : {
                    type : "json",
                    root : "autList"
                }
            },
            fields : [
                "supplierid",'suppliername'
            ],
            autoLoad : true
        });//供应商编码combox
        var usernameDefault={};
        Ext.Ajax.request({
            url: '/usernameDefaul',
            reader: 'json',
            async: false,
            success: function (response) {
                usernameDefault = Ext.JSON.decode(response.responseText);
            }
        });
        var username=usernameDefault.username;
        Ext.each(mydata.fElist, function(item, index){
            me.comColumns[index] = {};
            me.comColumns[index].dataIndex = item.columnName;
            me.comColumns[index].text = item.description;
            me.comColumns[index].type = me.getType(item.typeName);
        });
        me.myColumns = Ext.create('myjs.Extjs.CommonColumns');
        Ext.each(me.myColumns.comColumns,function(item){
            if(item) {
                Ext.Array.remove(me.myColumns.comColumns, item);
            }
        });
        Ext.apply(me.myColumns.comColumns,  me.comColumns);
        me.comItems = me.myColumns.getItems();
        var ddd=new Date();
        me.newdate=Ext.Date.format(ddd,"Y-m-d");
        Ext.each(me.comItems,function(item,index){
            if(item.fieldLabel=='入库时间'){
                me.comItems[index].readOnly =true;
                me.comItems[index].value= me.newdate;
            }
            if(item.fieldLabel=='操作员编码'){
                me.comItems[index].readOnly =true;
                me.comItems[index].value= username;
                me.comItems[index].fieldLabel='操作员';
            }
        });
        Ext.each(me.comItems,function(item,index){
            if(item.fieldLabel=='供应商编码'){
                me.comItems[index].fieldLabel='供应商';
                me.comItems[index].width=300;
                me.comItems[index].editable = false;
                me.comItems[index].xtype='combo';
                me.comItems[index].store=mygyscmobobm;
                me.comItems[index].displayField='suppliername';
                me.comItems[index].valueField='supplierid';

            }
        });
        Ext.each(me.comItems,function(item,index){
            if(item.fieldLabel=='金额'){
                me.comItems[index].readOnly =true;
                me.comItems[index].id='testintotalmoney';
            }
        });
        Ext.Array.remove(me.comItems,me.comItems[0]);
        Ext.Array.remove(me.comItems,me.comItems[0]);
        Ext.each(me.comItems,function(item,index){
            if(item.fieldLabel=='备注'){
                me.comItems[index].xtype='textarea';
                me.comItems[index].colspan=2;
                me.comItems[index].width=555;
                me.comItems[index].height=30;
            }
        });
        Ext.each(me.comItems,function(item,index){
            if(item.fieldLabel=='经手人'){
                me.comItems[index].width=300;
            }
        });
        Ext.each(me.comItems,function(item,index){
            if(item.fieldLabel=='入库方式'){
//                me.comItems[index].width=300;
                me.comItems[index].editable = false;
                me.comItems[index].xtype='combo';
                me.comItems[index].store=me.type;
                me.comItems[index].displayField='name';
                me.comItems[index].valueField='abbr';

            }
        });
//        Ext.each(me.comItems,function(item,index){
//           item.name='instockinfo.'+item.name;
//        });
        return me.comItems;
    },

    type: Ext.create('Ext.data.Store', {
        fields: ['abbr', 'name'],
        data: [
            {"abbr": 1, "name": "正常入库"},
            {"abbr": 2, "name": "报溢"},
            {"abbr": 3, "name": "盘盈"}

        ]
    }),

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


    mygridmobobm: Ext.create("Ext.data.Store",{
        proxy : {
            url : "inStockDefault",
            type : "ajax",
            reader : {
                type : "json",
                root : "merlist"
            }
        },
        fields : [
            'merchandiseid','merchandisename'
        ],
        autoLoad : true
    }),//供应商编码combox


    t:function (titlname,turl,gridId) {
        var me=this;
        var data = {};
        var dajad = Ext.Ajax.request({
            url: turl,
            reader: 'json',
            root:'fElist',
            async: false,
            success: function (response) {
                data = Ext.JSON.decode(response.responseText);
            }
        });
        Ext.each(data.fElist, function(item, index){
            me.comColumns2[index] = {};
            me.comColumns2[index].dataIndex = item.columnName;
            me.comColumns2[index].text = item.description;
            me.comColumns2[index].type = me.getType(item.typeName);
        });
        Ext.each(me.comColumns2,function(item,index){
            if(item.dataIndex=='id'){
                item.dataIndex='inStockMerNameHidden';
                item.type='string'
            }
        });
        Ext.each(me.comColumns2,function(item,index){
            if(item){
            if(item.dataIndex=='billcode'){
                Ext.Array.remove(me.comColumns2,item);
            }
            }
        });
        me.myColumns2 = Ext.create('myjs.Extjs.CommonColumns');
        Ext.each(me.myColumns2.comColumns,function(item){
            if(item) {
                Ext.Array.remove(me.myColumns2.comColumns, item);
            }
        });
        Ext.apply(me.myColumns2.comColumns,  me.comColumns2);
        me.comItems2 = me.myColumns2.getItems();
        me.kongStore =  Ext.create('Ext.data.ArrayStore', {
            fields:me.myColumns2.getRecord(),
            data:[
                {}
            ]
        });
        this.cellEditing = new Ext.grid.plugin.CellEditing({
            clicksToEdit: 1,
            listeners:{
                edit:function(editor, context){
                    if (context.value)
                    {
                        var myStore = Ext.data.StoreManager.lookup(me.kongStore);
                        if (context.field === 'merchandiseid')
                        {
                            context.record.set('inStockMerNameHidden', me.myCode);
                            context.record.set('merchandiseid', me.myName);
                        }
                        if (context.field === "num")
                        {
                            if (context.record.data.price)
                            {
//                                context.record.data.total = context.record.data.price * context.value;
//                                myStore.remove(context.record);
//                                myStore.insert(context.rowIdx, context.record);
                                context.record.set('total',context.record.data.price * context.value)
                            }
                        }
                        if (context.field === "price")
                        {
                            if (context.record.data.num)
                            {
                                context.record.set('total',context.record.data.num * context.value)
                            }
                        }
                        if (context.record.data.merchandiseid && context.record.data.num && context.record.data.price)
                        {
                            var isEmptyRow = false;
                            context.grid.store.each(function (record) {
                                if (record.get('merchandiseid') === '' || record.get('num') <= 0||record.get('price')<=0) {
                                    isEmptyRow = true;
                                    return false;
                                }
                            });
                            if (!isEmptyRow) {
                                context.grid.store.add({});
                            }
                        }
                        me.totalmoney = 0;
                        for(var i=0;i<myStore.data.items.length;i++){
                            if (!isNaN(myStore.data.items[i].data.total) && myStore.data.items[i].data.total != "")
                            {
                                me.totalmoney += myStore.data.items[i].data.total;
                            }
                        }
                        Ext.getCmp('testintotalmoney').setValue(me.totalmoney);
                    }
                }
            }
        });
        var myColumns = me.myColumns2.getColumn();
        myColumns[0]={
            dataIndex: 'inStockMerNameHidden',
            hidden:true
        };
        myColumns[1].editor = ({
            xtype : "combo",
            store :me.mygridmobobm,
            queryMode : "local",
            displayField : "merchandisename",
            editable : false,
            valueField : "merchandiseid",
            listeners:{
                select: function(combo, records){
                    me.myCode = this.value;
                    me.myName = records[0].data.merchandisename;
                }
            }
        });
        myColumns[2].editor = ({
            xtype: 'numberfield',
            regex : /^[0-9]*$/,
            blankText : "只能输入数字",
            minValue: 1,
            allowBlank: false
        });
        myColumns[3].editor = ({
            xtype: 'numberfield',
            minValue: 1,
            regex : /^[0-9]*$/,
            blankText : "只能输入数字",
            allowBlank: false
        });
        myColumns[4]={
            text: '总价',
            dataIndex: 'total'
        };
        var resu=Ext.create('Ext.grid.Panel',
            {
                plugins: this.cellEditing,
                id:gridId,
                columns: myColumns,
                multiSelect: true,
                store: me.kongStore
            });
        return resu;
    },
    myInsert: function (me) {
        var instockdetalist=[];
        var myStore = Ext.data.StoreManager.lookup(me.kongStore);
        var index=0;
        myStore.each(function(item){
            if(item.get('num')<=0&item.get('price')<=0){
                return;
            }else{
            instockdetalist[index]=item.data;
            instockdetalist[index]['merchandiseid']={};
            instockdetalist[index]['merchandiseid']['merchandiseid']=item.get('inStockMerNameHidden');
//            instockdetalist[index][merchandiseid]={};
            }
            index++;
        });
        var instockinfo={};
        var formdata= Ext.getCmp('rukuform').query();
        Ext.each(formdata,function(item){
            if(item){
                if(item.xtype=='combo'||item.xtype=='textfield'){
                    if(item.name=='supplierid'){
                        instockinfo[item.name]={};
                        instockinfo[item.name][item.name]=item.lastValue;
//                        instockinfo[item.name]={supplierid:item.lastValue}
                    }else if(item.name=='operid'){
                        instockinfo[item.name]={};
                        instockinfo[item.name]['opername']=item.lastValue;
                    }else{
                    instockinfo[item.name]=item.lastValue
                    }
                }

            }
        });
   Ext.getCmp('rukuform').getForm().submit({
        url: '/instockinfoadd',
        jsonSubmit:true,
        params:{
            instockdetalist:instockdetalist,
            instockinfo:instockinfo

        },
        success : function(form,action){
            var msg = Ext.JSON.decode(action.response.responseText);
            Ext.Msg.show({
                title : "系统提示",
                msg : msg.message,
                icon : Ext.Msg.WARNING,
                buttons : Ext.Msg.YES
            });

            Ext.getCmp("rukuxianxiguanlipanle").store.reload();
        },
        failure : function(form,action){
            var msg = Ext.JSON.decode(action.response.responseText);
            Ext.Msg.show({
                title : "系统提示",
                msg : msg.message,
                icon : Ext.Msg.WARNING,
                buttons : Ext.Msg.YES
            })
        }
    });
    }
});