// Generated code from Butter Knife. Do not modify!
package com.gozdekahraman.currencyapp.ui.exchange;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.gozdekahraman.currencyapp.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ExchangeFragment_ViewBinding implements Unbinder {
  private ExchangeFragment target;

  @UiThread
  public ExchangeFragment_ViewBinding(ExchangeFragment target, View source) {
    this.target = target;

    target.spinnerCurrencyCurrent = Utils.findRequiredViewAsType(source, R.id.spinnerCurrent, "field 'spinnerCurrencyCurrent'", Spinner.class);
    target.spinnerCurrencyTarget = Utils.findRequiredViewAsType(source, R.id.spinnerTarget, "field 'spinnerCurrencyTarget'", Spinner.class);
    target.amountText = Utils.findRequiredViewAsType(source, R.id.editTextNumber, "field 'amountText'", EditText.class);
    target.convertButton = Utils.findRequiredViewAsType(source, R.id.convertButton, "field 'convertButton'", Button.class);
    target.result = Utils.findRequiredViewAsType(source, R.id.result, "field 'result'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ExchangeFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.spinnerCurrencyCurrent = null;
    target.spinnerCurrencyTarget = null;
    target.amountText = null;
    target.convertButton = null;
    target.result = null;
  }
}
