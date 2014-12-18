/**
 * Created by Administrator on 2014/11/17.
 */
Ext.define('myjs.Caigao',{
    extend: 'Ext.tab.Panel',
    requires:['myjs.Extjs.test','myjs.CaigaoDownCXInstockInfo'],
    id:'caigaopanel',
    tabPosition: 'left',
    closable: true,
    myStore: null,
    myColumns: {},
    comColumns:[],
    comItems: new Array(),
    initComponent:function(){
        var me = this;
        Ext.apply(this,{
            items: [
                Ext.create('myjs.Extjs.test'),
                Ext.create('myjs.CaigaoDownCXInstockInfo')
                ]
        });
        this.callParent();
    }

});