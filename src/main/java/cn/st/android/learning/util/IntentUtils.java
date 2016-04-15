package cn.st.android.learning.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

/**
 * Created by coolearth on 2016/4/14.
 */
public class IntentUtils {
    /**
     * 检验是否存在接受该意图的activity
     * @param intent
     * @return
     */
    public static boolean chkIntentHandlerExist(Intent intent,Context context){
        PackageManager packageManager=context.getPackageManager();
        List<ResolveInfo> resolveInfoList=packageManager.queryIntentActivities(intent, 0);
        return CollectionUtils.isNotEmpty(resolveInfoList);
    }
}
