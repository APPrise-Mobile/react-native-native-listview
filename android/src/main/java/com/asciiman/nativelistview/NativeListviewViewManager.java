package com.asciiman.nativelistview;

import android.view.View;
import android.view.ViewGroup;
import android.util.Log;
import android.support.v7.widget.RecyclerView;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.modules.core.DeviceEventManagerModule;

public class NativeListviewViewManager extends ViewGroupManager<NativeListviewView> {
    @Override
    public String getName() {
        return NativeListviewView.class.getSimpleName();
    }

    @Override
    protected NativeListviewView createViewInstance(final ThemedReactContext reactContext) {
        NativeListviewView view = new NativeListviewView(reactContext);

        view.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                  reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class).emit("onScrollEnd", newState);
                }
            }
        });
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return view;
    }
    @Override
    public void addView(NativeListviewView parent, View child, int index) {
        parent.addNewView(child);
    }
    @ReactProp(name = "numRows")
    public void setNumRows(NativeListviewView parent, int size) {
        parent.setNumRows(size);
    }

    @ReactProp(name="rowHeight")
    public void setRowHeight(NativeListviewView parent, int val){
        parent.setRowHeight(val);
    }

    @Override
    public int getChildCount(NativeListviewView parent) {
        return parent.getChildCount();
    }

    @Override
    public void removeAllViews(NativeListviewView parent) {
        parent.removeAllView();
    }
}
