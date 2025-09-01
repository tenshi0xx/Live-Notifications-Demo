package com.vamsi.livenotifications.model

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import com.vamsi.livenotifications.R

enum class DeliveryStatus(
    val displayName: String,
    val description: String,
    @ColorRes val colorRes: Int,
    @DrawableRes val iconRes: Int,
    val estimatedMinutes: Int,
    val segmentLength: Int, // Length of this segment in the journey
    val progressPoints: List<Int> = emptyList(), // Key milestone points within this segment
) {
    CONFIRMED(
        displayName = "Order Confirmed",
        description = "Your order has been confirmed and we're getting started",
        colorRes = R.color.status_confirmed,
        iconRes = R.drawable.ic_order_confirmed,
        estimatedMinutes = 25,
        segmentLength = 100,
        progressPoints = listOf(25, 50, 75, 100) // Confirmation milestones
    ),
    PREPARING(
        displayName = "Preparing Your Order",
        description = "Our kitchen team is carefully preparing your meal",
        colorRes = R.color.status_preparing,
        iconRes = R.drawable.ic_preparing,
        estimatedMinutes = 15,
        segmentLength = 150,
        progressPoints = listOf(50, 100, 150) // Preparation stages
    ),
    EN_ROUTE(
        displayName = "On the Way",
        description = "Your driver is on their way to deliver your order",
        colorRes = R.color.status_en_route,
        iconRes = R.drawable.ic_en_route,
        estimatedMinutes = 8,
        segmentLength = 120,
        progressPoints = listOf(40, 80, 120) // Journey checkpoints
    ),
    DELIVERED(
        displayName = "Delivered",
        description = "Your order has been successfully delivered. Enjoy!",
        colorRes = R.color.status_delivered,
        iconRes = R.drawable.ic_delivered,
        estimatedMinutes = 0,
        segmentLength = 30,
        progressPoints = listOf(30) // Final completion
    );

    fun getProgressPercentage(): Int {
        return when (this) {
            CONFIRMED -> 25
            PREPARING -> 50
            EN_ROUTE -> 75
            DELIVERED -> 100
        }
    }

    /**
     * Get the cumulative progress up to this status including segment start
     */
    fun getCumulativeProgress(): Int {
        val allStatuses = entries
        val currentIndex = allStatuses.indexOf(this)
        return allStatuses.take(currentIndex).sumOf { it.segmentLength }
    }

    /**
     * Get the total progress including current segment completion
     */
    fun getTotalProgressWithSegment(segmentProgress: Int): Int {
        return getCumulativeProgress() + segmentProgress
    }

    companion object {
        fun getNext(current: DeliveryStatus): DeliveryStatus? {
            val values = entries.toTypedArray()
            val currentIndex = values.indexOf(current)
            return if (currentIndex < values.size - 1) values[currentIndex + 1] else null
        }

        fun getAllStatuses() = entries

        fun getCompletedStatuses(current: DeliveryStatus): List<DeliveryStatus> {
            return entries.toTypedArray().takeWhile { it != current }
        }

        /**
         * Get total journey length across all segments
         */
        fun getTotalJourneyLength(): Int {
            return entries.sumOf { it.segmentLength }
        }

        /**
         * Calculate progress for a specific status at a specific point within its segment
         */
        fun calculateProgress(status: DeliveryStatus, pointIndex: Int): Int {
            val segmentProgress = if (pointIndex < status.progressPoints.size) {
                status.progressPoints[pointIndex]
            } else {
                status.segmentLength
            }
            return status.getTotalProgressWithSegment(segmentProgress)
        }
    }
}
