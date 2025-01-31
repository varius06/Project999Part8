package com.example.course999part8.core

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.util.AttributeSet
import android.view.View

class CustomButton : androidx.appcompat.widget.AppCompatButton, HideAndShow {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun onSaveInstanceState(): Parcelable? = super.onSaveInstanceState()?.let {
        val visibilityState = VisibilityState(it)
        visibilityState.visibile = visibility
        return visibilityState
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        val visibilityState = state as VisibilityState?
        super.onRestoreInstanceState(visibilityState)
        visibilityState?.let {
            visibility = it.visibile
        }
    }

    override fun show() {
        visibility = View.VISIBLE
    }

    override fun hide() {
        visibility = View.GONE
    }
}

class VisibilityState : View.BaseSavedState {

    var visibile: Int = View.VISIBLE

    constructor(superState: Parcelable) : super(superState)

    private constructor(parcelIn: Parcel) : super(parcelIn)

    override fun writeToParcel(out: Parcel, flags: Int) {
        super.writeToParcel(out, flags)
        out.writeInt(visibile)
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<VisibilityState> {
        override fun createFromParcel(parcel: Parcel): VisibilityState = VisibilityState(parcel)
        override fun newArray(size: Int): Array<VisibilityState?> = arrayOfNulls(size)
    }

}
