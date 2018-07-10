/*
 * Copyright (C) 2016-2017 Marco Collovati (mcollovati@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.vaadin.addon.twitter.model;

import java.util.Objects;

import com.vaadin.flow.templatemodel.ModelEncoder;

@SuppressWarnings("unchecked")
public abstract class EnumModelEncoder<T extends Enum<T>> implements ModelEncoder<T, String> {

    private final Class<T> decodedType;

    public EnumModelEncoder(Class<T> decodedType) {
        this.decodedType = decodedType;
    }

    @Override
    public Class<T> getDecodedType() {
        return decodedType;
    }

    @Override
    public String encode(T value) {
        return Objects.toString(value, null);
    }

    @Override
    public T decode(String value) {
        if (value != null) {
            return Enum.valueOf(getDecodedType(), value);
        }
        return null;
    }

}
