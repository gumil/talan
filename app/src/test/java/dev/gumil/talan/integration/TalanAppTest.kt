package dev.gumil.talan.integration

import com.nhaarman.acorn.testing.TestContext
import dev.gumil.talan.MainNavigator
import dev.gumil.talan.androidweekly.list.IssueListStateUi
import dev.gumil.talan.androidweekly.mapToUiModel
import dev.gumil.talan.integration.awlist.awList
import dev.gumil.talan.integration.fake.FakeTalanApi
import org.junit.Assert.assertTrue
import org.junit.Ignore
import org.junit.Test

internal class TalanAppTest {

    private val appComponent = TestAppComponent()
    private val navigator = MainNavigator(null, appComponent)
    private val context = TestContext.create(navigator, TestContainerProvider)

    @Test
    @Ignore("Failing because of viewmodel")
    fun `app started and backed`() = TestContext.testWith(context) {
        awList {
            verifyState(IssueListStateUi.Screen(
                FakeTalanApi.issues.first().entries.map { it.mapToUiModel() },
                IssueListStateUi.Mode.IDLE)
            )

            pressBack()
        }

        assertTrue(context.finished)
    }
}
