package com.example.android.codelabs.sunandsandsports

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4


import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.runners.JUnit4

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(JUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.android.codelabs.sunandsandsports", appContext.packageName)
    }
}