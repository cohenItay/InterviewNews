package com.itaycohen.interviewnews.application.di

import javax.inject.Scope

/**
 * Define the required Component to be attached to a activity lifecycle.
 * Meaning this component will be construct and deconstruct in coherence with the activity life cycle.
 */
@Scope
@Retention(value = AnnotationRetention.RUNTIME)
annotation class ActivityScope