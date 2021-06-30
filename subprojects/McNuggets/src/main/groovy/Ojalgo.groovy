/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
//@Grab('org.ojalgo:ojalgo:48.4.2')
import org.ojalgo.optimisation.ExpressionsBasedModel
import static org.ojalgo.netio.BasicLogger.debug

// What's the largest number of nuggets you can order at McDonalds
// that they can't deliver (that exact quantity)?
// See: https://www.ojalgo.org/2019/05/the-mcnuggets-challenge/

def model = new ExpressionsBasedModel()

def packs6 = model.addVariable("#6-packs").lower(0).integer(true)
def packs9 = model.addVariable("#9-packs").lower(0).integer(true)
def packs20 = model.addVariable("#20-packs").lower(0).integer(true)

def totalNuggets = model.addExpression().weight(1)
totalNuggets.set(packs6, 6)
totalNuggets.set(packs9, 9)
totalNuggets.set(packs20, 20)

for (int i : 100..1) {
    totalNuggets.upper(i)
    def result = model.maximise()

    if (Math.round(result.value) < i) {
        debug("Not possible to order $i nuggets")
        break
    } else {
        debug(result)
    }
}
