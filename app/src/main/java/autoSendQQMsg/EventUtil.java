package autoSendQQMsg;

import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Author: zzx
 * Date: 2016/7/5 13:31
 */
public class EventUtil {
    private DataOutputStream dos;
    private static  EventUtil instance;

    private EventUtil() {
        init();
    }

    public static EventUtil getInstance(){
        if (instance==null)
            instance=new EventUtil();
        return  instance;
    }

    public  void init(){
        try {
            Process  p = Runtime.getRuntime().exec("su");
            dos = new DataOutputStream(p.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
            throw  new RuntimeException(e);
        }
    }

    public void execute(String commond){
        try {
        dos.writeBytes(commond + "\n");
        dos.flush();
        } catch (IOException e) {
            e.printStackTrace();
            throw  new RuntimeException(e);
        }
    }
    public void startActivity(String classpath){
        execute("am start -n "+classpath);
    }

    public void logEventType(AccessibilityEvent event) {
        int eventType = event.getEventType();
        String eventText = "";
        switch (eventType) {
            case AccessibilityEvent.TYPE_VIEW_CLICKED:
                eventText = "TYPE_VIEW_CLICKED";
                break;
            case AccessibilityEvent.TYPE_VIEW_FOCUSED:
                eventText = "TYPE_VIEW_FOCUSED";
                break;
            case AccessibilityEvent.TYPE_VIEW_LONG_CLICKED:
                eventText = "TYPE_VIEW_LONG_CLICKED";
                break;
            case AccessibilityEvent.TYPE_VIEW_SELECTED:
                eventText = "TYPE_VIEW_SELECTED";
                break;
            case AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED:
                eventText = "TYPE_VIEW_TEXT_CHANGED";
                break;
            case AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED:
                eventText = "TYPE_WINDOW_STATE_CHANGED";
                break;
            case AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED:
                eventText = "TYPE_NOTIFICATION_STATE_CHANGED";
                break;
            case AccessibilityEvent.TYPE_TOUCH_EXPLORATION_GESTURE_END:
                eventText = "TYPE_TOUCH_EXPLORATION_GESTURE_END";
                break;
            case AccessibilityEvent.TYPE_ANNOUNCEMENT:
                eventText = "TYPE_ANNOUNCEMENT";
                break;
            case AccessibilityEvent.TYPE_TOUCH_EXPLORATION_GESTURE_START:
                eventText = "TYPE_TOUCH_EXPLORATION_GESTURE_START";
                break;
            case AccessibilityEvent.TYPE_VIEW_HOVER_ENTER:
                eventText = "TYPE_VIEW_HOVER_ENTER";
                break;
            case AccessibilityEvent.TYPE_VIEW_HOVER_EXIT:
                eventText = "TYPE_VIEW_HOVER_EXIT";
                break;
            case AccessibilityEvent.TYPE_VIEW_SCROLLED:
                eventText = "TYPE_VIEW_SCROLLED";
                break;
            case AccessibilityEvent.TYPE_VIEW_TEXT_SELECTION_CHANGED:
                eventText = "TYPE_VIEW_TEXT_SELECTION_CHANGED";
                break;
            case AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED:
                eventText = "TYPE_WINDOW_CONTENT_CHANGED";
                break;
        }
        eventText = eventText + ":" + eventType;
        LogUtils.d( eventText);
    }

    public void logNode(AccessibilityNodeInfo info) {
        if (info.getChildCount() == 0
            //&& !TextUtils.isEmpty(info.getText())
                ) {
            LogUtils.d( info.toString());
//            LogUtils.d( "\nchild widget----------------------------" + info.getClassName());
//            LogUtils.d( "Text：" + info.getText());
//            LogUtils.d("windowId:" + info.getWindowId()+"\n");
        } else {
            for (int i = 0; i < info.getChildCount(); i++) {
                if(info.getChild(i)!=null){
                    logNode(info.getChild(i));
                }
            }
        }
    }
}
