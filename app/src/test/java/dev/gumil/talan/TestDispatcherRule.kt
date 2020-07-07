package dev.gumil.talan

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class TestDispatcherRule : TestRule {
    private val testDispatcher by lazy { TestCoroutineDispatcher() }

    override fun apply(base: Statement, description: Description?): Statement =
        object : Statement() {
            override fun evaluate() {
                try {
                    Dispatchers.setMain(testDispatcher)
                    base.evaluate()
                } finally {
                    Dispatchers.resetMain()
                    testDispatcher.cleanupTestCoroutines()
                }
            }
        }
}
