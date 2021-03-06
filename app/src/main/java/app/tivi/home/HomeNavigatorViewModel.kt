/*
 * Copyright 2017 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package app.tivi.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import app.tivi.AppNavigator
import app.tivi.SharedElementHelper
import app.tivi.data.entities.TiviShow
import app.tivi.util.SingleLiveEvent
import javax.inject.Inject
import javax.inject.Provider

class HomeNavigatorViewModel @Inject constructor(
    private val appNavigatorProvider: Provider<AppNavigator>
) : ViewModel(), HomeNavigator {

    override fun showPopular(sharedElements: SharedElementHelper?) {
        _showPopularCall.value = sharedElements
    }

    override fun showTrending(sharedElements: SharedElementHelper?) {
        _showTrendingCall.value = sharedElements
    }

    override fun showWatched(sharedElements: SharedElementHelper?) {
        _showWatchedCall.value = sharedElements
    }

    override fun showMyShows(sharedElements: SharedElementHelper?) {
        _showMyShowsCall.value = sharedElements
    }

    override fun showShowDetails(show: TiviShow, sharedElements: SharedElementHelper?) {
        appNavigatorProvider.get().startShowDetails(show.id, sharedElements)
    }

    override fun showSettings() {
        appNavigatorProvider.get().startSettings()
    }

    override fun onUpClicked() {
        _upClickedCall.call()
    }

    private val _showPopularCall = SingleLiveEvent<SharedElementHelper>(errorOnNoObservers = true)
    val showPopularCall: LiveData<SharedElementHelper>
        get() = _showPopularCall

    private val _showTrendingCall = SingleLiveEvent<SharedElementHelper>(errorOnNoObservers = true)
    val showTrendingCall: LiveData<SharedElementHelper>
        get() = _showTrendingCall

    private val _showWatchedCall = SingleLiveEvent<SharedElementHelper>(errorOnNoObservers = true)
    val showWatchedCall: LiveData<SharedElementHelper>
        get() = _showWatchedCall

    private val _showMyShowsCall = SingleLiveEvent<SharedElementHelper>(errorOnNoObservers = true)
    val showMyShowsCall: LiveData<SharedElementHelper>
        get() = _showMyShowsCall

    private val _upClickedCall = SingleLiveEvent<Unit>(errorOnNoObservers = true)
    val upClickedCall: LiveData<Unit>
        get() = _upClickedCall
}
