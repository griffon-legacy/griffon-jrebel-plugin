/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the 'License');
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an 'AS IS' BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import griffon.util.Environment
import static griffon.util.GriffonNameUtils.isBlank

/**
 * @author Andres Almiray
 */

eventRunAppStart = {
    if(Environment.current != Environment.DEVELOPMENT) return

    if (!buildConfig.jrebel.location) {
        println """
            | jrebel.location is not defined
            | The application will run with JRebel disabled.
            |""".stripMargin()
        return
    }

    File jrebelJar = new File(buildConfig.jrebel.location)
    if (!jrebelJar.exists()) {
        println """
            | jrebel.jar could not be found at the following path
            |   $jrebelJar
            | The application will run with JRebel disabled.
            |""".stripMargin()
        return
    }

    if (!buildConfig.griffon.app.jvmOpts) buildConfig.griffon.app.jvmOpts = []
    String jrebelAgent = "-javaagent:${jrebelJar.canonicalPath}".toString()
    if (!buildConfig.griffon.app.jvmOpts.contains(jrebelAgent)) {
        buildConfig.griffon.app.jvmOpts << jrebelAgent
    }

    if (!buildConfig.griffon.app.javaOpts) buildConfig.griffon.app.javaOpts = []
    for (option in buildConfig.jrebel.options) {
        if (!option.startsWith('-D')) option = '-D' + option
        buildConfig.griffon.app.javaOpts << option
    }
}