/*
 * Copyright (C) 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.scrollablelistofbuttons.data
import com.example.oplevappprojekt.R
import com.example.scrollablelistofbuttons.model.ScrollableList

/**
 * [Datasource] generates a list of [Affirmation]
 */
class Datasource() {
    fun loadScrollableList(): List<ScrollableList> {
        return listOf<ScrollableList>(
            ScrollableList(R.string.country1, R.drawable.image1),
            ScrollableList(R.string.country2, R.drawable.image2),
            ScrollableList(R.string.country3, R.drawable.image3),
            ScrollableList(R.string.country4, R.drawable.image4),
            ScrollableList(R.string.country5, R.drawable.image5),
            ScrollableList(R.string.country6, R.drawable.image6),
            ScrollableList(R.string.country7, R.drawable.image7),
            ScrollableList(R.string.country8, R.drawable.image8),
            ScrollableList(R.string.country9, R.drawable.image9),
            ScrollableList(R.string.country10, R.drawable.image10))
    }
}
