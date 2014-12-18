Ext.define('myjs.Extjs.CommonWindow', {
    extend: 'Ext.window.Window',
    alternateClassName: 'myComWin',
    comwintit: '添加',
    comwinite: new Array(),
    comsuburl: '',
    comsubpar: {},
    comsubsuc: function () { },
    initComponent: function () {
        var me = this;
        Ext.apply(this, {
            title: me.comwintit,
            width: 400,
            layout: 'fit',
            modal: true,
            items: [
                {
                    xtype: 'form',
                    border: false,
                    margin: '5 5 5 5',
                    layout: 'form',
                    defaults: {
                        xtype: 'textfield',
                        labelAlign: 'right',
                        labelWidth: 100
                    },
                    items: me.comwinite
                }
            ],
            buttonAlign: 'center',
            buttons: [
                {
                    text: '提交',
                    handler: function () {
                        var form = this.up('window').down('form').getForm();
                        if (form.isValid()) {
                            form.submit({
                                url: me.comsuburl,
                                params: me.comsubpar,
                                success: me.comsubsuc
                            });
                        }
                    }
                },
                {
                    text: '重置',
                    handler: function () {
                        this.up('window').down('form').getForm().reset();
                    }
                }
            ]
        });
        this.callParent();
    }
}); 