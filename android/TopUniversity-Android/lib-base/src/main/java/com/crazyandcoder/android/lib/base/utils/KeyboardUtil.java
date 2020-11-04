package com.crazyandcoder.android.lib.base.utils;

import android.app.Activity;
import android.content.Context;
import android.text.InputType;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.lang.reflect.Method;

public class KeyboardUtil {


	public static void hideKeyboard(Activity activity){
		/**隐藏软键盘**/
		View view = activity.getWindow().peekDecorView();
		if (view != null) {
			InputMethodManager inputmanger = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
			inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
		}
	}

	/**
	 * 打开软键盘  
	 * @param activity
	 * @param editText
	 */
	public static void openKeyboard(Activity activity, EditText editText) {  
		 InputMethodManager imm = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE); 
		 imm.showSoftInput(editText, InputMethodManager.RESULT_SHOWN); 
		 imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
	 }

	 /*
	 * 关闭软键盘显示游标
	 * */
	public static void showCursor(Activity activity, EditText editText){
		if (android.os.Build.VERSION.SDK_INT <= 10) {
			editText.setInputType(InputType.TYPE_NULL);
		} else {
			activity.getWindow().setSoftInputMode(
					WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
			try {
				Class<EditText> cls = EditText.class;
				Method setShowSoftInputOnFocus;
				setShowSoftInputOnFocus = cls.getMethod(
						"setShowSoftInputOnFocus", boolean.class);
				setShowSoftInputOnFocus.setAccessible(true);
				setShowSoftInputOnFocus.invoke(editText, false);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
