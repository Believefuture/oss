/**
 * Created by Administrator on 2014/11/14.
 */
Ext.define('myjs.CXdownwindowpanel',{
    extend: 'Ext.panel.Panel',
    requires:'myjs.Extjs.CommonColumns',
    defaultvalue:{},
    myStore: null,
    myColumns: {},
    comColumns:[],
    comItems: new Array(),
    comColumns2:[],
    resu:{},
    comItems2: new Array(),
    initComponent:function(){
        var me = this;
        Ext.apply(this,{
            layout:'border',
            items: [
                {
                    xtype:'form',
                    id:"cxoutstockdetainfoform",
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
                    items:me.test('出库单','/queryField?tableName=tme_outstockinfo'),
                    buttons:[
                        {
                            text:"保存",
                            hidden:true,
                            handler:function(){me.myupdate(me);}
                        },
                        {
                            text:"编辑",
                            handler:function(){me.myeditor(me);}
                        }
                    ]
                },
                {
                    region: 'center',
                    layout:'fit',
                    xtype:'panel',
//                    height:816,
                    items:me.t('标题','/queryField?tableName=tme_outstockdetailsinfo','cxoutstockdetaxianxilipanle')
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
            if(item.fieldLabel=='出库时间'){
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
            if(item.fieldLabel=='出库金额'){
                me.comItems[index].readOnly =true;
                me.comItems[index].id='outtotalmoney';
                me.comItems[index].value=me.defaultvalue.totalmoney
            }
            if(item.fieldLabel=='出库单号'){
                me.comItems[index].value=me.defaultvalue.outbillcode;
//                me.comItems[index].hidden=true;

            }
        });
        Ext.Array.remove(me.comItems,me.comItems[0]);
//        Ext.Array.remove(me.comItems,me.comItems[1]);
        Ext.each(me.comItems,function(item,index){
            if(item.fieldLabel=='备注'){
                me.comItems[index].xtype='textarea';
                me.comItems[index].colspan=2;
                me.comItems[index].width=555;
                me.comItems[index].height=30;
                me.comItems[index].value=me.defaultvalue.remark
            }
        });
        Ext.each(me.comItems,function(item,index){
            if(item.fieldLabel=='经手人'){
                me.comItems[index].width=300;
                me.comItems[index].value=me.defaultvalue.handler
            }
        });
        Ext.each(me.comItems,function(item,index){
            if(item.fieldLabel=='出库方式'){
                me.comItems[index].editable = false;
                me.comItems[index].xtype='combo';
                me.comItems[index].store=me.type;
                me.comItems[index].displayField='name';
                me.comItems[index].valueField='abbr';
//                me.comItems[index].hidden=true;
            }
        });

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
        var dataderaultvalue=[];
        var dajad = Ext.Ajax.request({
            url: turl,
            reader: 'json',
            root:'fElist',
            async: false,
            success: function (response) {
                data = Ext.JSON.decode(response.responseText);
            }
        });
        var datadefault = Ext.Ajax.request({
            url: 'outstockdetavalue?outstock.outbillcode='+me.defaultvalue.outbillcode,
            reader: 'json',
            root:'instockdetalist',
            async: false,
            success: function (response) {
                tttdata = Ext.JSON.decode(response.responseText);
            }
        });
        Ext.each(tttdata.outstockdetalist,function(item,index){
            dataderaultvalue[index]={};
            dataderaultvalue[index].inStockMerNameHidden=item.merchandiseid.merchandiseid;
            dataderaultvalue[index].merchandiseid=item.merchandiseid.merchandisename;
            dataderaultvalue[index].price=item.price;
            dataderaultvalue[index].num=item.num;
            dataderaultvalue[index].total=item.num*item.price;
            dataderaultvalue[index].id=item.id;
            dataderaultvalue[index].stockprice =item.stockprice;
        });
        var comColumns2=[];
        Ext.each(data.fElist, function(item, index){
            comColumns2[index] = {};
            comColumns2[index].dataIndex = item.columnName;
            comColumns2[index].text = item.description;
            comColumns2[index].type = me.getType(item.typeName);
        });
        Ext.each(comColumns2,function(item){
            if(item.dataIndex=='id'){
                item.dataIndex='inStockMerNameHidden';
                item.type='string';
            }
            if(item.dataIndex=='stock_price'){
                item.dataIndex='stockprice'
            }
        });
        Ext.each(comColumns2,function(item){
            if(item){
                if(item.dataIndex=='outbillcode'){
                    Ext.Array.remove(comColumns2,item)
                }
            }
        });
        Ext.Array.push(comColumns2,{
            dataIndex:'total',
            type:'int'
        });
        me.myColumns2 = Ext.create('myjs.Extjs.CommonColumns');
        Ext.each(me.myColumns2.comColumns,function(item){
            if(item) {
                Ext.Array.remove(me.myColumns2.comColumns, item);
            }
        });
        Ext.apply(me.myColumns2.comColumns,  comColumns2);
        me.comItems2 = me.myColumns2.getItems();
        me.kongStore =  Ext.create('Ext.data.ArrayStore', {
            fields:me.myColumns2.getRecord(),
            data:[]

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
                        Ext.getCmp('outtotalmoney').setValue(me.totalmoney);
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
        myColumns[4].editor = ({
            xtype: 'numberfield',
            regex : /^[0-9]*$/,
            blankText : "只能输入数字",
            minValue: 1,
            allowBlank: false
        });
        myColumns[4].width=160;
        myColumns[6]={
            text: '总价',
            dataIndex: 'total'
        };
        myColumns[5]={
            dataIndex: 'id',
            hidden:true
        };
        me.resu=Ext.create('Ext.grid.Panel',
            {
                plugins: this.cellEditing,
                id:gridId,
                columns: myColumns,
                multiSelect: true,
                store: me.kongStore,
                readOnly:true
            });
        me.kongStore.add(dataderaultvalue);
        Ext.getCmp('cxoutstockdetaxianxilipanle').disable();
        return me.resu;
    },
    myeditor:function(me){
        var formdata= Ext.getCmp('cxoutstockdetainfoform').query();
        Ext.each(formdata,function(item){
                if(item.text=='保存'){
                    item.show();
                }
        });
        Ext.getCmp('cxoutstockdetaxianxilipanle').enable();
        me.kongStore.add({});

    },
    myupdate: function (me) {
        var outstockdetalist=[];
        var myStore = Ext.data.StoreManager.lookup(me.kongStore);
        var index=0;
        myStore.each(function(item){
            if(item.get('num')<=0&item.get('price')<=0){
                return;
            }else{
                if(item.data.id===''){
                    item.data.id=null;
                }
                outstockdetalist[index]=item.data;
                outstockdetalist[index]['merchandiseid']={};
                outstockdetalist[index]['merchandiseid']['merchandiseid']=item.get('inStockMerNameHidden');
            }
            index++;
        });
        var outstock={};
        var formdata= Ext.getCmp('cxoutstockdetainfoform').query();
        Ext.each(formdata,function(item){
            if(item){
                if(item.xtype=='combo'||item.xtype=='textfield'||item.xtype=='textarea'){
                     if(item.name=='operid'){
                        outstock[item.name]={};
                        outstock[item.name]['opername']=item.lastValue;
                    }else{
                        outstock[item.name]=item.lastValue
                    }
                }

            }
        });
   Ext.getCmp('cxoutstockdetainfoform').getForm().submit({
        url: '/outstockupdate',
        jsonSubmit:true,
        params:{
            outstockdetalist:outstockdetalist,
            outstock:outstock

        },
        success : function(form,action){
            var msg = Ext.JSON.decode(action.response.responseText);
            Ext.Msg.show({
                title : "系统提示",
                msg : msg.message,
                icon : Ext.Msg.WARNING,
                buttons : Ext.Msg.YES
            });
            Ext.getCmp('mainoutstockwindow').close();
            Ext.getCmp('cxoutstockdetaxianxilipanle').store.reload();
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