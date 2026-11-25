import com.kms.katalon.core.annotation.BeforeTestCase
import com.kms.katalon.core.context.TestCaseContext
import com.kms.katalon.core.configuration.RunConfiguration

class BrowserSetupChrome {

    @BeforeTestCase
    def setupBrowser(TestCaseContext testCaseContext) {

        RunConfiguration.setWebDriverPreferencesProperty("prefs", [

            // Password manager
            "credentials_enable_service": false,
            "profile.password_manager_enabled": false,

            // Compromised password alerts
            "profile.password_manager_leak_detection": false,

            // Notifications
            "profile.default_content_setting_values.notifications": 2,

            // Geolocation
            "profile.default_content_setting_values.geolocation": 2,

            // Microphone
            "profile.default_content_setting_values.media_stream_mic": 2,

            // Camera
            "profile.default_content_setting_values.media_stream_camera": 2,

            // Browser pop-ups
            "profile.default_content_setting_values.popups": 2,

            // Automatic translation
            "translate.enabled": false
        ])

        println("[HOOK] Chrome configuration initialized")
    }
}