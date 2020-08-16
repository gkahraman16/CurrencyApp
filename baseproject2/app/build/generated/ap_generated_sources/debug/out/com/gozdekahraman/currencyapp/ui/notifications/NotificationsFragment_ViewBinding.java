// Generated code from Butter Knife. Do not modify!
package com.gozdekahraman.currencyapp.ui.notifications;

import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.viewpager.widget.ViewPager;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.google.android.material.tabs.TabLayout;
import com.gozdekahraman.currencyapp.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class NotificationsFragment_ViewBinding implements Unbinder {
  private NotificationsFragment target;

  @UiThread
  public NotificationsFragment_ViewBinding(NotificationsFragment target, View source) {
    this.target = target;

    target.tabLayout = Utils.findRequiredViewAsType(source, R.id.tabLayout, "field 'tabLayout'", TabLayout.class);
    target.viewPager = Utils.findRequiredViewAsType(source, R.id.view_pager, "field 'viewPager'", ViewPager.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    NotificationsFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tabLayout = null;
    target.viewPager = null;
  }
}
