package com.merteroglu286.monolitharchitecturesample.utility.extension

import android.content.res.Resources
import android.graphics.Paint
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.StyleSpan
import android.util.TypedValue
import android.view.View
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.core.text.buildSpannedString
import com.merteroglu286.monolitharchitecturesample.R


val Int.px: Int
    get() = (this / Resources.getSystem().displayMetrics.density).toInt()

val Int.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()

fun View.addRipple() = with(TypedValue()) {
    context.theme.resolveAttribute(android.R.attr.selectableItemBackground, this, true)
    setBackgroundResource(resourceId)
}


fun View.changeBackgroundTintList(colorId: Int) {
    if (colorId != -1) {
        this.backgroundTintList =
            ContextCompat.getColorStateList(this.context, colorId)
    }
}

fun TextView.changeTextColor(colorId: Int) {
    this.setTextColor(ContextCompat.getColor(this.context, colorId))
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun TextView.spannableExtensionWithText(
    text: String,
    @ColorRes colorId: Int = R.color.green,
    clickSpan: () -> Unit
) {
    val spannableString = SpannableString(this.text)

    val spannable = object : ClickableSpan() {
        override fun onClick(textView: View) {
            clickSpan()
        }

        override fun updateDrawState(ds: TextPaint) {
            super.updateDrawState(ds)
            ds.color = ContextCompat.getColor(
                this@spannableExtensionWithText.context,
                colorId
            )
            ds.isUnderlineText = false

        }
    }

    val startIndex = this.text.indexOf(text, 0)
    val endIndex = startIndex + text.length

    spannableString.setSpan(
        StyleSpan(Typeface.BOLD),
        startIndex,
        endIndex,
        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
    );
    spannableString.setSpan(
        spannable,
        startIndex,
        endIndex,
        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
    )

    this.movementMethod = LinkMovementMethod.getInstance()
    this.text = spannableString
}

fun TextView.setStrikeFlag() {
    paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
}


fun TextView.spannable(
    shortTerm: String,
    shortPolicy: String,
    onClickTerms: () -> Unit,
    onClickPolicy: () -> Unit
) {
    val spannable = buildSpannedString {
        append(this@spannable.text)

        val spannableTerm = object : ClickableSpan() {
            override fun onClick(textView: View) {
                onClickTerms()
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = ContextCompat.getColor(
                    this@spannable.context, R.color.green
                )
                ds.isUnderlineText = false
            }
        }

        val spannablePolicy = object : ClickableSpan() {
            override fun onClick(textView: View) {
                onClickPolicy()
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = ContextCompat.getColor(
                    this@spannable.context, R.color.green
                )
                ds.isUnderlineText = false
            }
        }

        setSpan(
            spannableTerm,
            text.indexOf(shortTerm),
            text.indexOf(shortTerm) + shortTerm.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        setSpan(
            spannablePolicy,
            text.indexOf(shortPolicy),
            text.indexOf(shortPolicy) + shortPolicy.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    }
    this.text = spannable
    this.movementMethod = LinkMovementMethod.getInstance()
}