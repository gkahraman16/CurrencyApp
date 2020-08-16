// Generated code from Butter Knife. Do not modify!
package com.gozdekahraman.currencyapp.ui.home;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.gozdekahraman.currencyapp.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class HomeFragment_ViewBinding implements Unbinder {
  private HomeFragment target;

  @UiThread
  public HomeFragment_ViewBinding(HomeFragment target, View source) {
    this.target = target;

    target.price_usd = Utils.findRequiredViewAsType(source, R.id.priceHome1, "field 'price_usd'", TextView.class);
    target.price_eur = Utils.findRequiredViewAsType(source, R.id.priceHome2, "field 'price_eur'", TextView.class);
    target.price_eurusd = Utils.findRequiredViewAsType(source, R.id.priceHome3, "field 'price_eurusd'", TextView.class);
    target.price_au = Utils.findRequiredViewAsType(source, R.id.priceHome4, "field 'price_au'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    HomeFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.price_usd = null;
    target.price_eur = null;
    target.price_eurusd = null;
    target.price_au = null;
  }
}
