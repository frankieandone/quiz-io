package one.and.frankie.quizio;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Map;

public class f {
    public static void log(final String message) {
        String callerClass = "<callerClass>", callerMethod = "<callerMethod>", t = "frankie.and.one";

        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        for (int i = 1; i < stackTraceElements.length; i++) {
            StackTraceElement element = stackTraceElements[i];
            if (!element.getClassName().equals(f.class.getName()) &&
                element.getClassName().indexOf("java.lang.Thread") != 0) {
                String classname = element.getClassName();
                callerClass = classname.lastIndexOf(".") != -1 ?
                        classname.substring(classname.lastIndexOf(".") + 1) : classname;
                callerMethod = element.getMethodName();
                break;
            }
        }
        Log.d(t, message != null ? callerClass + " #" + callerMethod + " " + message :
                callerClass + " #" + callerMethod);
    }

    public static void log(final int message) {
        log(message + "");
    }

    public static void log(final String message, final Map map) {
        if (map == null) {
            log(message + " map: null");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("map: {");
        for (Object o : map.entrySet()) {
            Map.Entry pair = (Map.Entry) o;
            sb.append(" ").append(pair.getKey()).append(":").append(pair.getValue());
        }
        sb.append(" }");
        log(message + " " + sb.toString());
    }

    public static void log(final String message, final Object[] array) {
        if (array == null) {
            log(message + " array: null");
            return;
        }

        if (array.length == 0) {
            log(message + " array.length: 0");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(" [ ");
        for (int i = 0; ; ++i) {
            sb.append(array[i]);
            if (i == array.length - 1) {
                log(message + sb.append(" ] ").toString());
                return;
            }
            sb.append(", ");
        }
    }

    public static void log(final String message, final ArrayList list) {
        log(message, list.toArray());
    }

    public static void log(final Intent intent) {
        if (intent == null) {
            f.log("intent: null");
            return;
        }

        if (intent.getExtras() == null) {
            f.log("intent.getExtras(): null");
            return;
        }

        Bundle bundle = intent.getExtras();
        for (String key : bundle.keySet()) {
            f.log("key: " + key + " value: " + bundle.get(key));
        }
    }

    public static void log() {
        log("");
    }
}
