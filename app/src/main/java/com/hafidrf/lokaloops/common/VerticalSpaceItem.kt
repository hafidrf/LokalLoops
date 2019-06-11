package com.hafidrf.lokaloops.common

import android.content.Context
import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

class VerticalSpaceItem(private val ctx:Context?, private val space: Float, private val margin: Float = 0f) :RecyclerView.ItemDecoration(){
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {

        outRect.right = dpToPx(ctx, margin)
        outRect.left = dpToPx(ctx, margin)
        outRect.bottom = dpToPx(ctx, space)
        if(parent.getChildLayoutPosition(view)==0) outRect.top = dpToPx(ctx, space)
        else outRect.top = 0

    }


    fun dpToPx(ctx:Context?, dp:Float):Int = ((dp*ctx!!.resources.displayMetrics.density+0.5f).toInt())

}