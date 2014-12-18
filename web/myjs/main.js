/**
 * Created by Administrator on 2014/10/26.
 */
Ext.define('ShinowMain', {
    extend: 'Ext.container.Viewport',
    initComponent: function () {
        var me = this;
        this.createMenuList();
        Ext.apply(this, {
            layout: 'border',
            style: {
                backgroundColor: '#d3e1f1'
            },
            items: [
                {
                    region: 'north',
                    xtype: 'toolbar',
                    border: false,
                    height: 40,
                    items: [
                        {
                            xtype: 'tbtext',
                            text: 'XXX系统',
                            style: {
                                color: 'red',
                                fontSize: '20pt'
                            }
                        },
                        { xtype: 'tbfill' },
                        {
                            xtype: 'button',
                            text: '注销'
                        }
                    ]
                },
                {
                    region: 'west',
                    width: 200,
                    title: '菜单栏',
                    layout: 'accordion',
                    collapsible: true,
                    split: true,
                    margin: '5 0 5 0',
                    items: me.menuList
                },
                {
                    region: 'center',
                    xtype:'tabpanel',
                    id:'ddt',
                    margin: '5 0 5 0'
                },
                {
                    region: 'south',
                    xtype: 'toolbar',
                    border: false,
                    items: [
                        { xtype: 'tbfill' },
                        {
                            xtype: 'tbtext',
                            text: '版权所有 启奥实训',
                            style: {
                                color: 'red',
                                fontWeight: 'bold'
                            }
                        }
                    ]
                }
            ]
        });
        this.callParent();
    },
    menuList: new Array(),
    createMenuList: function () {
        var menuData = {}, tpl, me = this;
        tpl = new Ext.XTemplate(
            '<tpl for=".">',
            '<div class="part01">',
            '<img src="{src}">',
            '<div class="con">',
            '<span>{title}</span>',
            '</div>',
            '</div>',
            '</tpl>'
        );
//        var jsonUrl = document.getElementById('jsonUrl').value;
        Ext.Ajax.request({
            url: '/querymenu',//+jsonUrl,
            async: false,
            success: function (response) {
                menuData = Ext.JSON.decode(response.responseText);
            }
        });

        for (var i = 0, len = menuData.tree.children.length; i < len; i++) {
            var storeID = 'store_' + i, item, title = menuData.tree.children[i].menuinfoentity.menuname;
            Ext.create('Ext.data.Store', {
                id: storeID,
                data:menuData.tree.children[i].children,
                fields: [
                    { name: 'src', type: 'string' ,mapping:'menuinfoentity.src'},
                    { name: 'title', type: 'string' ,mapping:'menuinfoentity.menuname'},
                    { name: 'tag', type: 'string' ,mapping:'menuinfoentity.modelid'},
                    { name: 'module', type: 'string' ,mapping:'menuinfoentity.url'}
                ]
            });

            item = {
                xtype: 'panel',
                title: title,
                layout: 'fit',
                items: [{
                    xtype: 'dataview',
                    store: Ext.data.StoreManager.lookup(storeID),
                    tpl: tpl,
                    itemSelector: 'div.part01',
                    listeners:{
                        itemclick:function(view,record){
                            Ext.require(record.get('module'),function(){

                                var center=Ext.getCmp('ddt');
                                var tab=center.items.get(record.get('tag'));
                                if(!tab){
                                    var obj=Ext.create(record.get('module'),{title:record.get('title')});
                                    center.add(obj);
                                    center.setActiveTab(obj);
                                }
                                else{
                                    if(center.setActiveTab()!==tab){
                                        center.setActiveTab(tab);
                                    }
                                }
                            },this);

                        }
                    }

                }]
            };
            me.menuList.push(item);
        }
    }
});