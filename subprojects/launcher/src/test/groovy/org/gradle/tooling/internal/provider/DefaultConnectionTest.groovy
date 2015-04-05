/*
 * Copyright 2014 the original author or authors.
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

package org.gradle.tooling.internal.provider

import org.gradle.internal.FileUtils
import org.gradle.tooling.internal.consumer.parameters.ConsumerOperationParameters
import org.gradle.util.UsesNativeServices
import spock.lang.Specification

@UsesNativeServices
class DefaultConnectionTest extends Specification {
    def connection = new DefaultConnection()

    def "adapts from consumer to provider parameters"() {
        def consumerParams = ConsumerOperationParameters.builder().setColorOutput(true).build()

        when:
        connection.initializeServices(FileUtils.createTempDir("native"))
        def providerParams = connection.toProviderParameters(consumerParams)

        then:
        Boolean.TRUE.equals(providerParams.isColorOutput())
    }
}
