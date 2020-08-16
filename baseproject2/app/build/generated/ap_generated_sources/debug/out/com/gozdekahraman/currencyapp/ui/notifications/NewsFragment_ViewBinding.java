// Generated code from Butter Knife. Do not modify!
package com.gozdekahraman.currencyapp.ui.notifications;

import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.gozdekahraman.currencyapp.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class NewsFragment_ViewBinding implements Unbinder {
  private NewsFragment target;

  @UiThread
  public NewsFragment_ViewBinding(NewsFragment target, View source) {
    this.target = target;

    target.rcv = Utils.findRequiredViewAsType(source, R.id.alarmRcv2, "field 'rcv'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    NewsFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.rcv = null;
  }
}
