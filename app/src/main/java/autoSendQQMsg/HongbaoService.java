package autoSendQQMsg;

import android.accessibilityservice.AccessibilityService;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.AbsListView;

import java.util.List;


public class HongbaoService extends AccessibilityService {

    private String currentActivityName;
    private List<AccessibilityNodeInfo> list;
    private EventUtil eventUtil;
    final String QQ_MAIN_CLASS="com.tencent.mobileqq/.activity.SplashActivity ";
    final String QQ_NEAR_CLASS="com.tencent.mobileqq/.activity.NearbyActivity";
    @Override
    protected void onServiceConnected() {
        LogUtils.d("");
        eventUtil=EventUtil.getInstance();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        LogUtils.d("");
        return super.onUnbind(intent);
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        AccessibilityNodeInfo eventSource = event.getSource();
        // Not a message
        setCurrentActivityName(event);
        LogUtils.d("");
        if (event.getEventType() != AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED || eventSource == null)
            return ;
        eventUtil.logEventType(event);
        eventUtil.logNode(event.getSource());
        list= NodeUtil.findViewByClass(event.getSource(), AbsListView.class);
        if(list!=null&&list.size()>0){
            showListName(list.get(0));
        }

    }

    private void showListName(AccessibilityNodeInfo node) {
        if (node == null||node.getChildCount()==0)
            return ;
        List<AccessibilityNodeInfo> nodes =node.findAccessibilityNodeInfosByText("附近的人");
        if (nodes!=null&&nodes.size()>0)
            LogUtils.d("view found");
        //layout元素，遍历找button
//        for (int i = 1; i < node.getChildCount(); i++) {
//
//
//            //AccessibilityNodeInfo view=findView(node.getChild(i), RelativeLayout.class);
//            if (nodes!=null&&nodes.size()>0)
//            LogUtils.d("view found");
////            AccessibilityNodeInfo relativeLay = node.getChild(i);
////            for (int j = 0; j < relativeLay.getChildCount(); j++){
////                AccessibilityNodeInfo name=relativeLay.getChild(j);
////                if (name!=null&&name.getText()!=null)
////                LogUtils.d(name.getText().toString());
////            }
//
//        }
        return ;
    }



    @Override
    public void onInterrupt() {
        LogUtils.d("onInterrupt");
    }


    private void setCurrentActivityName(AccessibilityEvent event) {
        if (event.getEventType() != AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
            return;
        }
        try {
            ComponentName componentName = new ComponentName(
                    event.getPackageName().toString(),
                    event.getClassName().toString()
            );

            getPackageManager().getActivityInfo(componentName, 0);
            currentActivityName = componentName.flattenToShortString();
        } catch (PackageManager.NameNotFoundException e) {
            currentActivityName = "";
        }
        LogUtils.d("currentActivityName=============="+currentActivityName);
       switch (currentActivityName){
           case QQ_MAIN_CLASS:
               onQQMain();
               break;
           case  QQ_NEAR_CLASS:
               onQQNearby();
               break;
       }
    }

    private void onQQMain(){

    }
    private void onQQNearby(){

    }
}
