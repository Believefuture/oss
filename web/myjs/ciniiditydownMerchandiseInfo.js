/**
 * Created by Administrator on 2014/11/20.
 */
Ext.define('myjs.ciniiditydownMerchandiseInfo', {
    extend: 'Ext.grid.Panel',
    requires: ['myjs.Extjs.CommonColumns', 'myjs.Extjs.CommonWindow'],
    tabPosition: 'left',
    title: '商品信息管理',
    cxcomboxStore:null,
    dwcomboxStore:null,
    lbcomboxStore:null,
    myStore: null,
    myColumns: {},
    comColumns: [],
    comItems: new Array(),
    initComponent: function () {
        var me = this;
        var mydata = {};
        var dajax = Ext.Ajax.request({
            url: '/queryField?tableName=tme_merchandiseinfo',
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
            if (item.text == '商品编码') {
                item.hidden=true;
            }
            if(item.text=='单位编码'){
                item.hidden=true;
                item.dataIndex='unitid.unitid';
            }
            if(item.text=='促销状态编码'){
                item.hidden=true;
                item.dataIndex='prostatusid.prostatusid';
            }
            if(item.text=='商品类别编码'){
                item.hidden=true;
                item.dataIndex='merchandisecid.merchandisecid';
            }
        });
        Ext.Array.push(me.comColumns,{
            text:'单位名称',
            dataIndex:'unitid.name',
            type:'string'
        },{
            text:'促销状态名称',
            dataIndex:'prostatusid.prostatusname',
            type:'string'
        },{
            text:'商品类别名称',
            dataIndex:'merchandisecid.merchandisecname',
            type:'string'
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
            id: 'MerchandiseInfogridplane',
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
            buttons: [
                {
                    text: "添加",
                    handler: function () {
                        me.myInsert(me);
                    }
                },
                {
                    text: "修改",
                    handler: function () {
                        me.myUpdata(me);
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
                url: '/qureymer',
                reader: {
                    root: 'merList',
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

    myInsert: function (myGlobal) {
        var cinidmccIcomboxstore = Ext.create("Ext.data.Store",{
            proxy : {
                url : "/qureymccI",
                type : "ajax",
                reader : {
                    type : "json",
                    root : "mccIList"
                }
            },
            fields : [
                "merchandisecid","merchandisecname"
            ]
        });
        var cinidUnitcomboxstore = Ext.create("Ext.data.Store",{
            proxy : {
                url : "/qureyuni",
                type : "ajax",
                reader : {
                    type : "json",
                    root : "uniList"
                }
            },
            fields : [
                "name","unitid"
            ]
        });
        var cinidprocomboxstore = Ext.create("Ext.data.Store",{
            proxy : {
                url : "/qureypro",
                type : "ajax",
                reader : {
                    type : "json",
                    root : "proList"
                }
            },
            fields : [
                "prostatusid","prostatusname"
            ]
        });
        var winITems=new Array();
        Ext.each(myGlobal.comItems, function(item, index){
            winITems[index] = {};
            winITems[index].fieldLabel = item.fieldLabel;
            winITems[index].name= 'mer.'+item.name;
        });
        Ext.each(winITems, function(item){
            if(item){
                if(item.fieldLabel=='商品编码'){
                    Ext.Array.remove(winITems,item);
                }
            }
        });
        Ext.each(winITems, function(item){
            if(item){
                if(item.fieldLabel=='单位编码'){
                    Ext.Array.remove(winITems,item);
                }
            }
        });
        Ext.each(winITems, function(item){
            if(item){
                if(item.fieldLabel=='促销状态编码'){
                    Ext.Array.remove(winITems,item);
                }
            }
        });
        Ext.each(winITems, function(item){
            if(item){
                if(item.fieldLabel=='商品类别编码'){
                    Ext.Array.remove(winITems,item);
                }
            }
        });
        Ext.each(winITems,function(item,index){
            if(item.fieldLabel=='单位名称'){
                winITems[index].name='mer.unitid.unitid';
                winITems[index].editable = false;
                winITems[index].xtype='combo';
                winITems[index].store=cinidUnitcomboxstore;
                winITems[index].displayField='name';
                winITems[index].valueField='unitid';

            }
            if(item.fieldLabel=='促销状态名称'){
                winITems[index].name='mer.prostatusid.prostatusid';
                winITems[index].editable = false;
                winITems[index].xtype='combo';
                winITems[index].store=cinidprocomboxstore;
                winITems[index].displayField='prostatusname';
                winITems[index].valueField='prostatusid';

            }
            if(item.fieldLabel=='商品类别名称'){
                winITems[index].name='mer.merchandisecid.merchandisecid';
                winITems[index].editable = false;
                winITems[index].xtype='combo';
                winITems[index].store=cinidmccIcomboxstore;
                winITems[index].displayField='merchandisecname';
                winITems[index].valueField='merchandisecid';

            }
        });
        var session=document
        var wd= Ext.create('myjs.Extjs.CommonWindow',
            {
                id:'pssgltianjiawin',
                comsuburl:'/Addmer',
                comwinite:winITems,
                comsubsuc: function (form, action) {
                    var msg = Ext.JSON.decode(action.response.responseText);
                    if(msg.success=='true'){
                        Ext.Msg.show({
                            title: '系统提示',
                            msg: msg.message,
                            buttons: Ext.MessageBox.YES,
                            fn: function(){

                                Ext.getCmp('MerchandiseInfogridplane').store.reload();
                                Ext.getCmp('pssgltianjiawin').close();
                            }
                        });
                    }else{
                        Ext.Msg.alert('系统提示', msg.message);
                    }
                }

            }).show().center();

    },
    myUpdata: function (myGlobal) {
        var cinidmccIcomboxstore = Ext.create("Ext.data.Store",{
            proxy : {
                url : "/qureymccI",
                type : "ajax",
                reader : {
                    type : "json",
                    root : "mccIList"
                }
            },
            fields : [
                "merchandisecid","merchandisecname"
            ]
        });
        var cinidUnitcomboxstore = Ext.create("Ext.data.Store",{
            proxy : {
                url : "/qureyuni",
                type : "ajax",
                reader : {
                    type : "json",
                    root : "uniList"
                }
            },
            fields : [
                "name","unitid"
            ]
        });
        var cinidprocomboxstore = Ext.create("Ext.data.Store",{
            proxy : {
                url : "/qureypro",
                type : "ajax",
                reader : {
                    type : "json",
                    root : "proList"
                }
            },
            fields : [
                "prostatusid","prostatusname"
            ]
        });
        var winITems=new Array();
        var record= Ext.getCmp('MerchandiseInfogridplane').getSelectionModel().getSelection()[0];
        if(record){
            Ext.each(myGlobal.comItems, function(item, index){
                winITems[index] = {};
                winITems[index].fieldLabel = item.fieldLabel;
                winITems[index].name= 'mer.'+item.name;
            });
            Ext.each(winITems, function(item){
                if(item){
                    if(item.fieldLabel=='商品编码'){
                        item.hidden=true;
                    }
                }
            });
            Ext.each(winITems, function(item){
                if(item){
                    if(item.fieldLabel=='单位编码'){
                        Ext.Array.remove(winITems,item);
                    }
                }
            });
            Ext.each(winITems, function(item){
                if(item){
                    if(item.fieldLabel=='促销状态编码'){
                        Ext.Array.remove(winITems,item);
                    }
                }
            });
            Ext.each(winITems, function(item){
                if(item){
                    if(item.fieldLabel=='商品类别编码'){
                        Ext.Array.remove(winITems,item);
                    }
                }
            });
            Ext.each(winITems,function(item,index){
                if(item.fieldLabel=='单位名称'){
                    winITems[index].name='mer.unitid.unitid';
                    winITems[index].editable = false;
                    winITems[index].xtype='combo';
                    winITems[index].store=cinidUnitcomboxstore;
                    winITems[index].displayField='name';
                    winITems[index].valueField='unitid';
                    winITems[index].value=record.get('unitid.name');

                }
                if(item.fieldLabel=='促销状态名称'){
                    winITems[index].name='mer.prostatusid.prostatusid';
                    winITems[index].editable = false;
                    winITems[index].xtype='combo';
                    winITems[index].store=cinidprocomboxstore;
                    winITems[index].displayField='prostatusname';
                    winITems[index].valueField='prostatusid';

                }
                if(item.fieldLabel=='商品类别名称'){
                    winITems[index].name='mer.merchandisecid.merchandisecid';
                    winITems[index].editable = false;
                    winITems[index].xtype='combo';
                    winITems[index].store=cinidmccIcomboxstore;
                    winITems[index].displayField='merchandisecname';
                    winITems[index].valueField='merchandisecid';

                }
            });
            Ext.each(winITems,function(item, index){
                winITems[index].value=record.get(item.name.replace('mer.',''));
            });
        }else{
            Ext.Msg.alert('系统提示', '请选择修改项');
            return;
        }

        var wd= Ext.create('myjs.Extjs.CommonWindow',
            {
                id:'pssglxiugaiwin',
                comsuburl:'/updatamer',
                comwinite:winITems,
                comsubsuc: function (form, action) {
                    var msg = Ext.JSON.decode(action.response.responseText);
                    if(msg.success=='true'){
                        Ext.Msg.show({
                            title: '系统提示',
                            msg: msg.message,
                            buttons: Ext.MessageBox.YES,
                            fn: function(){
                                Ext.getCmp('MerchandiseInfogridplane').store.reload();
                                Ext.getCmp('pssglxiugaiwin').close();
                            }
                        });
                    }else{
                        Ext.Msg.alert('系统提示', msg.message);
                    }
                }

            }).show().center();

    },
    delete:function() {
        var record = Ext.getCmp('MerchandiseInfogridplane').getSelectionModel().getSelection()[0];
        if (record) {
            Ext.MessageBox.show({
                title: '删除提示',
                msg: '确实要删除数据么?',
                buttons: Ext.MessageBox.YESNO,
                icon: Ext.MessageBox.WARNING,
                fn: function (btn) {
                    if (btn === 'yes') {
                        Ext.Ajax.request({
                            url: '/delectmer?mer.merchandiseid=' + record.get('merchandiseid'),
                            success: function (action) {
                                var result;
                                if (typeof (action.responseText) === "string") {
                                    result = Ext.JSON.decode(action.responseText);
                                }
                                else {
                                    result = action.responseText;
                                }
                                if (result.success) {
                                    Ext.getCmp('MerchandiseInfogridplane').store.reload();
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
//        var testdata={};
//        var te=[];
//        Ext.each(this.myStore.data.items,function(item,index){
//            if(item){
//
//                te[index]=item.data;
//            }
//        });
//        var from= Ext.create('Ext.form.Panel');
//        from.submit({
//            url:'/testss',
//            jsonSubmit:true,
////            params:{stest:Ext.decode("[{id:11},{id:22}]")}
//            params:{
//                stest:te
//            }
//        });
//     }
});