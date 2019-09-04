package com.victorrendina.touchhelpercrash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val touchHelper = ItemTouchHelper(SampleItemTouchHelperCallback())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.adapter = SampleAdapter()

        /*
         * Attach the touch helper to the RecyclerView.
         */
        touchHelper.attachToRecyclerView(recyclerView)

        /*
         * Detach the touch helper from the RecyclerView by passing null (this approach is
         * recommended by the documentation) when the button is pressed.
         */
        detachButton.setOnClickListener {
            touchHelper.attachToRecyclerView(null)
        }
    }

}
