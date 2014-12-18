/**
 * Created by Administrator on 2014/12/10.
 */
/**
 * Created by Administrator on 2014/10/27.
 */
Ext.define('myjs.roleinfowork', {
    extend: 'Ext.panel.Panel',
    requires: ['myjs.Extjs.CommonColumns'],
    id: 'roleinfoid',
    tabPosition: 'left',
    tree:{},
    jsonData: {},
    myStore: null,
    myColumns: {},
    comColumns: [],
    comItems: new Array(),
    initComponent: function () {
        var me = this;
        Ext.apply(this, {
            closable: true,
            layout: 'border',
            items: [
                {
                    id: 'rolegridpanel',
                    region: 'center',
                    xtype: 'grid',
                    width: 900,
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
                    listeners: {
                        itemclick: function (view, record) {
                            var state = record.get('state');
                            if (!state) {
                                Ext.Msg.alert('系统提示', '该角色已删除或未启用');
                            }
                                var roleid = record.get('roleid');
                                name = record.get("rolename");
                                var aa = Ext.getCmp("roletree2").getRootNode();
                                Ext.Ajax.request({
                                    url: "/queryroletree?role.roleid=" + roleid,
                                    async: false,
                                    success: function (response) {
                                        me.jsonData = response.responseText;
                                        if (typeof(me.jsonData) === 'string') {
                                            me.jsonData = Ext.JSON.decode(me.jsonData);
                                            me.treestore = me.jsonData.roletree.children;
                                            aa.removeAll(false);
                                            Ext.getCmp("roletree2").setRootNode(me.jsonData.roletree);
                                            Ext.getCmp("roletree2").getRootNode().data.text = name;
                                            Ext.getCmp("roletree2").expandAll();

                                        }
                                    }
                                });
                            }
                        },
//
                        tbar: [
                            { xtype: 'button', text: '添加', handler: function () {
                                me.insert(me);
                            }},
                            {xtype: 'button', text: '删除', handler: function () {
                                me.delete(me)
                            }},
                            {xtype: 'button', text: '修改', handler: function () {
                                me.update(me);
                            }}

                        ]

                    },{
                    region: 'east',
                    title: "权限信息", width: 200,
                    items: Ext.create('Ext.tree.Panel', {
                        id: 'roletree2',
                        border: false,
//                        collapsible: true,
                        store: Ext.create("Ext.data.TreeStore", {
                            fields: [
                                {name: "text", type: "string", mapping: "menuinfoentity.menuname"}
                            ],
                            root: {
                                text: '展示权限：请选择',
                                id: '-1',
                                children: me.treestore
                            }
                        })
                    })
                }
            ]
        });
        this.callParent();
    },
    test: function () {
        var me = this;
        var mydata = {};
        Ext.Ajax.request({
            url: '/queryField?tableName=tau_roleinfo',
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
                if (item.text == "递增的流水号") {
                    Ext.Array.remove(me.comColumns, item);
                }
            }

        });
        Ext.each(me.comColumns, function (item) {
            if (item.text == '角色编码') {
                item.hidden = true;
            }

        });
        me.myColumns = Ext.create('myjs.Extjs.CommonColumns');
        me.myColumns.comColumns = me.comColumns;
        me.comItems = me.myColumns.getItems();
        me.createStore();

        return me.myColumns.getColumn();
        //me.t(titlname, gridId);
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
                url: 'queryrolelist',
                reader: {
                    root: 'rolelist',
                    type: 'json',
                    totalProperty: 'countNumed'
                }
            },
            fields: me.myColumns.getRecord(),
            autoLoad: false
        });
        this.myStore.load({
            params: {
                start: 0,
                limit: 5
            }
        });
    },


