package com.itaycohen.interviewnews.dependency_injection

import com.itaycohen.interviewnews.NewsApp
import javax.inject.Scope

/**
 * Define the required Component to be attached to the [NewsApp] lifecycle.
 * Meaning this component will be construct and deconstruct in coherence with the [NewsApp] life cycle.
 */
@Scope
@Retention(value = AnnotationRetention.RUNTIME)
annotation class ApplicationScope {
}