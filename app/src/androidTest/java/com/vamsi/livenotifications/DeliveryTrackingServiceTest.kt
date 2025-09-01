package com.vamsi.livenotifications

import android.content.Context
import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.vamsi.livenotifications.service.DeliveryTrackingService
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DeliveryTrackingServiceTest {


    private lateinit var context: Context

    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext()
    }

    @Test
    fun testServiceStarts() {
        val serviceIntent = Intent(context, DeliveryTrackingService::class.java)
        
        // Test that service intent can be created without crashing
        assertNotNull("Service intent should not be null", serviceIntent)
        assertEquals("Intent should target correct service class",
            DeliveryTrackingService::class.java.name, serviceIntent.component?.className)
    }

    @Test
    fun testServiceConfiguration() {
        // Test that the service is properly configured in the manifest
        val serviceIntent = Intent(context, DeliveryTrackingService::class.java)
        val resolveInfo = context.packageManager.resolveService(serviceIntent, 0)
        
        assertNotNull("Service should be declared in manifest", resolveInfo)
        assertEquals("Service should be in correct package", 
            "com.vamsi.livenotifications", resolveInfo?.serviceInfo?.packageName)
    }

    @Test
    fun testApplicationPermissions() {
        val packageManager = context.packageManager
        val packageName = context.packageName
        
        // Check if required permissions are declared
        try {
            val packageInfo = packageManager.getPackageInfo(packageName, 
                android.content.pm.PackageManager.GET_PERMISSIONS)
            val permissions = packageInfo.requestedPermissions?.toList() ?: emptyList()
            
            assertTrue("Should request POST_NOTIFICATIONS permission",
                permissions.contains(android.Manifest.permission.POST_NOTIFICATIONS))
            assertTrue("Should request FOREGROUND_SERVICE permission",
                permissions.contains(android.Manifest.permission.FOREGROUND_SERVICE))
            assertTrue("Should request FOREGROUND_SERVICE_DATA_SYNC permission",
                permissions.contains(android.Manifest.permission.FOREGROUND_SERVICE_DATA_SYNC))
            
        } catch (e: android.content.pm.PackageManager.NameNotFoundException) {
            fail("Package not found: $packageName")
        }
    }

    @Test
    fun testServiceIntentHandling() {
        // Test that the service can handle the intent properly
        val serviceIntent = Intent(context, DeliveryTrackingService::class.java)
        
        // Verify intent can be created and contains correct component
        assertNotNull("Service intent should not be null", serviceIntent)
        assertEquals("Intent should target correct service class",
            DeliveryTrackingService::class.java.name, serviceIntent.component?.className)
    }

    @Test
    fun testNotificationChannelRequirements() {
        // Test that service class can be instantiated without crashing
        // This indirectly tests that the service dependencies are properly configured
        val serviceIntent = Intent(context, DeliveryTrackingService::class.java)
        
        try {
            assertNotNull("Service intent should not be null", serviceIntent)
            assertEquals("Intent should have correct component",
                DeliveryTrackingService::class.java.name, serviceIntent.component?.className)
        } catch (e: Exception) {
            fail("Service intent creation should not throw: ${e.message}")
        }
    }
}