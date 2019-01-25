package com.twy.eventbus.main;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Looper;
import android.util.ArrayMap;

import com.twy.eventbus.listener.Subscribe;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EventBus {

    private Handler handler = new Handler(Looper.getMainLooper());

    private EventBus(){
    }
    @SuppressLint("NewApi")
    private Map<Object,List<Method>> map = new ArrayMap<>();
    private static EventBus eventBus = new EventBus();
    public static EventBus getDefault(){
        return eventBus;
    }

    public void register(Object object){
        Method[] methods = object.getClass().getMethods();
        List<Method> ls = new ArrayList<>();
        for (Method method : methods) {
            if(method.getAnnotation(Subscribe.class)!=null){
                ls.add(method);
            }
            /*for (Annotation a : method.getAnnotations()){
                if(Subscribe.class.equals(a.annotationType())){
                    ls.add(method);
                    break;
                }
            }*/
        }
        if(ls.size()>0){
            map.put(object,ls);
        }
    }

    public void unRegister(Object object){
        map.get(object).clear();
        map.remove(object);
    }

    public void post(final Object event){
        for (final Object o : map.keySet()) {
            List<Method> methods = map.get(o);
            for (final Method m : methods){
                Class<?>[] getTypeParameters = m.getParameterTypes();
                for (Class<?> class1 : getTypeParameters) {
                    if(event.getClass().getName().equals(class1.getName())){
                        Subscribe annotation = m.getAnnotation(Subscribe.class);
                        switch (annotation.threadMode()){
                            case MAIN://主线程执行
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            m.invoke(o,event);
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                                break;
                            case BACKG://子线程执行
                                ThreadPoolManager.getInstance().threadPoolExecutor.execute(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            m.invoke(o,event);
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                                break;
                            default:// POSTING: 随在什么线程发起事件 就在什么线程执行
                                try {
                                    m.invoke(o,event);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                        }
                        break;
                    }
                }
            }
        }
    }

}
