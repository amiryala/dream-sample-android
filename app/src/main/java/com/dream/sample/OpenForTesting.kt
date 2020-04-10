package com.dream.sample

/**
 * Similar to @VisibleForTesting, indicates a Kotlin class should be considered open for
 * testing purposes. Instrumentation tests require extra consideration when mocking dependencies.
 *
 * Existing mocking support provided by Mockito is only sufficient when executing JVM tests, not
 * on-device.
 *
 * See: https://github.com/mockito/mockito/issues/1082
 *
 * Use the annotation at the class level to mark the class & properties
 * automatically.
 */
@Target(AnnotationTarget.CLASS)
annotation class OpenForTesting