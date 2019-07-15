package com.szauren.mockk

import android.widget.TextView;
import io.mockk.MockKAnnotations;
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class MockkInitTestCase {

    @MockK
    lateinit var textView: TextView

    @Before
    fun setUp() = MockKAnnotations.init(this)

    @Test
    fun `Mockk1`() {

    }

}