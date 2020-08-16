// Generated code from Butter Knife. Do not modify!
package com.gozdekahraman.currencyapp.ui.notifications;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.gozdekahraman.currencyapp.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AlarmFragment_ViewBinding implements Unbinder {
  private AlarmFragment target;

  @UiThread
  public AlarmFragment_ViewBinding(AlarmFragment target, View source) {
    this.target = target;

    target.alarmName = Utils.findRequiredViewAsType(source, R.id.editAlarmName, "field 'alarmName'", EditText.class);
    target.spinnerCurrency = Utils.findRequiredViewAsType(source, R.id.spinner2, "field 'spinnerCurrency'", Spinner.class);
    target.alarmValue = Utils.findRequiredViewAsType(source, R.id.editTextTarget, "field 'alarmValue'", EditText.class);
    target.setAlarmButton = Utils.findRequiredViewAsType(source, R.id.setAlarmButton, "field 'setAlarmButton'", Button.class);
    target.alarmRecyclerview = Utils.findRequiredViewAsType(source, R.id.alarmRcv2, "field 'alarmRecyclerview'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    AlarmFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.alarmName = null;
    target.spinnerCurrency = null;
    target.alarmValue = null;
    target.setAlarmButton = null;
    target.alarmRecyclerview = null;
  }
}
