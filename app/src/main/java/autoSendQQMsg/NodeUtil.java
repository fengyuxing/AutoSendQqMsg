package autoSendQQMsg;

import android.view.accessibility.AccessibilityNodeInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: zhangshupeng
 * Email: zzx
 * Date: 2016/7/5 14:08
 */
public class NodeUtil {
    /**
     * 查找某一类型node
     */
    public static List<AccessibilityNodeInfo> findViewByClass(AccessibilityNodeInfo node, Class view) {
        if (node == null)
            return null;
        List<AccessibilityNodeInfo> list=new ArrayList<>();
        if (view.getName().equals(node.getClassName()))
            list.add(node);
        //layout元素，遍历找button
        for (int i = 0; i < node.getChildCount(); i++) {
            List<AccessibilityNodeInfo> chlidList = findViewByClass(node.getChild(i), view);
            list.addAll(chlidList);
        }
        return list;
    }
}
