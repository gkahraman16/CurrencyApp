// Generated code from Butter Knife. Do not modify!
package com.gozdekahraman.currencyapp.ui.dashboard;

import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.gozdekahraman.currencyapp.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class DashboardFragment_ViewBinding implements Unbinder {
  private DashboardFragment target;

  @UiThread
  public DashboardFragment_ViewBinding(DashboardFragment target, View source) {
    this.target = target;

    target.rcviewer = Utils.findRequiredViewAsType(source, R.id.rcviewer, "field 'rcviewer'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    DashboardFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.rcviewer = null;
  }
}