//    insert:function(me){
//        var winITems=new Array();
//        Ext.each(me.comItems, function(item,index){
//            winITems[index] = {};
//            winITems[index].fieldLabel = item.fieldLabel;
//            winITems[index].name= ''+item.name;
//
//        });
//        Ext.each(winITems, function(item){
//            if(item){
//                if(item.fieldLabel=='角色编码'){
//                    Ext.Array.remove(winITems,item);
//                }
//            }
//        });
//        treedate={};
//        Ext.Ajax.request({
//            url: 'treedefault',
//            async: false,
//            success: function (response) {
//               treedate = response.responseText;
//                if (typeof(me.jsonData) === 'string') {
//                    treedate = Ext.JSON.decode(me.jsonData);
//                    me.tree=treedate.roletree.children
//                }
//            }
//        });
//
//         Ext.Array.push(winITems,{
//             xtype:'panel',
//             items: Ext.create('Ext.tree.Panel', {
//                 id: 'roletree3',
//                 border: false,
//                 store: Ext.create("Ext.data.TreeStore", {
//                     fields: [
//                         {name: "text", type: "string", mapping: "children.menuinfoentity.menuname"}
////                         {name:'id',type:'string',mapping:"menuinfoentity.id"}
//                     ],
//                     root: {
//                         text: '请选择',
//                         id: '-1',
//                         children:me.tree
//                     }
//                 })
//             })});
//
//        var wd= Ext.create('myjs.Extjs.CommonWindow',
//            {
//                id:'testtesttest',
//                comsuburl:'tsststststst',
//                comwinite:winITems,
//                comsubsuc: function (form, action) {
//                    var msg = Ext.JSON.decode(action.response.responseText);
//                    if(msg.success=='true'){
//                        Ext.Msg.show({
//                            title: '系统提示',
//                            msg: msg.message,
//                            buttons: Ext.MessageBox.YES,
//                            fn: function(){
//
//                                Ext.getCmp('rolegridpanel').store.reload();
//                                Ext.getCmp('testtesttest').close();
//                            }
//                        });
//                    }else{
//                        Ext.Msg.alert('系统提示', msg.message);
//                    }
//                }
//
//            }).show().center();
//
//    },


    insert : function(me){
        Ext.Ajax.request({
            url : "/treedefault",
            async : false,
            success : function(response){
                me.tree = Ext.JSON.decode(response.responseText);
            }
        });
        var treeStore = Ext.create("Ext.data.TreeStore",{
            fields : [
                {name : "id",type : "String",mapping : "menuinfoentity.menuid"},
                {name : "text",type : "String",mapping : "menuinfoentity.menuname"}
            ],
            root: {
                text: "权限展示",
                id: '-1',
                children: me.tree.roletree.children
            }
        });
        var tttstate = Ext.create('Ext.data.Store', {
            fields: ['value', 'key'],
            data: [
                {"value":true, "key": "应用"},
                {"value":false, "key": "未应用"}

            ]
        });
        Ext.create("Ext.window.Window",{
            title : "角色录入",
            layout : "fit",
            frame : true,
            id : "testtesttest",
            height : 400,
            items : [{
                xtype : "form",
                layout : "border",
                width : 270,
                height : 230,
                id : "roleformid",
                items : [{
                    region : "north",
                    border : false,
                    defaults : {
                        xtype : "textfield",
                        margin : "5 5 5 5",
                        labelWidth : 65,
                        labelAlign : "right"
                    },
                    items : [{
                        fieldLabel : "角色名称",
                        name : "rolename"
                    },{
                        fieldLabel : "排序编码",
                        name : "sortid"
                    },{
                        fieldLabel : "状态",
                        xtype : "combo",
                        queryMode: 'local',
                        displayField: 'key',
                        valueField: 'value',
                        store :tttstate ,
                        name : "state"
                    }]
                },{
                    region : "center",
                    border : false,
                    autoScroll:true,
                    items : [{
                        xtype : "treepanel",
                        store : treeStore,
                        id : "roletreeinsert",
                        collapsible: false,
                        border : false,
                        autoScroll:true,
                        listeners: {
                            'checkchange': function (node, checked) {
                                node.expand();
                                node.checked = checked;
                                if (true == checked) {
                                    var parent_node = node.parentNode;
                                    while (parent_node != null) {
                                        parent_node.set('checked', checked);
                                        parent_node = parent_node.parentNode;
                                    }
                                    node.eachChild(function (child) {
                                        child.set('checked', checked);
                                        child.fireEvent('checkchange', child, checked);
                                    });
                                }
                                if(Ext.getCmp("roletreeinsert").getRootNode().data.id=="-1"){
                                    Ext.getCmp("roletreeinsert").getRootNode().data.checked=false;
                                }
                            }
                        }
                    }]
                }],
                buttonAlign : "center",
                buttons : [{
                    text : "提交",
                    handler : function(){
                        var realform = Ext.getCmp("roleformid").query();
                        var treearray =[];
                        var treedata = Ext.getCmp("roletreeinsert").getChecked();
                        Ext.each(treedata,function(node,index){
                            if(node.data.id!="-1"){
                                treearray[index]={};
                                treearray[index].menuid=node.data.id;
                            }
                        });
                        var roleform = {};
                        Ext.each(realform, function (item) {
                            if (item) {
                                if (item.xtype == 'combo' || item.xtype == 'textfield') {
                                    roleform[item.name] = item.lastValue;
                                }
                            }
                        });
                        var form=Ext.create('Ext.form.Panel',{

                        });
                        //  if(form.isValid()){
                        form.submit({
                            url : "/Addrole",
                            jsonSubmit : true,
                            params : {
                                role:roleform,
                                arry : treearray
                            },
                            success : function(form,action){
                                var msg = Ext.JSON.decode(action.response.responseText);
                                Ext.Msg.show({
                                    title : "系统提示",
                                    msg : msg.message,
                                    icon : Ext.Msg.WARNING,
                                    buttons : Ext.Msg.YES
                                });
                                Ext.getCmp("testtesttest").close();
                                Ext.getCmp("rolegridpanel").store.reload();
                            },
                            failure : function(form,action){
                                var msg = Ext.JSON.decode(action.response.responseText);
                                Ext.Msg.show({
                                    title : "系统提示",
                                    msg : msg.message,
                                    icon : Ext.Msg.WARNING,
                                    buttons : Ext.Msg.YES
                                });
                            }
                        });
                        // }
                    }
                },{
                    text : "重置",
                    handler : function(){
                        Ext.getCmp("roleformid").getForm().reset();
                    }
                }]
            }]
        }).show().center();
        Ext.getCmp("roletreeinsert").expandAll();
    },
    delete: function () {
        var record = Ext.getCmp('rolegridpanel').getSelectionModel().getSelection()[0];
        if (record) {
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
                                    Ext.getCmp('rolegridpanel').store.reload();
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



