package com.starwarsapp.characters

import androidx.compose.runtime.Composable
import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import com.android.ide.common.rendering.api.SessionParams
import com.android.resources.NightMode
import com.starwarsapp.characters.ui.characterdetails.PreviewCharacterDetailsLightDark
import com.starwarsapp.characters.ui.characterlist.PreviewCharacterListContentLightDark
import org.junit.Rule
import org.junit.Test

class CharacterScreenshotTest {
    val lightConfig = DeviceConfig.PIXEL_6.copy(nightMode = NightMode.NOTNIGHT, fontScale = 1.0f)
    val darkConfig = DeviceConfig.PIXEL_6.copy(nightMode = NightMode.NIGHT, fontScale = 1.0f)
    val largeFontConfig = DeviceConfig.PIXEL_6.copy(nightMode = NightMode.NOTNIGHT, fontScale = 2.0f)

    @get:Rule
    val paparazzi = Paparazzi(
        deviceConfig = lightConfig,
        theme = "android:Theme.Material.Light.NoActionBar",
        renderingMode = SessionParams.RenderingMode.SHRINK,
    )

    fun Paparazzi.runAllConfigs(composable: @Composable (() -> Unit)) {
        snapshot(name = "light") {
            composable()
        }
        // explanation behind unsafe wording: https://github.com/cashapp/paparazzi/discussions/944
        unsafeUpdateConfig(darkConfig)
        snapshot(name = "dark") {
            composable()
        }
        unsafeUpdateConfig(largeFontConfig)
        snapshot(name = "largeFont") {
            composable()
        }
    }

    @Test
    fun characterListScreenshotTest() {
        paparazzi.runAllConfigs {
            PreviewCharacterListContentLightDark()
        }
    }

    @Test
    fun characterDetailsScreenshotTest() {
        paparazzi.runAllConfigs {
            PreviewCharacterDetailsLightDark()
        }
    }
}