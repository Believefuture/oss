/**
 * Created by Administrator on 2014/10/26.
 */
Ext.define('ShinowLogin', {
    extend: 'Ext.form.Panel',
    initComponent: function () {
        var me = this;
        Ext.apply(this, {
//            title: '登陆',
            layout: 'form',
            border:false,
            frame: true,
            bodyPadding: 5,
            width: 400,
            defaults: {
                xtype: 'textfield',
                allowBlank: false,
                labelWidth: 60,
                labelAlign: 'right'
            },
            items: [
                {
                    fieldLabel: '用户名',
                    name: 'user.opername'
                },
                {
                    fieldLabel: '密码',
                    name: 'user.pwd',
                    inputType: 'password',
                    width:100
                },
                {
                    xtype: 'panel',
                    layout: 'column',
                    border: false,
                    items: [
                        {
                            xtype: 'textfield',
                            name:'validCode',
                            fieldLabel: '验证码',
                            labelWidth: 60,
                            labelAlign: 'right',
                            allowBlank: false,
                            maxLength:4,//最多字符设置
                            maxLengthText:'最多可输入4个字符',
                            columnWidth: .7
                        },
                        {
                            xtype: 'panel',
                            border: false,
                            html: '&nbsp;<img id="myImg" src="../myjs/validCode.jsp" onclick="this.src=\'../myjs/validCode.jsp?r=\'+Math.random()" width="60px" height="20px" >',
                            columnWidth: .3,
                            width:100
                        }
                    ]
                }
            ],
            buttonAlign: 'center',
            buttons: [
                { text: '登陆', handler: me.doLogin },
                { text: '重置', handler: function () { this.up('form').getForm().reset(); } }
            ]
        });

        this.callParent();
    },
    doLogin:function() {
        var form = this.up('form').getForm();
        if(form.isValid()){
            form.submit({
                url: '/oplogin',
                success: function(form, action) {
                    var msg=Ext.JSON.decode(action.response.responseText);
                    if(msg.valid){
                        window.location="/loginSucceed";
                        return;
                    }
                    Ext.Msg.alert(msg.message);
                },
                failure: function(form, action) {
                    var msg=Ext.JSON.decode(action.response.responseText);
                    Ext.Msg.alert(msg.message);
                }
            });
        };
    }
});