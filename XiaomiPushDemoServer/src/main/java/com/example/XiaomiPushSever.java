package com.example;

import com.xiaomi.xmpush.server.Constants;
import com.xiaomi.xmpush.server.Message;
import com.xiaomi.xmpush.server.Region;
import com.xiaomi.xmpush.server.Sender;

/**
 * Created by jkl on 2018/3/23.
 *
 */

public class XiaomiPushSever {

    private static String APP_SECRET_KEY = "Iua85d0tL56xesjLvX85Zg==";
    private static String MY_PACKAGE_NAME = "com.xiaomi.jackpush";

    public static void main(String[] args) throws Exception {
        try {
            sendPushBroadcast();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void sendPushBroadcast() throws Exception {
        Constants.useOfficial();
        Sender sender = new Sender(APP_SECRET_KEY, Region.Other);
        String title = "小米推送——消息标题";
        String description = "小米推送——消息描述";
        String messagePayload ="小米推送——消息内容";
        Message message = new Message.Builder()
                //title设置在通知栏展示的通知的标题，不允许全是空白字符，长度小于16，中英文均以一个计算。
                .title(title)
                //description设置在通知栏展示的通知的描述，不允许全是空白字符，长度小于128，中英文均以一个计算。
                .description(description)
                //payload设置要发送的消息内容payload，不允许全是空白字符，长度小于4K，中英文均以一个计算。
                .payload(messagePayload)
                //设置app的包名packageName。packageName必须和开发者网站上申请的结果一致。
                .restrictedPackageName(MY_PACKAGE_NAME)
                //如果passThrough值为0,则是通知栏消息。1,则是透传消息；
                .passThrough(0)
                // 使用默认提示音提示,notifyType是消息的提醒方式，如震动、响铃和闪光灯。
                .notifyType(1)
                .extra(Constants.EXTRA_PARAM_NOTIFY_FOREGROUND, "0")
                //1. 打开当前app对应的Launcher Activity
                //.extra(Constants.EXTRA_PARAM_NOTIFY_EFFECT, "1")
                //2. 打开一个任意一个Activity：CustomActivity
                //.extra(Constants.EXTRA_PARAM_NOTIFY_EFFECT, Constants.NOTIFY_ACTIVITY)
                //.extra(Constants.EXTRA_PARAM_INTENT_URI, "intent:#Intent;component=com.xiaomi.jackpush/.CustomActivity;end")
                //3. 打开网页
                //.extra(Constants.EXTRA_PARAM_NOTIFY_EFFECT, Constants.NOTIFY_WEB)
                //.extra(Constants.EXTRA_PARAM_WEB_URI, "http://www.xiaomi.com")
                .build();
        System.out.println(message.toString());
        //发送给全部用户, 发送消息到指定一组设备上
        sender.broadcastAll(message, 0);

        //根据Alias, 发送消息到指定一组设备上,2是重发次数
        //sender.sendToAlias(message,"jkl",2);
        //sender.sendToUserAccount(message,"jack",2);//根据Alias, 发送消息到指定一组设备上
    }


/*    4.2.2. 通知消息的处理-----小米官方文档：https://dev.mi.com/console/doc/detail?pId=41#_3_0

    通知消息分为 自定义通知消息和预定义通知消息——————————————————————————————
    如果服务端调用Message.Builder类的extra(String key, String value)方法设置了Constants.EXTRA_PARAM_NOTIFY_EFFECT的值，则为预定义通知消息；
    否则为自定义通知消息。如果在小米推送服务开发者站推送消息，需要通过指定“点击后续动作”来指定通知消息类型。
    通知消息到达客户端后会在通知栏弹出notification，这时候消息已经传到PushMessageReceiver继承类的onNotificationMessageArrived方法，
    但这时候消息还没有通过PushMessageReceiver继承类的的onNotificationMessageClicked方法传到客户端。
    当用户需要点击了自定义通知消息，消息会通过onNotificationMessageClicked方法传到客户端。
    ！！！注：用户点击了预定义通知消息，消息不会通过onNotificationMessageClicked方法传到客户端。

    客户端定义了不同的通知栏消息点击行为，分为下面四种：

    1. 自定义通知消息的处理服务端没有设置Constants.EXTRA_PARAM_NOTIFY_EFFECT的值表示是自定义通知消息。客户端收到自定义通知消息后，可以自定义一些操作。
    比如：通过启动一个Activity将消息传过去，这时需要给Intent添加FLAG_ACTIVITY_NEW_TASK。intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    透传消息和自定义通知消息的处理参考代码如下：

    2. 打开当前app对应的Launcher Activity服务端调用Message.Builder类的extra(String key, String value)方法，将key设置为Constants.EXTRA_PARAM_NOTIFY_EFFECT，
    value设置为Constants.NOTIFY_LAUNCHER_ACTIVITY。具体请参考服务端文档：小米推送服务Server端SDK。封装消息的MiPushMessage对象通过Intent传到客户端，
    客户端在相应的Activity中可以调用Intent的getSerializableExtra（PushMessageHelper.KEY_MESSAGE）方法得到MiPushMessage对象。
    3. 打开当前app内的任意一个Activity服务端调用Message.Builder类的extra(String key, String value)方法，将key设置为Constants.EXTRA_PARAM_NOTIFY_EFFECT，
    value设置为Constants.NOTIFY_ACTIVITY。具体请参考服务端文档：小米推送服务Server端SDK。封装消息的MiPushMessage对象通过Intent传到客户端，
    客户端在相应的Activity中可以调用Intent的getSerializableExtra（PushMessageHelper.KEY_MESSAGE）方法得到MiPushMessage对象。
    4. 打开网页服务端调用Message.Builder类的extra(String key, String value)方法，将key设置为Constants.EXTRA_PARAM_NOTIFY_EFFECT，
    value设置为Constants.NOTIFY_WEB。具体请参考服务端文档：小米推送服务Server端SDK。

  */
}
